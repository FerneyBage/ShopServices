package services.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import services.shop.entities.Product;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    List<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.stock > 0")
    List<Product> findProductsInStock();

    @Query("SELECT p FROM Product p WHERE p.price <= ?1 AND p.stock <= ?2")
    List<Product> findByPriceLessThanEqualAndStockLessThanEqual( double price, int stock);
}
