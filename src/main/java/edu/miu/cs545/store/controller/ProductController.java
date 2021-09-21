package edu.miu.cs545.store.controller;

import edu.miu.cs545.store.dto.ProductDTO;
import edu.miu.cs545.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Collection<ProductDTO>> getProductList() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        productService.saveProduct(productDTO);
        productDTO = productService.getProduct(productDTO.getProductNumber());
        return ResponseEntity.created(null).body(productDTO);
    }

    @PutMapping("/products/{productNumber}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("productNumber") String productNumber,
                                                    @RequestBody @Valid ProductDTO productDTO) {
        productService.getProduct(productDTO.getProductNumber());
        productService.saveProduct(productDTO);
        productDTO = productService.getProduct(productDTO.getProductNumber());
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/products/{productNumber}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("productNumber") String productNumber) {
        ProductDTO productDTO = productService.getProduct(productNumber);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("/products/{productNumber}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productNumber") String productNumber) {
        ProductDTO productDTO = productService.getProduct(productNumber);
        productService.deleteProduct(productDTO.getProductNumber());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
