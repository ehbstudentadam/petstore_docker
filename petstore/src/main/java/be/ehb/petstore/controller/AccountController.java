package be.ehb.petstore.controller;

import be.ehb.petstore.model.Address;
import be.ehb.petstore.model.User;
import be.ehb.petstore.model.UserForm;
import be.ehb.petstore.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getAccountPage(Model model) {
        accountService.getAccountPage(model);
        return "myAccount";
    }

    @GetMapping("/address/shipping")
    public String getShippingAddressPage(Model model) {
        model.addAttribute("address", new Address());
        model.addAttribute("userForm", new UserForm());
        return "myAccount-shipping-address";
    }

    @PostMapping("/address/shipping")
    public String updateShippingAddress(@Valid @ModelAttribute("address") Address address, BindingResult addressBindingResult,
                                        @Valid @ModelAttribute("userForm") UserForm userForm, BindingResult userFormBindingResult) {
        if (addressBindingResult.hasErrors() || userFormBindingResult.hasErrors()) {
            return "myAccount-shipping-address";
        }
        accountService.updateShipping(address, userForm.getFirstName(), userForm.getLastName());
        return "redirect:/account";
    }

    @GetMapping("/address/billing")
    public String getBillingAddressPage(Model model) {
        model.addAttribute("address", new Address());
        model.addAttribute("userForm", new UserForm());

        return "myAccount-billing-address";
    }

    @PostMapping("/address/billing")
    public String updateBillingAddress(@Valid @ModelAttribute("address") Address address, BindingResult addressBindingResult,
                                       @Valid @ModelAttribute("userForm") UserForm userForm, BindingResult userFormBindingResult
    ) {
        if (addressBindingResult.hasErrors() || userFormBindingResult.hasErrors()) {
            return "myAccount-billing-address";
        }
        accountService.updateBilling(address, userForm.getFirstName(), userForm.getLastName());
        return "redirect:/account";
    }

}
