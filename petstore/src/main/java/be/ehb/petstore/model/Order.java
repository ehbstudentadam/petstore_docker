package be.ehb.petstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.FetchType.EAGER;

@Entity(name = "orders")
public class Order {
    public enum OrderStatus {
        PENDING,            //The order has been created but is not yet processed or confirmed.
        PROCESSING,         //The order is being prepared and processed for fulfillment.
        SHIPPED,            //The order has been shipped and is in transit to the customer.
        DELIVERED,          //The order has been successfully delivered to the customer.
        CANCELLED,          // The order has been canceled by the customer or the business.
        ON_HOLD,            //The order is temporarily on hold and not being processed.
        BACKORDERED,        //Some items in the order are currently out of stock and will be shipped when available.
        COMPLETED,          //The order has been successfully processed and fulfilled.
        REFUNDED,           //The customer has been refunded for the order.
        RETURNED            //The customer has returned some or all of the items from the order.
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal totalPrice;
    private BigDecimal taxPrice;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date shippingDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PENDING;


    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Payment payment;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> orderItems;
    @ManyToOne
    //@Cascade(org.hibernate.annotations.CascadeType.ALL)
    //@JoinColumn(name = "address_id", nullable = false)
    private Address shippingAddress;
    @ManyToOne
    //@Cascade(org.hibernate.annotations.CascadeType.ALL)
    //@JoinColumn(name = "customer_id", nullable = false)
    private User user;

    public Order() {
    }
    public Order(BigDecimal totalPrice, BigDecimal taxPrice, Date orderDate, Date shippingDate, OrderStatus orderStatus, Payment payment, List<OrderItem> orderItems, Address shippingAddress, User user) {
        this.totalPrice = totalPrice;
        this.taxPrice = taxPrice;
        this.orderDate = orderDate;
        this.shippingDate = shippingDate;
        this.orderStatus = orderStatus;
        this.payment = payment;
        this.orderItems = orderItems;
        this.shippingAddress = shippingAddress;
        this.user = user;
    }

    public void calculateOrderPrices(){
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem orderItem: this.orderItems) {
            BigDecimal itemPrice = orderItem.getItem().getItemPrice();
            Integer quantity = orderItem.getQuantity();
            BigDecimal totalItemPrice = itemPrice.multiply(BigDecimal.valueOf(quantity));
            totalPrice = totalPrice.add(totalItemPrice);
        }

        this.totalPrice = totalPrice;

        //todo taxPrice
    }

    public Long getId() {
        return id;
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User customer) {
        this.user = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(getTotalPrice(), order.getTotalPrice()) && Objects.equals(getTaxPrice(), order.getTaxPrice()) && Objects.equals(getOrderDate(), order.getOrderDate()) && Objects.equals(getShippingDate(), order.getShippingDate()) && getOrderStatus() == order.getOrderStatus() && Objects.equals(getPayment(), order.getPayment()) && Objects.equals(getOrderItems(), order.getOrderItems()) && Objects.equals(getShippingAddress(), order.getShippingAddress()) && Objects.equals(getUser(), order.getUser());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, getTotalPrice(), getTaxPrice(), getOrderDate(), getShippingDate(), getOrderStatus(), getPayment(), getOrderItems(), getShippingAddress(), getUser());
    }
}


