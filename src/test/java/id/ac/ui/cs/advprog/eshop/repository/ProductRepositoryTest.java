package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById_ExistingProduct() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Existing Product");
        product.setProductQuantity(50);
        productRepository.create(product);


        Product foundProduct = productRepository.findById("123");


        assertNotNull(foundProduct, "Product should be found");
        assertEquals("123", foundProduct.getProductId(), "Product ID should match");
        assertEquals("Existing Product", foundProduct.getProductName(), "Product name should match");
        assertEquals(50, foundProduct.getProductQuantity(), "Product quantity should match");
    }


    @Test
    void testFindById_NonExistingProduct() {

        Product foundProduct = productRepository.findById("non-existing-id");


        assertNull(foundProduct, "No product should be found with a non-existing ID");
    }


    @Test
    void testFindById_AmongMultipleProducts() {

        Product product1 = new Product();
        product1.setProductId("101");
        product1.setProductName("Product 101");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("102");
        product2.setProductName("Product 102");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        Product product3 = new Product();
        product3.setProductId("103");
        product3.setProductName("Product 103");
        product3.setProductQuantity(30);
        productRepository.create(product3);


        Product foundProduct = productRepository.findById("102");


        assertNotNull(foundProduct, "Product with ID 102 should be found");
        assertEquals("102", foundProduct.getProductId(), "Found product ID should match 102");
        assertEquals("Product 102", foundProduct.getProductName(), "Found product name should match 'Product 102'");
        assertEquals(20, foundProduct.getProductQuantity(), "Found product quantity should match 20");
    }

    @Test
    void testEditExistingProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Original Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(20);

        Product result = productRepository.update(updatedProduct);

        assertNotNull(result);
        assertEquals("Updated Product", result.getProductName());
        assertEquals(20, result.getProductQuantity());
    }

    @Test
    void testEditNonExistingProduct() {
        Product nonExistingProduct = new Product();
        nonExistingProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        nonExistingProduct.setProductName("Non-existing Product");
        nonExistingProduct.setProductQuantity(30);

        Product result = productRepository.update(nonExistingProduct);

        assertNull(result, "Updating a non-existing product should return null");
    }

    @Test
    void testDeleteExistingProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Deletable Product");
        productRepository.create(product);

        productRepository.deleteById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext(), "Product list should be empty after deletion");
    }

    @Test
    void testDeleteNonExistingProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Deletable Product");
        productRepository.create(product);

        productRepository.deleteById("999");

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext(), "Existing products should not be affected by deleting a non-existing one");
        Product existingProduct = products.next();
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", existingProduct.getProductId());
    }
}