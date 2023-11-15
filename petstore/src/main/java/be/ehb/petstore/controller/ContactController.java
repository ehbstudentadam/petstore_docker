package be.ehb.petstore.controller;

import be.ehb.petstore.model.ContactMessage;
import be.ehb.petstore.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String getContactPage(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "contact";
    }

    @PostMapping("/contact")
    public String sendContactMessage(@Valid @ModelAttribute("contactMessage") ContactMessage contactMessage, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "contact";
        }
        contactService.saveContactMessage(contactMessage);

        return "redirect:/index";
    }
}
