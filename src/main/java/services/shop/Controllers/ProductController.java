package services.shop.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        List<ProductDto> ProductsDto = _productService.getAllProducts();
        return ResponseEntity.ok().body(ProductsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getUsuarioById(@PathVariable("id") Long idProduct){
        ProductDto productDto = _productService.getProductByID(idProduct);
        return ResponseEntity.ok().body(productDto);
    }
}
