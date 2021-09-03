package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub


		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		//1) Open https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//2) Mouseover on Brands and Mouseover on Popular
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		WebElement popular = driver.findElement(By.xpath("//a[text()='Popular']"));
		//WebElement lorealParis = driver.findElement(By.xpath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brand).perform();
		builder.moveToElement(popular).perform();
		
		//3) Click L'Oreal Paris
		driver.findElement(By.xpath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']")).click();

		//4) Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		
		String title = driver.getTitle();
		//System.out.println(title);
		if (title.contains("L'Oreal Paris"))
				{
				System.out.println("Landed in correct page");
				}
		else
			System.out.println("Landed in incorrect page");
		
		//5) Click sort By and select customer top rated
		driver.findElement(By.xpath("//i[@class='fa fa-angle-down']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		
		Thread.sleep(2000);
		
		//6) Click Category and click Shampoo
		driver.findElement(By.xpath("//div[text()='Category']")).click();
		driver.findElement(By.xpath("(//li[@class='filter-list-header  '])[1]")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//label[@for='chk_Shampoo_undefined']")).click();
		
		//7) check whether the Filter is applied with Shampoo
		String filterShampoo = driver.findElement(By.xpath("//ul[@class='pull-left applied-filter-lists']")).getText();
		if (filterShampoo.contains("Shampoo"))
		{
			System.out.println("Filter is applied with Shampoo");
		}
		else
			System.out.println("Filter is not applied with Shampoo");
		
		//8) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//span[contains(text(), 'Oreal Paris Colour Protect Shampoo')]")).click();
		
		
		//9) GO to the new window and select size as 175ml
		Set<String> windowHandlesSet1 = driver.getWindowHandles();
		List<String> windowHandlesList1 = new ArrayList<String>(windowHandlesSet1);
		driver.switchTo().window(windowHandlesList1.get(2));
		System.out.println(driver.getTitle());		
		
		Thread.sleep(2000);
		//select size as 175ml
		driver.findElement(By.xpath("//span[text()='175ml']")).click();
		
		//10) Print the MRP of the product
		String MRP = driver.findElement(By.className("post-card__content-price-offer")).getText();
		String MRP1 = MRP.replaceAll("\\D", "");
		System.out.println("Rs."+MRP1);
		
		//11) Click on ADD to BAG
		driver.findElement(By.xpath("//div[@class='pull-left']//button")).click();
		
		Thread.sleep(1000);
		
		//12) Go to Shopping Bag 
		driver.findElement(By.className("AddBagIcon")).click();
		//driver.findElement(By.xpath("//div[@class='close']")).click();
		
		//13) Print the Grand Total amount
		String grandTotal = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		String grandTotal1 = grandTotal.replaceAll("\\D", "");
		System.out.println("Grand Total : Rs."+grandTotal1);
		Thread.sleep(5000);
		
		//14) Click Proceed
		//driver.findElement(By.xpath("(//span[@class='vernacular-string'])[13]")).click();
		driver.findElement(By.xpath("//span[text()='Proceed']//ancestor::button")).click();
		//driver.findElement(By.xpath("//div[@class='second-col']/button")).click();
		Thread.sleep(2000);
		
		//15) Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		
		//16) Check if this grand total is the same in step 13
		String grandTotal2 = driver.findElement(By.xpath("(//div[@class='value'])[2]")).getText();
		if (grandTotal1.equals(grandTotal2))
		{
			System.out.println("Grand Total is same");
		}
		else
			System.out.println("Grand Total is not same");
		
		//17) Close all windows
		driver.quit();
		 
	}

}
