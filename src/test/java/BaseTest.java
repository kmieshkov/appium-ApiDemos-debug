import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

	public AndroidDriver driver;
	AppiumDriverLocalService service;

	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/Users/kmieshkov/.nvm/versions/node/v18.0.0/lib/node_modules/appium/build/lib/main.js"))
				.withIPAddress("127.0.0.1")
				.withArgument(() -> "--base-path", "/wd/hub/")
				.usingPort(4723)
				.build();
		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel_2_XL_API_30");
		options.setApp("/Users/kmieshkov/Projects/IdeaProjects/appium/src/test/resources/ApiDemos-debug.apk");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver(url, options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	public void LongPressAction(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"duration", 2000
		));
	}

	@AfterClass
	public void TearDown() {
		driver.quit();
		service.stop();
	}
}
