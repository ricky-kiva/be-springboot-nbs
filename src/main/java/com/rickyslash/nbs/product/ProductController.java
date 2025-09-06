package com.rickyslash.nbs.product;

import com.rickyslash.nbs.product.model.Product;
import com.rickyslash.nbs.product.services.CreateProductSvc;
import com.rickyslash.nbs.product.services.DeleteProductSvc;
import com.rickyslash.nbs.product.services.GetProductsSvc;
import com.rickyslash.nbs.product.services.UpdateProductSvc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {
  private final CreateProductSvc createProductSvc;
  private final GetProductsSvc getProductsSvc;
  private final UpdateProductSvc updateProductSvc;
  private final DeleteProductSvc deleteProductSvc;

  public ProductController(
      CreateProductSvc createProductSvc,
      GetProductsSvc getProductsSvc,
      UpdateProductSvc updateProductSvc,
      DeleteProductSvc deleteProductSvc
  ) {
    this.createProductSvc = createProductSvc;
    this.getProductsSvc = getProductsSvc;
    this.updateProductSvc = updateProductSvc;
    this.deleteProductSvc = deleteProductSvc;
  }

  @PostMapping
  public ResponseEntity<String> createProduct() {
    return createProductSvc.execute(null);
  }

  @GetMapping
  public ResponseEntity<List<Product>> getProducts() {
    return getProductsSvc.execute(null);
  }

  @PutMapping
  public ResponseEntity<String> updateProduct() {
    return updateProductSvc.execute(null);
  }

  @DeleteMapping
  public ResponseEntity<String> deleteProduct() {
    return deleteProductSvc.execute(null);
  }
}
