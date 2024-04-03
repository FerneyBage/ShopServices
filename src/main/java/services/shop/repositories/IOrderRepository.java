package services.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import services.shop.entities.Customer;
import services.shop.entities.Order;
import org.springframework.data.jpa.repository.Query;
import services.shop.entities.Status;

import java.util.Date;
import java.util.List;

public interface    IOrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderDateBetween(Date startDate, Date endDate);
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByCustomerAndStatus(Customer customer, Status status);
    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.customer = ?1")
    List<Order> fetchOrdersWithItemsForCustomer(Customer customer);
}
