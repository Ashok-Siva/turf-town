package coupon;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class couponcreation
{
	
    public WebDriver driver;
	
	public Actions a1;
	
	public HSSFWorkbook workbook;
	
    public HSSFSheet sheet;
    
    public HSSFCell cell;
    
    public String pass = "pass";
    
    public String fail = "fail";
	
	public String dev_URL = "http://ec2-13-232-87-232.ap-south-1.compute.amazonaws.com/login";
	
	public String coupon = "http://ec2-13-232-87-232.ap-south-1.compute.amazonaws.com/app/pages/coupon";
	
	@BeforeTest
	  public void beforeTest() throws AWTException 
	{
		
	   	    System.setProperty("webdriver.gecko.driver", "C:\\Users\\ashok\\Downloads\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		    
		    driver= new FirefoxDriver();
		    
		    a1 = new Actions(driver);
			
			driver.manage().window().maximize();
			
			//driver.manage().deleteAllCookies();
			
			driver.get(dev_URL);
			
			Robot robot = new Robot();
			
			for (int i = 0; i < 3; i++)
				{
				   robot.keyPress(KeyEvent.VK_CONTROL);
				   robot.keyPress(KeyEvent.VK_SUBTRACT);
				   robot.keyRelease(KeyEvent.VK_SUBTRACT);
				   robot.keyRelease(KeyEvent.VK_CONTROL);
			    }
	  }
	
	@Test(priority=0)
	  public void login() throws InterruptedException 
	  {
	  		WebElement Email = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/div[2]/div/div/div/div/section[2]/form/div[1]/div/div/div/input"));
	  		
	  		a1.click(Email).sendKeys("akshliver93@gmail.com");
	  		
	  		Thread.sleep(1000);
	  		
	  		WebElement password = driver.findElement( By.xpath("/html/body/div[1]/div/div[2]/main/div[2]/div/div/div/div/section[2]/form/div[2]/div/div/div/input"));
	  		
	  		a1.click(password).sendKeys("kopsupport8");
	  		
	  		Thread.sleep(1000);
	  		
	  		WebElement submit = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/div[2]/div/div/div/div/section[2]/form/div[4]/button/span[1]"));
	  		
	  		a1.click(submit).build().perform();
	  
	  		Thread.sleep(3000);
	  }
	
  @Test(priority=1)
  public void coupon_creation() throws InterruptedException, IOException
  {
	    // Import excel sheet.
	     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\coupon.xls");
	      
	     // Load the file.
	     FileInputStream finput = new FileInputStream(src);
	      
	     // Load he workbook.
	     workbook = new HSSFWorkbook(finput);
	      
	     // Load the sheet in which data is stored.
	     sheet= workbook.getSheet("coupon");
	     
	     for(int i=1; i<=sheet.getLastRowNum(); i++)
	     {
	    	 sheet= workbook.getSheet("coupon");
	    	 Thread.sleep(2000);
	    	 driver.navigate().to(coupon);
	   	     Thread.sleep(1000);
	   	     WebElement coupon_btn = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/button"));
	   	     a1.click(coupon_btn).build().perform();
	    	 
	    	 cell = sheet.getRow(i).getCell(0);
	         cell.setCellType(CellType.STRING);
	  WebElement coupon_title = driver.findElement(By.xpath("//*[@id=\"coupon_title\"]"));
	  coupon_title.clear();
	  Thread.sleep(500);
	  coupon_title.sendKeys(cell.getStringCellValue());
	  System.out.println("coupon title is enetered");
	  
	  cell = sheet.getRow(i).getCell(1);
      cell.setCellType(CellType.STRING);	
	  WebElement discount_or_flat = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div/fieldset[1]/div/label["+cell.getStringCellValue()+"]/span[2]"));
	  discount_or_flat.click();
	  
	  cell = sheet.getRow(i).getCell(2);
      cell.setCellType(CellType.STRING);
	  WebElement amount_or_percentage = driver.findElement(By.xpath("//*[@id=\"adornment-amount\"]"));
	  amount_or_percentage.clear();
	  Thread.sleep(500);
	  amount_or_percentage.sendKeys(cell.getStringCellValue());
	  System.out.println("amount is enetered");
	  
	  cell = sheet.getRow(i).getCell(3);
      cell.setCellType(CellType.STRING);
	  WebElement coupon_description = driver.findElement(By.xpath("//*[@id=\"coupon_description\"]"));
	  coupon_description.clear();
	  Thread.sleep(500);
	  coupon_description.sendKeys(cell.getStringCellValue());
	  System.out.println("coupon description is enetered");
	  
	  cell = sheet.getRow(i).getCell(4);
      cell.setCellType(CellType.STRING);
	  WebElement coupon_code = driver.findElement(By.xpath("//*[@id=\"coupon_code\"]"));
	  coupon_code.clear();
	  Thread.sleep(500);
	  coupon_code.sendKeys(cell.getStringCellValue());
	  System.out.println("coupon code is enetered");
	  
	  cell = sheet.getRow(i).getCell(5);
      cell.setCellType(CellType.STRING);
	  WebElement venues = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
	  venues.clear();
      a1.click(venues).sendKeys(cell.getStringCellValue()).sendKeys(Keys.ENTER).build().perform();
	  Thread.sleep(500);
	  System.out.println("venue list is selected");
	  
	  cell = sheet.getRow(i).getCell(6);
      cell.setCellType(CellType.STRING);
	  WebElement events = driver.findElement(By.xpath("//*[@id=\"react-select-3-input\"]"));
	  events.clear();
	  a1.click(events).sendKeys(cell.getStringCellValue()).sendKeys(Keys.ENTER).build().perform();
	  Thread.sleep(500);
	  System.out.println("event list is selected");
	  
	  WebElement visible = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[4]/div[2]/span/span[1]/span[1]/input"));
	  visible.click();
	  
	  cell = sheet.getRow(i).getCell(7);
      cell.setCellType(CellType.STRING);
	  WebElement usage_limit = driver.findElement(By.xpath("//*[@id=\"usage_limit\"]"));
	  usage_limit.clear();
	  Thread.sleep(500);
	  usage_limit.sendKeys(cell.getStringCellValue());
	  System.out.println("usage limit is entered");
	  
	  WebElement Start_Date = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[2]/div/div/div/div/div[2]"));
	  Start_Date.click();
	  Thread.sleep(500);
	  cell = sheet.getRow(i).getCell(8);
      cell.setCellType(CellType.STRING);
	  WebElement start_day = driver.findElement(By.xpath("//*[@class=\"jss261\" and contains(text(),'"+cell.getStringCellValue()+"')]"));
	  start_day.click();
	  Thread.sleep(500);
	  WebElement OK = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/button[2]"));
	  OK.click();
	  Thread.sleep(500);
	  
	  WebElement End_Date = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[3]/div/div/div/div/div[2]"));
	  End_Date.click();
	  Thread.sleep(500);
	  cell = sheet.getRow(i).getCell(9);
      cell.setCellType(CellType.STRING);
	  WebElement end_day = driver.findElement(By.xpath("//*[@class=\"jss261\" and contains(text(),'"+cell.getStringCellValue()+"')]"));
	  end_day.click();
	  Thread.sleep(500);
	  WebElement OK1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/button[2]"));
	  OK1.click();
	  Thread.sleep(500);
	  
	  cell = sheet.getRow(i).getCell(10);
      cell.setCellType(CellType.STRING);
	  WebElement discount_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[5]/div/div/div/div"));
	  discount_type.click();
	  WebElement discouttype_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	  discouttype_list.click();
	  Thread.sleep(500);
	  System.out.println("Type is selected");
	  
	  if(!driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[7]/div[1]/div[2]/div/div/div")).isEmpty() && !driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[7]/div[2]/div[2]/div/div/div")).isEmpty())
	  {
		
	/*  
	  cell = sheet.getRow(i).getCell(11);
      cell.setCellType(CellType.STRING);
	  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[7]/div[1]/div[2]/div/div/div"));
	  start_time.click();
	  WebElement startime_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	  startime_list.click();
	  //Thread.sleep(500);
	  System.out.println("Start time is entered");
	  
	  
	  cell = sheet.getRow(i).getCell(12);
      cell.setCellType(CellType.STRING);
	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[7]/div[2]/div[2]"));
	  end_time.click();
	  WebElement endtime_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	  endtime_list.click();
	  //Thread.sleep(500);
	  System.out.println("End time is selected");
	  */
	  }
	  else
	  {
		  boolean Mon = isClickable(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[6]/div[1]/div")));
		  boolean Tue = isClickable(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[6]/div[2]/div")));
		  boolean Wed = isClickable(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[6]/div[3]/div")));
		  boolean Thu = isClickable(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[6]/div[4]/div")));
		  boolean Fri = isClickable(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[6]/div[5]/div")));
		  boolean Sat = isClickable(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[6]/div[6]/div")));
		  boolean Sun = isClickable(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[6]/div[7]/div")));
		  
		  if(Mon==true || Tue==true || Wed==true || Thu==true || Fri==true || Sat==true || Sun==true)
		  {
			  WebElement set_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[6]/div[8]/button"));
			  set_time.click();
			  
			  cell = sheet.getRow(i).getCell(11);
		      cell.setCellType(CellType.STRING);
			  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[7]/div[2]/div/div/div"));
			  start_time.click();
			  WebElement startime_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			  startime_list.click();
			  //Thread.sleep(500);
			  System.out.println("Start time is entered");
			  /*
			  cell = sheet.getRow(i).getCell(12);
		      cell.setCellType(CellType.STRING);
			  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[7]/div[4]/div/div/div"));
			  end_time.click();
			  WebElement endtime_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			  endtime_list.click();
			  //Thread.sleep(500);
			  System.out.println("End time is selected");
			  */
			  WebElement block_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[6]/div[8]/button/span[1]"));
			  block_time.click();
			  
		  }
	  }
	  
	  WebElement Next_btn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[2]/button[2]/span[1]"));
	  Next_btn.click();
	  Thread.sleep(2000);
	  System.out.println("Next button is clicked");
	  
	  boolean coupontitle_error = driver.findElements(By.xpath("//*[@id=\"coupon_title-helper-text\"]")).size() != 0;
	  boolean coupondescription_error = driver.findElements(By.xpath("//*[@id=\"coupon_description-helper-text\"]")).size() != 0 ;
	  boolean couponcode_error = driver.findElements(By.xpath("//*[@id=\"coupon_code-helper-text\"]")).size() != 0 ;
	  boolean venueorevent_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[1]/div[5]/div/span")).size() != 0 ;
	  boolean discounttype_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/span[1]")).size() != 0 ;
	  boolean rate_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[1]/div/fieldset[2]/div/span")).size() != 0;
	  boolean type_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[5]/span")).size() != 0 ;
	  boolean customday_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[1]/div[2]/span[3]")).size() !=0;
	  boolean success_msg = driver.findElements(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[4]/div/div/div[1]")).size() != 0;
	  if(coupontitle_error==true || coupondescription_error==true || couponcode_error==true || venueorevent_error==true || discounttype_error==true || type_error == true || rate_error==true || customday_error == true || success_msg == false)
	  {
		  screenCapture();
		    sheet.getRow(i).createCell(13).setCellValue(fail);  
			System.out.println("Error throws in info page...Kindly refer screenshot");   
			// Specify the file in which data needs to be written.
	        FileOutputStream fileOutput = new FileOutputStream(src);
	        // finally write content
	        workbook.write(fileOutput);
	         // close the file
	        fileOutput.close();
	        
		   }
		  else
		  {
			  sheet.getRow(i).createCell(13).setCellValue(pass);
			  System.out.println("Doesn't throw any alert in this page");
			    // Specify the file in which data needs to be written.
		        FileOutputStream fileOutput = new FileOutputStream(src);
		        // finally write content
		        workbook.write(fileOutput);
		         // close the file
		        fileOutput.close();
		        System.out.println("file is closed");      
		  } 
	     }
  }
  
  
  //@Test(priority=2)
  public void search() throws IOException, InterruptedException
  {
       	// Import excel sheet.
	     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\coupon.xls");
	      
	     // Load the file.
	     FileInputStream finput = new FileInputStream(src);
	      
	     // Load he workbook.
	     workbook = new HSSFWorkbook(finput);
	      
	     // Load the sheet in which data is stored.
	     sheet= workbook.getSheet("search");
	     
	     for(int i=1; i<=sheet.getLastRowNum(); i++)
	     {
	    	 Thread.sleep(2000);
	    	 
	    	 driver.navigate().to(coupon);
	   	     Thread.sleep(500);
	   	  
	   	     WebElement search_icon = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[2]/div/div/div[1]/div[2]/button[1]"));
	   	     search_icon.click();
	   	     Thread.sleep(1000);
	    	 
	    	 cell = sheet.getRow(i).getCell(0);
	         cell.setCellType(CellType.STRING);
	         WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[2]/div/div/div[1]/div[1]/div/div/div/input"));
	         search.clear();
	         search.sendKeys(cell.getStringCellValue());
	         Thread.sleep(1000);
	  
	  boolean no_coupon = driver.findElements(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[2]/div/div/div[3]/table/tbody/tr/td[2]/h3")).size()!=0;
	  
	  if(no_coupon==true)
	  {
		  screenCapture();
		    System.out.println("No data present in the list");
		    sheet.getRow(i).createCell(6).setCellValue("No");
		    // Specify the file in which data needs to be written.
	        FileOutputStream fileOutput = new FileOutputStream(src);
	        // finally write content
	        workbook.write(fileOutput);
	         // close the file
	        fileOutput.close();
	        System.out.println("file is closed");
	  }
	  else
	  {
		    System.out.println("No data present in the list");
		    sheet.getRow(i).createCell(6).setCellValue("Yes");
		    // Specify the file in which data needs to be written.
	        FileOutputStream fileOutput = new FileOutputStream(src);
	        // finally write content
	        workbook.write(fileOutput);
	         // close the file
	        fileOutput.close();
	        System.out.println("file is closed");
		  
	  WebElement list = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[2]/div/div/div[3]/table/tbody/tr[1]/td[16]/div/button"));
	  list.click();
	  Thread.sleep(1000);
	  
	  cell = sheet.getRow(i).getCell(1);
      cell.setCellType(CellType.STRING);
	  WebElement Actions = driver.findElement(By.xpath("/html/body/div[3]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	  Actions.click();
	  Thread.sleep(1000);
	  
	  
	  cell = sheet.getRow(i).getCell(1);
      cell.setCellType(CellType.STRING);
      String data = cell.getStringCellValue();
      int num = Integer.parseInt(data);
      
	  if(num == 1)
	  {
		  //Edit coupon
		  coupon_creation();
	  }
	  else if (num == 2) 
	  {
		  	// Delete coupon
		     cell = sheet.getRow(i).getCell(2);
	         cell.setCellType(CellType.STRING);
	         WebElement delete_yes_or_no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[3]/button["+cell.getStringCellValue()+"]/span[1]"));
	         delete_yes_or_no.click();
	         Thread.sleep(2000);
	  
	         boolean alert = driver.findElements(By.xpath("//*[@id=\"message-id\"]")).size()>0;
	  
	         if(alert==true)
	         {
	        	 screenCapture();
	        	 sheet.getRow(i).createCell(4).setCellValue("Deleted successfully");
	        	 System.out.println("Deleted successfully");
	        	 // 		Specify the file in which data needs to be written.
	        	 FileOutputStream fileOutput1 = new FileOutputStream(src);
	        	 // finally write content
	        	 workbook.write(fileOutput1);
	        	 // close the file
	        	 fileOutput1.close();
	        	 System.out.println("file is closed");
	         }
	         else
	         {
	        	 sheet.getRow(i).createCell(4).setCellValue("Deleted failed");
	        	 System.out.println("Deleted failed");
	        	 // Specify the file in which data needs to be written.
	        	 FileOutputStream fileOutput2 = new FileOutputStream(src);
	        	 // finally write content
	        	 workbook.write(fileOutput2);
	        	 // close the file
	        	 fileOutput2.close();
	        	 System.out.println("file is closed");
	         }
	  }
	  else 
	  {
		//Disable_coupon();
		  cell = sheet.getRow(i).getCell(2);
	         cell.setCellType(CellType.STRING);
	         WebElement disable_yes_or_no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[3]/button["+cell.getStringCellValue()+"]/span[1]"));
	         disable_yes_or_no.click();
	         Thread.sleep(2000);
	  
	         boolean alert = driver.findElements(By.xpath("//*[@id=\"message-id\"]")).size()>0;
	  
	         if(alert==true)
	         {
	        	 screenCapture();
	        	 sheet.getRow(i).createCell(5).setCellValue("Disable successfull");
	        	 System.out.println("Disable successfully");
	        	 // Specify the file in which data needs to be written.
	        	 FileOutputStream fileOutput3 = new FileOutputStream(src);
	        	 // finally write content
	        	 workbook.write(fileOutput3);
	        	 // close the file
	        	 fileOutput3.close();
	        	 System.out.println("file is closed");
	         }
	         else
	         {
	        	 sheet.getRow(i).createCell(5).setCellValue("Disable failed");
	        	 System.out.println("Disable failed");
	        	 // Specify the file in which data needs to be written.
	        	 FileOutputStream fileOutput4 = new FileOutputStream(src);
	        	 // finally write content
	        	 workbook.write(fileOutput4);
	        	 // close the file
	        	 fileOutput4.close();
	        	 System.out.println("file is closed");
	         }
	  }
      }
   }
  }
  
  public void screenCapture() throws IOException{
	  
	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  File screenshotName = new File ("C:\\Users\\ashok\\eclipse-workspace\\New_Turftown\\screenshots\\Error_screenshot.png");
	  FileUtils.copyFile(scrFile, screenshotName);
	  Reporter.log("<br><img src='"+screenshotName+"' height='300' width='300'/><br>");  
	 }
  
  public boolean isClickable(WebElement webe)      
  {
      try
      {
          WebDriverWait wait = new WebDriverWait(driver, 5);
          wait.until(ExpectedConditions.elementToBeClickable(webe)).click();
          return true;
      }
      catch (Exception e)
      {
          return false;
      }
  }

  //@AfterTest
  public void afterTest() 
  {
	  WebElement logout_btn = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/header/div/div[2]/div/button[2]"));
	  logout_btn.click();
	  
	  driver.close();

  }

}
