package be.ehb.petstore.controller;

import be.ehb.petstore.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController{

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping(value = {"/", "/index"})
    public String getHomePage(Model model){
        homeService.getHomePage(model);
        return "index";
    }

}
