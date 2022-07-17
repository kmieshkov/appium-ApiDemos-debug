import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LongPressGesture extends BaseTest {
	// Long Press Gesture
	//Views > Expandable list > 1. Custom Adapter
	@Test
	public void longPressGesture() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement element = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='People Names']"));

		longPressAction(element);

		Thread.sleep(2000);
		String menu = driver.findElement(AppiumBy.id("android:id/title")).getText();
		Assert.assertEquals(menu, "Sample menu");
		Assert.assertTrue(driver.findElement(AppiumBy.id("android:id/title")).isDisplayed());
	}
}
