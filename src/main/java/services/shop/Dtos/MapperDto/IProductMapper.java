package services.shop.Dtos.MapperDto;

import org.mapstruct.Mapper;
import services.shop.Dtos.EntitiesDto.ProductsDto.NewProductDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
import services.shop.entities.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    ProductDto productToProductDTO(Product product);
    List<ProductDto> productsToProductDTOs(List<Product> products);

    Product NewproductToProduct(NewProductDto product);
}
