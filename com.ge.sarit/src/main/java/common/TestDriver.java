package common;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import common.Enums.*;

public class TestDriver {

	private WebDriver driver;
	private JavascriptExecutor js;
	private static final int TIMEOUT_TO_FIND_ELEMENT = 5;
	private static final int TIMEOUT_ELEMENT_TO_BE_CLICKABLE = 5;

	public WebDriver getDriver() {
		return driver;
	}

	public boolean startWebSession(eBrowserType browser, boolean isWindows) {
		boolean result = false;
		try {

			switch (browser) {
			case Chrome:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();// options);
				driver.manage().window().maximize();
				result = true;
				break;
			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed to create " + browser + " driver");
			return result;
		}
		return result;
	}

	public boolean getWebsite(String url) {
		js = (JavascriptExecutor) driver;
		try {
			driver.get(url);// "https://material.io/components/data-tables");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public WebElement fluentWaitWebElement(final By locator) {

		WebElement element;
		try {

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(TIMEOUT_TO_FIND_ELEMENT)).pollingEvery(Duration.ofSeconds(1))
					.ignoring(org.openqa.selenium.NoSuchElementException.class);
			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(locator);
				}
			});
			if (!element.isDisplayed()) {
				js.executeScript("arguments[0].scrollIntoView();", element);
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return element;
	}

	public List<WebElement> fluentWaitWebElements(final By locator) {

		List<WebElement> elements;

		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(TIMEOUT_TO_FIND_ELEMENT)).pollingEvery(Duration.ofSeconds(1))
					.ignoring(org.openqa.selenium.NoSuchElementException.class);
			elements = (List<WebElement>) wait.until(new Function<WebDriver, List<WebElement>>() {
				public List<WebElement> apply(WebDriver driver) {
					return driver.findElements(locator);
				}
			});
			if (!elements.get(0).isDisplayed()) {
				js.executeScript("arguments[0].scrollIntoView();", elements.get(0));
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return elements;
	}

	public boolean onClick(By by) {
		// get the element
		WebElement webElement = fluentWaitWebElement(by);
		if (webElement == null) {
			return false;
		}
		// click and wait until element be clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_ELEMENT_TO_BE_CLICKABLE));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(webElement));// presenceOfElementLocated(by));
			webElement.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<WebElement> getChildrensOfWebElement(WebElement parentElement, By byForChild) {
		List<WebElement> listOfElements = null;
		// if the elements not found in 10 seconds return null
		driver.manage().timeouts().implicitlyWait(TIMEOUT_TO_FIND_ELEMENT, TimeUnit.SECONDS);
		listOfElements = parentElement.findElements(byForChild);
		return listOfElements;
	}

	/**
	 * switch the content by locator or to default content
	 * @param iframe - the locator to switch by. if null switch to default
	 */
	public boolean switchToContent(By iframe) {
		if (iframe == null)
			driver.switchTo().defaultContent();
		else {
			WebElement iFramElement;
			iFramElement = fluentWaitWebElement(iframe);
			if (iFramElement == null)
				return false;
			driver.switchTo().frame(iFramElement);
		}
		return true;
	}
}
