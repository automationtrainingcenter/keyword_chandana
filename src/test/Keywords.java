package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utitlities.LocatorHelper;

public class Keywords {
	private WebDriver driver;
	private String alertText;

// openBrowser
	public void openBrowser(String locType, String locValue, String data) {
		if (data.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (data.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//	navigate
	public void navigate(String locType, String locValue, String data) {
		driver.get(data);
	}

//	type
	public void type(String locType, String locValue, String data) {
		driver.findElement(LocatorHelper.locate(locType, locValue)).sendKeys(data);
	}

//	click
	public void click(String locType, String locValue, String data) {
		driver.findElement(LocatorHelper.locate(locType, locValue)).click();
	}

//	selectOption
	public void selectOption(String locType, String locValue, String data) {
		WebElement ele = driver.findElement(LocatorHelper.locate(locType, locValue));
		new Select(ele).selectByVisibleText(data);
	}

//	getAlertText
	public void getAlertText(String locType, String locValue, String data) {
		alertText = driver.switchTo().alert().getText();
		System.out.println(alertText);
	}

//	acceptAlert
	public void acceptAlert(String locType, String locValue, String data) {
		driver.switchTo().alert().accept();
	}

//	closeBrowser
	public void closeBrowser(String locType, String locValue, String data) {
		if (driver.getWindowHandles().size() > 1) {
			driver.quit();
		} else {
			driver.close();
		}
	}

	// wait
	public void wait(String locType, String locValue, String data) {
		try {
			Thread.sleep(Long.parseLong(data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	moveToElement
	public void moveToElement(String locType, String locValue, String data) {
		WebElement ele = driver.findElement(LocatorHelper.locate(locType, locValue));
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).build().perform();
	}

//	moveToEleAndClick
	public void moveToEleAndClick(String locType, String locValue, String data) {
		WebElement ele = driver.findElement(LocatorHelper.locate(locType, locValue));
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).click().build().perform();
	}

//	switchToFrame
	public void switchToFrame(String locType, String locValue, String data) {
		WebElement frame = driver.findElement(LocatorHelper.locate(locType, locValue));
		driver.switchTo().frame(frame);
	}

//	switchToDefaultContent
	public void switchToDefaultContent(String locType, String locValue, String data) {
		driver.switchTo().defaultContent();
	}

	// switchToWindow
	public void switchToWindow(String locType, String locValue, String data) {
		List<String> handles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(handles.get(Integer.parseInt(data)));
	}

}
