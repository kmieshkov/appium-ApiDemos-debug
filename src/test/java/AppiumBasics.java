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

	// Scroll method used if no prior data
	// Views > WebView
	@Test
	public void ScrollGestureJavaScript() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();

		// scroll till end of the page
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
					"left", 100,
					"top", 100,
					"width", 200,
					"height", 200,
					"direction", "down",
					"percent", 3.0
			));
		} while (canScrollMore);

		Thread.sleep(2000);
	}

	// This scroll method used if the element known prior
	// Views > WebView
	@Test
	public void ScrollGesture() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();

		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))")).click();

		driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc='i am a link']/android.widget.TextView")).click();
		String text = driver.findElement(AppiumBy.className("android.view.View")).getText();
		Assert.assertEquals(text, "I am some other page content");
	}

	// Views > Gallery > Photos
	@Test
	public void SwipeGesture() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "true");

		WebElement firstImage = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));

		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)firstImage).getId(),
				"direction", "left",
				"percent", 0.75
		));

		Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "false");
	}
}
