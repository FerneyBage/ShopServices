package services.shop.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import services.shop.AbstractIntegrationDBTest;
import services.shop.entities.Customer;
import services.shop.entities.Order;
import services.shop.entities.Payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IPaymentRepositoryTest extends AbstractIntegrationDBTest {

    private final IPaymentRepository paymentRepository;
    private final IOrderRepository orderRepository;
    private final ICustomerRepository customerRepository;
    private Customer testCustomer;
    private Order testOrder;

    @Autowired
    IPaymentRepositoryTest(IPaymentRepository paymentRepository, IOrderRepository orderRepository, ICustomerRepository customerRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @BeforeEach
    void setup() {
        paymentRepository.deleteAll();
        orderRepository.deleteAll();
        customerRepository.deleteAll();

        testCustomer = new Customer();
        testCustomer.setFullName("John Doe");
        testCustomer.setEmail("john.doe@example.com");
        testCustomer.setAddress("123 Elm Street");
        customerRepository.save(testCustomer);

        testOrder = new Order();
        testOrder.setCustomer(testCustomer);
        testOrder.setOrderDate(LocalDateTime.now().minusDays(1));
        orderRepository.save(testOrder);


        Payment payment1 = new Payment();
        payment1.setOrder(testOrder);
        payment1.setTotalPayment(100.0);
        payment1.setPaymentDate(LocalDateTime.now());
        payment1.setPaymentMethod("Credit Card");
        paymentRepository.save(payment1);

        Payment payment2 = new Payment();
        payment2.setOrder(testOrder);
        payment2.setTotalPayment(50.0);
        payment2.setPaymentDate(LocalDateTime.now().minusDays(5));
        payment2.setPaymentMethod("PayPal");
        paymentRepository.save(payment2);
    }

    @Test
    void testCreatePayment() {
        Payment payment = new Payment();
        payment.setOrder(testOrder);
        payment.setTotalPayment(100.0);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod("Credit Card");
        Payment savedPayment = paymentRepository.save(payment);

        assertNotNull(savedPayment.getId());
    }

    @Test
    void testReadPayment() {
        Payment payment = new Payment();
        payment.setOrder(testOrder);
        payment.setTotalPayment(200.0);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod("Debit Card");
        payment = paymentRepository.save(payment);

        Optional<Payment> foundPayment = paymentRepository.findById(payment.getId());
        assertTrue(foundPayment.isPresent());
        assertEquals("Debit Card", foundPayment.get().getPaymentMethod());
    }

    @Test
    void testUpdatePayment() {
        Payment payment = new Payment();
        payment.setOrder(testOrder);
        payment.setTotalPayment(300.0);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod("PayPal");
        payment = paymentRepository.save(payment);

        payment.setTotalPayment(350.0);
        Payment updatedPayment = paymentRepository.save(payment);

        assertEquals(350.0, updatedPayment.getTotalPayment(), 0.001);
    }

    @Test
    void testDeletePayment() {
        Payment payment = new Payment();
        payment.setOrder(testOrder);
        payment.setTotalPayment(400.0);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod("Cash");
        payment = paymentRepository.save(payment);

        Long paymentId = payment.getId();
        paymentRepository.deleteById(paymentId);

        Optional<Payment> deletedPayment = paymentRepository.findById(paymentId);
        assertFalse(deletedPayment.isPresent());
    }
}