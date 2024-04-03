package services.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import services.shop.entities.Customer;
import services.shop.entities.Product;

import java.util.List;
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByEmail(String email);
    @Query("SELECT p FROM Customer p WHERE p.address LIKE %?1%")
    List<Customer> findByAddress(String address);
    @Query("SELECT c FROM Customer c WHERE c.fullName LIKE ?1%")
    List<Customer> findByFullNameStartsWith(String name);
}
