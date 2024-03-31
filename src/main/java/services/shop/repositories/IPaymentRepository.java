package services.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import services.shop.entities.Payment;
import java.util.List;
import java.util.Date;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByPaymentDateBetween(Date startDate, Date endDate);
    List<Payment> findByOrderIdAndPaymentMethod(Long orderId, String paymentMethod);
}
