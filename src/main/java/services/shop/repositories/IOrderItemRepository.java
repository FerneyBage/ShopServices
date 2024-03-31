package services.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import services.shop.entities.OrderItem;
import java.util.List;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByProductId(Long productId);
    @Query("SELECT SUM(oi.quantity * oi.unitPrice) FROM OrderItem oi WHERE oi.product.id = ?1")
    Double sumTotalSalesByProductId(Long productId);
}
