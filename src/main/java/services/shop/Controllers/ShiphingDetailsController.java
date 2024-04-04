package services.shop.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
import services.shop.Dtos.EntitiesDto.ShiphingDto.ShiphingDto;
import services.shop.services.implementation.ShiphingDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shiphingDetails")
public class ShiphingDetailsController {
    private final ShiphingDetailService _shiphingDetailService;

    public ShiphingDetailsController(ShiphingDetailService shiphingDetailService) {
        _shiphingDetailService = shiphingDetailService;
    }

    @GetMapping()
    public ResponseEntity<List<ShiphingDto>> getProducts(){
        List<ShiphingDto> allShiphing = _shiphingDetailService.getAllShiphing();
        return ResponseEntity.ok().body(allShiphing);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiphingDto> getProductById(@PathVariable("id") Long idShiphiLong){
        ShiphingDto Shiphing = _shiphingDetailService.findById(idShiphiLong);
        return ResponseEntity.ok().body(Shiphing);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<ShiphingDto>> getProductById(@PathVariable("orderId") long orderId){
        List<ShiphingDto> Shiphings = _shiphingDetailService.findByOrderId(orderId);
        return ResponseEntity.ok().body(Shiphings);
    }


}
