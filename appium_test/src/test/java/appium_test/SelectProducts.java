package appium_test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;



public class SelectProducts {

    @Test
    public void AddToCartAndCheckCart() throws MalformedURLException, URISyntaxException, InterruptedException
    {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("AppiumTestCases");
        options.setApp("C:/Users/ercirci/Projects/AppiumTestCases/Resources/General-Store.apk");
                
        AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options);

        Thread.sleep(5000);
        
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ricardo Polanski Alves");
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));
        int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for (int i = 0; i < productCount; i++)
        {
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (productName.equalsIgnoreCase("Jordan 6 Rings"))
            {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }

        String cartCount = driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText();
        Assert.assertEquals(cartCount, "1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.androidsample.generalstore:id/toolbar_title"))); // Wait for the toolbar to appear
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        // wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

        String cartProductName = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        String cartProductPrice = driver.findElement(By.id("com.androidsample.generalstore:id/productPrice")).getText();
        Assert.assertEquals(cartProductName, "Jordan 6 Rings");
        Assert.assertEquals(cartProductPrice, "$165.0");
    }

    public static void main(String[] args) {
        
    }
}
