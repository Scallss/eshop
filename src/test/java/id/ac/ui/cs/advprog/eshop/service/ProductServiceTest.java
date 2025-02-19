package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        List<Product> products = List.of(new Product());
        when(productRepository.findAll()).thenReturn(products.iterator());
        List<Product> foundProducts = productService.findAll();
        assertEquals(products, foundProducts);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        productService.deleteById("1");
        verify(productRepository, times(1)).deleteById("1");
    }

    @Test
    void testFindById() {
        Product product = new Product();
        when(productRepository.findById("1")).thenReturn(product);
        Product foundProduct = productService.findById("1");
        assertEquals(product, foundProduct);
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        when(productRepository.findById("1")).thenReturn(product);
        when(productRepository.update(product)).thenReturn(product);
        Product updatedProduct = productService.update("1", product);
        assertEquals(product, updatedProduct);
        verify(productRepository, times(1)).findById("1");
        verify(productRepository, times(1)).update(product);
    }
}