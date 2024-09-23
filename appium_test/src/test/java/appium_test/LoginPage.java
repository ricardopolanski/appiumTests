package appium_test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;



public class LoginPage {

    @Test
    public void LoginSuccess() throws MalformedURLException, URISyntaxException, InterruptedException
    {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("AppiumTestCases");
        options.setApp("C:/Users/ercirci/Projects/AppiumTestCases/Resources/General-Store.apk");
                
        AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options);

        Thread.sleep(5000);
       
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"))")).click();
        String selectedCountry = driver.findElement(By.id("android:id/text1")).getText();
        
        Assert.assertEquals(selectedCountry, "Brazil");
        
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ricardo Polanski Alves");
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }

    @Test
    public void LoginError() throws MalformedURLException, URISyntaxException, InterruptedException
    {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("AppiumTestCases");
        options.setApp("C:/Users/ercirci/Projects/AppiumTestCases/Resources/General-Store.apk");
                
        AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options);

        Thread.sleep(5000);
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[@text='Please enter your name']")).getText();
        Assert.assertEquals("Please enter your name", toastMessage);
        
    }

    public static void main(String[] args) {
        
    }
}
