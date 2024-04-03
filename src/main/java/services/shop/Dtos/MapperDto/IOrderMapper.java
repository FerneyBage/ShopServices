package services.shop.Dtos.MapperDto;

import org.mapstruct.Mapper;
import services.shop.Dtos.EntitiesDto.OrderDto.NewOrderDto;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
import services.shop.entities.Order;
import services.shop.entities.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderMapper {
    OrderDto OrderToOrderDTO(Order order);
    Order NewOrderDtoToOrder(NewOrderDto orderDto);
    List<OrderDto> OrdersToOrderDtoDTOs(List<Order> ordersDto);
}
