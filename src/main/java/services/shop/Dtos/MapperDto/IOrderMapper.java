package services.shop.Dtos.MapperDto;

import org.mapstruct.Mapper;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderDto;
import services.shop.entities.Order;

@Mapper(componentModel = "spring")
public interface IOrderMapper {
    OrderDto OrderToOrderDTO(Order product);
}
