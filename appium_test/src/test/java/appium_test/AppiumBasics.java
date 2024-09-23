package appium_test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;



public class AppiumBasics {

    @Test
    public void AppiumFormTest() throws MalformedURLException, URISyntaxException, InterruptedException
    {
        // The UiAutomator2Options and AndroidDriver must be declared in this class to use the existent Appium Server connection        
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("AppiumTestCases");
        options.setApp(
                "C:/Users/ercirci/Projects/AppiumTestCases/Resources/General-Store.apk");
                
        AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options);
        
        
        // Wait<WebDriver> wait = new FluentWait<>(driver)
        //         .withTimeout(Duration.ofSeconds(10))
        //         .pollingEvery(Duration.ofSeconds(2))
        //         .ignoring(NoSuchElementException.class);

        Thread.sleep(5000);
       
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"))")).click();
        String selectedCountry = driver.findElement(By.id("android:id/text1")).getText();
        
        Assert.assertEquals(selectedCountry, "Brazil");
        
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ricardo Polanski Alves");


        
        

    }

    public static void main(String[] args) {
        
    }
}
