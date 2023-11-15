package be.ehb.petstore.service;

import be.ehb.petstore.model.Address;
import be.ehb.petstore.model.Order;
import be.ehb.petstore.model.User;
import be.ehb.petstore.repository.OrderRepository;
import be.ehb.petstore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AccountService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;

    public AccountService(UserRepository userRepository, UserService userService, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    public void getAccountPage(Model model) {
        User loggedInUser = userService.getLoggedInUser();
        List<Order> userOrders = orderRepository.getOrdersByUser(loggedInUser);

        model.addAttribute("name", loggedInUser.getFirstName() != null ? loggedInUser.getFirstName() : "");
        model.addAttribute("billingAddress", loggedInUser.getBillingAddress() != null ? loggedInUser.getBillingAddress().toString() : "You have not set up this type of address yet.");
        model.addAttribute("shippingAddress", loggedInUser.getShippingAddress() != null ? loggedInUser.getShippingAddress().toString() : "You have not set up this type of address yet.");
        model.addAttribute("myOrders", userOrders != null ? userOrders : "No orders have been made.");
    }


    public void updateBilling(Address address, String firstName, String lastName) {
        User loggedInUser = userService.getLoggedInUser();

        loggedInUser.setBillingAddress(address);
        loggedInUser.setFirstName(firstName);
        loggedInUser.setLastName(lastName);

        userRepository.save(loggedInUser);
    }

    public void updateShipping(Address address, String firstName, String lastName) {
        User loggedInUser = userService.getLoggedInUser();

        loggedInUser.setShippingAddress(address);
        loggedInUser.setFirstName(firstName);
        loggedInUser.setLastName(lastName);

        userRepository.save(loggedInUser);
    }
}
