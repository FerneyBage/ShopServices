package services.shop.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import services.shop.AbstractIntegrationDBTest;
import services.shop.entities.Order;
import services.shop.entities.ShippingDetail;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IShippingDetailRepositoryTest extends AbstractIntegrationDBTest {


    private final IShippingDetailRepository shippingDetailRepository;

    private final IOrderRepository orderRepository;

    private Order testOrder;

    @Autowired
    IShippingDetailRepositoryTest(IShippingDetailRepository shippingDetailRepository, IOrderRepository orderRepository) {
        this.shippingDetailRepository = shippingDetailRepository;
        this.orderRepository = orderRepository;
    }
    @BeforeEach
    void setup() {
        shippingDetailRepository.deleteAll();
        orderRepository.deleteAll();
        testOrder = new Order();
        orderRepository.save(testOrder);
        ShippingDetail shippingDetail = new ShippingDetail();
        shippingDetail.setOrder(testOrder);
        shippingDetail.setAddress("123 Main St");
        shippingDetail.setCarrier("UPS");
        shippingDetail.setTrackingNumber("1Z999AA10123456784");
        shippingDetailRepository.save(shippingDetail);
    }

    @Test
    void testFindByOrderId() {
        List<ShippingDetail> results = shippingDetailRepository.findByOrderId(testOrder.getId());
        assertFalse(results.isEmpty());
        assertEquals("UPS", results.get(0).getCarrier());
    }

    @Test
    void testFindByCarrier() {
        List<ShippingDetail> results = shippingDetailRepository.findByCarrier("UPS");
        assertFalse(results.isEmpty());
        assertEquals(testOrder.getId(), results.get(0).getOrder().getId());
    }

    @Test
    void testCreateShippingDetail() {
        ShippingDetail shippingDetail = new ShippingDetail();
        shippingDetail.setOrder(testOrder);
        shippingDetail.setAddress("123 Main St");
        shippingDetail.setCarrier("FedEx");
        shippingDetail.setTrackingNumber("TRACK123");
        ShippingDetail savedDetail = shippingDetailRepository.save(shippingDetail);

        assertNotNull(savedDetail.getId());
    }

    @Test
    void testReadShippingDetail() {
        ShippingDetail shippingDetail = new ShippingDetail();
        shippingDetail.setOrder(testOrder);
        shippingDetail.setAddress("456 Main St");
        shippingDetail.setCarrier("DHL");
        shippingDetail.setTrackingNumber("TRACK456");
        shippingDetail = shippingDetailRepository.save(shippingDetail);

        Optional<ShippingDetail> foundDetail = shippingDetailRepository.findById(shippingDetail.getId());
        assertTrue(foundDetail.isPresent());
        assertEquals("DHL", foundDetail.get().getCarrier());
    }

    @Test
    void testUpdateShippingDetail() {
        ShippingDetail shippingDetail = new ShippingDetail();
        shippingDetail.setOrder(testOrder);
        shippingDetail.setAddress("789 Main St");
        shippingDetail.setCarrier("UPS");
        shippingDetail.setTrackingNumber("TRACK789");
        shippingDetail = shippingDetailRepository.save(shippingDetail);

        shippingDetail.setCarrier("USPS");
        ShippingDetail updatedDetail = shippingDetailRepository.save(shippingDetail);

        assertEquals("USPS", updatedDetail.getCarrier());
    }

    @Test
    void testDeleteShippingDetail() {
        ShippingDetail shippingDetail = new ShippingDetail();
        shippingDetail.setOrder(testOrder);
        shippingDetail.setAddress("101 Main St");
        shippingDetail.setCarrier("UPS");
        shippingDetail.setTrackingNumber("TRACK101");
        shippingDetail = shippingDetailRepository.save(shippingDetail);

        shippingDetailRepository.deleteById(shippingDetail.getId());

        Optional<ShippingDetail> deletedDetail = shippingDetailRepository.findById(shippingDetail.getId());
        assertFalse(deletedDetail.isPresent());
    }

}