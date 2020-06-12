package venue;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class venuecreation {
  
    public WebDriver driver;
	
	public Actions a1;
	
	public HSSFWorkbook workbook;
	
    public HSSFSheet sheet;
    
    public HSSFCell cell;
    
    public String pass = "pass";
    
    public String fail = "fail";
	
	public String dev_URL = "http://ec2-13-232-87-232.ap-south-1.compute.amazonaws.com/login";
	
	public String venue_URL = "http://ec2-13-232-87-232.ap-south-1.compute.amazonaws.com/app/pages/venue"; 

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
		
		for (int i = 0; i < 2; i++) {
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
  public void info_page() throws IOException, InterruptedException, AWTException
  {
	     // Import excel sheet.
	     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\venue.xls");
	      
	     // Load the file.
	     FileInputStream finput = new FileInputStream(src);
	      
	     // Load he workbook.
	     workbook = new HSSFWorkbook(finput);
	      
	     // Load the sheet in which data is stored.
	     sheet= workbook.getSheet("info");
	     
	     
	     for(int i=1; i<=sheet.getLastRowNum(); i++)
	     {
	    	 
	    	 driver.navigate().to(venue_URL);
	  
	    	 WebElement venue_button = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/button"));
	    	 venue_button.click();
	  
	    	 cell = sheet.getRow(i).getCell(0);
	    	 cell.setCellType(CellType.STRING);
	    	 WebElement option = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div["+cell.getStringCellValue()+"]/div/div[1]"));
	    	 option.click();
	    	 
	    	 WebElement Next_btn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]"));
	    	 Next_btn.click();
	    	 
	    	 cell = sheet.getRow(i).getCell(1);
	    	 cell.setCellType(CellType.STRING);
	    	 WebElement name = driver.findElement(By.xpath("//*[@id=\"name\"]"));
	    	 name.sendKeys(cell.getStringCellValue());
	    	 
	    	 cell = sheet.getRow(i).getCell(2);
	    	 cell.setCellType(CellType.STRING);
	    	 WebElement address = driver.findElement(By.xpath("//*[@id=\"address\"]"));
	    	 address.sendKeys(cell.getStringCellValue());
	    	 
	    	 cell = sheet.getRow(i).getCell(3);
	    	 cell.setCellType(CellType.STRING);
	    	 WebElement pincode = driver.findElement(By.xpath("//*[@id=\"pincode\"]"));
	    	 pincode.sendKeys(cell.getStringCellValue());
	    	 
	    	 cell = sheet.getRow(i).getCell(4);
	    	 cell.setCellType(CellType.STRING);
	    	 WebElement area = driver.findElement(By.xpath("//*[@id=\"area\"]"));
	    	 area.sendKeys(cell.getStringCellValue());
	    	 
	    	 cell = sheet.getRow(i).getCell(5);
	    	 cell.setCellType(CellType.STRING);
	    	 WebElement phone_no = driver.findElement(By.xpath("//*[@id=\"phone\"]"));
	    	 phone_no.sendKeys(cell.getStringCellValue());
	    	 
	    	 cell = sheet.getRow(i).getCell(6);
	    	 cell.setCellType(CellType.STRING);
	    	 WebElement lat = driver.findElement(By.xpath("//*[@id=\"Lat\"]"));
	    	 lat.sendKeys(cell.getStringCellValue());
	    	 
	    	 cell = sheet.getRow(i).getCell(7);
	    	 cell.setCellType(CellType.STRING);
	    	 WebElement lon = driver.findElement(By.xpath("//*[@id=\"long\"]"));
	    	 lon.sendKeys(cell.getStringCellValue());
	    	 
	    	 cell = sheet.getRow(i).getCell(8);
	    	 cell.setCellType(CellType.STRING);
	    	 WebElement profile_pic = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div[3]/label"));
	    	 a1.click(profile_pic).build().perform();
	    	 Thread.sleep(10000);
	    	 uploadFile(cell.getStringCellValue());
	    	 
	    	 cell = sheet.getRow(i).getCell(9);
	    	 cell.setCellType(CellType.STRING);
	    	 String a = cell.getStringCellValue();
	    	 int no_of_images = Integer.parseInt(a);
	    	 
	    	 if(no_of_images==1)
	    	 {
	    	 cell = sheet.getRow(i).getCell(10);
	    	 cell.setCellType(CellType.STRING);
	    	 WebElement cover_pic = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
	    	 a1.click(cover_pic).build().perform();
	    	 Thread.sleep(10000);
	    	 uploadFile(cell.getStringCellValue());
	    	 }
	    	 else if (no_of_images==2)
	    	 {
	    		 cell = sheet.getRow(i).getCell(10);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic1).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
		    	 
		    	 cell = sheet.getRow(i).getCell(11);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic2 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic2).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
			 }
	    	 else if (no_of_images==3) 
	    	 {
	    		 cell = sheet.getRow(i).getCell(10);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic1).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
		    	 
		    	 cell = sheet.getRow(i).getCell(11);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic2 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic2).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
		    	 
		    	 cell = sheet.getRow(i).getCell(12);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic3 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic3).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
			 }
	    	 else if (no_of_images==4) 
	    	 {
	    		 cell = sheet.getRow(i).getCell(10);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic1).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
		    	 
		    	 cell = sheet.getRow(i).getCell(11);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic2 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic2).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
		    	 
		    	 cell = sheet.getRow(i).getCell(12);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic3 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic3).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
		    	 
		    	 cell = sheet.getRow(i).getCell(13);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic4 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic4).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
			 }
	    	 else 
	    	 {
	    		 cell = sheet.getRow(i).getCell(10);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic1).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
		    	 
		    	 cell = sheet.getRow(i).getCell(11);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic2 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic2).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
		    	 
		    	 cell = sheet.getRow(i).getCell(12);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic3 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic3).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
		    	 
		    	 cell = sheet.getRow(i).getCell(13);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic4 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic4).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
		    	 
		    	 cell = sheet.getRow(i).getCell(14);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement cover_pic5 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/label"));
		    	 a1.click(cover_pic5).build().perform();
		    	 Thread.sleep(10000);
		    	 uploadFile(cell.getStringCellValue());
			 }
	    	 WebElement Next_btn2 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]"));
	    	 Next_btn2.click();
	    	 Thread.sleep(5000);
	    	 
	    	 boolean name_error = driver.findElements(By.xpath("//*[@id=\"name-helper-text\"]")).size()!=0;
	    	 boolean address_error = driver.findElements(By.xpath("//*[@id=\"address-helper-text\"]")).size()!=0;
	    	 boolean pincode_error = driver.findElements(By.xpath("//*[@id=\"pincode-helper-text\"]")).size()!=0;
	    	 boolean area_error = driver.findElements(By.xpath("//*[@id=\"area-helper-text\"]")).size()!=0;
	    	 boolean phone_error = driver.findElements(By.xpath("//*[@id=\"phone-helper-text\"]")).size()!=0;
	    	 boolean lat_error = driver.findElements(By.xpath("//*[@id=\"Lat-helper-text\"]")).size()!=0;
	    	 boolean lon_error = driver.findElements(By.xpath("//*[@id=\"long-helper-text\"]")).size()!=0;
	    	 
	    	 if(name_error==true || address_error==true || pincode_error==true || area_error==true || phone_error==true || lat_error==true || lon_error==true)
	    	 {
	   		  screenCapture();
	   		    sheet.getRow(i).createCell(15).setCellValue(fail);  
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
	   			  sheet.getRow(i).createCell(15).setCellValue(pass);
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
  public void football_gametype() throws IOException, InterruptedException
  {
	     // Import excel sheet.
	     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\venue.xls");
	      
	     // Load the file.
	     FileInputStream finput = new FileInputStream(src);
	      
	     // Load he workbook.
	     workbook = new HSSFWorkbook(finput);
	      
	     // Load the sheet in which data is stored.
	     sheet= workbook.getSheet("fbgametype");
	     
	     
	     for(int i=1; i<=sheet.getLastRowNum(); i++)
	     {
	    	 cell = sheet.getRow(i).getCell(0);
	    	 cell.setCellType(CellType.STRING);
	    	 String sec = cell.getStringCellValue();
	    	 int yes = Integer.parseInt(sec);
	    	 if(yes==1)
	    	 {
	    		 WebElement secondary = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[1]/div[2]/span/span[1]/span[1]/input"));
	    		 secondary.click();
	    		 cell = sheet.getRow(i).getCell(1);
		    	 cell.setCellType(CellType.STRING);
	    		 WebElement venues = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div/div/div[1]/div[1]"));
	    		 Thread.sleep(500);
	    		 a1.click(venues).sendKeys(cell.getStringCellValue()).sendKeys(Keys.ENTER).build().perform();
				 Thread.sleep(1000);
				 System.out.println("venue is selected");
	    	 }	
	    	 else if(yes==2)
	    	 {
	    		 System.out.println("Secondary is not selected");
	    	 }
	    	 
	    	 //convertable
	    	 cell = sheet.getRow(i).getCell(2);
	    	 cell.setCellType(CellType.STRING);
	    	 String con = cell.getStringCellValue();
	    	 int yes1 = Integer.parseInt(con);
	   if(yes1==1)
	    	 {
	    		 WebElement convertable = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div[2]/span/span[1]/span[1]/input"));
	    		 convertable.click();
	    		 Thread.sleep(500);
	    		 
	    		 //9-A-SIDE
	    		 cell = sheet.getRow(i).getCell(3);
		    	 cell.setCellType(CellType.STRING);
		    	 String nine_side = cell.getStringCellValue();
		    	 int yes2 = Integer.parseInt(nine_side);
		    	 if(yes2==1)
		    	 {
	    		 WebElement nine_a_side = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[2]/span/span[1]/span[1]/input"));
	    		 nine_a_side.click();
	    		 Thread.sleep(500);
	    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[3]/div/div/div"));
	    		 no.click();
	    		 Thread.sleep(500);
	    		 cell = sheet.getRow(i).getCell(4);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	 list.click();
		    	 Thread.sleep(500);
		    	 System.out.println("9-a-side is selected");
		    	 }
		    	 else
		    	 {
		    		 System.out.println("9-A-SIDE is not selected");
		    	 }
		    	 
		    	 //7-A-SIDE
		    	 cell = sheet.getRow(i).getCell(6);
		    	 cell.setCellType(CellType.STRING);
		    	 String seven_side = cell.getStringCellValue();
		    	 int yes3 = Integer.parseInt(seven_side);
		    	 if(yes3==1)
		    	 {
		         WebElement seven_a_side = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[3]/div[2]/span/span[1]/span[1]/input"));
	    		 seven_a_side.click();
	    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[3]/div[3]/div/div/div"));
	    		 no.click();
	    		 cell = sheet.getRow(i).getCell(7);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	 list.click();
		    	 Thread.sleep(500);
		    	 System.out.println("7-a-side is selected");
		    	 
		    	 }
		    	 else
		    	 {
		    		 System.out.println("7-A-SIDE is not selected");
		    	 }
		    	 
		    	 //5-A-SIDE
		    	 cell = sheet.getRow(i).getCell(9);
		    	 cell.setCellType(CellType.STRING);
		    	 String five_side = cell.getStringCellValue();
		    	 int yes4 = Integer.parseInt(five_side);
		    	 if(yes4==1)
		    	 {
	    		 WebElement five_a_side = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[4]/div[2]/span/span[1]/span[1]/input"));
	    		 five_a_side.click();
	    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[4]/div[3]/div/div/div"));
	    		 no.click();
	    		 cell = sheet.getRow(i).getCell(10);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	 list.click();
		    	 Thread.sleep(500);
		    	 System.out.println("5-a-side is selected");
		    	 }
		    	 else
		    	 {
		    		 System.out.println("5-A-SIDE is not selected");
		    	 }
		    	 
		    	 //Ratio 9,7,5
		    	 if(yes2==1 && yes3==1 && yes4==1)
		    	 {
		    		System.out.println("9,7,5 ratio loop");
		    		 WebElement five_side_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[2]/div/div/div"));
			    	 five_side_ratio.click();
			    	 Thread.sleep(500);
			    	 cell = sheet.getRow(i).getCell(11);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement ratio_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 ratio_list.click();
			    	 Thread.sleep(500);
		    		 
		    		 WebElement seven_side_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[3]/div[2]/div/div/div"));
			    	 seven_side_ratio.click();
			    	 Thread.sleep(500);
			    	 cell = sheet.getRow(i).getCell(8);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement ratio_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 ratio_list1.click();
			    	 Thread.sleep(500);
		    		 
		    		 WebElement nine_side_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[4]/div[2]/div/div/div"));
			    	 nine_side_ratio.click();
			    	 Thread.sleep(500);
			    	 cell = sheet.getRow(i).getCell(5);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement ratio_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 ratio_list2.click();
			    	 Thread.sleep(500); 
			    	
			    	//outs 
		    		 cell = sheet.getRow(i).getCell(15);
		    	  	 cell.setCellType(CellType.STRING);
		    	  	 String out = cell.getStringCellValue();
		    	  	 int outs = Integer.parseInt(out);
		    	  	 if(outs==1)
		    	  	 {
		    	  		 WebElement outs_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[2]/span/span[1]/span[1]/input"));
		    	  		 outs_check.click();
		    	  		 WebElement outs_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[3]/div/div/div/div"));
		    	  		 outs_list.click();
		    	  		 cell = sheet.getRow(i).getCell(16);
		    	  	  	 cell.setCellType(CellType.STRING);
		    	  	  	 WebElement out_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	   	     out_list2.click();
		    	   	     Thread.sleep(500);
		    	  	 }
		    	  	 else 
		    	  	 {
		    	  		 System.out.println("Outs is not selected");
		    	  	 }
		    	  	 
		    	  	 //surface
		    	   	 cell = sheet.getRow(i).getCell(17);
		    	 	 cell.setCellType(CellType.STRING);
		    	 	 String surface = cell.getStringCellValue();
		    	 	 int surfaces = Integer.parseInt(surface);
		    	 	 if(surfaces==1)
		    	 	 {
		    	 		 WebElement surface_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/span/span[1]/span[1]/input"));
		    	 		 surface_check.click();
		    	 		 WebElement surface_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[3]/div/div/div/div"));
		    	 		 surface_list.click();
		    	 		 cell = sheet.getRow(i).getCell(18);
		    	 	  	 cell.setCellType(CellType.STRING);
		    	 	  	 WebElement surface_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	  	     surface_list2.click();
		    	  	     Thread.sleep(500);
		    	 	 }
		    	 	 else 
		    	 	 {
		    	 		 System.out.println("Surface is not selected");
		    	 	 }
		    		 
			    	 
			    	 WebElement nine_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/div/div/div/div"));
			    	 nine_upto.click();
			    	 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list.click();
			    	 Thread.sleep(500); 
			    	 
			    	 WebElement seven_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[9]/div[2]/div/div/div/div"));
			    	 seven_upto.click();
			    	 cell = sheet.getRow(i).getCell(13);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list1.click();
			    	 Thread.sleep(500);
			    	 
			    	 WebElement five_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[10]/div[2]/div/div/div/div"));
			    	 five_upto.click();
			    	 cell = sheet.getRow(i).getCell(14);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list2.click();
			    	 Thread.sleep(500);
		    	 }
		    	 //ration 9,7
		    	 else if (yes2==1 && yes3==1) 
		    	 {
		    		 System.out.println("9,7 ratio loop");
		    		 WebElement seven_side_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[1]/div[2]/div/div/div"));
			    	 seven_side_ratio.click();
			    	 Thread.sleep(500);
			    	 cell = sheet.getRow(i).getCell(8);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement ratio_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 ratio_list1.click();
			    	 Thread.sleep(500);
		    		 
		    		 WebElement nine_side_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[2]/div[2]/div/div/div"));
			    	 nine_side_ratio.click();
			    	 Thread.sleep(500);
			    	 cell = sheet.getRow(i).getCell(5);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement ratio_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 ratio_list2.click();
			    	 Thread.sleep(500);
			    	 
			    	//outs 
		    		 cell = sheet.getRow(i).getCell(15);
		    	  	 cell.setCellType(CellType.STRING);
		    	  	 String out = cell.getStringCellValue();
		    	  	 int outs = Integer.parseInt(out);
		    	  	 if(outs==1)
		    	  	 {
		    	  		 WebElement outs_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[2]/span/span[1]/span[1]/input"));
		    	  		 outs_check.click();
		    	  		 WebElement outs_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[3]/div/div/div/div"));
		    	  		 outs_list.click();
		    	  		 cell = sheet.getRow(i).getCell(16);
		    	  	  	 cell.setCellType(CellType.STRING);
		    	  	  	 WebElement out_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	   	     out_list2.click();
		    	   	     Thread.sleep(500);
		    	  	 }
		    	  	 else 
		    	  	 {
		    	  		 System.out.println("Outs is not selected");
		    	  	 }
		    	  	 
		    	  	 //surface
		    	   	 cell = sheet.getRow(i).getCell(17);
		    	 	 cell.setCellType(CellType.STRING);
		    	 	 String surface = cell.getStringCellValue();
		    	 	 int surfaces = Integer.parseInt(surface);
		    	 	 if(surfaces==1)
		    	 	 {
		    	 		 WebElement surface_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/span/span[1]/span[1]/input"));
		    	 		 surface_check.click();
		    	 		 WebElement surface_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[3]/div/div/div/div"));
		    	 		 surface_list.click();
		    	 		 cell = sheet.getRow(i).getCell(18);
		    	 	  	 cell.setCellType(CellType.STRING);
		    	 	  	 WebElement surface_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	  	     surface_list2.click();
		    	  	     Thread.sleep(500);
		    	 	 }
		    	 	 else 
		    	 	 {
		    	 		 System.out.println("Surface is not selected");
		    	 	 }
			    	 
			    	 WebElement nine_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/div/div/div/div"));
			    	 nine_upto.click();
			    	 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list.click();
			    	 Thread.sleep(500); 
			    	 
			    	 WebElement seven_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[9]/div[2]/div/div/div/div"));
			    	 seven_upto.click();
			    	 cell = sheet.getRow(i).getCell(13);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list1.click();
			    	 Thread.sleep(500);
				 }
		    	 //ratio 9,5
		    	 else if (yes2==1 && yes4==1) 
		    	 { 
		    		 System.out.println("9,5 ratio loop");
		    		 WebElement five_side_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[2]/div/div/div"));
			    	 five_side_ratio.click();
			    	 Thread.sleep(500);
			    	 cell = sheet.getRow(i).getCell(11);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement ratio_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 ratio_list.click();
			    	 Thread.sleep(500);
		    		 
		    		 WebElement nine_side_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[4]/div[2]/div/div/div"));
			    	 nine_side_ratio.click();
			    	 Thread.sleep(500);
			    	 cell = sheet.getRow(i).getCell(5);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement ratio_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 ratio_list2.click();
			    	 Thread.sleep(500);
			    	 
			    	//outs 
		    		 cell = sheet.getRow(i).getCell(15);
		    	  	 cell.setCellType(CellType.STRING);
		    	  	 String out = cell.getStringCellValue();
		    	  	 int outs = Integer.parseInt(out);
		    	  	 if(outs==1)
		    	  	 {
		    	  		 WebElement outs_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[2]/span/span[1]/span[1]/input"));
		    	  		 outs_check.click();
		    	  		 WebElement outs_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[3]/div/div/div/div"));
		    	  		 outs_list.click();
		    	  		 cell = sheet.getRow(i).getCell(16);
		    	  	  	 cell.setCellType(CellType.STRING);
		    	  	  	 WebElement out_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	   	     out_list2.click();
		    	   	     Thread.sleep(500);
		    	  	 }
		    	  	 else 
		    	  	 {
		    	  		 System.out.println("Outs is not selected");
		    	  	 }
		    	  	 
		    	  	 //surface
		    	   	 cell = sheet.getRow(i).getCell(17);
		    	 	 cell.setCellType(CellType.STRING);
		    	 	 String surface = cell.getStringCellValue();
		    	 	 int surfaces = Integer.parseInt(surface);
		    	 	 if(surfaces==1)
		    	 	 {
		    	 		 WebElement surface_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/span/span[1]/span[1]/input"));
		    	 		 surface_check.click();
		    	 		 WebElement surface_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[3]/div/div/div/div"));
		    	 		 surface_list.click();
		    	 		 cell = sheet.getRow(i).getCell(18);
		    	 	  	 cell.setCellType(CellType.STRING);
		    	 	  	 WebElement surface_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	  	     surface_list2.click();
		    	  	     Thread.sleep(500);
		    	 	 }
		    	 	 else 
		    	 	 {
		    	 		 System.out.println("Surface is not selected");
		    	 	 }
			    	 
			    	 WebElement nine_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/div/div/div/div"));
			    	 nine_upto.click();
			    	 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list.click();
			    	 Thread.sleep(500);
			    	 
			    	 WebElement five_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[9]/div[2]/div/div/div/div"));
			    	 five_upto.click();
			    	 cell = sheet.getRow(i).getCell(14);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list2.click();
			    	 Thread.sleep(500);
				 }
		    	 //ratio 7,5
		    	 else if (yes3==1 && yes4==1) 
		    	 { 
		    		 System.out.println("7,5 ratio loop");
		    		 WebElement five_side_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[2]/div/div/div"));
			    	 five_side_ratio.click();
			    	 Thread.sleep(500);
			    	 cell = sheet.getRow(i).getCell(11);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement ratio_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 ratio_list.click();
			    	 Thread.sleep(500);
		    		 
		    		 WebElement seven_side_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[3]/div[2]/div/div/div"));
			    	 seven_side_ratio.click();
			    	 Thread.sleep(500);
			    	 cell = sheet.getRow(i).getCell(8);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement ratio_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 ratio_list1.click();
			    	 Thread.sleep(500);
			    	 
			    	//outs 
		    		 cell = sheet.getRow(i).getCell(15);
		    	  	 cell.setCellType(CellType.STRING);
		    	  	 String out = cell.getStringCellValue();
		    	  	 int outs = Integer.parseInt(out);
		    	  	 if(outs==1)
		    	  	 {
		    	  		 WebElement outs_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[2]/span/span[1]/span[1]/input"));
		    	  		 outs_check.click();
		    	  		 WebElement outs_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[3]/div/div/div/div"));
		    	  		 outs_list.click();
		    	  		 cell = sheet.getRow(i).getCell(16);
		    	  	  	 cell.setCellType(CellType.STRING);
		    	  	  	 WebElement out_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	   	     out_list2.click();
		    	   	     Thread.sleep(500);
		    	  	 }
		    	  	 else 
		    	  	 {
		    	  		 System.out.println("Outs is not selected");
		    	  	 }
		    	  	 
		    	  	 //surface
		    	   	 cell = sheet.getRow(i).getCell(17);
		    	 	 cell.setCellType(CellType.STRING);
		    	 	 String surface = cell.getStringCellValue();
		    	 	 int surfaces = Integer.parseInt(surface);
		    	 	 if(surfaces==1)
		    	 	 {
		    	 		 WebElement surface_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/span/span[1]/span[1]/input"));
		    	 		 surface_check.click();
		    	 		 WebElement surface_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[3]/div/div/div/div"));
		    	 		 surface_list.click();
		    	 		 cell = sheet.getRow(i).getCell(18);
		    	 	  	 cell.setCellType(CellType.STRING);
		    	 	  	 WebElement surface_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	  	     surface_list2.click();
		    	  	     Thread.sleep(500);
		    	 	 }
		    	 	 else 
		    	 	 {
		    	 		 System.out.println("Surface is not selected");
		    	 	 }
			    	 
			    	 WebElement seven_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/div/div/div/div"));
			    	 seven_upto.click();
			    	 cell = sheet.getRow(i).getCell(13);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list1.click();
			    	 Thread.sleep(500);
			    	 
			    	 WebElement five_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[9]/div[2]/div/div/div/div"));
			    	 five_upto.click();
			    	 cell = sheet.getRow(i).getCell(14);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list2.click();
			    	 Thread.sleep(500);
				 }
		    	 else
		    	 {
		    		 System.out.println("No ground is selected");
		    	 }
		    	 
		    	 
		    	 if(yes3!=1 && yes4!=1)
		    	 {
		    		 System.out.println("9 ratio loop");
		    		//Ratio
			    	 WebElement nine_side_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[2]/div[2]/div/div/div"));
			    	 nine_side_ratio.click();
			    	 Thread.sleep(500);
			    	 cell = sheet.getRow(i).getCell(5);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement ratio_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 ratio_list.click();
			    	 Thread.sleep(500);
			    	 
			    	//outs 
		    		 cell = sheet.getRow(i).getCell(15);
		    	  	 cell.setCellType(CellType.STRING);
		    	  	 String out = cell.getStringCellValue();
		    	  	 int outs = Integer.parseInt(out);
		    	  	 if(outs==1)
		    	  	 {
		    	  		 WebElement outs_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[2]/span/span[1]/span[1]/input"));
		    	  		 outs_check.click();
		    	  		 WebElement outs_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[3]/div/div/div/div"));
		    	  		 outs_list.click();
		    	  		 cell = sheet.getRow(i).getCell(16);
		    	  	  	 cell.setCellType(CellType.STRING);
		    	  	  	 WebElement out_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	   	     out_list2.click();
		    	   	     Thread.sleep(500);
		    	  	 }
		    	  	 else 
		    	  	 {
		    	  		 System.out.println("Outs is not selected");
		    	  	 }
		    	  	 
		    	  	 //surface
		    	   	 cell = sheet.getRow(i).getCell(17);
		    	 	 cell.setCellType(CellType.STRING);
		    	 	 String surface = cell.getStringCellValue();
		    	 	 int surfaces = Integer.parseInt(surface);
		    	 	 if(surfaces==1)
		    	 	 {
		    	 		 WebElement surface_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/span/span[1]/span[1]/input"));
		    	 		 surface_check.click();
		    	 		 WebElement surface_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[3]/div/div/div/div"));
		    	 		 surface_list.click();
		    	 		 cell = sheet.getRow(i).getCell(18);
		    	 	  	 cell.setCellType(CellType.STRING);
		    	 	  	 WebElement surface_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	  	     surface_list2.click();
		    	  	     Thread.sleep(500);
		    	 	 }
		    	 	 else 
		    	 	 {
		    	 		 System.out.println("Surface is not selected");
		    	 	 }
			    	 
			    	 WebElement nine_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/div/div/div/div"));
			    	 nine_upto.click();
			    	 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list.click();
			    	 Thread.sleep(500);
		    	 }
		    	 else
		    	 {
		    		 System.out.println("9-side is not selected");
		    	 }
		    	 if(yes2!=1 && yes4!=1)
		    	 {
		    		 System.out.println("7 ratio loop");
		    		//Ratio
			    	 WebElement seven_side_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[1]/div[2]/div/div/div"));
			    	 seven_side_ratio.click();
			    	 Thread.sleep(500);
			    	 cell = sheet.getRow(i).getCell(8);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement ratio_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 ratio_list.click();
			    	 Thread.sleep(500);
			    	 
			    	//outs 
		    		 cell = sheet.getRow(i).getCell(15);
		    	  	 cell.setCellType(CellType.STRING);
		    	  	 String out = cell.getStringCellValue();
		    	  	 int outs = Integer.parseInt(out);
		    	  	 if(outs==1)
		    	  	 {
		    	  		 WebElement outs_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[2]/span/span[1]/span[1]/input"));
		    	  		 outs_check.click();
		    	  		 WebElement outs_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[3]/div/div/div/div"));
		    	  		 outs_list.click();
		    	  		 cell = sheet.getRow(i).getCell(16);
		    	  	  	 cell.setCellType(CellType.STRING);
		    	  	  	 WebElement out_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	   	     out_list2.click();
		    	   	     Thread.sleep(500);
		    	  	 }
		    	  	 else 
		    	  	 {
		    	  		 System.out.println("Outs is not selected");
		    	  	 }
		    	  	 
		    	  	 //surface
		    	   	 cell = sheet.getRow(i).getCell(17);
		    	 	 cell.setCellType(CellType.STRING);
		    	 	 String surface = cell.getStringCellValue();
		    	 	 int surfaces = Integer.parseInt(surface);
		    	 	 if(surfaces==1)
		    	 	 {
		    	 		 WebElement surface_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/span/span[1]/span[1]/input"));
		    	 		 surface_check.click();
		    	 		 WebElement surface_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[3]/div/div/div/div"));
		    	 		 surface_list.click();
		    	 		 cell = sheet.getRow(i).getCell(18);
		    	 	  	 cell.setCellType(CellType.STRING);
		    	 	  	 WebElement surface_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	  	     surface_list2.click();
		    	  	     Thread.sleep(500);
		    	 	 }
		    	 	 else 
		    	 	 {
		    	 		 System.out.println("Surface is not selected");
		    	 	 }
			    	 
			    	 WebElement seven_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/div/div/div/div"));
			    	 seven_upto.click();
			    	 cell = sheet.getRow(i).getCell(13);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list1.click();
			    	 Thread.sleep(500);
		    	 }
		    	 else
		    	 {
		    		 System.out.println("7-s-side is not selected");
		    	 }
		    	 if(yes2!=1 && yes3!=1)
		    	 {
		    		 System.out.println("5 ratio loop");
		    		//Ratio
			    	 WebElement five_side_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[2]/div/div/div"));
			    	 five_side_ratio.click();
			    	 Thread.sleep(500);
			    	 cell = sheet.getRow(i).getCell(11);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement ratio_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 ratio_list.click();
			    	 Thread.sleep(500);
			    	 
			    	//outs 
		    		 cell = sheet.getRow(i).getCell(15);
		    	  	 cell.setCellType(CellType.STRING);
		    	  	 String out = cell.getStringCellValue();
		    	  	 int outs = Integer.parseInt(out);
		    	  	 if(outs==1)
		    	  	 {
		    	  		 WebElement outs_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[2]/span/span[1]/span[1]/input"));
		    	  		 outs_check.click();
		    	  		 WebElement outs_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[3]/div/div/div/div"));
		    	  		 outs_list.click();
		    	  		 cell = sheet.getRow(i).getCell(16);
		    	  	  	 cell.setCellType(CellType.STRING);
		    	  	  	 WebElement out_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	   	     out_list2.click();
		    	   	     Thread.sleep(500);
		    	  	 }
		    	  	 else 
		    	  	 {
		    	  		 System.out.println("Outs is not selected");
		    	  	 }
		    	  	 
		    	  	 //surface
		    	   	 cell = sheet.getRow(i).getCell(17);
		    	 	 cell.setCellType(CellType.STRING);
		    	 	 String surface = cell.getStringCellValue();
		    	 	 int surfaces = Integer.parseInt(surface);
		    	 	 if(surfaces==1)
		    	 	 {
		    	 		 WebElement surface_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/span/span[1]/span[1]/input"));
		    	 		 surface_check.click();
		    	 		 WebElement surface_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[3]/div/div/div/div"));
		    	 		 surface_list.click();
		    	 		 cell = sheet.getRow(i).getCell(18);
		    	 	  	 cell.setCellType(CellType.STRING);
		    	 	  	 WebElement surface_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	  	     surface_list2.click();
		    	  	     Thread.sleep(500);
		    	 	 }
		    	 	 else 
		    	 	 {
		    	 		 System.out.println("Surface is not selected");
		    	 	 }
			    	 
			    	 WebElement five_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/div/div/div/div"));
			    	 five_upto.click();
			    	 cell = sheet.getRow(i).getCell(14);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list2.click();
			    	 Thread.sleep(500);
		    	 }
		    	 else
		    	 {
		    		 System.out.println("5-s-side is not selected"
		    		 		+ "");
		    	 }
	    	 }
	   else
	    	 {
	    		//9-A-SIDE
	    		 cell = sheet.getRow(i).getCell(3);
		    	 cell.setCellType(CellType.STRING);
		    	 String nine_side = cell.getStringCellValue();
		    	 int yes2 = Integer.parseInt(nine_side);
		    	 if(yes2==1)
		    	 {
	    		 WebElement nine_a_side = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[2]/span/span[1]/span[1]/input"));
	    		 nine_a_side.click();
	    		 Thread.sleep(500);
	    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[3]/div/div/div"));
	    		 no.click();
	    		 Thread.sleep(500);
	    		 cell = sheet.getRow(i).getCell(4);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	 list.click();
		    	 Thread.sleep(500);
		    	 
		    	 }
		    	 else
		    	 {
		    		 System.out.println("9-A-SIDE is not selected");
		    	 }
		    	 
		    	 //7-A-SIDE
		    	 cell = sheet.getRow(i).getCell(6);
		    	 cell.setCellType(CellType.STRING);
		    	 String seven_side = cell.getStringCellValue();
		    	 int yes3 = Integer.parseInt(seven_side);
		    	 if(yes3==1)
		    	 {
	    		 WebElement seven_a_side = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[3]/div[2]/span/span[1]/span[1]/input"));
	    		 seven_a_side.click();
	    		 Thread.sleep(500);
	    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[3]/div[3]/div/div/div"));
	    		 no.click();
	    		 Thread.sleep(500);
	    		 cell = sheet.getRow(i).getCell(7);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	 list.click();
		    	 Thread.sleep(500);
		    	 
		    	 }
		    	 else
		    	 {
		    		 System.out.println("7-A-SIDE is not selected");
		    	 }
		    	 
		    	 //5-A-SIDE
		    	 cell = sheet.getRow(i).getCell(9);
		    	 cell.setCellType(CellType.STRING);
		    	 String five_side = cell.getStringCellValue();
		    	 int yes4 = Integer.parseInt(five_side);
		    	 if(yes4==1)
		    	 {
	    		 WebElement five_a_side = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[4]/div[2]/span/span[1]/span[1]/input"));
	    		 five_a_side.click();
	    		 Thread.sleep(500);
	    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[4]/div[3]/div/div/div"));
	    		 no.click();
	    		 Thread.sleep(500);
	    		 cell = sheet.getRow(i).getCell(10);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	 list.click();
		    	 Thread.sleep(500);
		    	 }
		    	 else
		    	 {
		    		 System.out.println("5-A-SIDE is not selected");
		    	 }
		    	 
		    	 
		    	  //outs 
		    	 cell = sheet.getRow(i).getCell(15);
		      	 cell.setCellType(CellType.STRING);
		      	 String out = cell.getStringCellValue();
		      	 int outs = Integer.parseInt(out);
		      	 if(outs==1)
		      	 {
		      		 WebElement outs_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[2]/span/span[1]/span[1]/input"));
		      		 outs_check.click();
		      		 WebElement outs_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div[3]/div/div/div/div"));
		      		 outs_list.click();
		      		 cell = sheet.getRow(i).getCell(16);
		      	  	 cell.setCellType(CellType.STRING);
		      	  	 WebElement out_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		       	     out_list2.click();
		       	     Thread.sleep(500);
		      	 }
		      	 else 
		      	 {
		      		 System.out.println("Outs is not selected");
		      	 }
		      	 
		      	 //surface
		       	 cell = sheet.getRow(i).getCell(17);
		     	 cell.setCellType(CellType.STRING);
		     	 String surface = cell.getStringCellValue();
		     	 int surfaces = Integer.parseInt(surface);
		     	 if(surfaces==1)
		     	 {
		     		 WebElement surface_check = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[2]/span/span[1]/span[1]/input"));
		     		 surface_check.click();
		     		 WebElement surface_list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[3]/div/div/div/div"));
		     		 surface_list.click();
		     		 cell = sheet.getRow(i).getCell(18);
		     	  	 cell.setCellType(CellType.STRING);
		     	  	 WebElement surface_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		      	     surface_list2.click();
		      	     Thread.sleep(500);
		     	 }
		     	 else 
		     	 {
		     		 System.out.println("Surface is not selected");
		     	 }
		    	 
		     	 if(yes2==1 && yes3==1 && yes4==1)
		     	 {
		     		 System.out.println("Upto 9");
		    		 WebElement nine_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/div/div/div/div"));
			    	 nine_upto.click();
			    	 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list.click();
			    	 Thread.sleep(500);
			    	 
			    	 System.out.println("Upto 7");
		    		 WebElement seven_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/div/div/div/div"));
			    	 seven_upto.click();
			    	 cell = sheet.getRow(i).getCell(13);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list1.click();
			    	 Thread.sleep(500);
			    	 
			    	 System.out.println("Upto 5");
			    	 WebElement five_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[9]/div[2]/div/div/div/div"));
			    	 five_upto.click();
			    	 cell = sheet.getRow(i).getCell(14);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list2.click();
			    	 Thread.sleep(500);
		     	 }
		     	 else if (yes2==1 && yes3==1) 
		     	 {
		     		 System.out.println("Upto 9");
		    		 WebElement nine_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/div/div/div/div"));
			    	 nine_upto.click();
			    	 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list.click();
			    	 Thread.sleep(500);
			    	 
			    	 System.out.println("Upto 7");
		    		 WebElement seven_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/div/div/div/div"));
			    	 seven_upto.click();
			    	 cell = sheet.getRow(i).getCell(13);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list1.click();
			    	 Thread.sleep(500);
				 }
		     	 else if (yes2==1 && yes4==1) 
		     	 {
		     		 System.out.println("Upto 9");
		    		 WebElement nine_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/div/div/div/div"));
			    	 nine_upto.click();
			    	 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list.click();
			    	 Thread.sleep(500);
			    	 
			    	 System.out.println("Upto 5");
			    	 WebElement five_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/div/div/div/div"));
			    	 five_upto.click();
			    	 cell = sheet.getRow(i).getCell(14);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list2.click();
			    	 Thread.sleep(500);
				 }
		     	 else if (yes3==1 && yes4==1) 
		     	 {
		     		 System.out.println("Upto 7");
		    		 WebElement seven_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/div/div/div/div"));
			    	 seven_upto.click();
			    	 cell = sheet.getRow(i).getCell(13);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list1.click();
			    	 Thread.sleep(500);
			    	 
			    	 System.out.println("Upto 5");
			    	 WebElement five_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/div/div/div/div"));
			    	 five_upto.click();
			    	 cell = sheet.getRow(i).getCell(14);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list2.click();
			    	 Thread.sleep(500);
				 }
		     	  
		    	 if(yes3!=1 && yes4!=1)
		    	 {
		    		 System.out.println("Upto 9");
		    		 WebElement nine_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/div/div/div/div"));
			    	 nine_upto.click();
			    	 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list.click();
			    	 Thread.sleep(500);
		    	 }
		    	 if(yes2!=1 && yes4!=1)
		    	 {
		    		 System.out.println("Upto 7");
		    		 WebElement seven_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/div/div/div/div"));
			    	 seven_upto.click();
			    	 cell = sheet.getRow(i).getCell(13);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list1.click();
			    	 Thread.sleep(500);
		    	 }
		    	 if(yes2!=1 && yes3!=1)
		    	 { 
		    		 System.out.println("Upto 5");
			    	 WebElement five_upto = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/div/div/div/div"));
			    	 five_upto.click();
			    	 cell = sheet.getRow(i).getCell(14);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement upto_list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 upto_list2.click();
			    	 Thread.sleep(500);
		    	 }
	    	 }
	 cell = sheet.getRow(i).getCell(19);
  	 cell.setCellType(CellType.STRING);
  	 String ch = cell.getStringCellValue();
  	 int toilet = Integer.parseInt(ch);
  	 if(toilet==1)
  	 {
  		WebElement toilet1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[2]/div[2]/span/span[1]/span[1]/input"));
  		toilet1.click();
  	 }else {System.out.println("toilet is unchecked");}
 
	   
  	 cell = sheet.getRow(i).getCell(20);
 	 cell.setCellType(CellType.STRING);
 	 String ch1 = cell.getStringCellValue();
 	 int parking = Integer.parseInt(ch1);
 	 if(parking==1)
 	 {
 		WebElement park1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[2]/div[5]/span/span[1]/span[1]/input"));
 		park1.click();
 	 }else {System.out.println("parking is unchecked");}
  	
 	 
 	 cell = sheet.getRow(i).getCell(21);
 	 cell.setCellType(CellType.STRING);
 	 String ch2 = cell.getStringCellValue();
 	 int bib = Integer.parseInt(ch2);
 	 if(bib==1)
 	 {
 		WebElement bib1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[3]/div[3]/span/span[1]/span[1]/input"));
 		bib1.click();
 	 }else {System.out.println("bib is unchecked");}
 	 
 	 
   	 cell = sheet.getRow(i).getCell(22);
	 cell.setCellType(CellType.STRING);
	 String ch3 = cell.getStringCellValue();
	 int dressing = Integer.parseInt(ch3);
	 if(dressing==1)
	 {
		WebElement room1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[4]/div[2]/span/span[1]/span[1]/input"));
		room1.click();
	 }else {System.out.println("Room is unchecked");}
 	 
	 
	 cell = sheet.getRow(i).getCell(23);
	 cell.setCellType(CellType.STRING);
	 String ch4 = cell.getStringCellValue();
	 int shower = Integer.parseInt(ch4);
	 if(shower==1)
	 {
		WebElement shower1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[4]/div[5]/span/span[1]/span[1]/input"));
		shower1.click();
	 }else {System.out.println("Shower is unchecked");}
	 
	 
	 cell = sheet.getRow(i).getCell(24);
	 cell.setCellType(CellType.STRING);
	 String ch5 = cell.getStringCellValue();
	 int towel = Integer.parseInt(ch5);
	 if(towel==1)
	 {
		WebElement towel1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[5]/div[2]/span/span[1]/span[1]/input"));
		towel1.click();
	 }else {System.out.println("towel is unchecked");}
	 
	 
	 cell = sheet.getRow(i).getCell(25);
	 cell.setCellType(CellType.STRING);
	 String ch6 = cell.getStringCellValue();
	 int locker = Integer.parseInt(ch6);
	 if(locker==1)
	 {
		WebElement locker1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[5]/div[5]/span/span[1]/span[1]/input"));
		locker1.click();
	 }else {System.out.println("locker is unchecked");}
	 
	 
	 cell = sheet.getRow(i).getCell(26);
	 cell.setCellType(CellType.STRING);
	 String ch7 = cell.getStringCellValue();
	 int phonepe = Integer.parseInt(ch7);
	 if(phonepe==1)
	 {
		WebElement phonepe1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[6]/div[2]/span/span[1]/span[1]/input"));
		phonepe1.click();
	 }else {System.out.println("Phonepe is unchecked");}
	 
	 
	 cell = sheet.getRow(i).getCell(27);
	 cell.setCellType(CellType.STRING);
	 String ch8 = cell.getStringCellValue();
	 int card = Integer.parseInt(ch8);
	 if(card==1)
	 {
		WebElement card1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[6]/div[5]/span/span[1]/span[1]/input"));
		card1.click();
	 }else {System.out.println("Phonepe is unchecked");}
	 
	 
	 cell = sheet.getRow(i).getCell(28);
	 cell.setCellType(CellType.STRING);
	 String ch9 = cell.getStringCellValue();
	 int paytm = Integer.parseInt(ch9);
	 if(paytm==1)
	 {
		WebElement paytm1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[7]/div[2]/span/span[1]/span[1]/input"));
		paytm1.click();
	 }else {System.out.println("Paytm is unchecked");}
	 
	 
	 cell = sheet.getRow(i).getCell(29);
	 cell.setCellType(CellType.STRING);
	 String ch10 = cell.getStringCellValue();
	 int gpay = Integer.parseInt(ch10);
	 if(gpay==1)
	 {
		WebElement gpay1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[7]/div[3]/div[2]/span/span[1]/span[1]/input"));
		gpay1.click();
	 }else {System.out.println("Gpay is unchecked");}
	 
	 boolean error_msg = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[7]/span")).size()!=0;
	 
	 if(error_msg==true)
	 {
  		  screenCapture();
  		    sheet.getRow(i).createCell(30).setCellValue(fail);  
  			System.out.println("Error throws in Gametype page...Kindly refer screenshot");   
  			// Specify the file in which data needs to be written.
  	        FileOutputStream fileOutput = new FileOutputStream(src);
  	        // finally write content
  	        workbook.write(fileOutput);
  	         // close the file
  	        fileOutput.close();
  	        
  		   }
  		  else
  		  {
  			  sheet.getRow(i).createCell(30).setCellValue(pass);
  			  System.out.println("Doesn't throw any alert in this page");
  			    // Specify the file in which data needs to be written.
  		        FileOutputStream fileOutput = new FileOutputStream(src);
  		        // finally write content
  		        workbook.write(fileOutput);
  		         // close the file
  		        fileOutput.close();
  		        System.out.println("file is closed");      
  		  }
 	 WebElement Next_btn3 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]/span[1]"));
 	 Next_btn3.click();
 	 Thread.sleep(1000);
	     }
	     
  }
  
  //@Test(priority=2)
  public void cricket_gametype() throws IOException, InterruptedException
  {
	     // Import excel sheet.
	     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\venue.xls");
	      
	     // Load the file.
	     FileInputStream finput = new FileInputStream(src);
	      
	     // Load he workbook.
	     workbook = new HSSFWorkbook(finput);
	      
	     // Load the sheet in which data is stored.
	     sheet= workbook.getSheet("cricgametype");
	     
	     
	     for(int i=1; i<=sheet.getLastRowNum(); i++)
	     {
	    	 cell = sheet.getRow(i).getCell(0);
	    	 cell.setCellType(CellType.STRING);
	    	 String sec = cell.getStringCellValue();
	    	 int yes = Integer.parseInt(sec);
	    	 if(yes==1)
	    	 {
	    		 WebElement secondary = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[1]/div/div[1]/div[2]/span/span[1]/span[1]/input"));
	    		 secondary.click();
	    		 cell = sheet.getRow(i).getCell(1);
		    	 cell.setCellType(CellType.STRING);
	    		 WebElement venues = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[2]/div/div/div/div[1]/div[1]/div[1]"));
	    		 Thread.sleep(500);
	    		 a1.click(venues).sendKeys(cell.getStringCellValue()).sendKeys(Keys.ENTER).build().perform();
				 Thread.sleep(1000);
				 System.out.println("venue is selected");
	    	 
	    	 //convertable
	    	 cell = sheet.getRow(i).getCell(2);
	    	 cell.setCellType(CellType.STRING);
	    	 String con = cell.getStringCellValue();
	    	 int yes1 = Integer.parseInt(con);
	    	 
	    	 if(yes1==1)
	    	 {
	    		 WebElement convertable = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[3]/div[2]/span/span[1]/span[1]/input"));
	    		 convertable.click();
	    		 Thread.sleep(500);
	    		 
	    		 //Ground
	    		 cell = sheet.getRow(i).getCell(3);
		    	 cell.setCellType(CellType.STRING);
		    	 String gro = cell.getStringCellValue();
		    	 int yes2 = Integer.parseInt(gro);
		    	 if(yes2==1) 
		    	 {
	    		 WebElement ground = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[5]/div[1]/div[2]/span/span[1]/span[1]/input"));
	    		 ground.click();
	    		 Thread.sleep(500);
	    		 WebElement list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[5]/div[1]/div[3]/div/div/div"));
	    		 list.click();
	    		 Thread.sleep(500);
	    		 cell = sheet.getRow(i).getCell(4);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement no = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	 no.click();
		    	 Thread.sleep(500);
		    	 
		    	 //Ratio
		    	 WebElement ground_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[6]/div[1]/div[2]/div/div/div"));
		    	 ground_ratio.click();
		    	 cell = sheet.getRow(i).getCell(7);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement no1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	 no1.click();
		    	 Thread.sleep(500);
		    	 }
		    	 else {System.out.println("Ground is not selected");}
		    	 
		    	//Nets
	    		 cell = sheet.getRow(i).getCell(5);
		    	 cell.setCellType(CellType.STRING);
		    	 String net = cell.getStringCellValue();
		    	 int yes3 = Integer.parseInt(net);
		    	 if(yes3==1) 
		    	 {
		    		 WebElement nets = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[5]/div[2]/div[2]/span/span[1]/span[1]/input"));
		    		 nets.click();
		    		 Thread.sleep(500);
		    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[5]/div[2]/div[3]/div/div/div"));
		    		 no.click();
		    		 cell = sheet.getRow(i).getCell(6);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list.click();
			    	 Thread.sleep(500);
			    	 
			    	//Ratio
			    	 WebElement net_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[6]/div[2]/div[2]/div/div/div"));
			    	 net_ratio.click();
			    	 cell = sheet.getRow(i).getCell(8);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement no1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 no1.click();
			    	 Thread.sleep(500);
			    	 
		    	 }else {System.out.println("nets is not selected");}
	    	 }
	    	 else
	    	 {
	    		//Ground
	    		 cell = sheet.getRow(i).getCell(3);
		    	 cell.setCellType(CellType.STRING);
		    	 String gro = cell.getStringCellValue();
		    	 int yes2 = Integer.parseInt(gro);
		    	 if(yes2==1) 
		    	 {
	    		 WebElement ground = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[5]/div[1]/div[2]/span/span[1]/span[1]/input"));
	    		 ground.click();
	    		 Thread.sleep(500);
	    		 WebElement list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[5]/div[1]/div[3]/div/div/div"));
	    		 list.click();
	    		 Thread.sleep(500);
	    		 cell = sheet.getRow(i).getCell(4);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement no = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	 no.click();
		    	 Thread.sleep(500);
		    	 }else {System.out.println("Ground is not selected");}
		    	 
		    	 
		    	//Nets
	    		 cell = sheet.getRow(i).getCell(5);
		    	 cell.setCellType(CellType.STRING);
		    	 String net = cell.getStringCellValue();
		    	 int yes3 = Integer.parseInt(net);
		    	 if(yes3==1) 
		    	 {
		    		 WebElement nets = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[5]/div[2]/div[2]/span/span[1]/span[1]/input"));
		    		 nets.click();
		    		 Thread.sleep(500);
		    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[5]/div[2]/div[3]/div/div/div"));
		    		 no.click();
		    		 cell = sheet.getRow(i).getCell(6);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list.click();
			    	 Thread.sleep(500);
		    	 }else {System.out.println("Net is not selected");}
	    	 }
	    	 
	    	 }	
	    	 else
	    	 {
	    		 System.out.println("Secondary is not selected");
	    		 
	    		 //convertable
		    	 cell = sheet.getRow(i).getCell(2);
		    	 cell.setCellType(CellType.STRING);
		    	 String con = cell.getStringCellValue();
		    	 int yes1 = Integer.parseInt(con);
		    	 
		    	 if(yes1==1)
		    	 {
		    		 WebElement convertable = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[2]/div[2]/span/span[1]/span[1]/input"));
		    		 convertable.click();
		    		 Thread.sleep(500);
		    		 
		    		 //Ground
		    		 cell = sheet.getRow(i).getCell(3);
			    	 cell.setCellType(CellType.STRING);
			    	 String gro = cell.getStringCellValue();
			    	 int yes2 = Integer.parseInt(gro);
			    	 if(yes2==1) 
			    	 {
		    		 WebElement ground = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[4]/div[1]/div[2]/span/span[1]/span[1]/input"));
		    		 ground.click();
		    		 Thread.sleep(500);
		    		 WebElement list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[4]/div[1]/div[3]/div/div/div"));
		    		 list.click();
		    		 Thread.sleep(500);
		    		 cell = sheet.getRow(i).getCell(4);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement no = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 no.click();
			    	 Thread.sleep(500);
			    	 
			    	 //Ratio
			    	 WebElement ground_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[5]/div[1]/div[2]/div/div/div"));
			    	 ground_ratio.click();
			    	 cell = sheet.getRow(i).getCell(7);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement no1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 no1.click();
			    	 Thread.sleep(500);
			    	 }
			    	 else {System.out.println("Ground is not selected");}
			    	 
			    	//Nets
		    		 cell = sheet.getRow(i).getCell(5);
			    	 cell.setCellType(CellType.STRING);
			    	 String net = cell.getStringCellValue();
			    	 int yes3 = Integer.parseInt(net);
			    	 if(yes3==1) 
			    	 {
			    		 WebElement nets = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[4]/div[2]/div[2]/span/span[1]/span[1]/input"));
			    		 nets.click();
			    		 Thread.sleep(500);
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[4]/div[2]/div[3]/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(6);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
				    	 
				    	//Ratio
				    	 WebElement net_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[5]/div[2]/div[2]/div/div/div"));
				    	 net_ratio.click();
				    	 cell = sheet.getRow(i).getCell(8);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement no1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 no1.click();
				    	 Thread.sleep(500);
				    	 
			    	 }else {System.out.println("nets is not selected");}
		    	 }
		    	 else
		    	 {
		    		//Ground
		    		 cell = sheet.getRow(i).getCell(3);
			    	 cell.setCellType(CellType.STRING);
			    	 String gro = cell.getStringCellValue();
			    	 int yes2 = Integer.parseInt(gro);
			    	 if(yes2==1) 
			    	 {
		    		 WebElement ground = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[4]/div[1]/div[2]/span/span[1]/span[1]/input"));
		    		 ground.click();
		    		 Thread.sleep(500);
		    		 WebElement list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[4]/div[1]/div[3]/div/div/div"));
		    		 list.click();
		    		 Thread.sleep(500);
		    		 cell = sheet.getRow(i).getCell(4);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement no = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 no.click();
			    	 Thread.sleep(500);
			    	 }else {System.out.println("Ground is not selected");}
			    	 
			    	 
			    	//Nets
		    		 cell = sheet.getRow(i).getCell(5);
			    	 cell.setCellType(CellType.STRING);
			    	 String net = cell.getStringCellValue();
			    	 int yes3 = Integer.parseInt(net);
			    	 if(yes3==1) 
			    	 {
			    		 WebElement nets = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[4]/div[2]/div[2]/span/span[1]/span[1]/input"));
			    		 nets.click();
			    		 Thread.sleep(500);
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div/div[4]/div[2]/div[3]/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(6);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
			    	 }else {System.out.println("Net is not selected");}
	    		 
	    		 
	    	 }
	     }
	    	 boolean equip1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div[2]/div[2]/span/span[1]/span[1]/input")).size()!=0;
	    	 
	    	 if(equip1==true)
	    	 {
	    		 //ground equipment
	    		 cell = sheet.getRow(i).getCell(9);
		    	 cell.setCellType(CellType.STRING);
		    	 String euip = cell.getStringCellValue();
		    	 int yes1 = Integer.parseInt(euip);
		    	 if(yes1==1) 
		    	 {
		    		 WebElement equipment = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div[2]/div[2]/span/span[1]/span[1]/input"));
		    		 equipment.click();
		    	 }else {System.out.println("Ground equipment is not selected");}
		    	 
		    	 //surface
		    	 cell = sheet.getRow(i).getCell(10);
		    	 cell.setCellType(CellType.STRING);
		    	 String sur = cell.getStringCellValue();
		    	 int yes2 = Integer.parseInt(sur);
		    	 if(yes2==1) 
		    	 {
		    		 WebElement surface = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div[3]/div[2]/span/span[1]/span[1]/input"));
		    		 surface.click();
		    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div[3]/div[3]/div/div/div/div"));
		    		 no.click();
		    		 cell = sheet.getRow(i).getCell(11);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list.click();
			    	 Thread.sleep(500);	 
		    	 }else {System.out.println("Ground surface is not selected");}
		    	 
		    	//upto
		    	 cell = sheet.getRow(i).getCell(12);
		    	 cell.setCellType(CellType.STRING);
		    	 String up = cell.getStringCellValue();
		    	 int upto = Integer.parseInt(up);
		    	 if(upto==1) 
		    	 {
		    		 WebElement upto1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div[4]/div[2]/span/span[1]/span[1]/input"));
		    		 upto1.click();
		    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div[4]/div[3]/div/div/div/div"));
		    		 no.click();
		    		 cell = sheet.getRow(i).getCell(13);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list.click();
			    	 Thread.sleep(500);	 
		    	 }else {System.out.println("Ground upto is not selected");}
		    	 
	    	 }else {System.out.println("Ground is not selected");}
	    	 
	    	 boolean equip2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[2]/div[4]/span/span[1]/span[1]/input")).size()!=0;
	    	 
	    	 if(equip2==true)
	    	 {
	    		//nets equipment
	    		 cell = sheet.getRow(i).getCell(14);
		    	 cell.setCellType(CellType.STRING);
		    	 String euip = cell.getStringCellValue();
		    	 int yes1 = Integer.parseInt(euip);
		    	 if(yes1==1) 
		    	 {
		    		 WebElement equipment = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[2]/div[2]/span/span[1]/span[1]/input"));
		    		 equipment.click();
		    	 }else {System.out.println("Nets equipment is not selected");}
	    		 
		    	//nets bowling
	    		 cell = sheet.getRow(i).getCell(15);
		    	 cell.setCellType(CellType.STRING);
		    	 String bowl = cell.getStringCellValue();
		    	 int yes2 = Integer.parseInt(bowl);
		    	 if(yes2==1) 
		    	 {
		    		 WebElement equipment = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[2]/div[4]/span/span[1]/span[1]/input"));
		    		 equipment.click();
		    	 }else {System.out.println("Nets bowling is not selected");}
		    	 
		    	 WebElement net_surface = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[3]/div/div/div/div[1]/div[1]/div[1]"));
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", net_surface);
		    	 cell = sheet.getRow(i).getCell(16);
		    	 cell.setCellType(CellType.STRING);
		    	 Thread.sleep(500);
		    	 a1.click(net_surface).sendKeys(cell.getStringCellValue()).sendKeys(Keys.ENTER).build().perform();
		    	 Thread.sleep(1000);
		    	 
	    	 }else {System.out.println("Net is not selected");}
	    	 
	    	 
	    	 //toilet
	    	 cell = sheet.getRow(i).getCell(17);
	      	 cell.setCellType(CellType.STRING);
	      	 String ch = cell.getStringCellValue();
	      	 int toilet = Integer.parseInt(ch);
	      	 if(toilet==1)
	      	 {
	      		WebElement toilet1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[2]/div[2]/span/span[1]/span[1]/input"));
	      		toilet1.click();
	      	 }else {System.out.println("toilet is unchecked");}
	     
	    	   
	      	 cell = sheet.getRow(i).getCell(18);
	     	 cell.setCellType(CellType.STRING);
	     	 String ch1 = cell.getStringCellValue();
	     	 int parking = Integer.parseInt(ch1);
	     	 if(parking==1)
	     	 {
	     		WebElement park1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[2]/div[5]/span/span[1]/span[1]/input"));
	     		park1.click();
	     	 }else {System.out.println("parking is unchecked");}
	      	
	     	 
	     	 cell = sheet.getRow(i).getCell(19);
	     	 cell.setCellType(CellType.STRING);
	     	 String ch2 = cell.getStringCellValue();
	     	 int bib = Integer.parseInt(ch2);
	     	 if(bib==1)
	     	 {
	     		WebElement bib1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[3]/div[3]/span/span[1]/span[1]/input"));
	     		bib1.click();
	     	 }else {System.out.println("bib is unchecked");}
	     	 
	     	 
	       	 cell = sheet.getRow(i).getCell(20);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch3 = cell.getStringCellValue();
	    	 int dressing = Integer.parseInt(ch3);
	    	 if(dressing==1)
	    	 {
	    		WebElement room1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[4]/div[2]/span/span[1]/span[1]/input"));
	    		room1.click();
	    	 }else {System.out.println("Room is unchecked");}
	     	 
	    	 
	    	 cell = sheet.getRow(i).getCell(21);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch4 = cell.getStringCellValue();
	    	 int shower = Integer.parseInt(ch4);
	    	 if(shower==1)
	    	 {
	    		WebElement shower1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[4]/div[5]/span/span[1]/span[1]/input"));
	    		shower1.click();
	    	 }else {System.out.println("Shower is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(22);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch5 = cell.getStringCellValue();
	    	 int towel = Integer.parseInt(ch5);
	    	 if(towel==1)
	    	 {
	    		WebElement towel1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[5]/div[2]/span/span[1]/span[1]/input"));
	    		towel1.click();
	    	 }else {System.out.println("towel is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(23);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch6 = cell.getStringCellValue();
	    	 int locker = Integer.parseInt(ch6);
	    	 if(locker==1)
	    	 {
	    		WebElement locker1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[5]/div[5]/span/span[1]/span[1]/input"));
	    		locker1.click();
	    	 }else {System.out.println("locker is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(24);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch7 = cell.getStringCellValue();
	    	 int phonepe = Integer.parseInt(ch7);
	    	 if(phonepe==1)
	    	 {
	    		WebElement phonepe1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[6]/div[2]/span/span[1]/span[1]/input"));
	    		phonepe1.click();
	    	 }else {System.out.println("Phonepe is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(25);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch8 = cell.getStringCellValue();
	    	 int card = Integer.parseInt(ch8);
	    	 if(card==1)
	    	 {
	    		WebElement card1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[6]/div[5]/span/span[1]/span[1]/input"));
	    		card1.click();
	    	 }else {System.out.println("Phonepe is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(26);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch9 = cell.getStringCellValue();
	    	 int paytm = Integer.parseInt(ch9);
	    	 if(paytm==1)
	    	 {
	    		WebElement paytm1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[7]/div[2]/span/span[1]/span[1]/input"));
	    		paytm1.click();
	    	 }else {System.out.println("Paytm is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(27);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch10 = cell.getStringCellValue();
	    	 int gpay = Integer.parseInt(ch10);
	    	 if(gpay==1)
	    	 {
	    		WebElement gpay1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[7]/div[3]/div[2]/span/span[1]/span[1]/input"));
	    		gpay1.click();
	    	 }else {System.out.println("Gpay is unchecked");}
	    	 
	    	 WebElement Next_btn3 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]/span[1]"));
	     	 Next_btn3.click();
	     	 Thread.sleep(1000);
	    	 
	    	 
	    	 boolean error_msg = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[7]/span")).size()!=0;
	    	 
	    	 if(error_msg==true)
	    	 {
	      		  screenCapture();
	      		    sheet.getRow(i).createCell(28).setCellValue(fail);  
	      			System.out.println("Error throws in Gametype page...Kindly refer screenshot");   
	      			// Specify the file in which data needs to be written.
	      	        FileOutputStream fileOutput = new FileOutputStream(src);
	      	        // finally write content
	      	        workbook.write(fileOutput);
	      	         // close the file
	      	        fileOutput.close();
	      	        
	      		   }
	      		  else
	      		  {
	      			  sheet.getRow(i).createCell(28).setCellValue(pass);
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
  @Test(priority=2)
  public void badmiton_gametype() throws InterruptedException, IOException
  {
	// Import excel sheet.
	     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\venue.xls");
	      
	     // Load the file.
	     FileInputStream finput = new FileInputStream(src);
	      
	     // Load he workbook.
	     workbook = new HSSFWorkbook(finput);
	      
	     // Load the sheet in which data is stored.
	     sheet= workbook.getSheet("badmitongt");
	     
	     
	     for(int i=1; i<=sheet.getLastRowNum(); i++)
	     {
	    	 cell = sheet.getRow(i).getCell(0);
	    	 cell.setCellType(CellType.STRING);
	    	 String sec = cell.getStringCellValue();
	    	 int yes = Integer.parseInt(sec);
	    	 if(yes==1)
	    	 {
	    		 WebElement secondary = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[1]/div[2]/span/span[1]/span[1]/input"));
	    		 secondary.click();
	    		 cell = sheet.getRow(i).getCell(1);
		    	 cell.setCellType(CellType.STRING);
	    		 WebElement venues = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div/div/div[1]/div[1]"));
	    		 Thread.sleep(500);
	    		 a1.click(venues).sendKeys(cell.getStringCellValue()).sendKeys(Keys.ENTER).build().perform();
				 Thread.sleep(1000);
				 System.out.println("venue is selected");
				 
				//convertable
		    	 cell = sheet.getRow(i).getCell(2);
		    	 cell.setCellType(CellType.STRING);
		    	 String con = cell.getStringCellValue();
		    	 int yes1 = Integer.parseInt(con);
		    	 
		    	 if(yes1==1)
		    	 {
		    		 WebElement convertable = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[2]/span/span[1]/span[1]/input"));
		    		 convertable.click();
		    		 Thread.sleep(500);
		    		 
		    		 //AC
		    		 cell = sheet.getRow(i).getCell(3);
			    	 cell.setCellType(CellType.STRING);
			    	 String ac = cell.getStringCellValue();
			    	 int yes2 = Integer.parseInt(ac);
			    	 if(yes2==1) 
			    	 {
		    		 WebElement AC1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div/div[1]/div[2]/span/span[1]/span[1]/input"));
		    		 AC1.click();
		    		 Thread.sleep(500);
		    		 WebElement list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div/div[1]/div[3]/div/div/div"));
		    		 list.click();
		    		 Thread.sleep(500);
		    		 cell = sheet.getRow(i).getCell(4);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement no = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 no.click();
			    	 Thread.sleep(500);
			    	 
			    	 //Ratio
			    	 WebElement ac_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[2]/div[1]/div[2]/div/div/div"));
			    	 ac_ratio.click();
			    	 cell = sheet.getRow(i).getCell(7);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement no1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 no1.click();
			    	 Thread.sleep(500);
			    	 }
			    	 else {System.out.println("AC ground is not selected is not selected");}
			    	 
			    	//Nets
		    		 cell = sheet.getRow(i).getCell(5);
			    	 cell.setCellType(CellType.STRING);
			    	 String non = cell.getStringCellValue();
			    	 int yes3 = Integer.parseInt(non);
			    	 if(yes3==1) 
			    	 {
			    		 WebElement nonac = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div/div[2]/div[2]/span/span[1]/span[1]/input"));
			    		 nonac.click();
			    		 Thread.sleep(500);
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div/div[2]/div[3]/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(6);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
				    	 
				    	//Ratio
				    	 WebElement non_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[2]/div[2]/div[2]/div/div/div"));
				    	 non_ratio.click();
				    	 cell = sheet.getRow(i).getCell(8);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement no1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 no1.click();
				    	 Thread.sleep(500);
				    	 
			    	 }else {System.out.println("non-ac is not selected");}
			    	 
			    	 //equipment
		    		 cell = sheet.getRow(i).getCell(9);
			    	 cell.setCellType(CellType.STRING);
			    	 String equip = cell.getStringCellValue();
			    	 int yes4 = Integer.parseInt(equip);
			    	 if(yes4==1) 
			    	 {
			    		 WebElement equipment = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[3]/div[2]/span/span[1]/span[1]/input"));
			    		 equipment.click();
			    		 Thread.sleep(500);
			    	 }else {System.out.println("Equipment is not selected");}
			    	 
			    	//floor
		    		 cell = sheet.getRow(i).getCell(10);
			    	 cell.setCellType(CellType.STRING);
			    	 String floor = cell.getStringCellValue();
			    	 int yes5 = Integer.parseInt(floor);
			    	 if(yes5==1) 
			    	 {
			    		 WebElement floor1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[4]/div[2]/span/span[1]/span[1]/input"));
			    		 floor1.click();
			    		 Thread.sleep(500);
			    		 
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[4]/div[3]/div/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(11);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
			    		 
			    	 }else {System.out.println("floor is not selected");}
			    	 
			    	//shoe
		    		 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 String shoe = cell.getStringCellValue();
			    	 int yes6 = Integer.parseInt(shoe);
			    	 if(yes6==1) 
			    	 {
			    		 WebElement shoe1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[5]/div[2]/span/span[1]/span[1]/input"));
			    		 shoe1.click();
			    		 Thread.sleep(500);
			    		 
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[5]/div[3]/div/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(11);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
			    		 
			    	 }else {System.out.println("shoe is not selected");}
	    	 }
		    	 else
		    	 {
		    		//AC
		    		 cell = sheet.getRow(i).getCell(3);
			    	 cell.setCellType(CellType.STRING);
			    	 String ac = cell.getStringCellValue();
			    	 int yes2 = Integer.parseInt(ac);
			    	 if(yes2==1) 
			    	 {
		    		 WebElement AC1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div/div[1]/div[2]/span/span[1]/span[1]/input"));
		    		 AC1.click();
		    		 Thread.sleep(500);
		    		 WebElement list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div/div[1]/div[3]/div/div/div"));
		    		 list.click();
		    		 Thread.sleep(500);
		    		 cell = sheet.getRow(i).getCell(4);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement no = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 no.click();
			    	 Thread.sleep(500);
			    	 
			    	 }
			    	 else {System.out.println("AC ground is not selected is not selected");}
			    	 
			    	//Nets
		    		 cell = sheet.getRow(i).getCell(5);
			    	 cell.setCellType(CellType.STRING);
			    	 String non = cell.getStringCellValue();
			    	 int yes3 = Integer.parseInt(non);
			    	 if(yes3==1) 
			    	 {
			    		 WebElement nonac = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div/div[2]/div[2]/span/span[1]/span[1]/input"));
			    		 nonac.click();
			    		 Thread.sleep(500);
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div/div[2]/div[3]/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(6);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
				    	 	 
			    	 }else {System.out.println("non-ac is not selected");}
			    	 
			    	 //equipment
		    		 cell = sheet.getRow(i).getCell(9);
			    	 cell.setCellType(CellType.STRING);
			    	 String equip = cell.getStringCellValue();
			    	 int yes4 = Integer.parseInt(equip);
			    	 if(yes4==1) 
			    	 {
			    		 WebElement equipment = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[2]/div[2]/span/span[1]/span[1]/input"));
			    		 equipment.click();
			    		 Thread.sleep(500);
			    	 }else {System.out.println("Equipment is not selected");}
			    	 
			    	//floor
		    		 cell = sheet.getRow(i).getCell(10);
			    	 cell.setCellType(CellType.STRING);
			    	 String floor = cell.getStringCellValue();
			    	 int yes5 = Integer.parseInt(floor);
			    	 if(yes5==1) 
			    	 {
			    		 WebElement floor1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[3]/div[2]/span/span[1]/span[1]/input"));
			    		 floor1.click();
			    		 Thread.sleep(500);
			    		 
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[3]/div[3]/div/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(11);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
			    		 
			    	 }else {System.out.println("floor is not selected");}
			    	 
			    	//shoe
		    		 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 String shoe = cell.getStringCellValue();
			    	 int yes6 = Integer.parseInt(shoe);
			    	 if(yes6==1) 
			    	 {
			    		 WebElement shoe1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[4]/div[2]/span/span[1]/span[1]/input"));
			    		 shoe1.click();
			    		 Thread.sleep(500);
			    		 
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[4]/div[3]/div/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(11);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
			    		 
			    	 }else {System.out.println("shoe is not selected");}
		    	 }  	 
	     }
	    	 else 
	    	 {
	    		//convertable
		    	 cell = sheet.getRow(i).getCell(2);
		    	 cell.setCellType(CellType.STRING);
		    	 String con = cell.getStringCellValue();
		    	 int yes1 = Integer.parseInt(con);
		    	 
		    	 if(yes1==1)
		    	 {
		    		 WebElement convertable = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div[2]/span/span[1]/span[1]/input"));
		    		 convertable.click();
		    		 Thread.sleep(500);
		    		 
		    		 //AC
		    		 cell = sheet.getRow(i).getCell(3);
			    	 cell.setCellType(CellType.STRING);
			    	 String ac = cell.getStringCellValue();
			    	 int yes2 = Integer.parseInt(ac);
			    	 if(yes2==1) 
			    	 {
		    		 WebElement AC1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[1]/div/div[1]/div[2]/span/span[1]/span[1]/input"));
		    		 AC1.click();
		    		 Thread.sleep(500);
		    		 WebElement list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[1]/div/div[1]/div[3]/div/div/div"));
		    		 list.click();
		    		 Thread.sleep(500);
		    		 cell = sheet.getRow(i).getCell(4);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement no = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 no.click();
			    	 Thread.sleep(500);
			    	 
			    	 //Ratio
			    	 WebElement ac_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[2]/div[1]/div[2]/div/div/div"));
			    	 ac_ratio.click();
			    	 cell = sheet.getRow(i).getCell(7);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement no1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 no1.click();
			    	 Thread.sleep(500);
			    	 }
			    	 else {System.out.println("AC ground is not selected is not selected");}
			    	 
			    	//Non ac
		    		 cell = sheet.getRow(i).getCell(5);
			    	 cell.setCellType(CellType.STRING);
			    	 String non = cell.getStringCellValue();
			    	 int yes3 = Integer.parseInt(non);
			    	 if(yes3==1) 
			    	 {
			    		 WebElement nonac = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[1]/div/div[2]/div[2]/span/span[1]/span[1]/input"));
			    		 nonac.click();
			    		 Thread.sleep(500);
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[1]/div/div[2]/div[3]/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(6);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
				    	 
				    	//Ratio
				    	 WebElement non_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[2]/div[2]/div[2]/div/div/div"));
				    	 non_ratio.click();
				    	 cell = sheet.getRow(i).getCell(8);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement no1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 no1.click();
				    	 Thread.sleep(500);
				    	 
			    	 }else {System.out.println("non-ac is not selected");}
			    	 
			    	 //equipment
		    		 cell = sheet.getRow(i).getCell(9);
			    	 cell.setCellType(CellType.STRING);
			    	 String equip = cell.getStringCellValue();
			    	 int yes4 = Integer.parseInt(equip);
			    	 if(yes4==1) 
			    	 {
			    		 WebElement equipment = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[3]/div[2]/span/span[1]/span[1]/input"));
			    		 equipment.click();
			    		 Thread.sleep(500);
			    	 }else {System.out.println("Equipment is not selected");}
			    	 
			    	//floor
		    		 cell = sheet.getRow(i).getCell(10);
			    	 cell.setCellType(CellType.STRING);
			    	 String floor = cell.getStringCellValue();
			    	 int yes5 = Integer.parseInt(floor);
			    	 if(yes5==1) 
			    	 {
			    		 WebElement floor1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[4]/div[2]/span/span[1]/span[1]/input"));
			    		 floor1.click();
			    		 Thread.sleep(500);
			    		 
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[4]/div[3]/div/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(11);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
			    		 
			    	 }else {System.out.println("floor is not selected");}
			    	 
			    	//shoe
		    		 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 String shoe = cell.getStringCellValue();
			    	 int yes6 = Integer.parseInt(shoe);
			    	 if(yes6==1) 
			    	 {
			    		 WebElement shoe1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[5]/div[2]/span/span[1]/span[1]/input"));
			    		 shoe1.click();
			    		 Thread.sleep(500);
			    		 
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[5]/div[3]/div/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(11);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
			    		 
			    	 }else {System.out.println("shoe is not selected");}
	    	 }
		    	 else
		    	 {
		    		//AC
		    		 cell = sheet.getRow(i).getCell(3);
			    	 cell.setCellType(CellType.STRING);
			    	 String ac = cell.getStringCellValue();
			    	 int yes2 = Integer.parseInt(ac);
			    	 if(yes2==1) 
			    	 {
		    		 WebElement AC1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[1]/div/div[1]/div[2]/span/span[1]/span[1]/input"));
		    		 AC1.click();
		    		 Thread.sleep(500);
		    		 WebElement list = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[1]/div/div[1]/div[3]/div/div/div"));
		    		 list.click();
		    		 Thread.sleep(500);
		    		 cell = sheet.getRow(i).getCell(4);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement no = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 no.click();
			    	 Thread.sleep(500);
			    	 
			    	 }
			    	 else {System.out.println("AC ground is not selected is not selected");}
			    	 
			    	//Non ac
		    		 cell = sheet.getRow(i).getCell(5);
			    	 cell.setCellType(CellType.STRING);
			    	 String non = cell.getStringCellValue();
			    	 int yes3 = Integer.parseInt(non);
			    	 if(yes3==1) 
			    	 {
			    		 WebElement nonac = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[1]/div/div[2]/div[2]/span/span[1]/span[1]/input"));
			    		 nonac.click();
			    		 Thread.sleep(500);
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[1]/div/div[2]/div[3]/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(6);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
				    	 	 
			    	 }else {System.out.println("non-ac is not selected");}
			    	 
			    	 //equipment
		    		 cell = sheet.getRow(i).getCell(9);
			    	 cell.setCellType(CellType.STRING);
			    	 String equip = cell.getStringCellValue();
			    	 int yes4 = Integer.parseInt(equip);
			    	 if(yes4==1) 
			    	 {
			    		 WebElement equipment = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[2]/div[2]/span/span[1]/span[1]/input"));
			    		 equipment.click();
			    		 Thread.sleep(500);
			    	 }else {System.out.println("Equipment is not selected");}
			    	 
			    	//floor
		    		 cell = sheet.getRow(i).getCell(10);
			    	 cell.setCellType(CellType.STRING);
			    	 String floor = cell.getStringCellValue();
			    	 int yes5 = Integer.parseInt(floor);
			    	 if(yes5==1) 
			    	 {
			    		 WebElement floor1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[3]/div[2]/span/span[1]/span[1]/input"));
			    		 floor1.click();
			    		 Thread.sleep(500);
			    		 
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[3]/div[3]/div/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(11);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
			    		 
			    	 }else {System.out.println("floor is not selected");}
			    	 
			    	//shoe
		    		 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 String shoe = cell.getStringCellValue();
			    	 int yes6 = Integer.parseInt(shoe);
			    	 if(yes6==1) 
			    	 {
			    		 WebElement shoe1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[4]/div[2]/span/span[1]/span[1]/input"));
			    		 shoe1.click();
			    		 Thread.sleep(500);
			    		 
			    		 WebElement no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div[4]/div[3]/div/div/div/div"));
			    		 no.click();
			    		 cell = sheet.getRow(i).getCell(11);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list.click();
				    	 Thread.sleep(500);
			    		 
			    	 }else {System.out.println("shoe is not selected");}
		    	 }
	    	 }
	    	 
	    	//toilet
	    	 cell = sheet.getRow(i).getCell(14);
	      	 cell.setCellType(CellType.STRING);
	      	 String ch = cell.getStringCellValue();
	      	 int toilet = Integer.parseInt(ch);
	      	 if(toilet==1)
	      	 {
	      		WebElement toilet1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[2]/div[2]/span/span[1]/span[1]/input"));
	      		toilet1.click();
	      	 }else {System.out.println("toilet is unchecked");}
	     
	    	   
	      	 cell = sheet.getRow(i).getCell(15);
	     	 cell.setCellType(CellType.STRING);
	     	 String ch1 = cell.getStringCellValue();
	     	 int parking = Integer.parseInt(ch1);
	     	 if(parking==1)
	     	 {
	     		WebElement park1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[2]/div[5]/span/span[1]/span[1]/input"));
	     		park1.click();
	     	 }else {System.out.println("parking is unchecked");}
	      	
	     	 
	     	 cell = sheet.getRow(i).getCell(16);
	     	 cell.setCellType(CellType.STRING);
	     	 String ch2 = cell.getStringCellValue();
	     	 int bib = Integer.parseInt(ch2);
	     	 if(bib==1)
	     	 {
	     		WebElement bib1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[3]/div[3]/span/span[1]/span[1]/input"));
	     		bib1.click();
	     	 }else {System.out.println("bib is unchecked");}
	     	 
	     	 
	       	 cell = sheet.getRow(i).getCell(17);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch3 = cell.getStringCellValue();
	    	 int dressing = Integer.parseInt(ch3);
	    	 if(dressing==1)
	    	 {
	    		WebElement room1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[4]/div[2]/span/span[1]/span[1]/input"));
	    		room1.click();
	    	 }else {System.out.println("Room is unchecked");}
	     	 
	    	 
	    	 cell = sheet.getRow(i).getCell(18);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch4 = cell.getStringCellValue();
	    	 int shower = Integer.parseInt(ch4);
	    	 if(shower==1)
	    	 {
	    		WebElement shower1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[4]/div[5]/span/span[1]/span[1]/input"));
	    		shower1.click();
	    	 }else {System.out.println("Shower is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(19);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch5 = cell.getStringCellValue();
	    	 int towel = Integer.parseInt(ch5);
	    	 if(towel==1)
	    	 {
	    		WebElement towel1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[5]/div[2]/span/span[1]/span[1]/input"));
	    		towel1.click();
	    	 }else {System.out.println("towel is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(20);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch6 = cell.getStringCellValue();
	    	 int locker = Integer.parseInt(ch6);
	    	 if(locker==1)
	    	 {
	    		WebElement locker1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[5]/div[5]/span/span[1]/span[1]/input"));
	    		locker1.click();
	    	 }else {System.out.println("locker is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(21);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch7 = cell.getStringCellValue();
	    	 int phonepe = Integer.parseInt(ch7);
	    	 if(phonepe==1)
	    	 {
	    		WebElement phonepe1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[6]/div[2]/span/span[1]/span[1]/input"));
	    		phonepe1.click();
	    	 }else {System.out.println("Phonepe is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(22);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch8 = cell.getStringCellValue();
	    	 int card = Integer.parseInt(ch8);
	    	 if(card==1)
	    	 {
	    		WebElement card1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[6]/div[5]/span/span[1]/span[1]/input"));
	    		card1.click();
	    	 }else {System.out.println("Phonepe is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(23);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch9 = cell.getStringCellValue();
	    	 int paytm = Integer.parseInt(ch9);
	    	 if(paytm==1)
	    	 {
	    		WebElement paytm1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[7]/div[2]/span/span[1]/span[1]/input"));
	    		paytm1.click();
	    	 }else {System.out.println("Paytm is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(24);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch10 = cell.getStringCellValue();
	    	 int gpay = Integer.parseInt(ch10);
	    	 if(gpay==1)
	    	 {
	    		WebElement gpay1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[7]/div[3]/div[2]/span/span[1]/span[1]/input"));
	    		gpay1.click();
	    	 }else {System.out.println("Gpay is unchecked");}
	    	 
	    	 WebElement Next_btn3 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]/span[1]"));
	     	 Next_btn3.click();
	     	 Thread.sleep(1000);
	    	 
	    	 
	    	 boolean error_msg = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[7]/span")).size()!=0;
	    	 
	    	 if(error_msg==true)
	    	 {
	      		  screenCapture();
	      		    sheet.getRow(i).createCell(25).setCellValue(fail);  
	      			System.out.println("Error throws in Gametype page...Kindly refer screenshot");   
	      			// Specify the file in which data needs to be written.
	      	        FileOutputStream fileOutput = new FileOutputStream(src);
	      	        // finally write content
	      	        workbook.write(fileOutput);
	      	         // close the file
	      	        fileOutput.close();
	      	        
	      		   }
	      		  else
	      		  {
	      			  sheet.getRow(i).createCell(25).setCellValue(pass);
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
  public void basket_gametype() throws IOException, InterruptedException
  {
	// Import excel sheet.
	     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\venue.xls");
	      
	     // Load the file.
	     FileInputStream finput = new FileInputStream(src);
	      
	     // Load he workbook.
	     workbook = new HSSFWorkbook(finput);
	      
	     // Load the sheet in which data is stored.
	     sheet= workbook.getSheet("basketgt");
	     
	     
	     for(int i=1; i<=sheet.getLastRowNum(); i++)
	     {
	    	 cell = sheet.getRow(i).getCell(0);
	    	 cell.setCellType(CellType.STRING);
	    	 String sec = cell.getStringCellValue();
	    	 int yes = Integer.parseInt(sec);
	    	 if(yes==1)
	    	 {
	    		 WebElement secondary = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[1]/div[2]/span/span[1]/span[1]/input"));
	    		 secondary.click();
	    		 cell = sheet.getRow(i).getCell(1);
		    	 cell.setCellType(CellType.STRING);
	    		 WebElement venues = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div/div/div[1]/div[1]"));
	    		 Thread.sleep(500);
	    		 a1.click(venues).sendKeys(cell.getStringCellValue()).sendKeys(Keys.ENTER).build().perform();
				 Thread.sleep(1000);
				 System.out.println("venue is selected");
				 
				 /*
				 //No of grounds
				 WebElement no_of_ground = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div[2]/div/div/div"));
				 no_of_ground.click();
				 cell = sheet.getRow(i).getCell(2);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	 list.click();
		    	 Thread.sleep(500);
		    	 */
		    	 
		    	 //convertable
		    	 cell = sheet.getRow(i).getCell(3);
		    	 cell.setCellType(CellType.STRING);
		    	 String con = cell.getStringCellValue();
		    	 int yes1 = Integer.parseInt(con);
		    	 
		    	 if(yes1==1)
		    	 {
		    	 WebElement convertable = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div[2]/span/span[1]/span[1]/input"));
		    	 convertable.click();
		    	 Thread.sleep(500);
		    	 
		    	 //full court
		    	 cell = sheet.getRow(i).getCell(4);
		    	 cell.setCellType(CellType.STRING);
		    	 String full = cell.getStringCellValue();
		    	 int yes2 = Integer.parseInt(full);
		    	 
		    	 if(yes2==1)
		    	 {
		    		 WebElement full_court =driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div[1]/div[2]/span/span[1]/span[1]/input"));
		    		 full_court.click();
		    		 WebElement no_of_court = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div[1]/div[3]/div/div/div"));
					 no_of_court.click();
					 cell = sheet.getRow(i).getCell(5);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list1.click();
			    	 Thread.sleep(500);
			    	 
			    	 //Ratio
			    	 WebElement no_of_full_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div/div[1]/div[2]/div/div/div"));
					 no_of_full_ratio.click();
					 cell = sheet.getRow(i).getCell(6);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list2.click();
			    	 Thread.sleep(500);
		    	 }else {System.out.println("full court is not selected");}
		    	 
		    	//Half court
		    	 cell = sheet.getRow(i).getCell(7);
		    	 cell.setCellType(CellType.STRING);
		    	 String half = cell.getStringCellValue();
		    	 int yes3 = Integer.parseInt(half);
		    	 
		    	 if(yes3==1)
		    	 {
		    		 WebElement half_court =driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div[2]/div[2]/span/span[1]/span[1]/input"));
		    		 half_court.click();
		    		 WebElement no_of_court = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div[2]/div[3]/div/div/div"));
					 no_of_court.click();
					 cell = sheet.getRow(i).getCell(8);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list1.click();
			    	 Thread.sleep(500);
			    	 
			    	 //Ratio
			    	 WebElement no_of_full_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div/div[2]/div[2]/div/div/div"));
					 no_of_full_ratio.click();
					 cell = sheet.getRow(i).getCell(9);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list2.click();
			    	 Thread.sleep(500);
		    	 }else {System.out.println("Half court is not selected");}
		    	 
		    	 //floor
		    	 cell = sheet.getRow(i).getCell(10);
		    	 cell.setCellType(CellType.STRING);
		    	 String fl = cell.getStringCellValue();
		    	 int yes4 = Integer.parseInt(fl);
		    	 
		    	 if(yes4==1)
		    	 {
		    		 WebElement floor =  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/span/span[1]/span[1]/input"));
		    		 floor.click();
		    		 
		    		 WebElement floor_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[3]/div/div/div/div"));
		    		 floor_type.click();
		    		 cell = sheet.getRow(i).getCell(11);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list2.click();
			    	 Thread.sleep(500);
		    	 }
		    	 
		    	 //Hoops
		    	 cell = sheet.getRow(i).getCell(12);
		    	 cell.setCellType(CellType.STRING);
		    	 String ho = cell.getStringCellValue();
		    	 int yes5 = Integer.parseInt(ho);
		    	 
		    	 if(yes5==1)
		    	 {
		    		 WebElement hoop =  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/span/span[1]/span[1]/input"));
		    		 hoop.click();
		    		 
		    		 WebElement hoop_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[3]/div/div/div/div"));
		    		 hoop_type.click();
		    		 cell = sheet.getRow(i).getCell(13);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list2.click();
			    	 Thread.sleep(500);
		    	 }
		    	 
		    	 //Shoes
		    	 cell = sheet.getRow(i).getCell(14);
		    	 cell.setCellType(CellType.STRING);
		    	 String sh = cell.getStringCellValue();
		    	 int yes6 = Integer.parseInt(sh);
		    	 
		    	 if(yes6==1)
		    	 {
		    		 WebElement shoe =  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[9]/div[2]/span/span[1]/span[1]/input"));
		    		 shoe.click();
		    		 
		    		 WebElement shoe_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[9]/div[3]/div/div/div/div"));
		    		 shoe_type.click();
		    		 cell = sheet.getRow(i).getCell(15);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list2.click();
			    	 Thread.sleep(500);
		    	 }
		    	 
		    }
		    	 else
		    	 {
		    		//full court
			    	 cell = sheet.getRow(i).getCell(4);
			    	 cell.setCellType(CellType.STRING);
			    	 String full = cell.getStringCellValue();
			    	 int yes2 = Integer.parseInt(full);
			    	 
			    	 if(yes2==1)
			    	 {
			    		 WebElement full_court =driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div[1]/div[2]/span/span[1]/span[1]/input"));
			    		 full_court.click();
			    		 WebElement no_of_court = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div[1]/div[3]/div/div/div"));
						 no_of_court.click();
						 cell = sheet.getRow(i).getCell(5);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list1.click();
				    	 Thread.sleep(500);
				    	 
			    	 }else {System.out.println("full court is not selected");}
			    	 
			    	//Half court
			    	 cell = sheet.getRow(i).getCell(7);
			    	 cell.setCellType(CellType.STRING);
			    	 String half = cell.getStringCellValue();
			    	 int yes3 = Integer.parseInt(half);
			    	 
			    	 if(yes3==1)
			    	 {
			    		 WebElement half_court =driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div[2]/div[2]/span/span[1]/span[1]/input"));
			    		 half_court.click();
			    		 WebElement no_of_court = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div[2]/div[3]/div/div/div"));
						 no_of_court.click();
						 cell = sheet.getRow(i).getCell(8);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list1.click();
				    	 Thread.sleep(500);
				    	 
			    	 }else {System.out.println("Half court is not selected");}
			    	 
			    	 //floor
			    	 cell = sheet.getRow(i).getCell(10);
			    	 cell.setCellType(CellType.STRING);
			    	 String fl = cell.getStringCellValue();
			    	 int yes4 = Integer.parseInt(fl);
			    	 
			    	 if(yes4==1)
			    	 {
			    		 WebElement floor =  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/span/span[1]/span[1]/input"));
			    		 floor.click();
			    		 
			    		 WebElement floor_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[3]/div/div/div/div"));
			    		 floor_type.click();
			    		 cell = sheet.getRow(i).getCell(11);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list2.click();
				    	 Thread.sleep(500);
			    	 }
			    	 
			    	 //Hoops
			    	 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 String ho = cell.getStringCellValue();
			    	 int yes5 = Integer.parseInt(ho);
			    	 
			    	 if(yes5==1)
			    	 {
			    		 WebElement hoop =  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/span/span[1]/span[1]/input"));
			    		 hoop.click();
			    		 
			    		 WebElement hoop_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[3]/div/div/div/div"));
			    		 hoop_type.click();
			    		 cell = sheet.getRow(i).getCell(13);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list2.click();
				    	 Thread.sleep(500);
			    	 }
			    	 
			    	 //Shoes
			    	 cell = sheet.getRow(i).getCell(14);
			    	 cell.setCellType(CellType.STRING);
			    	 String sh = cell.getStringCellValue();
			    	 int yes6 = Integer.parseInt(sh);
			    	 
			    	 if(yes6==1)
			    	 {
			    		 WebElement shoe =  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[9]/div[2]/span/span[1]/span[1]/input"));
			    		 shoe.click();
			    		 
			    		 WebElement shoe_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[9]/div[3]/div/div/div/div"));
			    		 shoe_type.click();
			    		 cell = sheet.getRow(i).getCell(15);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list2.click();
				    	 Thread.sleep(500);
			    	 }
		    		 
		    	 }
		    	 
	    }
	    	 else
	    	 {
	    		 /*
	    		//No of grounds
				 WebElement no_of_ground = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div[2]/div/div/div"));
				 no_of_ground.click();
				 cell = sheet.getRow(i).getCell(2);
		    	 cell.setCellType(CellType.STRING);
		    	 WebElement list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		    	 list.click();
		    	 Thread.sleep(500);
		    	 */
	    		 
		    	 //convertable
		    	 cell = sheet.getRow(i).getCell(3);
		    	 cell.setCellType(CellType.STRING);
		    	 String con = cell.getStringCellValue();
		    	 int yes1 = Integer.parseInt(con);
		    	 
		    	 if(yes1==1)
		    	 {
		    	 WebElement convertable = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div[2]/span/span[1]/span[1]/input"));
		    	 convertable.click();
		    	 Thread.sleep(500);
		    	 
		    	 //full court
		    	 cell = sheet.getRow(i).getCell(4);
		    	 cell.setCellType(CellType.STRING);
		    	 String full = cell.getStringCellValue();
		    	 int yes2 = Integer.parseInt(full);
		    	 
		    	 if(yes2==1)
		    	 {
		    		 WebElement full_court =driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div[2]/span/span[1]/span[1]/input"));
		    		 full_court.click();
		    		 WebElement no_of_court = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div[3]/div/div/div"));
					 no_of_court.click();
					 cell = sheet.getRow(i).getCell(5);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list1.click();
			    	 Thread.sleep(500);
			    	 
			    	 //Ratio
			    	 WebElement no_of_full_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div[1]/div[2]/div/div/div"));
					 no_of_full_ratio.click();
					 cell = sheet.getRow(i).getCell(6);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list2.click();
			    	 Thread.sleep(500);
		    	 }else {System.out.println("full court is not selected");}
		    	 
		    	//Half court
		    	 cell = sheet.getRow(i).getCell(7);
		    	 cell.setCellType(CellType.STRING);
		    	 String half = cell.getStringCellValue();
		    	 int yes3 = Integer.parseInt(half);
		    	 
		    	 if(yes3==1)
		    	 {
		    		 WebElement half_court =driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[2]/div[2]/span/span[1]/span[1]/input"));
		    		 half_court.click();
		    		 WebElement no_of_court = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[2]/div[3]/div/div/div"));
					 no_of_court.click();
					 cell = sheet.getRow(i).getCell(8);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list1.click();
			    	 Thread.sleep(500);
			    	 
			    	 //Ratio
			    	 WebElement no_of_full_ratio = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div[1]/div[2]/div/div/div"));
					 no_of_full_ratio.click();
					 cell = sheet.getRow(i).getCell(9);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list2.click();
			    	 Thread.sleep(500);
		    	 }else {System.out.println("Half court is not selected");}
		    	 
		    	 //floor
		    	 cell = sheet.getRow(i).getCell(10);
		    	 cell.setCellType(CellType.STRING);
		    	 String fl = cell.getStringCellValue();
		    	 int yes4 = Integer.parseInt(fl);
		    	 
		    	 if(yes4==1)
		    	 {
		    		 WebElement floor =  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[2]/span/span[1]/span[1]/input"));
		    		 floor.click();
		    		 
		    		 WebElement floor_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[3]/div/div/div/div"));
		    		 floor_type.click();
		    		 cell = sheet.getRow(i).getCell(11);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list2.click();
			    	 Thread.sleep(500);
		    	 }
		    	 
		    	 //Hoops
		    	 cell = sheet.getRow(i).getCell(12);
		    	 cell.setCellType(CellType.STRING);
		    	 String ho = cell.getStringCellValue();
		    	 int yes5 = Integer.parseInt(ho);
		    	 
		    	 if(yes5==1)
		    	 {
		    		 WebElement hoop =  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/span/span[1]/span[1]/input"));
		    		 hoop.click();
		    		 
		    		 WebElement hoop_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[3]/div/div/div/div"));
		    		 hoop_type.click();
		    		 cell = sheet.getRow(i).getCell(13);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list2.click();
			    	 Thread.sleep(500);
		    	 }
		    	 
		    	 //Shoes
		    	 cell = sheet.getRow(i).getCell(14);
		    	 cell.setCellType(CellType.STRING);
		    	 String sh = cell.getStringCellValue();
		    	 int yes6 = Integer.parseInt(sh);
		    	 
		    	 if(yes6==1)
		    	 {
		    		 WebElement shoe =  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/span/span[1]/span[1]/input"));
		    		 shoe.click();
		    		 
		    		 WebElement shoe_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[3]/div/div/div/div"));
		    		 shoe_type.click();
		    		 cell = sheet.getRow(i).getCell(15);
			    	 cell.setCellType(CellType.STRING);
			    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
			    	 list2.click();
			    	 Thread.sleep(500);
		    	 }
		    	 
		    }
		    	 else
		    	 {
		    		//full court
			    	 cell = sheet.getRow(i).getCell(4);
			    	 cell.setCellType(CellType.STRING);
			    	 String full = cell.getStringCellValue();
			    	 int yes2 = Integer.parseInt(full);
			    	 
			    	 if(yes2==1)
			    	 {
			    		 WebElement full_court =driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div[2]/span/span[1]/span[1]/input"));
			    		 full_court.click();
			    		 WebElement no_of_court = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div[3]/div/div/div"));
						 no_of_court.click();
						 cell = sheet.getRow(i).getCell(5);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list1.click();
				    	 Thread.sleep(500);
				    	 
			    	 }else {System.out.println("full court is not selected");}
			    	 
			    	//Half court
			    	 cell = sheet.getRow(i).getCell(7);
			    	 cell.setCellType(CellType.STRING);
			    	 String half = cell.getStringCellValue();
			    	 int yes3 = Integer.parseInt(half);
			    	 
			    	 if(yes3==1)
			    	 {
			    		 WebElement half_court =driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[2]/div[2]/span/span[1]/span[1]/input"));
			    		 half_court.click();
			    		 WebElement no_of_court = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[4]/div/div[2]/div[3]/div/div/div"));
						 no_of_court.click();
						 cell = sheet.getRow(i).getCell(8);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list1.click();
				    	 Thread.sleep(500);
				    	 
			    	 }else {System.out.println("Half court is not selected");}
			    	 
			    	 //floor
			    	 cell = sheet.getRow(i).getCell(10);
			    	 cell.setCellType(CellType.STRING);
			    	 String fl = cell.getStringCellValue();
			    	 int yes4 = Integer.parseInt(fl);
			    	 
			    	 if(yes4==1)
			    	 {
			    		 WebElement floor =  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[2]/span/span[1]/span[1]/input"));
			    		 floor.click();
			    		 
			    		 WebElement floor_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[6]/div[3]/div/div/div/div"));
			    		 floor_type.click();
			    		 cell = sheet.getRow(i).getCell(11);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list2.click();
				    	 Thread.sleep(500);
			    	 }
			    	 
			    	 //Hoops
			    	 cell = sheet.getRow(i).getCell(12);
			    	 cell.setCellType(CellType.STRING);
			    	 String ho = cell.getStringCellValue();
			    	 int yes5 = Integer.parseInt(ho);
			    	 
			    	 if(yes5==1)
			    	 {
			    		 WebElement hoop =  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[2]/span/span[1]/span[1]/input"));
			    		 hoop.click();
			    		 
			    		 WebElement hoop_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[7]/div[3]/div/div/div/div"));
			    		 hoop_type.click();
			    		 cell = sheet.getRow(i).getCell(13);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list2.click();
				    	 Thread.sleep(500);
			    	 }
			    	 
			    	 //Shoes
			    	 cell = sheet.getRow(i).getCell(14);
			    	 cell.setCellType(CellType.STRING);
			    	 String sh = cell.getStringCellValue();
			    	 int yes6 = Integer.parseInt(sh);
			    	 
			    	 if(yes6==1)
			    	 {
			    		 WebElement shoe =  driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[2]/span/span[1]/span[1]/input"));
			    		 shoe.click();
			    		 
			    		 WebElement shoe_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[8]/div[3]/div/div/div/div"));
			    		 shoe_type.click();
			    		 cell = sheet.getRow(i).getCell(15);
				    	 cell.setCellType(CellType.STRING);
				    	 WebElement list2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				    	 list2.click();
				    	 Thread.sleep(500);
			    	 }
		    		 
		    	 }
	    		 
	    	 }
	    	 
	    	 
	    	//toilet
	    	 cell = sheet.getRow(i).getCell(16);
	      	 cell.setCellType(CellType.STRING);
	      	 String ch = cell.getStringCellValue();
	      	 int toilet = Integer.parseInt(ch);
	      	 if(toilet==1)
	      	 {
	      		WebElement toilet1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[2]/div[2]/span/span[1]/span[1]/input"));
	      		toilet1.click();
	      	 }else {System.out.println("toilet is unchecked");}
	     
	    	   
	      	 cell = sheet.getRow(i).getCell(17);
	     	 cell.setCellType(CellType.STRING);
	     	 String ch1 = cell.getStringCellValue();
	     	 int parking = Integer.parseInt(ch1);
	     	 if(parking==1)
	     	 {
	     		WebElement park1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[2]/div[5]/span/span[1]/span[1]/input"));
	     		park1.click();
	     	 }else {System.out.println("parking is unchecked");}
	      	
	     	 
	     	 cell = sheet.getRow(i).getCell(18);
	     	 cell.setCellType(CellType.STRING);
	     	 String ch2 = cell.getStringCellValue();
	     	 int bib = Integer.parseInt(ch2);
	     	 if(bib==1)
	     	 {
	     		WebElement bib1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[3]/div[3]/span/span[1]/span[1]/input"));
	     		bib1.click();
	     	 }else {System.out.println("bib is unchecked");}
	     	 
	     	 
	       	 cell = sheet.getRow(i).getCell(19);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch3 = cell.getStringCellValue();
	    	 int dressing = Integer.parseInt(ch3);
	    	 if(dressing==1)
	    	 {
	    		WebElement room1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[4]/div[2]/span/span[1]/span[1]/input"));
	    		room1.click();
	    	 }else {System.out.println("Room is unchecked");}
	     	 
	    	 
	    	 cell = sheet.getRow(i).getCell(20);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch4 = cell.getStringCellValue();
	    	 int shower = Integer.parseInt(ch4);
	    	 if(shower==1)
	    	 {
	    		WebElement shower1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[4]/div[5]/span/span[1]/span[1]/input"));
	    		shower1.click();
	    	 }else {System.out.println("Shower is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(21);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch5 = cell.getStringCellValue();
	    	 int towel = Integer.parseInt(ch5);
	    	 if(towel==1)
	    	 {
	    		WebElement towel1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[5]/div[2]/span/span[1]/span[1]/input"));
	    		towel1.click();
	    	 }else {System.out.println("towel is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(22);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch6 = cell.getStringCellValue();
	    	 int locker = Integer.parseInt(ch6);
	    	 if(locker==1)
	    	 {
	    		WebElement locker1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[5]/div[5]/span/span[1]/span[1]/input"));
	    		locker1.click();
	    	 }else {System.out.println("locker is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(23);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch7 = cell.getStringCellValue();
	    	 int phonepe = Integer.parseInt(ch7);
	    	 if(phonepe==1)
	    	 {
	    		WebElement phonepe1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[6]/div[2]/span/span[1]/span[1]/input"));
	    		phonepe1.click();
	    	 }else {System.out.println("Phonepe is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(24);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch8 = cell.getStringCellValue();
	    	 int card = Integer.parseInt(ch8);
	    	 if(card==1)
	    	 {
	    		WebElement card1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[6]/div[5]/span/span[1]/span[1]/input"));
	    		card1.click();
	    	 }else {System.out.println("Phonepe is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(25);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch9 = cell.getStringCellValue();
	    	 int paytm = Integer.parseInt(ch9);
	    	 if(paytm==1)
	    	 {
	    		WebElement paytm1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[7]/div[2]/span/span[1]/span[1]/input"));
	    		paytm1.click();
	    	 }else {System.out.println("Paytm is unchecked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(26);
	    	 cell.setCellType(CellType.STRING);
	    	 String ch10 = cell.getStringCellValue();
	    	 int gpay = Integer.parseInt(ch10);
	    	 if(gpay==1)
	    	 {
	    		WebElement gpay1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[7]/div[3]/div[2]/span/span[1]/span[1]/input"));
	    		gpay1.click();
	    	 }else {System.out.println("Gpay is unchecked");}
	    	 
	    	 WebElement Next_btn3 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]/span[1]"));
	     	 Next_btn3.click();
	     	 Thread.sleep(1000);
	    	 
	    	 
	    	 boolean error_msg = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div/div[7]/span")).size()!=0;
	    	 
	    	 if(error_msg==true)
	    	 {
	      		  screenCapture();
	      		    sheet.getRow(i).createCell(27).setCellValue(fail);  
	      			System.out.println("Error throws in Gametype page...Kindly refer screenshot");   
	      			// Specify the file in which data needs to be written.
	      	        FileOutputStream fileOutput = new FileOutputStream(src);
	      	        // finally write content
	      	        workbook.write(fileOutput);
	      	         // close the file
	      	        fileOutput.close();
	      	        
	      		   }
	      		  else
	      		  {
	      			  sheet.getRow(i).createCell(27).setCellValue(pass);
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
  
  
  @Test(priority=3)
  public void commercial() throws InterruptedException, IOException
  {
	  
         // Import excel sheet.
	     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\venue.xls");
	      
	     // Load the file.
	     FileInputStream finput = new FileInputStream(src);
	      
	     // Load he workbook.
	     workbook = new HSSFWorkbook(finput);
	      
	     // Load the sheet in which data is stored.
	     sheet= workbook.getSheet("pricing");
	     
	     
	     for(int i=1; i<=sheet.getLastRowNum(); i++)
	     {
	    	 cell = sheet.getRow(i).getCell(0);
	    	 cell.setCellType(CellType.STRING);
	    	 WebElement commission = driver.findElement(By.xpath("//*[@id=\"commission\"]"));
	    	 commission.sendKeys(cell.getStringCellValue());
	    	 Thread.sleep(1000);
	  
	    	 cell = sheet.getRow(i).getCell(1);
	    	 cell.setCellType(CellType.STRING);
	    	 String price = cell.getStringCellValue();
	    	 int yes = Integer.parseInt(price);	    	 
	    	 
	    	 if(yes==1)
	    	 {
	    		 WebElement pricing = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[1]"));
	    		 pricing.click();
	    		 Thread.sleep(500);
	    	 }else {System.out.println("Pricing is not selected");}
	    	 
	    	 cell = sheet.getRow(i).getCell(2);
	    	 cell.setCellType(CellType.STRING);
	    	 String mon = cell.getStringCellValue();
	    	 int mond = Integer.parseInt(mon);
	    	 
	    	 if(mond==1)
	    	 {
	    		  WebElement monday = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div"));
	    		  monday.click();
	    		 
	    		  WebElement time_slot = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div/div[9]/button/span[1]"));
	    		  time_slot.click();
	    		  Thread.sleep(500);
	    		  
	 	    	 cell = sheet.getRow(i).getCell(3);
		    	 cell.setCellType(CellType.STRING);
		    	 String no = cell.getStringCellValue();
		    	 int session = Integer.parseInt(no);
		    	 int max = 2 + session;
		    	 
		    	 for(int j=2;j<max;j++)
		    	 {
		    		 if(j==2) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(4);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(5);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(6);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(7);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(8);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(6);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(7);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(6);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
	    		 
		    		 
		    		 if(j==3) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(9);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(10);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(11);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(12);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(13);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(11);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(12);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(11);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 
		    		 if(j==4) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(14);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(15);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(16);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(17);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(18);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(16);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(17);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(16);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==5) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(19);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(20);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(21);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(22);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(23);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(21);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(22);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(21);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==6) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(24);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(25);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(26);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(27);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(28);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(26);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(27);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(26);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    	 }
	    		  WebElement block = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[9]/button/span[1]"));
	    		  block.click();
	    		  Thread.sleep(1000);
	    		  System.out.println("Monday is blocked");
	    	 }else {System.out.println("Monday is not blocked");}

	    	 
	    	 cell = sheet.getRow(i).getCell(29);
	    	 cell.setCellType(CellType.STRING);
	    	 String tue = cell.getStringCellValue();
	    	 int tues = Integer.parseInt(tue);
	    	 
	    	 if(tues==1)
	    	 {
	    		  WebElement tuesday = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[3]/div"));
	    		  tuesday.click();
	    		 
	    		  WebElement time_slot = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div/div[9]/button/span[1]"));
	    		  time_slot.click();
	    		  Thread.sleep(500);
	    		  
	 	    	 cell = sheet.getRow(i).getCell(30);
		    	 cell.setCellType(CellType.STRING);
		    	 String no = cell.getStringCellValue();
		    	 int session = Integer.parseInt(no);
		    	 int max = 2 + session;
		    	 
		    	 for(int j=2;j<max;j++)
		    	 {
		    		 if(j==2) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(31);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(32);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(33);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(34);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(35);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(33);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(34);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(33);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
	    		 
		    		 
		    		 if(j==3) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(36);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(37);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(38);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(39);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(40);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(38);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(39);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(38);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 
		    		 if(j==4) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(41);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(42);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(43);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(44);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(45);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(43);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(44);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(43);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==5) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(46);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(47);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(48);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(49);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(50);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(48);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(49);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(48);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==6) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(51);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(52);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(53);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(54);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(55);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(53);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(54);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(53);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    	 }
	    		  WebElement block = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[9]/button/span[1]"));
	    		  block.click();
	    		  Thread.sleep(500);
	    		  System.out.println("Tuesday is blocked");
	    	 }else {System.out.println("Tuesday is not blocked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(56);
	    	 cell.setCellType(CellType.STRING);
	    	 String wed = cell.getStringCellValue();
	    	 int wedn = Integer.parseInt(wed);
	    	 
	    	 if(wedn==1)
	    	 {
	    		  WebElement wednesday = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[4]/div"));
	    		  wednesday.click();
	    		 
	    		  WebElement time_slot = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div/div[9]/button/span[1]"));
	    		  time_slot.click();
	    		  Thread.sleep(500);
	    		  
	 	    	 cell = sheet.getRow(i).getCell(57);
		    	 cell.setCellType(CellType.STRING);
		    	 String no = cell.getStringCellValue();
		    	 int session = Integer.parseInt(no);
		    	 int max = 2 + session;
		    	 
		    	 for(int j=2;j<max;j++)
		    	 {
		    		 if(j==2) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(58);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(59);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(60);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(61);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(62);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(60);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(61);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(60);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
	    		 
		    		 
		    		 if(j==3) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(63);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(64);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(65);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(66);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(67);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(65);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(66);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(65);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 
		    		 if(j==4) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(68);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(69);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(70);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(71);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(72);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(70);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(71);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(70);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==5) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(73);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(74);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(75);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(76);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(77);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(75);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(76);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(75);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==6) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(78);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(79);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(80);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(81);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(82);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(80);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(81);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(80);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    	 }
	    		  WebElement block = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[9]/button/span[1]"));
	    		  block.click();
	    		  Thread.sleep(500);
	    		  System.out.println("Wednesday is blocked");
	    	 }else {System.out.println("Wednesday is not blocked");}
	    	 
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(83);
	    	 cell.setCellType(CellType.STRING);
	    	 String thur = cell.getStringCellValue();
	    	 int thurs = Integer.parseInt(thur);
	    	 
	    	 if(thurs==1)
	    	 {
	    		  WebElement thursday = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[5]/div"));
	    		  thursday.click();
	    		 
	    		  WebElement time_slot = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div/div[9]/button/span[1]"));
	    		  time_slot.click();
	    		  Thread.sleep(500);
	    		  
	 	    	 cell = sheet.getRow(i).getCell(84);
		    	 cell.setCellType(CellType.STRING);
		    	 String no = cell.getStringCellValue();
		    	 int session = Integer.parseInt(no);
		    	 int max = 2 + session;
		    	 
		    	 for(int j=2;j<max;j++)
		    	 {
		    		 if(j==2) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(85);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(86);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(87);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(88);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(89);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(87);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(88);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(87);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
	    		 
		    		 
		    		 if(j==3) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(90);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(91);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(92);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(93);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(94);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(92);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(93);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(92);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 
		    		 if(j==4) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(95);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(96);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(97);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(98);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(99);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(97);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(98);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(97);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==5) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(100);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(101);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(102);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(103);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(104);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(102);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(103);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(102);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==6) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(105);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(106);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(107);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(108);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(109);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(107);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(108);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(107);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    	 }
	    		  WebElement block = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[9]/button/span[1]"));
	    		  block.click();
	    		  Thread.sleep(500);
	    		  System.out.println("Thursday is blocked");
	    	 }else {System.out.println("Thursday is not blocked");}
	    	 
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(110);
	    	 cell.setCellType(CellType.STRING);
	    	 String fri = cell.getStringCellValue();
	    	 int frid = Integer.parseInt(fri);
	    	 
	    	 if(frid==1)
	    	 {
	    		  WebElement friday = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[6]/div"));
	    		  friday.click();
	    		 
	    		  WebElement time_slot = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div/div[9]/button/span[1]"));
	    		  time_slot.click();
	    		  Thread.sleep(500);
	    		  
	 	    	 cell = sheet.getRow(i).getCell(111);
		    	 cell.setCellType(CellType.STRING);
		    	 String no = cell.getStringCellValue();
		    	 int session = Integer.parseInt(no);
		    	 int max = 2 + session;
		    	 
		    	 for(int j=2;j<max;j++)
		    	 {
		    		 if(j==2) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(112);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(113);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(114);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(115);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(116);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(114);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(115);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(114);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
	    		 
		    		 
		    		 if(j==3) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(117);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(118);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(119);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(120);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(121);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(119);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(120);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(119);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 
		    		 if(j==4) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(122);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(123);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(124);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(125);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(126);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(124);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(125);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(124);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==5) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(127);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(128);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(129);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(130);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(131);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(129);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(130);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(129);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==6) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(132);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(133);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(134);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(135);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(136);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(134);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(135);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(134);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    	 }
	    		  WebElement block = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[9]/button/span[1]"));
	    		  block.click();
	    		  Thread.sleep(500);
	    		  System.out.println("Friday is blocked");
	    	 }else {System.out.println("friday is not blocked");}
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(137);
	    	 cell.setCellType(CellType.STRING);
	    	 String sat = cell.getStringCellValue();
	    	 int satur = Integer.parseInt(sat);
	    	 
	    	 if(satur==1)
	    	 {
	    		  WebElement saturday = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[7]/div"));
	    		  saturday.click();
	    		 
	    		  WebElement time_slot = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div/div[9]/button/span[1]"));
	    		  time_slot.click();
	    		  Thread.sleep(500);
	    		  
	 	    	 cell = sheet.getRow(i).getCell(138);
		    	 cell.setCellType(CellType.STRING);
		    	 String no = cell.getStringCellValue();
		    	 int session = Integer.parseInt(no);
		    	 int max = 2 + session;
		    	 
		    	 for(int j=2;j<max;j++)
		    	 {
		    		 if(j==2) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(139);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(140);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(141);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(142);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(143);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(141);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(142);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(141);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
	    		 
		    		 
		    		 if(j==3) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(144);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(145);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(146);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(147);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(148);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(146);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(147);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(146);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 
		    		 if(j==4) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(149);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(150);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(151);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(152);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(153);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(151);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(152);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(151);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==5) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(154);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(155);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(156);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(157);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(158);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(156);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(157);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(156);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==6) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(159);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(160);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(161);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(162);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(163);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(161);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(162);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(161);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    	 }
	    		  WebElement block = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[9]/button/span[1]"));
	    		  block.click();
	    		  Thread.sleep(500);
	    		  System.out.println("Saturday is blocked");
	    	 }else {System.out.println("Saturday is not blocked");}
	    	 
	    	 
	    	 
	    	 cell = sheet.getRow(i).getCell(164);
	    	 cell.setCellType(CellType.STRING);
	    	 String sun = cell.getStringCellValue();
	    	 int sund = Integer.parseInt(sun);
	    	 
	    	 if(sund==1)
	    	 {
	    		  WebElement sunday = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[8]/div"));
	    		  sunday.click();
	    		 
	    		  WebElement time_slot = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div/div[9]/button/span[1]"));
	    		  time_slot.click();
	    		  Thread.sleep(500);
	    		  
	 	    	 cell = sheet.getRow(i).getCell(165);
		    	 cell.setCellType(CellType.STRING);
		    	 String no = cell.getStringCellValue();
		    	 int session = Integer.parseInt(no);
		    	 int max = 2 + session;
		    	 
		    	 for(int j=2;j<max;j++)
		    	 {
		    		 if(j==2) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(166);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(167);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(168);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(169);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(170);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(168);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(169);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(168);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
	    		 
		    		 
		    		 if(j==3) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(171);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(172);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(173);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(174);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(175);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(173);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(174);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(173);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 
		    		 if(j==4) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(176);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(177);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(178);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(179);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(180);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(178);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(179);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(178);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==5) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(181);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(182);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(183);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(184);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(185);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(183);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(184);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(183);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    		 if(j==6) 
		    		 {
	    		  WebElement start_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[1]/div/div"));
	    		  start_time.click();
	    		  cell = sheet.getRow(i).getCell(186);
	    	 	  cell.setCellType(CellType.STRING);
	    	 	  WebElement start_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    	 	  start_list1.click();
	    		  Thread.sleep(500);
	    		  
	    	 	  WebElement end_time = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[2]/div/div"));
	    	 	  end_time.click();
	    	 	  cell = sheet.getRow(i).getCell(187);
	    		  cell.setCellType(CellType.STRING);
	    		  WebElement end_list1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	    		  end_list1.click();
	    		  Thread.sleep(500);
	    		  
	    		  boolean price1 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input")).size()!=0;
	 	    	  boolean price2 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input")).size()!=0;
	 	    	  boolean price3 = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input")).size()!=0;
	    		  
	 	    	  if(price1==true && price2==true && price3==true)
	 	    	  {
	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
	    		  cell = sheet.getRow(i).getCell(188);
	    		  cell.setCellType(CellType.STRING);
	    		  price_one.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
	    		  cell = sheet.getRow(i).getCell(189);
	    		  cell.setCellType(CellType.STRING);
	    		  price_two.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	    		  
	    		  WebElement price_three = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[5]/div/input"));
	    		  cell = sheet.getRow(i).getCell(190);
	    		  cell.setCellType(CellType.STRING);
	    		  price_three.sendKeys(cell.getStringCellValue());
	    		  Thread.sleep(500);
	 	    	  }
	 	    	  else if (price1==true && price2==true) 
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(188);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
		    		  
		    		  WebElement price_two = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[4]/div/input"));
		    		  cell = sheet.getRow(i).getCell(189);
		    		  cell.setCellType(CellType.STRING);
		    		  price_two.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
				  }
	 	    	  else
	 	    	  {
	 	    		  WebElement price_one = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/div[3]/div/input"));
		    		  cell = sheet.getRow(i).getCell(188);
		    		  cell.setCellType(CellType.STRING);
		    		  price_one.sendKeys(cell.getStringCellValue());
		    		  Thread.sleep(500);
	 	    	  }
	 	    	  
	    		  WebElement Add = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div["+j+"]/div[2]/button[1]/span[1]"));
	    		  Add.click();
	    		  Thread.sleep(500);
		    	}
		    		 
		    	 }
	    		  WebElement block = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/div[9]/button/span[1]"));
	    		  block.click();
	    		  Thread.sleep(500);
	    		  System.out.println("Sunday is blocked");
	    	 }else {System.out.println("sunday is not blocked");}
	   	     
	     
	         System.out.println("Before next button");
	   	     WebElement Nxt_btn4 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]/span[1]"));
	   	     Nxt_btn4.click();
	   	     Thread.sleep(1000);
	   	     System.out.println("Next button is clicked");	     
	     }
  }
  @Test(priority=4)
  public void bank() throws IOException, InterruptedException 
  {
	  System.out.println("Bank method is started");
	  	 // Import excel sheet.
	     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\venue.xls");
	      
	     // Load the file.
	     FileInputStream finput = new FileInputStream(src);
	      
	     // Load he workbook.
	     workbook = new HSSFWorkbook(finput);
	      
	     // Load the sheet in which data is stored.
	     sheet= workbook.getSheetAt(3);
	     
	     for(int i=1; i<=sheet.getLastRowNum(); i++)
	     {
	    	 cell = sheet.getRow(i).getCell(0);
	         cell.setCellType(CellType.STRING);
	         WebElement Bank_name = driver.findElement(By.xpath("//*[@id=\"bankname\"]"));
	         Bank_name.clear();
	         Thread.sleep(1000);
	         Bank_name.sendKeys(cell.getStringCellValue());
	         System.out.println("Bank name is entered");
	         Thread.sleep(500);
	         
	         cell = sheet.getRow(i).getCell(1);
	         cell.setCellType(CellType.STRING);
	         WebElement Acc_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div"));
	         Acc_type.click();
	         Thread.sleep(2000);
	         WebElement Acctype_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
	         Acctype_list.click();
	         Thread.sleep(1000);
	 
	         cell = sheet.getRow(i).getCell(2);
	         cell.setCellType(CellType.STRING);
	         WebElement Acc_Name  = driver.findElement(By.xpath("//*[@id=\"accountname\"]"));
	         Acc_Name.clear();
	         Thread.sleep(500);
	         Acc_Name.sendKeys(cell.getStringCellValue());
	         System.out.println("Account name is entered");
	         Thread.sleep(500);
	  
	         cell = sheet.getRow(i).getCell(3);
	         cell.setCellType(CellType.STRING);
	         WebElement GST = driver.findElement(By.xpath("//*[@id=\"gst\"]"));
	         GST.clear();
	         Thread.sleep(500);
	         GST.sendKeys(cell.getStringCellValue());
	         System.out.println("GST is entered properly");
	         Thread.sleep(500);
	  
	         cell = sheet.getRow(i).getCell(4);
	         cell.setCellType(CellType.STRING);
	         WebElement Acc_no = driver.findElement(By.xpath("//*[@id=\"accountnumber\"]"));
	         Acc_no.clear();
	         Thread.sleep(500);
	         Acc_no.sendKeys(cell.getStringCellValue());
	         System.out.println("Accoint no. is entered");
	         Thread.sleep(500);
	  
	         cell = sheet.getRow(i).getCell(5);
	         cell.setCellType(CellType.STRING);
	         WebElement IFSC = driver.findElement(By.xpath("//*[@id=\"ifsc\"]"));
	         IFSC.clear();
	         Thread.sleep(500);
	         IFSC.sendKeys(cell.getStringCellValue());
	         System.out.println("IFSC is entered");
	         Thread.sleep(500);
	  
	  /*
	  WebElement cheque_image = driver.findElement(By.xpath("//*[@id=\"filepond--drop-label-mnl9v1fcs\"]"));
	  a1.click(cheque_image);
	  uploadFile("C:\\Users\\ashok\\OneDrive\\Pictures\\Camera Roll\\index.jpg");
	  Thread.sleep(10000);
	  */
	  WebElement finish = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]/span[1]"));
	  finish.click();
	  Thread.sleep(1000);
	  
	  
	  boolean Bankname_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/p[1]")).size()!=0;
	  boolean Accname_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[1]")).size()!=0;
	  boolean Accno_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/p[1]")).size()!=0;
	  boolean IFSC_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/p[1]")).size()!=0;
	  boolean GST_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p[1]")).size()!=0;
	  
	  if(Bankname_error==true || Accname_error==true || Accno_error==true || IFSC_error==true || GST_error==true )
	  		{
		  screenCapture();
		    sheet.getRow(i).createCell(6).setCellValue(fail);  
			System.out.println("Error throws in info page...Kindly refer screenshot");  
			screenCapture(); 
			// Specify the file in which data needs to be written.
	        FileOutputStream fileOutput = new FileOutputStream(src);
	        // finally write content
	        workbook.write(fileOutput);
	        // close the file
	        fileOutput.close();
		    }
		    else
		    {
			    sheet.getRow(i).createCell(6).setCellValue(pass);
			    System.out.println("Doesn't throw any alert in this page");
			    // Specify the file in which data needs to be written.
		        FileOutputStream fileOutput = new FileOutputStream(src);
		        // finally write content
		        workbook.write(fileOutput);
		         // close the file
		        fileOutput.close();
		   }
	  }
  }
  
  
  
  
  public void screenCapture() throws IOException 
     {
	  
	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  File screenshotName = new File ("C:\\Users\\ashok\\eclipse-workspace\\Turftown\\screenshots"+System.currentTimeMillis()+"_"+".png");
	  FileUtils.copyFile(scrFile, screenshotName);
	  Reporter.log("<br><img src='"+screenshotName+"' height='300' width='300'/><br>");  
	 }
  
  public void uploadFile(String fileLocation) throws AWTException, InterruptedException
  {
	     StringSelection stringSelection = new StringSelection(fileLocation);
	     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
      	
          //native key strokes for CTRL, V and ENTER keys
          Robot robot = new Robot();
	
          robot.keyPress(KeyEvent.VK_CONTROL);
          robot.keyPress(KeyEvent.VK_V);
          Thread.sleep(1000);
          System.out.println("path is copied");
          robot.keyRelease(KeyEvent.VK_V);
          robot.keyRelease(KeyEvent.VK_CONTROL);
          Thread.sleep(1000);
          System.out.println("path is pasted");
          robot.keyPress(KeyEvent.VK_ENTER);
          robot.keyRelease(KeyEvent.VK_ENTER);
          Thread.sleep(1000);
          System.out.println("Enter button is clicked");
      }
  
  
  @AfterTest
  public void afterTest() 
  {
	  WebElement logout_btn = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/header/div/div[2]/div/button[2]"));
	  logout_btn.click();
	  
	  driver.close();
  }

}
