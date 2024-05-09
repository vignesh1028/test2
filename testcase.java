package dealsdray_test01;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class testcase {
	public static WebDriver driver;

	public static void takeScreenshot() throws IOException {
		try {
		    TakesScreenshot scrShot = ((TakesScreenshot) driver);
		    File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		    File destFile = new File(System.getProperty("user.dir") + "\\Output\\screenshot.png");
		    FileUtils.copyFile(srcFile, destFile);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.dealsdray.com/");
		driver.manage().window().maximize();

		WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
		username.sendKeys("prexo.mis@dealsdray.com");
		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("prexo.mis@dealsdray.com");
		WebElement login = driver.findElement(By.xpath("//button[@type='submit']"));
		login.click();
		Thread.sleep(3000);

		WebElement orders = driver.findElement(By.xpath("//p[text()='Orders']"));
		orders.click();
		Thread.sleep(2000);

		WebElement addbulkorders = driver.findElement(By.xpath("//button[text()='Add Bulk Orders']"));
		addbulkorders.click();
		Thread.sleep(2000);

		WebElement uploadfile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadfile.sendKeys(System.getProperty("user.dir") + "\\Data\\demo-data.xlsx");
		Thread.sleep(4000);

		WebElement importButton = driver.findElement(By.xpath("//button[text()='Import']"));
		importButton.click();
		Thread.sleep(5000);

		WebElement validateButton = driver.findElement(By.xpath("//button[text()='Validate Data']"));
		validateButton.click();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();

		
		Actions actions = new Actions(driver);
		WebElement elementToScrollTo = driver.findElement(By.xpath("//th[text()='S.NO']"));
		actions.moveToElement(elementToScrollTo).perform();
		Thread.sleep(3000);
		
		takeScreenshot();

		driver.close();

	}

}
