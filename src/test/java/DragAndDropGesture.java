import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropGesture extends BaseTest {

	// Views > Drag and Drop
	@Test
	public void dragAndDrop() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement draggable = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
		WebElement droppable1 = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_2"));
		WebElement droppable2 = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_3"));

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) draggable).getId(),
				"endX", 830,
				"endY", 750
		));

		String actual = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getText();
		Assert.assertEquals(actual, "Dropped!");
	}
}
