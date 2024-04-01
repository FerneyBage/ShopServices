package services.shop.Dtos.MapperDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import services.shop.Dtos.EntitiesDto.ProductDto;
import services.shop.entities.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    ProductDto productToProductDTO(Product product);
    List<ProductDto> productsToProductDTOs(List<Product> products);
}
