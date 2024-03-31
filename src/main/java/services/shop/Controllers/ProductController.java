package services.shop.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.shop.Dtos.EntitiesDto.ProductDto;
import services.shop.services.contract.IProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private IProductService _productService;

    public ProductController(IProductService productService){
        _productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> usuariosDto = _productService.getAllProducts();
        return ResponseEntity.ok().body(usuariosDto);
    }
}
