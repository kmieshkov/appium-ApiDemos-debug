import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActivityExample extends BaseTest {

	// navigate directly to the particular window of the application
	public void activity() {

		// adb shell dumpsys window | grep -E 'mCurrentFocus'
		Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
		driver.startActivity(activity);
	}

	@Test
	public void wiFiTest() {

		// directly open Preference > Preference dependencies > WiFi & WiFi settings
		this.activity();

		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String popUpText = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
		Assert.assertEquals(popUpText, "WiFi settings");
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(popUpText);
		driver.findElement(AppiumBy.id("android:id/button1")).click();
	}
}
