package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        model = new BindingAwareModelMap();
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("CreateProduct", viewName);
        Product expectedProduct = new Product();
        Product actualProduct = (Product) model.getAttribute("product");
        assertEquals(expectedProduct.getProductName(), actualProduct.getProductName());
        assertEquals(expectedProduct.getProductQuantity(), actualProduct.getProductQuantity());
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = productController.createProductPost(product, model);
        verify(productService, times(1)).create(product);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testProductListPage() {
        List<Product> products = new ArrayList<>();
        when(productService.findAll()).thenReturn(products);
        String viewName = productController.productListPage(model);
        assertEquals("ProductList", viewName);
        assertEquals(products, model.getAttribute("products"));
    }

    @Test
    void testDeleteProduct() {
        String viewName = productController.deleteProduct("1");
        verify(productService, times(1)).deleteById("1");
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        when(productService.findById("1")).thenReturn(product);
        String viewName = productController.editProductPage("1", model);
        assertEquals("EditProduct", viewName);
        assertEquals(product, model.getAttribute("product"));
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        String viewName = productController.editProductPost("1", product);
        verify(productService, times(1)).update("1", product);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPageProductNotFound() {
        when(productService.findById("1")).thenReturn(null);

        String viewName = productController.editProductPage("1", model);

        assertEquals("redirect:/product/list", viewName);
        verify(productService, times(1)).findById("1");
        assertNull(model.getAttribute("product"));
    }
}