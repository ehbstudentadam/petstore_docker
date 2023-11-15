package be.ehb.petstore.service;

import be.ehb.petstore.model.*;
import be.ehb.petstore.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
public class OrderService {
    private final ItemRepository itemRepository;
    private final TagRepository tagRepository;

    private final HttpSession httpSession;

    @Autowired
    public OrderService(ItemRepository itemRepository, TagRepository tagRepository, HttpSession httpSession) {
        this.itemRepository = itemRepository;
        this.tagRepository = tagRepository;
        this.httpSession = httpSession;
    }

    // Populate model with data needed for the shop page
    public void getShopPage(Model model) {
        List<Item> allItems = itemRepository.findAll();
        List<Tag> allTags = tagRepository.findAll();
        List<Item.ItemCategory> allCategories = Arrays.asList(Item.ItemCategory.values());

        model.addAttribute("items", allItems);
        model.addAttribute("tags", allTags);
        model.addAttribute("categories", allCategories);
    }

    public void addItemToOrder(String itemId) {
        Order sessionOrder = (Order) httpSession.getAttribute("shoppingCart");

        if (sessionOrder == null) {
            sessionOrder = new Order();
        }

        List<OrderItem> myOrderItems = sessionOrder.getOrderItems();

        // Check if the item is already in the order
        if (myOrderItems != null) {
            for (OrderItem orderItem : myOrderItems) {
                if (orderItem.getItem().getId().equals(Long.parseLong(itemId))) {
                    // If the item is already in the order, increment its quantity by 1.
                    orderItem.setQuantity(orderItem.getQuantity() + 1);
                    sessionOrder.setOrderItems(myOrderItems);
                    sessionOrder.calculateOrderPrices();
                    httpSession.setAttribute("shoppingCart", sessionOrder);
                    return;
                }
            }
        } else {
            myOrderItems = new ArrayList<>();
        }

        Optional<Item> getItemById = itemRepository.getItemById(Long.parseLong(itemId));

        if (getItemById.isPresent()) {
            Item itemToAdd = getItemById.get();
            OrderItem orderItemToAdd = new OrderItem(1, sessionOrder, itemToAdd);
            myOrderItems.add(orderItemToAdd);
        }

        sessionOrder.setOrderItems(myOrderItems);
        sessionOrder.calculateOrderPrices();
        httpSession.setAttribute("shoppingCart", sessionOrder);
    }

    // Remove an item from the user's order
    public void removeItemFromOrder(String itemId) {
        Order sessionOrder = (Order) httpSession.getAttribute("shoppingCart");

        List<OrderItem> myOrderItems = sessionOrder.getOrderItems();

        // Check if the item is already in the order
        if (myOrderItems != null) {
            Iterator<OrderItem> iterator = myOrderItems.iterator();
            while (iterator.hasNext()) {
                OrderItem orderItem = iterator.next();
                if (orderItem.getItem().getId().equals(Long.parseLong(itemId))) {
                    // If the item is already in the order, decrement its quantity by 1.
                    orderItem.setQuantity(orderItem.getQuantity() - 1);
                    if (orderItem.getQuantity() == 0) {
                        iterator.remove(); // Use iterator to safely remove the item
                    }
                }
            }
        }

        sessionOrder.setOrderItems(myOrderItems);
        sessionOrder.calculateOrderPrices();
        httpSession.setAttribute("shoppingCart", sessionOrder);
    }
}
