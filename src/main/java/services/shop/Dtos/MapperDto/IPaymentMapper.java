package services.shop.Dtos.MapperDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderNotCasDTO;
import services.shop.Dtos.EntitiesDto.PaymentDto.NewPaymentDto;
import services.shop.Dtos.EntitiesDto.PaymentDto.PaymentDto;
import services.shop.Dtos.EntitiesDto.PaymentDto.PaymentNotCasDTO;
import services.shop.entities.Order;
import services.shop.entities.Payment;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IOrderMapper.class})
public interface IPaymentMapper {
    @Mapping(source = "order", target = "order")
    PaymentDto paymentToPaymentDto(Payment payment);

    @Mapping(source = "order", target = "order")
    Payment paymentDtoToPayment(PaymentDto paymentDto);

    @Mapping(source = "order", target = "order")
    List<PaymentDto> paymentsToPaymentDtos(List<Payment> payments);

    @Mapping(source = "orderId", target = "order.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPayment", ignore = true)
    Payment newPaymentDtoToPayment(NewPaymentDto newPaymentDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPayment", ignore = true)
    @Mapping(target = "order", ignore = true)
    void updatePaymentFromDto(NewPaymentDto dto, @MappingTarget Payment payment);


}
