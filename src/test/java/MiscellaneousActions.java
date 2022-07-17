import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MiscellaneousActions extends BaseTest {

	// App > Alert Dialogs
	@Test
	public void landscapeMode() {
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();

		DeviceRotation landscape = new DeviceRotation(0, 0, 90);
		driver.rotate(landscape);

		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String popUpText = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
		Assert.assertEquals(popUpText, "WiFi settings");
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(popUpText);
		driver.findElement(AppiumBy.id("android:id/button1")).click();
	}

	// copy to clipboard - paste from clipboard
	@Test
	public void copyPaste() {

		String str = "Test Wifi";
		driver.setClipboardText(str);

		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();

		// get from Clipboard
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(driver.getClipboardText());
		driver.findElement(AppiumBy.id("android:id/edit")).clear();

		driver.findElement(AppiumBy.id("android:id/button1")).click();
	}

	// Android Key
	@Test
	public void keyActions() {
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();

		// get from Clipboard
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Test Wifi");
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
	}

}
