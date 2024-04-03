package services.shop.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import services.shop.AbstractIntegrationDBTest;
import services.shop.entities.Product;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IProductRepositoryTest extends AbstractIntegrationDBTest {

    private final IProductRepository _productRepository;

    @Autowired
    IProductRepositoryTest(IProductRepository productRepository) {
        _productRepository = productRepository;
    }

    @BeforeEach
    void setUp() {
        _productRepository.deleteAll();
        _productRepository.save(new Product(null, "Product 1", 20.0, 5));
        _productRepository.save(new Product(null, "Product 2", 30.0, 0));
        _productRepository.save(new Product(null, "Product 3", 25.0, 10));
    }

    @Test
    void testFindByNameContaining() {
        List<Product> products = _productRepository.findByName("Product 1");
        assertEquals(1, products.size());
        assertEquals("Product 1", products.get(0).getName());
    }

    @Test
    void testFindProductsInStock() {
        List<Product> productsInStock = _productRepository.findProductsInStock();
        assertEquals(2, productsInStock.size());
        assertTrue(productsInStock.stream().allMatch(product -> product.getStock() > 0));
    }

    @Test
    void testFindByPriceLessThanEqualAndStockLessThanEqual() {
        List<Product> products = _productRepository.findByPriceLessThanEqualAndStockLessThanEqual(20.0, 5);
        assertEquals(1, products.size());
        assertTrue(products.get(0).getPrice() <= 20.0 && products.get(0).getStock() <= 5);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setStock(10);
        Product savedProduct = _productRepository.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals("Test Product", savedProduct.getName());
    }

    @Test
    void testReadProduct() {
        Product product = new Product();
        product.setName("Sample Product");
        product.setPrice(200.0);
        product.setStock(20);
        product = _productRepository.save(product);

        Optional<Product> foundProduct = _productRepository.findById(product.getId());
        assertTrue(foundProduct.isPresent());
        assertEquals("Sample Product", foundProduct.get().getName());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setName("Update Test");
        product.setPrice(300.0);
        product.setStock(30);
        product = _productRepository.save(product);

        product.setName("Updated Name");
        Product updatedProduct = _productRepository.save(product);

        assertEquals("Updated Name", updatedProduct.getName());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setName("Delete Test");
        product.setPrice(400.0);
        product.setStock(40);
        product = _productRepository.save(product);

        _productRepository.deleteById(product.getId());

        Optional<Product> deletedProduct = _productRepository.findById(product.getId());
        assertFalse(deletedProduct.isPresent());
    }
}