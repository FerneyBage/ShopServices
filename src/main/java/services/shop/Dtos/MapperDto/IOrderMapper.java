package services.shop.Dtos.MapperDto;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import services.shop.Dtos.EntitiesDto.OrderDto.NewOrderDto;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
import services.shop.Dtos.EntitiesDto.StatusDto.StatusDto;
import services.shop.entities.Order;
import services.shop.entities.Product;
import services.shop.entities.Status;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderMapper {
    OrderDto OrderToOrderDTO(Order order);
    @BeanMapping(ignoreByDefault = true)
    Order NewOrderDtoToOrder(NewOrderDto orderDto);
    List<StatusDto> StatusesToStatusDtos(List<Status> status);
    List<OrderDto> OrdersToOrderDtoDTOs(List<Order> ordersDto);
}
