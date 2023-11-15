package be.ehb.petstore.repository;

import be.ehb.petstore.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM items i WHERE i.id = :id")
    Optional<Item> getItemById(Long id);

    List<Item> getItemsByItemCategory(Item.ItemCategory itemCategory);
}
