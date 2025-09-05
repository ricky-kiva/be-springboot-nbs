package com.rickyslash.nbs.product;

import com.rickyslash.nbs.product.services.CreateProductSvc;
import com.rickyslash.nbs.product.services.DeleteProductSvc;
import com.rickyslash.nbs.product.services.GetProductSvc;
import com.rickyslash.nbs.product.services.UpdateProductSvc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
  private final CreateProductSvc createProductSvc;
  private final GetProductSvc getProductSvc;
  private final UpdateProductSvc updateProductSvc;
  private final DeleteProductSvc deleteProductSvc;

  public ProductController(
      CreateProductSvc createProductSvc,
      GetProductSvc getProductSvc,
      UpdateProductSvc updateProductSvc,
      DeleteProductSvc deleteProductSvc
  ) {
    this.createProductSvc = createProductSvc;
    this.getProductSvc = getProductSvc;
    this.updateProductSvc = updateProductSvc;
    this.deleteProductSvc = deleteProductSvc;
  }

  @PostMapping
  public ResponseEntity<String> createProduct() {
    return createProductSvc.execute();
  }

  @GetMapping
  public ResponseEntity<String> getProduct() {
    return getProductSvc.execute();
  }

  @PutMapping
  public ResponseEntity<String> updateProduct() {
    return updateProductSvc.execute();
  }

  @DeleteMapping
  public ResponseEntity<String> deleteProduct() {
    return deleteProductSvc.execute();
  }
}
