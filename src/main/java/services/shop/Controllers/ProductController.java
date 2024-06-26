package services.shop.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.shop.Dtos.EntitiesDto.ProductsDto.NewProductDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
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
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long idProduct){
        ProductDto productDto = _productService.getProductByID(idProduct);
        return ResponseEntity.ok().body(productDto);
    }

    @GetMapping("/instock")
    public ResponseEntity<List<ProductDto>> getProductInstock( String cityName){
        List<ProductDto> ProductsDto = _productService.getProductstack();
        return ResponseEntity.ok().body(ProductsDto);

    }
    @PostMapping()
    public ResponseEntity<ProductDto> addProduct(@RequestBody NewProductDto newProductDto){
        ProductDto ProductCreatedDto =  _productService.addProduct(newProductDto);
        return ResponseEntity.ok().body(ProductCreatedDto);
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> getProductBySearch(@RequestParam("searchTerm") String name){
        List<ProductDto> productDto = _productService.getProductForName(name);
        return ResponseEntity.ok().body(productDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> putProduct(@PathVariable("id") Long idProduct, @RequestBody NewProductDto newProductDto){
        ProductDto productDto = _productService.updateProduct(idProduct, newProductDto);
        return ResponseEntity.ok().body(productDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> putProduct(@PathVariable("id") Long idProduct){
        ProductDto productDto = _productService.deleteProduct(idProduct);
        return ResponseEntity.ok().body(productDto);
    }
}
