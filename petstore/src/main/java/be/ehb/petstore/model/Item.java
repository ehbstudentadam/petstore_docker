package be.ehb.petstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity(name = "items")
public class Item {

    public enum ItemCategory {
        ANIMALS, PLANTS, FOOD, ACCESSORIES, AQUARIUMS, MEDICINE, UNKNOWN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long id;
    @NotNull(message = "Item name can not be empty")
    @Size(min = 3, max = 30, message = "Item name must be between 3 and 30 characters")
    private String itemName;
    @NotNull(message = "Supplier name can not be empty")
    @Size(min = 3, max = 30, message = "Supplier name must be between 3 and 30 characters")
    private String supplier;
    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;
    @NotNull(message = "Item price can not be empty")
    private BigDecimal itemPrice;
    @NotNull(message = "Item weight can not be empty")
    private double itemWeight;
    @NotNull(message = "Details can not be empty")
    @Size(min = 10, max = 150, message = "Details must be between 10 and 150 characters")
    private String itemDetails;
    @NotNull(message = "Rating can not be empty")
    @Min(0)
    @Max(5)
    private int itemRating;

    @ManyToMany
    @JoinTable(name = "item_tags", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "item_images", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "image_data_id"))
    private Set<ImageData> imageData;
    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItem;

    public Item() {
    }

    public Item(String itemName, String supplier, ItemCategory itemCategory, BigDecimal itemPrice, double itemWeight, String itemDetails, int itemRating, List<Tag> tags, Set<ImageData> imageData, List<OrderItem> orderItem) {
        this.itemName = itemName;
        this.supplier = supplier;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemWeight = itemWeight;
        this.itemDetails = itemDetails;
        this.itemRating = itemRating;
        this.tags = tags;
        this.imageData = imageData;
        this.orderItem = orderItem;
    }

    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(double itemWeight) {
        this.itemWeight = itemWeight;
    }

    public Set<ImageData> getImageData() {
        return imageData;
    }

    public void setImageData(Set<ImageData> imageData) {
        this.imageData = imageData;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public int getItemRating() {
        return itemRating;
    }

    public void setItemRating(int itemRating) {
        this.itemRating = itemRating;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(getItemWeight(), item.getItemWeight()) == 0 && getItemRating() == item.getItemRating() && Objects.equals(id, item.id) && Objects.equals(getItemName(), item.getItemName()) && Objects.equals(getSupplier(), item.getSupplier()) && getItemCategory() == item.getItemCategory() && Objects.equals(getItemPrice(), item.getItemPrice()) && Objects.equals(getItemDetails(), item.getItemDetails()) && Objects.equals(getTags(), item.getTags()) && Objects.equals(getImageData(), item.getImageData()) && Objects.equals(getOrderItem(), item.getOrderItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getItemName(), getSupplier(), getItemCategory(), getItemPrice(), getItemWeight(), getItemDetails(), getItemRating(), getTags(), getImageData(), getOrderItem());
    }
}
