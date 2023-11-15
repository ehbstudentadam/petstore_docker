package be.ehb.petstore.controller;

import be.ehb.petstore.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    private final AboutService aboutService;

    @Autowired
    public AboutController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    @GetMapping("/about")
    public String getShopPage(Model model){
        aboutService.getAboutPage(model);
        return "about";
    }
}
