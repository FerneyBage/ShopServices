package services.shop.Dtos.MapperDto;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import services.shop.Dtos.EntitiesDto.OrderDto.NewOrderDto;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderDto;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderNotCasDTO;
import services.shop.Dtos.EntitiesDto.StatusDto.StatusDto;
import services.shop.Dtos.MapperDto.NotCas.IPaymentNotCasMapper;
import services.shop.entities.Order;
import services.shop.entities.Status;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IPaymentNotCasMapper.class})
public interface IOrderMapper {
    @Mapping(source = "payment", target = "payment")
    OrderDto OrderToOrderDTO(Order order);

    @BeanMapping(ignoreByDefault = true)
    Order NewOrderDtoToOrder(NewOrderDto orderDto);

    @Mapping(source = "payment", target = "payment")
    List<StatusDto> StatusesToStatusDtos(List<Status> status);

    @Mapping(source = "payment", target = "payment")
    List<OrderDto> OrdersToOrderDtoDTOs(List<Order> ordersDto);

    @BeanMapping(ignoreByDefault = true)
    void updateOrderFromDto(NewOrderDto dto, @MappingTarget Order order);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "payment.id", target = "paymentId")
    @Mapping(source = "shippingDetail.id", target = "shippingDetailId")
    OrderNotCasDTO orderToOrderDTO(Order order);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "payment", ignore = true)
    @Mapping(target = "shippingDetail", ignore = true)
    Order orderDTOToOrder(OrderNotCasDTO orderDTO);
}
