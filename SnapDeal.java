package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 2. Go to Mens Fashion
		driver.findElement(By.xpath("(//span[contains(text(),'Fashion')])[3]")).click();
		//Actions builder = new Actions(driver);
		//builder.moveToElement(Men);
		
		// 3. Go to Sports Shoes
		driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]")).click();
		
		// 4. Get the count of the sports shoes
		String sportsShoe = driver.findElement(By.xpath("//span[@class='category-count']")).getText();
		System.out.println("Count of sports shoe : "+sportsShoe);
		
		// 5. Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
		Thread.sleep(1000);
		// 6. Sort by Low to High
		driver.findElement(By.className("sort-label")).click();
		driver.findElement(By.xpath("//li[@data-sorttype='plth']")).click(); 

		// 7. Check if the items displayed are sorted correctly
		List<WebElement> items = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));	
		List<Integer> price = new ArrayList<Integer>();
		
		for (WebElement shoePrice : items) {
			String text = shoePrice.getText();
			String text1 = text.replaceAll("[^0-9]", "");
			int text2 = Integer.parseInt(text1);
			price.add(text2);
		}
		
		Integer maximumValue = Collections.max(price);
		System.out.println("Maximum value in the list : " + maximumValue);
		Integer lastValue = price.get(price.size()-1);
		System.out.println("Last value in the list : " + lastValue);
		if(maximumValue == lastValue)
			System.out.println("Prices are Sorted");
		else
			System.out.println("Prices are not sorted");
		
		Thread.sleep(1000);
		// 8. Mouse Hover on puma Blue Training shoes - selected the first product
		WebElement shoe = driver.findElement(By.xpath("(//img[@class='product-image wooble'])[1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(shoe).perform();
		
		Thread.sleep(1000);
		// 9. click QuickView button
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		
		// 10. Print the cost and the discount percentage
		String cost = driver.findElement(By.className("payBlkBig")).getText();
		String discount = driver.findElement(By.className("percent-desc")).getText();
		System.out.println("Cost : "+cost+ "Discount : "+discount);
		
		// 11. Take the snapshot of the shoes.
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/Shoe.png");
		FileUtils.copyFile(src, dst);
		
		// 12. Close the current window
		driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();
		
		// 13. Close the main window
		driver.close();
		

	}

}
