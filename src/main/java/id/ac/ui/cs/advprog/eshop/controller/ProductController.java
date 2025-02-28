package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private static final String PRODUCT_LIST_REDIRECT = "redirect:/product/list";

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return PRODUCT_LIST_REDIRECT;
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }
  
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String productId) {
        service.deleteById(productId);
        return PRODUCT_LIST_REDIRECT;
    }
  
    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        if (product == null) {
            // handle not found
            return PRODUCT_LIST_REDIRECT;
        }
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit/{productId}")
    public String editProductPost(@PathVariable String productId,
                                  @ModelAttribute Product updatedProduct) {
        service.update(productId, updatedProduct);
        return PRODUCT_LIST_REDIRECT;
    }
}

