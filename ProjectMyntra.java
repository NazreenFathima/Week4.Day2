package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProjectMyntra {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		//1) Open https://www.myntra.com/
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//2) Mouse hover on MeN 
		WebElement Men = driver.findElement(By.xpath("(//a[text()='Men'])[1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(Men).perform();
		
		//3) Click Jackets
		driver.findElement(By.xpath("(//a[text()='Jackets'])[1]")).click();
		
		//4) Find the total count of item
		String totalCount = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		String replaceAll3 = totalCount.replaceAll("[^0-9]", "");
		int totalCount1 = Integer.parseInt(replaceAll3);
		System.out.println("Total count of items :" +totalCount1);
		
		//5) Validate the sum of categories count matches
		String count1 = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
		String replaceAll1 = count1.replaceAll("[^0-9]", "");
		int parseInt1 = Integer.parseInt(replaceAll1);
		System.out.println("Count of Jackets : "+parseInt1);
		
		String count2 = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		String replaceAll2 = count2.replaceAll("[^0-9]", "");
		int parseInt2 = Integer.parseInt(replaceAll2);
		System.out.println("Count of Rain Jackets :"+parseInt2);
		int sum =parseInt1+parseInt2;
		System.out.println("Count of Jackets and Rain Jackets : "+sum);
		
		if (totalCount1==sum)
		{
			System.out.println("The total count and category count matches");
		}
		else
			System.out.println("The total count and category count doesnt match");
		
		//6) Check jackets
		driver.findElement(By.className("common-checkboxIndicator")).click();
		
		//7) Click + More option under BRAND
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		
		Thread.sleep(2000);
		//8) Type Duke and click checkbox
		driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("Duke");
		//driver.findElement(By.xpath("((//label[@class=' common-customCheckbox']//div)[1]")).click();
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[11]")).click();
		
		//9) Close the pop-up x
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		
		Thread.sleep(2000);
		//10) Confirm all the Coats are of brand Duke
		   // Hint : use List 
		List<WebElement> coats = driver.findElements(By.tagName("h3"));
		for (int i=0; i<coats.size()-1; i++)
		{
			String text = coats.get(i).getText();
			if(text.equals("Duke"))
			{
				System.out.println("Product is Duke");
			}
			else
			{
				System.out.println("Product is not Duke");
			}
		}
		
		//11) Sort by Better Discount
		WebElement betterDiscount = driver.findElement(By.xpath("//div[@class='sort-sortBy']"));
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(betterDiscount).perform();
		driver.findElement(By.xpath("(//label[@class='sort-label '])[3]")).click();
		
		Thread.sleep(2000);
		//12) Find the price of first displayed item
		String price = driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText();
		System.out.println("Price of first product :" +price);
		System.out.println(driver.getTitle());
		
		//Click on the first product
		driver.findElement(By.xpath("//div[@class='product-productMetaInfo']")).click();
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		System.out.println(driver.getTitle());
		
		Thread.sleep(2000);
		//13) Take a screen shot
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/MenDukeKJacket.png");
		FileUtils.copyFile(src, dst);
		
		//14) Click on WishList Now
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		
		//15) Close Browser
		driver.quit();
	}

}
