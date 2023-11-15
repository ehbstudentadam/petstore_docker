package be.ehb.petstore.controller;


import be.ehb.petstore.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopController {

    private final OrderService orderService;

    @Autowired
    public ShopController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/shop")
    public String getShopPage(Model model){
        orderService.getShopPage(model);
        return "shop";
    }
    @Transactional
    @PostMapping(value = "/shop", params = "addItemId")
    public String addOrderItem(
            @RequestParam("addItemId") String itemId,
            HttpServletRequest request
    ){
        orderService.addItemToOrder(itemId);
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }

    /*By adding @Transactional, these methods ensure that the database operations within them are treated
    as a single unit of work. If any part of the process fails (e.g., due to an exception),
    the entire set of operations is rolled back, maintaining the consistency of the database.*/
    @Transactional
    @PostMapping(value = "/shop", params = "removeItemId")
    public String removeOrderItem(
            @RequestParam("removeItemId") String itemId,
            HttpServletRequest request
    ){
        orderService.removeItemFromOrder(itemId);
        // Redirects back to the previous page (referer).
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }

}
