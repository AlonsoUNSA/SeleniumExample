package guru.springframework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeFunctionalTest {
	WebDriver driver;

	@BeforeEach
	public void setup() {
		driver = new ChromeDriver();
	}

	@Test
	public void eightComponents() {
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		driver.get("http://localhost:8083/product/new");
		
		WebElement productIdEntry = driver.findElement(By.id("productId"));
        productIdEntry.sendKeys("1203");

        WebElement productDescriptionEntry = driver.findElement(By.id("description"));
        productDescriptionEntry.sendKeys("A porable router");

        WebElement productPriceEntry = driver.findElement(By.id("price"));
        productPriceEntry.sendKeys("7");
        
        WebElement productImageUrlEntry = driver.findElement(By.id("imageUrl"));
        productImageUrlEntry.sendKeys("This is an URL");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(8000));
		
		WebElement submitProductButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[5]/button"));		
		submitProductButton.click();
		
		// Espera explícita
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		
        // Esperar a que se encuentre en la ubicación adecuada "Product Details"
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/h2 [text()='Product Details']"))); // Verifica que la URL contenga algo específico

		// Ejecución assert del porudcto reenvía la ruta esperada con el ID indicado
        String expectedUrl = "http://localhost:8083/product/1023";
        String actualUrl = driver.getCurrentUrl();
        System.out.println("===> URL de la página: " + actualUrl);
        assertEquals(expectedUrl, actualUrl);

	}

	@AfterEach
	public void teardown() {
		if(driver != null) {
			driver.quit();
		}
		
	}
}