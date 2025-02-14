package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = testBaseUrl + ":" + serverPort + "/product";
    }

    @Test
    void createProduct_andVerifyInList(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/create");

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        String newProductName = "Test Product";
        nameInput.sendKeys(newProductName);

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        int newProductQuantity = 12;
        quantityInput.sendKeys(String.valueOf(newProductQuantity));

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        assertEquals(baseUrl + "/list", driver.getCurrentUrl(), "The user should be redirected to the product list page");

        WebElement productTable = driver.findElement(By.tagName("table"));
        String pageSource = productTable.getText();
        assertTrue(pageSource.contains(newProductName), "The product name should be visible in the product list");
        assertTrue(pageSource.contains(String.valueOf(newProductQuantity)), "The product quantity should be visible in the product list");
    }
}