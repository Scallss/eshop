package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create (Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
  
    public void deleteById(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
      
    public Product findById(String productId) {
        return productData.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Product update(Product updatedProduct) {
        Product existing = findById(updatedProduct.getProductId());
        if (existing != null) {
            int index = productData.indexOf(existing);
            productData.set(index, updatedProduct);
            return updatedProduct;
        }
        return null;
    }
}