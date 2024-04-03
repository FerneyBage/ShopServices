package services.shop.services.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import services.shop.Dtos.MapperDto.IProductMapper;
import services.shop.Dtos.MapperDto.IProductMapperImpl;
import services.shop.repositories.IProductRepository;

class ProductServiceTest {


    @Mock
    public IProductRepository _productRepositoryMock;
    public IProductMapper _productMapper;
    public ProductService _productService;

    @BeforeEach
    void setUp() {
        _productMapper = new IProductMapperImpl();
        _productService = new ProductService(_productRepositoryMock,_productMapper);
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getProductByID() {
    }

    @Test
    void getProductInstock() {
    }

    @Test
    void addProduct() {
    }

    @Test
    void getproductForName() {
    }
}