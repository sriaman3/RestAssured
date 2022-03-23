package selenium4features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumFriendlyLocators {
	
	public static WebDriver driver;
	
	@Test
	public void newLoactors() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com/");
		WebElement element = driver.findElement(RelativeLocator.with(By.tagName("input")).above(By.name("login")));
		System.out.println(element.getAttribute("id"));
		element.sendKeys("aman@gmail.com");
	}

}
