package services.shop.services.implementación;


import org.springframework.stereotype.Service;
import services.shop.Dtos.EntitiesDto.ProductDto;
import services.shop.Dtos.MapperDto.IProductMapper;
import services.shop.entities.Product;
import services.shop.repositories.IProductRepository;
import services.shop.services.contract.IProductService;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductRepository _productRepository;
    private final IProductMapper _productMapper;

    public  ProductService(IProductRepository productRepository, IProductMapper productMapper){
        _productRepository = productRepository;
        _productMapper = productMapper;
    }
    public List<ProductDto> getAllProducts() {
        List<Product> products = _productRepository.findAll();
        return _productMapper.productsToProductDTOs(products);
    }

}
