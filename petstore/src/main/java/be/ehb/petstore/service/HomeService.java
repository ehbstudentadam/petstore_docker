package be.ehb.petstore.service;

import be.ehb.petstore.model.Item;
import be.ehb.petstore.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

@Service
public class HomeService {
    private final ItemRepository itemRepository;

    @Autowired
    public HomeService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void getHomePage(Model model){
        List<Item> allAnimals = itemRepository.getItemsByItemCategory(Item.ItemCategory.ANIMALS);
        model.addAttribute("animals", allAnimals);
    }

}
