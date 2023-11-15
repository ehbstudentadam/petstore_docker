package be.ehb.petstore.controller;

import be.ehb.petstore.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @GetMapping
    public String getCheckoutPage() {
        checkoutService.addLoggedInUserToOrder();
        return "checkout";
    }

    @GetMapping("/shipping")
    public String getShippingPage(Model model) {
        checkoutService.getShippingPageAttributes(model);
        return "shipping";
    }

    @PostMapping("/shipping")
    public String continueToPaymentPage(@RequestParam(name = "shipmentOption") String shipmentOption) {
        checkoutService.addAddressToOrder(shipmentOption);
        return "redirect:/checkout/shipping/payment";
    }

    @GetMapping("/shipping/payment")
    public String getPaymentPage(Model model) {
        checkoutService.getPaymentPage(model);
        return "payment";
    }

    @PostMapping("/shipping/payment")
    public String confirmOrder(@RequestParam(name = "paymentStatus") String paymentStatus) {
        checkoutService.confirmAndSaveOrder(paymentStatus);
        return "redirect:/checkout/shipping/payment/success";
    }

    @GetMapping("/shipping/payment/success")
    public String getOrderOverviewPage(Model model) {
        checkoutService.getOrderOverviewPage(model);
        return "order-success";
    }

    @GetMapping("/shipping/payment/fail")
    public String getCheckoutShippingPaymentFailPage() {
        return "order-fail";
    }

}
