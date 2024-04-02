package services.shop.services.implementación;


import org.springframework.stereotype.Service;
import services.shop.Dtos.EntitiesDto.ProductsDto.NewProductDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
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

    public ProductDto getProductByID(long id){
        Product product = _productRepository.getReferenceById(id);
        return _productMapper.productToProductDTO(product);
    }

    public List<ProductDto> getProductInstock(){
        List<Product> products = _productRepository.findProductsInStock();
        return _productMapper.productsToProductDTOs(products);
    }

    public ProductDto addProduct(NewProductDto newProductDto){
        Product newproduct = _productMapper.NewproductToProduct(newProductDto);
        var ProductCreated = _productRepository.save(newproduct);
        return  _productMapper.productToProductDTO(ProductCreated);
    }

    public List<ProductDto> getproductForName(String name){
        List<Product> products = _productRepository.findByName(name);
        return _productMapper.productsToProductDTOs(products);
    }

    /*  public ProductDto putProduct(long id, NewProductDto productDto){
      Product product = _productRepository.getReferenceById(id);
        if(product == null){
            return ;
        }


        Product newproduct = _productMapper.NewproductToProduct(productDto);
        newproduct.setId(id);
        Product I = _productRepository.

    }
    */

}
