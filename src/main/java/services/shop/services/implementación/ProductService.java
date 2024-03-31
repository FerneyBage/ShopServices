package services.shop.services.implementación;


import org.springframework.stereotype.Service;
import services.shop.repositories.IProductRepository;

@Service
public class ProductService {

    private final IProductRepository _productRepository;

    public  ProductService(IProductRepository productRepository ){
        _productRepository = productRepository;
    }


}
