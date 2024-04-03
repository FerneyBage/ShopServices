package services.shop.Controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.shop.Dtos.EntitiesDto.PaymentDto.NewPaymentDto;
import services.shop.Dtos.EntitiesDto.PaymentDto.PaymentDto;
import services.shop.services.contract.IPaymentService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final IPaymentService _paymentService;

    public PaymentController(IPaymentService paymentService) {
        _paymentService = paymentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymantById(@PathVariable("id") long id){
        PaymentDto paymentDto = _paymentService.getPaymentById(id);
        return ResponseEntity.ok().body(paymentDto);
    }

    @GetMapping()
    public  ResponseEntity<List<PaymentDto>> getAllPayments(){
        List<PaymentDto> paymentDtos = _paymentService.getALlPayment();
        return ResponseEntity.ok().body(paymentDtos);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<PaymentDto>> getPaymentByOrderId(@PathVariable("orderId") long orderId){
        List<PaymentDto> paymentDtos = _paymentService.getPaymentByOrderId(orderId);
        return ResponseEntity.ok().body(paymentDtos);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PaymentDto>> getOrdersByDate(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<PaymentDto> paymentDtos = _paymentService.getPaymentsByDate(startDate,endDate);
        return ResponseEntity.ok().body(paymentDtos);
    }

    @PostMapping()
    public  ResponseEntity<PaymentDto> addPayment(@RequestBody NewPaymentDto newPaymentDto){
        PaymentDto paymentDto = _paymentService.addPayment(newPaymentDto);
        return ResponseEntity.ok().body(paymentDto);
    }

    @PutMapping("{id}")
    public  ResponseEntity<PaymentDto> putPayment(@PathVariable("id") long id, @RequestBody NewPaymentDto newPaymentDto){
        PaymentDto paymentDto = _paymentService.UpdatePayment(id,newPaymentDto);
        return ResponseEntity.ok().body(paymentDto);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<PaymentDto> deletePayment(@PathVariable("id") long id){
        PaymentDto paymentDto = _paymentService.deletePayment(id);
        return ResponseEntity.ok().body(paymentDto);
    }


}
