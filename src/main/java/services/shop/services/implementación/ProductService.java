package services.shop.services.implementación;


import org.springframework.stereotype.Service;
import services.shop.Dtos.EntitiesDto.ProductDto;
import services.shop.Dtos.MapperDto.ProductMapper;
import services.shop.entities.Product;
import services.shop.repositories.IProductRepository;
import services.shop.services.contract.IProductService;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductRepository _productRepository;

    public  ProductService(IProductRepository productRepository ){
        _productRepository = productRepository;
    }
    public List<ProductDto> getAllProducts() {
        List<Product> products = _productRepository.findAll();
        return ProductMapper.INSTANCE.productsToProductDTOs(products);
    }

}
