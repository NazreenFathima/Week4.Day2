package week4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selectable1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/selectable");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement frames = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		driver.switchTo().frame(frames);
		WebElement item2 = driver.findElement(By.xpath("//li[text()='Item 2']"));
		WebElement item6 = driver.findElement(By.xpath("//li[text()='Item 6']"));
		Actions builder = new Actions(driver);
		builder.clickAndHold(item2).moveToElement(item6).release().perform();

	}

}
