package be.ehb.petstore.service;

import be.ehb.petstore.model.*;
import be.ehb.petstore.repository.AddressRepository;
import be.ehb.petstore.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class CheckoutService {

    private final UserService userService;
    private final HttpSession httpSession;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public CheckoutService(UserService userService, HttpSession httpSession, OrderRepository orderRepository, AddressRepository addressRepository) {
        this.userService = userService;
        this.httpSession = httpSession;
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
    }

    public void getShippingPageAttributes(Model model) {
        User user = userService.getLoggedInUser();

        if (user == null) {
            return;
        }

        if (user.getFirstName() != null && user.getLastName() != null) {
            model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
        }

        model.addAttribute("billingAddress", user.getBillingAddress() != null ? user.getBillingAddress().toString() : "You have not set up this type of address yet.");
        model.addAttribute("shippingAddress", user.getShippingAddress() != null ? user.getShippingAddress().toString() : "You have not set up this type of address yet.");
    }

    public void getPaymentPage(Model model) {
        Order sessionOrder = (Order) httpSession.getAttribute("shoppingCart");
        model.addAttribute("order", sessionOrder != null ? sessionOrder : "");
    }

    public void confirmAndSaveOrder(String paymentStatus) {
        Order sessionOrder = (Order) httpSession.getAttribute("shoppingCart");

        if (Objects.equals(paymentStatus, Payment.PaymentStatus.PAID.toString())) {
            //create new payment and add to order
            Payment payment = new Paypal();
            payment.setPaymentStatus(Payment.PaymentStatus.PAID);
            payment.setPrice(sessionOrder.getTotalPrice());
            sessionOrder.setPayment(payment);

            //set a new order status
            sessionOrder.setOrderStatus(Order.OrderStatus.PROCESSING);

            //set order date
            sessionOrder.setOrderDate(new Date());

            orderRepository.save(sessionOrder);
        }

        httpSession.removeAttribute("shoppingCart");
    }

    public void addAddressToOrder(String shipmentOption) {
        User user = userService.getLoggedInUser();
        Order sessionOrder = (Order) httpSession.getAttribute("shoppingCart");

        if (sessionOrder == null || user == null) {
            return;
        }

        Long addressId;
        if (Objects.equals(shipmentOption, "billing")) {
            addressId = user.getBillingAddress().getId();
        } else if (Objects.equals(shipmentOption, "shipping")) {
            addressId = user.getShippingAddress().getId();
        } else return;

        Optional<Address> optionalAddress = addressRepository.getAddressById(addressId);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            sessionOrder.setShippingAddress(address);
            httpSession.setAttribute("shoppingCart", sessionOrder);
        }
    }

    public void getOrderOverviewPage(Model model) {
        Optional<Order> optionalOrder = orderRepository.getFirstByOrderDateAndOrderStatus();

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            model.addAttribute("order", order);
        }
    }

    public void addLoggedInUserToOrder() {
        User user = userService.getLoggedInUser();
        Order sessionOrder = (Order) httpSession.getAttribute("shoppingCart");

        if (sessionOrder == null || user == null) {
            return;
        }

        sessionOrder.setUser(user);
        httpSession.setAttribute("shoppingCart", sessionOrder);
    }
}
