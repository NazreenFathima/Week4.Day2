package week4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MouseOver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/mouseOver.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Mouse Hover the TestLEaf course and print all the following links
		Actions builder = new Actions(driver);
		WebElement mouseOver = driver.findElement(By.xpath("//a[text()='TestLeaf Courses']"));
		builder.moveToElement(mouseOver).perform();
		String seleniumLink = driver.findElement(By.xpath("//a[text()='Selenium']")).getAttribute("href");
		String RPALink = driver.findElement(By.xpath("//a[text()='RPA']")).getAttribute("href");
		String webServicesLink = driver.findElement(By.xpath("//a[text()='WebServices']")).getAttribute("href");
		System.out.println("Selenium Link :"+seleniumLink);
		System.out.println("RPALink : "+RPALink);
		System.out.println("WebServices Link : "+webServicesLink);
		
		//Mouse Hover the TestLEaf course and click on any course and handle the alert
		Actions builder1 = new Actions(driver);
		WebElement mouseOver1 = driver.findElement(By.xpath("//a[text()='TestLeaf Courses']"));
		builder1.moveToElement(mouseOver1).perform();
		driver.findElement(By.xpath("//a[text()='RPA']")).click();
		driver.switchTo().alert().accept();
	
		
		
		
	}

}
