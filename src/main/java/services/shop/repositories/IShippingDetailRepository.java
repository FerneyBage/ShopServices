package services.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import services.shop.entities.ShippingDetail;
import java.util.List;

public interface IShippingDetailRepository extends JpaRepository<ShippingDetail, Long> {
    List<ShippingDetail> findByOrderId(Long orderId);
    List<ShippingDetail> findByCarrier(String carrier);
    @Query("SELECT sd FROM ShippingDetail sd WHERE sd.order.status = ?1")
    List<ShippingDetail> findByOrderStatus(String status);
}
