package services.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import services.shop.entities.Payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Date;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Payment> findByOrderIdAndPaymentMethod(Long orderId, String paymentMethod);
}
