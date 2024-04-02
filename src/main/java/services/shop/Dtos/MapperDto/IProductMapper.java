package services.shop.Dtos.MapperDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import services.shop.Dtos.EntitiesDto.ProductsDto.NewProductDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
import services.shop.entities.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    ProductDto productToProductDTO(Product product);
    List<ProductDto> productsToProductDTOs(List<Product> products);
    @Mapping(target = "id", ignore = true)
    Product NewproductToProduct(NewProductDto product);
}
