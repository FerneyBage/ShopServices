package services.shop.Dtos.MapperDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import services.shop.Dtos.EntitiesDto.ProductsDto.NewProductDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
import services.shop.Dtos.EntitiesDto.ShiphingDto.NewShiphingDetails;
import services.shop.Dtos.EntitiesDto.ShiphingDto.ShiphingDto;
import services.shop.entities.Product;
import services.shop.entities.ShippingDetail;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IOrderMapper.class})
public interface IshiphingMapper {
    List<ShiphingDto> ShiPhingsToShiphingsDto(List<ShippingDetail> shippingDetails);
    ShiphingDto shiphingToshiphingDTO(ShippingDetail shippingDetail);
    @Mapping(target = "id", ignore = true)
    ShippingDetail NewShippingDetailtoShippingDetail(NewShiphingDetails NewshiphingDetails);
    @Mapping(target = "id", ignore = true)
    void updateShiphingFromDto(NewShiphingDetails dto, @MappingTarget ShippingDetail shippingDetail);
}
