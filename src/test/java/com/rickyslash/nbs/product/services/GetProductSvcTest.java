package com.rickyslash.nbs.product.services;

import com.rickyslash.nbs.common.exceptions.ProductNotFoundException;
import com.rickyslash.nbs.domain.product.ProductRepository;
import com.rickyslash.nbs.domain.product.model.Product;
import com.rickyslash.nbs.domain.product.model.ProductDTO;
import com.rickyslash.nbs.domain.product.services.GetProductSvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetProductSvcTest {
  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private GetProductSvc getProductSvc;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void given_product_when_getProduct_then_return_productDTO() {
    // Given
    Product product = new Product();

    product.setId(1);
    product.setName("Product 1");
    product.setDescription("Some description which is at least 20 chars");
    product.setPrice(9.99);

    when(productRepository.findById(1)).thenReturn(Optional.of(product));

    // When
    ResponseEntity<ProductDTO> res = getProductSvc.execute(product.getId());

    // Then
    assertEquals(ResponseEntity.ok(new ProductDTO(product)), res);
    verify(productRepository, times(1)).findById(1);
  }

  @Test
  public void given_product_doesnt_exist_when_getProduct_then_throw_exception() {
    // Given
    when(productRepository.findById(1)).thenReturn(Optional.empty());

    // When & Then
    assertThrows(ProductNotFoundException.class, () -> getProductSvc.execute(1));
    verify(productRepository, times(1)).findById(1);
  }
}
