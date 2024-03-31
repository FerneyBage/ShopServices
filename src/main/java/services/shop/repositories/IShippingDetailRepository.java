package services.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import services.shop.entities.ShippingDetail;
import java.util.List;

public interface IShippingDetailRepository extends JpaRepository<ShippingDetail, Long> {
    List<ShippingDetail> findByOrderId(Long orderId);
    List<ShippingDetail> findByCarrier(String carrier);
    List<ShippingDetail> findByStatus(String status);
}
