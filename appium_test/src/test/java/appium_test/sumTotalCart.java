package appium_test;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class sumTotalCart extends androidConfig{

    @Test
    public void checkSumProductsPrice() throws InterruptedException{
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ricardo Polanski Alves");
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String products[] = {"Jordan 6 Rings", "PG 3"};

        
        for (String product : products) {
            // Print the product being processed
            System.out.println("Processing product: " + product);
        
            // Dynamically scroll to the product
            // driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));
            driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollToBeginning(1)"
            ));
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + product + "\"))"));
        
            // Get the count of visible products
            int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        
            // Loop through visible products to find a match
            for (int i = 0; i < productCount; i++) {
                String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
                if (productName.equalsIgnoreCase(product)) {
                    // Add product to cart
                    System.out.println("Adding product to cart: " + productName);
                    driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                    break;
                }
            }
        }

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.androidsample.generalstore:id/toolbar_title"))); // Wait for the toolbar to appear
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        int count = productPrices.size();

        double totalSum = 0;
        Double total = Double.parseDouble(driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(1));

        for(int i = 0; i< count; i++){
            String amountString = productPrices.get(i).getText();            
            Double price = Double.parseDouble(amountString.substring(1));
            totalSum = totalSum + price;
        }

        Assert.assertEquals(totalSum, total);

        WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
    
        driver.executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 4000));
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(AppiumBy.className("android.widget.Button")).click();
        Thread.sleep(2000);

    }
    
}
