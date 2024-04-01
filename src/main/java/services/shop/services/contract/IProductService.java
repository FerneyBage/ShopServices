package services.shop.services.contract;

import services.shop.Dtos.EntitiesDto.ProductDto;

import java.util.List;

public interface IProductService {
    public List<ProductDto> getAllProducts();
    public ProductDto getProductByID(long id);
}
