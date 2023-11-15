package be.ehb.petstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;

import java.util.Objects;

@Entity(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private Long id;
    @NotNull(message = "Item quantity can not be null")
    @DecimalMin(value = "1", message = "Value must be higher than 1")
    private Integer quantity;

    @ManyToOne
    //@Cascade(org.hibernate.annotations.CascadeType.ALL)
    //@JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne(fetch = FetchType.EAGER)
    //@Cascade(org.hibernate.annotations.CascadeType.ALL)
    //@JoinColumn(name = "item_id", nullable = false)
    private Item item;

    public OrderItem() {
    }
    public OrderItem(Integer quantity, Order order, Item item) {
        this.quantity = quantity;
        this.order = order;
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) && Objects.equals(getQuantity(), orderItem.getQuantity()) && Objects.equals(getOrder(), orderItem.getOrder()) && Objects.equals(getItem(), orderItem.getItem());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, getQuantity(), getOrder(), getItem());
    }
}