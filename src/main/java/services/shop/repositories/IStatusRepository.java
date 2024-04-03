package services.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import services.shop.entities.ShippingDetail;
import services.shop.entities.Status;

public interface IStatusRepository extends JpaRepository<Status, Long> {

}
