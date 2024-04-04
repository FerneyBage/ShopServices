package services.shop.Dtos.MapperDto;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import services.shop.Dtos.EntitiesDto.OrderItemDto.OrderItemDto;
import services.shop.entities.OrderItem;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IOrderMapper.class, IProductMapper.class})
public interface IOrderItemMapper {
    OrderItem toOrderItem(OrderItemDto orderItemDto);

    @Mapping(source = "order", target = "order")
    @Mapping(source = "product", target = "product")
    OrderItemDto toOrderItemDto(OrderItem orderItem);

    @Mapping(source = "order", target = "order")
    @Mapping(source = "product", target = "product")
    List<OrderItemDto> toOrderItemDtos(List<OrderItem> orderItems);
}
