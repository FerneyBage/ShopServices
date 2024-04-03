package services.shop.Dtos.MapperDto.NotCas;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import services.shop.Dtos.EntitiesDto.PaymentDto.PaymentNotCasDTO;
import services.shop.Dtos.MapperDto.IOrderMapper;
import services.shop.entities.Payment;

@Mapper(componentModel = "spring", uses = {IOrderMapper.class})
public interface IPaymentNotCasMapper {
    @Mapping(source = "order.id", target = "orderId")
    PaymentNotCasDTO PaymentToPaymentNotCasDTO(Payment payment);
}
