package services.shop.services.contract;

import services.shop.Dtos.EntitiesDto.ProductsDto.NewProductDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;

import java.util.List;

public interface IProductService {
    public List<ProductDto> getAllProducts();
    public ProductDto getProductByID(long id);
    public List<ProductDto> getProductInstock();
    public ProductDto addProduct(NewProductDto newProductDto);
    public List<ProductDto> getproductForName(String name);
}
