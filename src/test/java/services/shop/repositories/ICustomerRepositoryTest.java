package services.shop.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import services.shop.AbstractIntegrationDBTest;
import services.shop.entities.Customer;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ICustomerRepositoryTest extends AbstractIntegrationDBTest {

    private final ICustomerRepository customerRepository;

    @Autowired
    ICustomerRepositoryTest(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();

        Customer customer1 = new Customer();
        customer1.setFullName("John Doe");
        customer1.setEmail("john.doe@example.com");
        customer1.setAddress("123 Elm Street");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFullName("Jane Doe");
        customer2.setEmail("jane.doe@example.com");
        customer2.setAddress("456 Oak Street");
        customerRepository.save(customer2);
    }

    @Test
    void testFindByEmail() {
        List<Customer> customers = customerRepository.findByEmail("john.doe@example.com");
        assertEquals(1, customers.size());
        assertEquals("John Doe", customers.get(0).getFullName());
    }

    @Test
    void testFindByAddress() {
        List<Customer> customers = customerRepository.findByAddress("Elm");
        assertEquals(1, customers.size());
        assertEquals("John Doe", customers.get(0).getFullName());
    }

    @Test
    void testFindByFullNameStartsWith() {
        List<Customer> customers = customerRepository.findByFullNameStartsWith("Jane");
        assertEquals(1, customers.size());
        assertEquals("Jane Doe", customers.get(0).getFullName());
    }
    @Test
    void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setFullName("New Customer");
        customer.setEmail("new.customer@example.com");
        customer.setAddress("789 Pine Street");
        Customer savedCustomer = customerRepository.save(customer);

        assertNotNull(savedCustomer.getId());
        assertEquals("New Customer", savedCustomer.getFullName());
    }

    @Test
    void testReadCustomer() {
        Customer customer = new Customer();
        customer.setFullName("Existing Customer");
        customer.setEmail("existing.customer@example.com");
        customer.setAddress("1011 Maple Street");
        customer = customerRepository.save(customer);

        Optional<Customer> foundCustomer = customerRepository.findById(customer.getId());
        assertTrue(foundCustomer.isPresent());
        assertEquals("Existing Customer", foundCustomer.get().getFullName());
    }

    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setFullName("Before Update");
        customer.setEmail("update.customer@example.com");
        customer.setAddress("1213 Cedar Street");
        customer = customerRepository.save(customer);

        customer.setFullName("After Update");
        Customer updatedCustomer = customerRepository.save(customer);

        assertEquals("After Update", updatedCustomer.getFullName());
    }

    @Test
    void testDeleteCustomer() {
        Customer customer = new Customer();
        customer.setFullName("To Be Deleted");
        customer.setEmail("delete.customer@example.com");
        customer.setAddress("1415 Birch Street");
        customer = customerRepository.save(customer);

        customerRepository.deleteById(customer.getId());

        Optional<Customer> deletedCustomer = customerRepository.findById(customer.getId());
        assertFalse(deletedCustomer.isPresent());
    }
}