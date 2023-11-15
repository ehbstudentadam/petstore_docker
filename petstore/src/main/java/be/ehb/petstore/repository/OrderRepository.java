package be.ehb.petstore.repository;

import be.ehb.petstore.model.Order;
import be.ehb.petstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getOrdersByUserIdAndOrderStatus(Long user_id, Order.OrderStatus orderStatus);

    void removeAllByUserIdAndOrderStatus(Long user_id, Order.OrderStatus orderStatus);

    List<Order> getOrdersByUser(User user);

    @Query("SELECT o FROM orders o WHERE o.orderStatus = 'PROCESSING' ORDER BY o.orderDate DESC LIMIT 1")
    Optional<Order> getFirstByOrderDateAndOrderStatus();
}