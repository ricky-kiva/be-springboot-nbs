package com.rickyslash.nbs.domain.product;

import com.rickyslash.nbs.common.dto.ErrorResponse;
import com.rickyslash.nbs.common.exceptions.ProductNotFoundException;
import com.rickyslash.nbs.domain.product.model.Product;
import com.rickyslash.nbs.domain.product.model.ProductDTO;
import com.rickyslash.nbs.domain.product.model.command.UpdateProductCmd;
import com.rickyslash.nbs.domain.product.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {
  private final CreateProductSvc createProductSvc;
  private final GetProductsSvc getProductsSvc;
  private final UpdateProductSvc updateProductSvc;
  private final DeleteProductSvc deleteProductSvc;
  private final GetProductSvc getProductSvc;
  private final SearchProductSvc searchProductSvc;

  public ProductController(
      CreateProductSvc createProductSvc,
      GetProductsSvc getProductsSvc,
      UpdateProductSvc updateProductSvc,
      DeleteProductSvc deleteProductSvc,
      GetProductSvc getProductSvc,
      SearchProductSvc searchProductSvc
  ) {
    this.createProductSvc = createProductSvc;
    this.getProductsSvc = getProductsSvc;
    this.updateProductSvc = updateProductSvc;
    this.deleteProductSvc = deleteProductSvc;
    this.getProductSvc = getProductSvc;
    this.searchProductSvc = searchProductSvc;
  }

  @PostMapping("/product")
  public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
    return createProductSvc.execute(product);
  }

  @GetMapping("/products")
  public ResponseEntity<List<ProductDTO>> getProducts() {
    return getProductsSvc.execute(null);
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
    return getProductSvc.execute(id);
  }

  @GetMapping("/product/search")
  public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String name) {
    return searchProductSvc.execute(name);
  }

  @PutMapping("/product/{id}")
  public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
    return updateProductSvc.execute(new UpdateProductCmd(id, product));
  }

  @DeleteMapping("/product/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
    return deleteProductSvc.execute(id);
  }

  @ExceptionHandler(ProductNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponse handleProductNotFoundException(ProductNotFoundException ex) {
    return new ErrorResponse(ex.getMessage());
  }
}
