import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppiumBasics extends BaseTest {

	// Click Gesture
	//Preference > Preference dependencies > WiFi & WiFi settings
	@Test
	public void WiFiTest() {
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String popUpText = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
		Assert.assertEquals(popUpText, "WiFi settings");
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(popUpText);
		driver.findElement(AppiumBy.id("android:id/button1")).click();
	}


	// Long Press Gesture
	//Views > Expandable list > 1. Custom Adapter
	@Test
	public void LongPressGesture() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement element = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='People Names']"));
		LongPressAction(element);
		Thread.sleep(2000);
		String menu = driver.findElement(AppiumBy.id("android:id/title")).getText();
		Assert.assertEquals(menu, "Sample menu");
		Assert.assertTrue(driver.findElement(AppiumBy.id("android:id/title")).isDisplayed());
	}
}
