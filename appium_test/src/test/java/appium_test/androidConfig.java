package appium_test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.testng.annotations.BeforeClass;
import java.net.URI;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.time.Duration;

public class androidConfig {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void setApp() throws MalformedURLException, URISyntaxException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("AppiumTestCases");
        options.setApp("C:/Users/ercirci/Projects/AppiumTestCases/Resources/General-Store.apk");
                
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
