import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScrollGesture extends BaseTest {
	// Scroll method used if no prior data
	// Views > WebView
	@Test
	public void scrollGestureJavaScript() throws InterruptedException {
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
	public void scrollGesture() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();

		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))")).click();

		driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc='i am a link']/android.widget.TextView")).click();
		String text = driver.findElement(AppiumBy.className("android.view.View")).getText();
		Assert.assertEquals(text, "I am some other page content");
	}
}
