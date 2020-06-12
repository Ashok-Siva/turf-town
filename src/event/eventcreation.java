package event;

import org.testng.annotations.Test;

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
import org.testng.annotations.BeforeTest;

public class eventcreation {
	
	public WebDriver driver;
	
	public Actions a1;
	
	public HSSFWorkbook workbook;
	
    public HSSFSheet sheet;
    
    public HSSFCell cell;
    
    public String pass = "pass";
    
    public String fail = "fail";
	
	public String dev_URL = "http://ec2-13-232-87-232.ap-south-1.compute.amazonaws.com/login";
	
	public String event = "http://ec2-13-232-87-232.ap-south-1.compute.amazonaws.com/app/pages/event";
	
	
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
	  
	     
	 // @Test(priority=1)
	  public void event_alert_checking() throws InterruptedException, IOException
	  {
		 driver.navigate().to(event);
		 WebElement event_button = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/button/span[1]"));
		 a1.click(event_button).build().perform();
		 Thread.sleep(3000);
		 WebElement Next_Btn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]"));
		 Next_Btn.click();
		 
		 Thread.sleep(3000);
		 
		//step1 error text											
		 WebElement withoutgame_nextbutton = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/span"));
		 
		 //without selecting a option and give next error is displaying or not
		 if(withoutgame_nextbutton.isDisplayed())
		 {
			 screenCapture();
			 String error_text = withoutgame_nextbutton.getText();
			 System.out.println("Error Text : "+error_text);
			 System.out.println("selection of games is missing.kindly select the game and proceed");
		 }
		 
	  }
	  
	  public void excel_data() throws IOException, AWTException, InterruptedException
	  {
		// Import excel sheet.
		     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\Event.xls");
		      
		     // Load the file.
		     FileInputStream finput = new FileInputStream(src);
		      
		     // Load he workbook.
		     workbook = new HSSFWorkbook(finput);
		      
		     // Load the sheet in which data is stored.
		     sheet= workbook.getSheet("info");
		     
		     for(int j=1; j<=sheet.getLastRowNum(); j++)
		     {
		    	 event_creation();
		     }
	  }
	  
	  @Test(priority=1)
	  public void event_creation() throws IOException, AWTException, InterruptedException
	  {
		  driver.navigate().to(event);
		  
		  //create event button
	   	  WebElement event_button = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/button/span[1]"));
		  a1.click(event_button).build().perform();
		  Thread.sleep(3000);
		  
		  //step 1 selection
		  
		     // Import excel sheet.
		     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\Event.xls");
		      
		     // Load the file.
		     FileInputStream finput = new FileInputStream(src);
		      
		     // Load he workbook.
		     workbook = new HSSFWorkbook(finput);
		      
		     // Load the sheet in which data is stored.
		     sheet= workbook.getSheet("info");
		     
		     
		     for(int i=1; i<=sheet.getLastRowNum(); i++)
		     {
		    	 cell = sheet.getRow(i).getCell(0);
			      cell.setCellType(CellType.STRING);
				  WebElement event_selection = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div["+cell.getStringCellValue()+"]/div/div[1]"));
				  a1.click(event_selection).build().perform();
				  WebElement Next_Btn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]"));
				  Next_Btn.click();
				  Thread.sleep(3000);
				  
				  String option = cell.getStringCellValue();
				  int num = Integer.parseInt(option);
		      
		  football_selection();
		  //cricket_selection();
		  //badmiton_selection();
		  //basketball_selection();
		  
		  //step 2 
		  
		  infopage_venue();
		  
		  //step 3
		  
		  if(num==1)
		  {
		  football_commercial();
		  }
		  else if (num==2) 
		  {
		  cricket_commercial();
		  }
		  else if (num == 3) 
		  {
		  badmiton_commercial();
		  }
		  else
		  {
		  Baskettball_commercial();  
		  }
		  
		  
		  //step 4
		  
		  bank();
	  }
	  }
	  
	  public void Edit_event() throws InterruptedException, AWTException, IOException
	  {
		//Event creation page
		  driver.navigate().to(event);
		
		  //search the event in the list
		  WebElement search_btn = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[3]/div[2]/div/div[1]/div[2]/button[1]"));
		  search_btn.click();
		  
		     // Import excel sheet.
		     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\Event.xls");
		      
		     // Load the file.
		     FileInputStream finput = new FileInputStream(src);
		      
		     // Load he workbook.
		     workbook = new HSSFWorkbook(finput);
		      
		     // Load the sheet in which data is stored.
		     sheet= workbook.getSheet("search");
		     
		     for(int i=1; i<=sheet.getLastRowNum(); i++)
		     {
		    	 
		    	 cell = sheet.getRow(i).getCell(0);
		         cell.setCellType(CellType.STRING);
		         WebElement search_input = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[3]/div[2]/div/div[1]/div[1]/div/div/div/input"));
		         search_input.sendKeys(cell.getStringCellValue());  
		     
		
		//first element in the list
		Thread.sleep(2000);
		WebElement list = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[3]/div[2]/div/div[3]/table/tbody/tr[1]/td[22]/div/button/span[1]"));
		a1.click(list).build().perform();
		Thread.sleep(2000);
		  
		  //edit
		  
		  edit_button();
		  
         //step 1 selection
		  
		  football_selection();
		  //cricket_selection();
		  //badmiton_selection();
		  //basketball_selection();
		  
		  //step 2 
		  
		  infopage_venue();

		  //step 3
		  
		  football_commercial();
		  //cricket_commercial();
		  //badmiton_commercial();
		  //Baskettball_commercial();
		  	  
		  //step 4
		  
		  bank();
		  
		  
		  sheet.getRow(i).createCell(5).setCellValue(pass);
		  System.out.println("Edit Done");
		    // Specify the file in which data needs to be written.
	        FileOutputStream fileOutput = new FileOutputStream(src);
	        // finally write content
	        workbook.write(fileOutput);
	         // close the file
	        fileOutput.close();
		     }
		  
	  }
	       
	  
	  //@Test(priority=1)
	  public void Disable_event() throws InterruptedException, IOException
	  {
		//Event creation page
		  driver.navigate().to(event);
		
		//search the event in the list
		  WebElement search_btn = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[3]/div[2]/div/div[1]/div[2]/button[1]"));
		  search_btn.click();
		  
		     // Import excel sheet.
		     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\Event.xls");
		      
		     // Load the file.
		     FileInputStream finput = new FileInputStream(src);
		      
		     // Load he workbook.
		     workbook = new HSSFWorkbook(finput);
		      
		     // Load the sheet in which data is stored.
		     sheet= workbook.getSheet("search");
		     
		     for(int i=1; i<=sheet.getLastRowNum(); i++)
		     {
		    	 
		    	 cell = sheet.getRow(i).getCell(0);
		         cell.setCellType(CellType.STRING);
		  WebElement search_input = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[3]/div[2]/div/div[1]/div[1]/div/div/div/input"));
		  search_input.sendKeys(cell.getStringCellValue());  
		  
		//first element in the list
		  Thread.sleep(2000);
		  WebElement list = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[3]/div[2]/div/div[3]/table/tbody/tr[1]/td[22]/div/button/span[1]"));
		  a1.click(list).build().perform();
		  Thread.sleep(2000);
		 
		//disable button click  
		  disable_button();
		  Thread.sleep(2000);
		  
		     cell = sheet.getRow(i).getCell(1);
	         cell.setCellType(CellType.STRING);
		  WebElement disable_yes_or_no = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[1]/div[3]/button["+cell.getStringCellValue()+"]/span[1]"));
		  a1.click(disable_yes_or_no).build().perform();
		  Thread.sleep(5000);
		  
		  WebElement no_data = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[3]/div[2]/div/div[3]/table/tbody/tr/td[2]/h3"));
		  
		  if(no_data.isDisplayed())
		  {
			  System.out.println("No events available");
		  }
		  
		  sheet.getRow(i).createCell(2).setCellValue(pass);
		  System.out.println("Disable Done");
		    // Specify the file in which data needs to be written.
	        FileOutputStream fileOutput = new FileOutputStream(src);
	        // finally write content
	        workbook.write(fileOutput);
	         // close the file
	        fileOutput.close();
		     }
	  }
	  
	  
	 // @Test(priority=1)
	  public void Delete_event() throws InterruptedException, IOException
	  {
		//Event creation page
		  driver.navigate().to(event);
		  Thread.sleep(5000);
		  
		//search the event in the list
		  WebElement search_btn = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[3]/div[2]/div/div[1]/div[2]/button[1]/span[1]"));
		  search_btn.click();
		  
		// Import excel sheet.
		     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\Event.xls");
		      
		     // Load the file.
		     FileInputStream finput = new FileInputStream(src);
		      
		     // Load he workbook.
		     workbook = new HSSFWorkbook(finput);
		      
		     // Load the sheet in which data is stored.
		     sheet= workbook.getSheet("search");
		     
		     for(int i=1; i<=sheet.getLastRowNum(); i++)
		     {
		    	 
		    	 cell = sheet.getRow(i).getCell(0);
		         cell.setCellType(CellType.STRING);
		  WebElement search_input = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[3]/div[2]/div/div[1]/div[1]/div/div/div/input"));
		  search_input.sendKeys(cell.getStringCellValue());
		  
		  
		//first element in the list
		  Thread.sleep(3000);
		  WebElement list = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[3]/div[2]/div/div[3]/table/tbody/tr[1]/td[22]/div/button/span[1]"));
		  a1.click(list).build().perform();
		  Thread.sleep(3000);
		  
		//Delete button click
		  delete_button();
		  Thread.sleep(3000);
		  
		     cell = sheet.getRow(i).getCell(3);
	         cell.setCellType(CellType.STRING);
		  WebElement delete_yes_or_no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[3]/button["+cell.getStringCellValue()+"]/span[1]"));
		  
		  a1.click(delete_yes_or_no).build().perform();
		  Thread.sleep(3000);
		  
		  WebElement no_data = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/main/section/div/div/div[1]/div/section/div[3]/div[2]/div/div[3]/table/tbody/tr/td[2]/h3"));
		  
		  if(no_data.isDisplayed())
		  {
			  System.out.println("No events available");
		  }
		  
		  sheet.getRow(i).createCell(4).setCellValue(pass);
		  System.out.println("Delete Done");
		    // Specify the file in which data needs to be written.
	        FileOutputStream fileOutput = new FileOutputStream(src);
	        // finally write content
	        workbook.write(fileOutput);
	         // close the file
	        fileOutput.close();
		     }
	  }
	  
	  public void infopage_venue() throws AWTException, InterruptedException, IOException
	  {
	     
		     // Import excel sheet.
		     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\Event.xls");
		      
		     // Load the file.
		     FileInputStream finput = new FileInputStream(src);
		      
		     // Load he workbook.
		     workbook = new HSSFWorkbook(finput);
		      
		     // Load the sheet in which data is stored.
		     sheet= workbook.getSheet("info");
		     
		     
		     for(int i=1; i<=sheet.getLastRowNum(); i++)
		     {
		    	 
		    	 cell = sheet.getRow(i).getCell(1);
		         cell.setCellType(CellType.STRING);
		         //Event Name 
		         WebElement event_Name = driver.findElement(By.xpath("//*[@id=\"name\"]"));
		         event_Name.clear();
		         event_Name.sendKeys(cell.getStringCellValue());
		         Thread.sleep(1000);
		 
		         cell = sheet.getRow(i).getCell(2);
		         cell.setCellType(CellType.STRING);
		         //Organizer
		         WebElement organizer = driver.findElement(By.xpath("//*[@id=\"organizer\"]"));
		         organizer.clear();
		         organizer.sendKeys(cell.getStringCellValue());
		 
		 
		         //Venue selection
		         cell = sheet.getRow(i).getCell(3);
		         cell.setCellType(CellType.STRING);
		     WebElement venue_type_venue = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div/div[1]/label["+cell.getStringCellValue()+"]/span[1]/span[1]/input"));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", venue_type_venue);
			 venue_type_venue.click();
			 
			 boolean vv = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/fieldset/div/div/div/div/div/div[1]/div[1]")).size()!=0;
			 if(vv == true)
			 {
				 cell = sheet.getRow(i).getCell(4);
		         cell.setCellType(CellType.STRING);
			 WebElement venue_dropdown = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/fieldset/div/div/div/div/div/div[1]/div[1]"));
			 a1.click(venue_dropdown).sendKeys(cell.getStringCellValue()).sendKeys(Keys.ENTER);
			 Thread.sleep(1000);
			 }
			 else
			 {
				 WebElement Newvenue_no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/fieldset/div/div"));
				 a1.click(Newvenue_no).build().perform();
				 cell = sheet.getRow(i).getCell(5);
		         cell.setCellType(CellType.STRING);
				 WebElement no_selection = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
				 a1.click(no_selection).build().perform();
				 System.out.println("selection of drop down value");
				 Thread.sleep(1000);
				 
				 cell = sheet.getRow(i).getCell(5);
		         cell.setCellType(CellType.STRING);
		         String num = cell.getStringCellValue();
		         int ch = Integer.parseInt(num);
		         
		         if(ch==1)
		         {
				 cell = sheet.getRow(i).getCell(6);
		         cell.setCellType(CellType.STRING);
				 //Newvenue name
				 WebElement Newvenue_name = driver.findElement(By.xpath("//*[@id=\"venue_name\"]"));
				 Newvenue_name.clear();
				 Newvenue_name.sendKeys(cell.getStringCellValue());
				 System.out.println("New venue name enetered");
				 Thread.sleep(500);
				 
				 cell = sheet.getRow(i).getCell(7);
		         cell.setCellType(CellType.STRING);
				 //Venue area
				 WebElement Newvenue_Area = driver.findElement(By.xpath("//*[@id=\"area\"]"));
				 Newvenue_Area.clear();
				 Newvenue_Area.sendKeys(cell.getStringCellValue());
				 System.out.println("New venue area enetered");
				 Thread.sleep(500);
				 
				 cell = sheet.getRow(i).getCell(8);
		         cell.setCellType(CellType.STRING);
				 //venue address
				 WebElement Newvenue_Address = driver.findElement(By.xpath("//*[@id=\"address\"]"));
				 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Newvenue_Address);
				 Newvenue_Address.clear();
				 Newvenue_Address.sendKeys(cell.getStringCellValue());
				 System.out.println("New venue address enetered");
				 Thread.sleep(500);
				 
				 cell = sheet.getRow(i).getCell(9);
		         cell.setCellType(CellType.STRING);
				 //venue pincode
				 WebElement Newvenue_pincode = driver.findElement(By.xpath("//*[@id=\"pincode\"]"));
				 Newvenue_pincode.clear();
				 Newvenue_pincode.sendKeys(cell.getStringCellValue());
				 System.out.println("New venue pincode enetered");
				 Thread.sleep(500);
		         }
		         else if (ch==2) 
		         {
		        	 cell = sheet.getRow(i).getCell(6);
			         cell.setCellType(CellType.STRING);
					 //Newvenue name
					 WebElement Newvenue_name = driver.findElement(By.xpath("//*[@id=\"venue_name\"]"));
					 Newvenue_name.clear();
					 Newvenue_name.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue name enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(7);
			         cell.setCellType(CellType.STRING);
					 //Venue area
					 WebElement Newvenue_Area = driver.findElement(By.xpath("//*[@id=\"area\"]"));
					 Newvenue_Area.clear();
					 Newvenue_Area.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue area enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(8);
			         cell.setCellType(CellType.STRING);
					 //venue address
					 WebElement Newvenue_Address = driver.findElement(By.xpath("//*[@id=\"address\"]"));
					 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Newvenue_Address);
					 Newvenue_Address.clear();
					 Newvenue_Address.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue address enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(9);
			         cell.setCellType(CellType.STRING);
					 //venue pincode
					 WebElement Newvenue_pincode = driver.findElement(By.xpath("//*[@id=\"pincode\"]"));
					 Newvenue_pincode.clear();
					 Newvenue_pincode.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue pincode enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(10);
			         cell.setCellType(CellType.STRING);
					 //Newvenue name
					 WebElement Newvenue_name2 = driver.findElement(By.xpath("//*[@id=\"venue_name\"]"));
					 Newvenue_name2.clear();
					 Newvenue_name2.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue name enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(11);
			         cell.setCellType(CellType.STRING);
					 //Venue area
					 WebElement Newvenue_Area2 = driver.findElement(By.xpath("//*[@id=\"area\"]"));
					 Newvenue_Area2.clear();
					 Newvenue_Area2.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue area enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(12);
			         cell.setCellType(CellType.STRING);
					 //venue address
					 WebElement Newvenue_Address2 = driver.findElement(By.xpath("//*[@id=\"address\"]"));
					 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Newvenue_Address2);
					 Newvenue_Address2.clear();
					 Newvenue_Address2.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue address enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(13);
			         cell.setCellType(CellType.STRING);
					 //venue pincode
					 WebElement Newvenue_pincode2 = driver.findElement(By.xpath("//*[@id=\"pincode\"]"));
					 Newvenue_pincode2.clear();
					 Newvenue_pincode2.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue pincode enetered");
					 Thread.sleep(500);
				}
		         else if (ch==3) 
		         {
		        	 cell = sheet.getRow(i).getCell(6);
			         cell.setCellType(CellType.STRING);
					 //Newvenue name
					 WebElement Newvenue_name = driver.findElement(By.xpath("//*[@id=\"venue_name\"]"));
					 Newvenue_name.clear();
					 Newvenue_name.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue name enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(7);
			         cell.setCellType(CellType.STRING);
					 //Venue area
					 WebElement Newvenue_Area = driver.findElement(By.xpath("//*[@id=\"area\"]"));
					 Newvenue_Area.clear();
					 Newvenue_Area.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue area enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(8);
			         cell.setCellType(CellType.STRING);
					 //venue address
					 WebElement Newvenue_Address = driver.findElement(By.xpath("//*[@id=\"address\"]"));
					 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Newvenue_Address);
					 Newvenue_Address.clear();
					 Newvenue_Address.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue address enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(9);
			         cell.setCellType(CellType.STRING);
					 //venue pincode
					 WebElement Newvenue_pincode = driver.findElement(By.xpath("//*[@id=\"pincode\"]"));
					 Newvenue_pincode.clear();
					 Newvenue_pincode.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue pincode enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(10);
			         cell.setCellType(CellType.STRING);
					 //Newvenue name
					 WebElement Newvenue_name2 = driver.findElement(By.xpath("//*[@id=\"venue_name\"]"));
					 Newvenue_name2.clear();
					 Newvenue_name2.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue name enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(11);
			         cell.setCellType(CellType.STRING);
					 //Venue area
					 WebElement Newvenue_Area2 = driver.findElement(By.xpath("//*[@id=\"area\"]"));
					 Newvenue_Area2.clear();
					 Newvenue_Area2.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue area enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(12);
			         cell.setCellType(CellType.STRING);
					 //venue address
					 WebElement Newvenue_Address2 = driver.findElement(By.xpath("//*[@id=\"address\"]"));
					 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Newvenue_Address2);
					 Newvenue_Address2.clear();
					 Newvenue_Address2.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue address enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(13);
			         cell.setCellType(CellType.STRING);
					 //venue pincode
					 WebElement Newvenue_pincode2 = driver.findElement(By.xpath("//*[@id=\"pincode\"]"));
					 Newvenue_pincode2.clear();
					 Newvenue_pincode2.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue pincode enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(14);
			         cell.setCellType(CellType.STRING);
					 //Newvenue name
					 WebElement Newvenue_name3 = driver.findElement(By.xpath("//*[@id=\"venue_name\"]"));
					 Newvenue_name3.clear();
					 Newvenue_name3.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue name enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(15);
			         cell.setCellType(CellType.STRING);
					 //Venue area
					 WebElement Newvenue_Area3 = driver.findElement(By.xpath("//*[@id=\"area\"]"));
					 Newvenue_Area3.clear();
					 Newvenue_Area3.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue area enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(16);
			         cell.setCellType(CellType.STRING);
					 //venue address
					 WebElement Newvenue_Address3 = driver.findElement(By.xpath("//*[@id=\"address\"]"));
					 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Newvenue_Address3);
					 Newvenue_Address3.clear();
					 Newvenue_Address3.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue address enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(17);
			         cell.setCellType(CellType.STRING);
					 //venue pincode
					 WebElement Newvenue_pincode3 = driver.findElement(By.xpath("//*[@id=\"pincode\"]"));
					 Newvenue_pincode3.clear();
					 Newvenue_pincode3.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue pincode enetered");
					 Thread.sleep(500);
				}
		         else 
		         {
		        	 cell = sheet.getRow(i).getCell(6);
			         cell.setCellType(CellType.STRING);
					 //Newvenue name
					 WebElement Newvenue_name = driver.findElement(By.xpath("//*[@id=\"venue_name\"]"));
					 Newvenue_name.clear();
					 Newvenue_name.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue name enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(7);
			         cell.setCellType(CellType.STRING);
					 //Venue area
					 WebElement Newvenue_Area = driver.findElement(By.xpath("//*[@id=\"area\"]"));
					 Newvenue_Area.clear();
					 Newvenue_Area.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue area enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(8);
			         cell.setCellType(CellType.STRING);
					 //venue address
					 WebElement Newvenue_Address = driver.findElement(By.xpath("//*[@id=\"address\"]"));
					 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Newvenue_Address);
					 Newvenue_Address.clear();
					 Newvenue_Address.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue address enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(9);
			         cell.setCellType(CellType.STRING);
					 //venue pincode
					 WebElement Newvenue_pincode = driver.findElement(By.xpath("//*[@id=\"pincode\"]"));
					 Newvenue_pincode.clear();
					 Newvenue_pincode.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue pincode enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(10);
			         cell.setCellType(CellType.STRING);
					 //Newvenue name
					 WebElement Newvenue_name2 = driver.findElement(By.xpath("//*[@id=\"venue_name\"]"));
					 Newvenue_name2.clear();
					 Newvenue_name2.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue name enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(11);
			         cell.setCellType(CellType.STRING);
					 //Venue area
					 WebElement Newvenue_Area2 = driver.findElement(By.xpath("//*[@id=\"area\"]"));
					 Newvenue_Area2.clear();
					 Newvenue_Area2.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue area enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(12);
			         cell.setCellType(CellType.STRING);
					 //venue address
					 WebElement Newvenue_Address2 = driver.findElement(By.xpath("//*[@id=\"address\"]"));
					 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Newvenue_Address2);
					 Newvenue_Address2.clear();
					 Newvenue_Address2.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue address enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(13);
			         cell.setCellType(CellType.STRING);
					 //venue pincode
					 WebElement Newvenue_pincode2 = driver.findElement(By.xpath("//*[@id=\"pincode\"]"));
					 Newvenue_pincode2.clear();
					 Newvenue_pincode2.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue pincode enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(14);
			         cell.setCellType(CellType.STRING);
					 //Newvenue name
					 WebElement Newvenue_name3 = driver.findElement(By.xpath("//*[@id=\"venue_name\"]"));
					 Newvenue_name3.clear();
					 Newvenue_name3.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue name enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(15);
			         cell.setCellType(CellType.STRING);
					 //Venue area
					 WebElement Newvenue_Area3 = driver.findElement(By.xpath("//*[@id=\"area\"]"));
					 Newvenue_Area3.clear();
					 Newvenue_Area3.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue area enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(16);
			         cell.setCellType(CellType.STRING);
					 //venue address
					 WebElement Newvenue_Address3 = driver.findElement(By.xpath("//*[@id=\"address\"]"));
					 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Newvenue_Address3);
					 Newvenue_Address3.clear();
					 Newvenue_Address3.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue address enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(17);
			         cell.setCellType(CellType.STRING);
					 //venue pincode
					 WebElement Newvenue_pincode3 = driver.findElement(By.xpath("//*[@id=\"pincode\"]"));
					 Newvenue_pincode3.clear();
					 Newvenue_pincode3.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue pincode enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(18);
			         cell.setCellType(CellType.STRING);
					 //Newvenue name
					 WebElement Newvenue_name4 = driver.findElement(By.xpath("//*[@id=\"venue_name\"]"));
					 Newvenue_name4.clear();
					 Newvenue_name4.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue name enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(19);
			         cell.setCellType(CellType.STRING);
					 //Venue area
					 WebElement Newvenue_Area4 = driver.findElement(By.xpath("//*[@id=\"area\"]"));
					 Newvenue_Area4.clear();
					 Newvenue_Area4.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue area enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(20);
			         cell.setCellType(CellType.STRING);
					 //venue address
					 WebElement Newvenue_Address4 = driver.findElement(By.xpath("//*[@id=\"address\"]"));
					 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Newvenue_Address4);
					 Newvenue_Address4.clear();
					 Newvenue_Address4.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue address enetered");
					 Thread.sleep(500);
					 
					 cell = sheet.getRow(i).getCell(21);
			         cell.setCellType(CellType.STRING);
					 //venue pincode
					 WebElement Newvenue_pincode4 = driver.findElement(By.xpath("//*[@id=\"pincode\"]"));
					 Newvenue_pincode4.clear();
					 Newvenue_pincode4.sendKeys(cell.getStringCellValue());
					 System.out.println("New venue pincode enetered");
					 Thread.sleep(500);
		         }
			 }
		 
		 
		 		cell = sheet.getRow(i).getCell(22);
		 		cell.setCellType(CellType.STRING);
		 		//Contact number
		 		WebElement Contact_number = driver.findElement(By.xpath("//*[@id=\"phone\"]"));
		 		Contact_number.clear();
		 		Contact_number.sendKeys(cell.getStringCellValue());
		 		Thread.sleep(1000);
		 
		 		cell = sheet.getRow(i).getCell(23);
		        cell.setCellType(CellType.STRING);
		        //Email
		        WebElement venue_email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		        venue_email.clear();
		        venue_email.sendKeys(cell.getStringCellValue());
		        Thread.sleep(1000);
		     	
		 /*
		 //profile picture upload
		 WebElement upload_picture = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div[5]/label/img"));
		 a1.click(upload_picture);
		 Thread.sleep(35000);
		 uploadFile("C:\\Users\\ashok\\OneDrive\\Pictures\\Camera Roll\\index.jpg");
		 */
		        
		         WebElement Next_Btn2 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]"));
				 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Next_Btn2);
				 a1.click(Next_Btn2).build().perform();
				 Thread.sleep(3000);
				 
				 boolean eventname_error = driver.findElements(By.xpath("//*[@id=\"name-helper-text\"]")).size()!=0;
			  	 boolean venuetype_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div/div[2]")).size()!=0;
				 boolean venuenotselecting_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/fieldset/div/div/div/div/span")).size()!=0;
				 boolean organizer_error = driver.findElements(By.xpath("//*[@id=\"organizer-helper-text\"]")).size()!=0;
				 boolean contactno_error = driver.findElements(By.xpath("//*[@id=\"phone-helper-text\"]")).size()!=0;
				 boolean email_error = driver.findElements(By.xpath("//*[@id=\"email-helper-text\"]")).size()!=0;
				 boolean venuename_error = driver.findElements(By.xpath("//*[@id=\"venue_name-helper-text\"]")).size()!=0;
			     boolean venuearea_error = driver.findElements(By.xpath("//*[@id=\"area-helper-text\"]")).size()!=0;
			     boolean venueaddress_error = driver.findElements(By.xpath("//*[@id=\"address-helper-text\"]")).size()!=0;
				 boolean venuepincode_error = driver.findElements(By.xpath("//*[@id=\"pincode-helper-text\"]")).size()!=0;
				
				  if(eventname_error==true || venuetype_error==true || venuenotselecting_error==true || organizer_error==true || contactno_error==true || email_error==true || venuename_error==true || venuearea_error==true || venueaddress_error==true || venuepincode_error==true )
				  {
					screenCapture();  
					sheet.getRow(i).createCell(24).setCellValue(fail);  
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
					  sheet.getRow(i).createCell(24).setCellValue(pass);
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
	  
	  
	  
	  public void football_selection() throws InterruptedException
	  {
		  WebElement football = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/div[1]"));
		  a1.click(football).build().perform();
		  WebElement Next_Btn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]"));
		  Next_Btn.click();
		  Thread.sleep(3000);
	  }
	  
	  
	  public void cricket_selection() throws InterruptedException
	  {
		  WebElement cricket = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div/div[1]"));
		  a1.click(cricket).build().perform();
		  WebElement Next_Btn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]"));
		  Next_Btn.click();
		  Thread.sleep(3000);
	  }
	  
	  
	  public void badmiton_selection() throws InterruptedException
	  {
		  WebElement badmiton = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[3]/div/div[1]"));
		  a1.click(badmiton).build().perform();
		  WebElement Next_Btn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]"));
		  Next_Btn.click();
		  Thread.sleep(3000);
	  }
	  
	  
	  public void basketball_selection() throws InterruptedException
	  {
		  WebElement basketball = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[4]/div/div[1]"));
		  a1.click(basketball).build().perform();
		  WebElement Next_Btn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]"));
		  Next_Btn.click();
		  Thread.sleep(3000);
	  }
	  
	  
	  public void football_commercial() throws InterruptedException, IOException
	  {
		     // Import excel sheet.
		     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\Event.xls");
		      
		     // Load the file.
		     FileInputStream finput = new FileInputStream(src);
		      
		     // Load he workbook.
		     workbook = new HSSFWorkbook(finput);
		      
		     // Load the sheet in which data is stored.
		     sheet= workbook.getSheet("football_commercial");
		     
		     for(int i=1; i<=sheet.getLastRowNum(); i++)
		     {
		    	 
		    	 cell = sheet.getRow(i).getCell(0);
		         cell.setCellType(CellType.STRING);
		         WebElement Age = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[1]/div/div/div/div"));
		         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Age);
		         //a1.moveToElement(Age).click().sendKeys(Keys.ENTER).build().perform();
		         a1.click(Age).build().perform();
		         Thread.sleep(1000);
		         WebElement Age_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(Age_list).build().perform();
		         System.out.println("Age category is selected");
		         Thread.sleep(1000);
		  
		         cell = sheet.getRow(i).getCell(1);
		         cell.setCellType(CellType.STRING);
		         WebElement Gender = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div/div"));
		         a1.click(Gender).build().perform();
		         Thread.sleep(1000);
		         WebElement Gender_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(Gender_list).build().perform();
		         System.out.println("Gender category is selected");
		         Thread.sleep(1000);
		  
		         cell = sheet.getRow(i).getCell(2);
		         cell.setCellType(CellType.STRING);
		         WebElement typeofgame = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div/div/div"));
		         a1.click(typeofgame).build().perform();
		         Thread.sleep(1000);
		         WebElement typeofgame_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(typeofgame_list).build().perform();
		         System.out.println("Type of game is selected");
		         Thread.sleep(1000);
		  
		         cell = sheet.getRow(i).getCell(3);
		         cell.setCellType(CellType.STRING);
		         WebElement Noofteams = driver.findElement(By.xpath("//*[@id=\"noofteams\"]"));
		         Noofteams.clear();
		         Thread.sleep(500);
		         Noofteams.sendKeys(cell.getStringCellValue());
		         System.out.println("No of team is mentioned");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(4);
		         cell.setCellType(CellType.STRING);
		         WebElement format = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div/div/div"));
		         a1.click(format).build().perform();
		         Thread.sleep(1000);
		         WebElement format_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(format_list).build().perform();
		         System.out.println("Format is mentioneds");
		         Thread.sleep(1000);

		         cell = sheet.getRow(i).getCell(5);
		         cell.setCellType(CellType.STRING);
		         WebElement entryfee = driver.findElement(By.xpath("//*[@id=\"entryfee\"]"));
		         entryfee.clear();
		         Thread.sleep(500);
		         entryfee.sendKeys(cell.getStringCellValue());
		         System.out.println("Entry fee is mentioned");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(6);
		         cell.setCellType(CellType.STRING);
		         WebElement halflength = driver.findElement(By.xpath("//*[@id=\"halflength\"]"));
		         halflength.clear();
		         Thread.sleep(500);
		         halflength.sendKeys(cell.getStringCellValue());
		         System.out.println("halflength is mentioned");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(7);
		         cell.setCellType(CellType.STRING);
		         WebElement winner = driver.findElement(By.xpath("//*[@id=\"winner\"]"));
		         winner.clear();
		         Thread.sleep(500);
		         winner.sendKeys(cell.getStringCellValue());
		         System.out.println("winner prize amount is given");
		         Thread.sleep(500);
		         
		         cell = sheet.getRow(i).getCell(8);
		         cell.setCellType(CellType.STRING);
		  WebElement rollingsub = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div/div/label["+cell.getStringCellValue()+"]/span[2]"));
		  rollingsub.click();
		  
		  WebElement rollfix_no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/fieldset/div/div/div/div/div"));
		  a1.click(rollfix_no).build().perform();
		  		cell = sheet.getRow(i).getCell(9);
	            cell.setCellType(CellType.STRING);
		  WebElement no_selection = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		  a1.click(no_selection).build().perform();
		  System.out.println("Number is selected");
		  Thread.sleep(2000);
		  
		  		cell = sheet.getRow(i).getCell(10);
	            cell.setCellType(CellType.STRING);
	            WebElement runner = driver.findElement(By.xpath("//*[@id=\"runnerup\"]"));
	            runner.clear();
	            runner.sendKeys(cell.getStringCellValue());
	            System.out.println("runner prize amount is given");
	            Thread.sleep(500);
		  
	            cell = sheet.getRow(i).getCell(11);
		        cell.setCellType(CellType.STRING);
		        WebElement Description = driver.findElement(By.xpath("//*[@id=\"description\"]"));
		        Description.clear();
		        Description.sendKeys(cell.getStringCellValue());
		        System.out.println("description is filled");
		        Thread.sleep(500);
		  
		        WebElement Nextbtn3 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]/span[1]"));
		        Nextbtn3.click();
		        System.out.println("Next button is clicked");
		        Thread.sleep(5000);
	  		
	  		  boolean Age_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[1]/span")).size()!=0;
			  boolean typegame_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/span")).size()!=0;
			  boolean format_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/span")).size()!=0;
			  boolean description_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/p[1]")).size()!=0;
			  boolean winner_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/p[1]")).size()!=0;
			  boolean runner_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/p[1]")).size()!=0;
			  
	  		  if(Age_error==true || typegame_error==true || format_error==true || description_error==true || winner_error==true || runner_error==true)
	  		  		{
	  			    screenCapture();
					sheet.getRow(i).createCell(12).setCellValue(fail);  
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
					  sheet.getRow(i).createCell(12).setCellValue(pass);
					  System.out.println("Doesn't throw any alert in this page");
					    // Specify the file in which data needs to be written.
				        FileOutputStream fileOutput = new FileOutputStream(src);
				        // finally write content
				        workbook.write(fileOutput);
				         // close the file
				        fileOutput.close();
				        System.out.println("file is closed");
				  }
	  		  System.out.println("if is closed");
		     }
		     System.out.println("for loop is closed");
	  }
	  
	  
	  public void cricket_commercial() throws InterruptedException, IOException
	  {

		// Import excel sheet.
		     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\Event.xls");
		      
		     // Load the file.
		     FileInputStream finput = new FileInputStream(src);
		      
		     // Load he workbook.
		     workbook = new HSSFWorkbook(finput);
		      
		     // Load the sheet in which data is stored.
		     sheet= workbook.getSheet("cricket_commercial");
		     
		     for(int i=1; i<=sheet.getLastRowNum(); i++)
		     {
		    	 
		    	 cell = sheet.getRow(i).getCell(0);
		         cell.setCellType(CellType.STRING);
		         WebElement Age = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[1]/div/div/div/div"));
		         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Age);
		         //a1.moveToElement(Age).click().sendKeys(Keys.ENTER).build().perform();
		         a1.click(Age).build().perform();
		         Thread.sleep(1000);
		         WebElement Age_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(Age_list).build().perform();
		         System.out.println("Age category is selected");
		         Thread.sleep(1000);
		  
		         cell = sheet.getRow(i).getCell(1);
		         cell.setCellType(CellType.STRING);
		         WebElement Gender = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div/div"));
		         a1.click(Gender).build().perform();
		         Thread.sleep(1000);
		         WebElement Gender_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(Gender_list).build().perform();
		         System.out.println("Gender category is selected");
		         Thread.sleep(1000);
		  
		         cell = sheet.getRow(i).getCell(2);
		         cell.setCellType(CellType.STRING);
		         WebElement typeofgame = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div/div/div"));
		         a1.click(typeofgame).build().perform();
		         Thread.sleep(1000);
		         WebElement typeofgame_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(typeofgame_list).build().perform();
		         System.out.println("Type of game is selected");
		         Thread.sleep(1000);
		  
		         cell = sheet.getRow(i).getCell(3);
		         cell.setCellType(CellType.STRING);
		         WebElement Noofteams = driver.findElement(By.xpath("//*[@id=\"noofteams\"]"));
		         Noofteams.clear();
		         Thread.sleep(500);
		         Noofteams.sendKeys(cell.getStringCellValue());
		         System.out.println("No of team is mentioned");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(4);
		         cell.setCellType(CellType.STRING);
		         WebElement format = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div/div/div"));
		         a1.click(format).build().perform();
		         Thread.sleep(1000);
		         WebElement format_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(format_list).build().perform();
		         System.out.println("Format is mentioneds");
		         Thread.sleep(1000);

		         cell = sheet.getRow(i).getCell(5);
		         cell.setCellType(CellType.STRING);
		         WebElement entryfee = driver.findElement(By.xpath("//*[@id=\"entryfee\"]"));
		         entryfee.clear();
		         Thread.sleep(500);
		         entryfee.sendKeys(cell.getStringCellValue());
		         System.out.println("Entry fee is mentioned");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(6);
		         cell.setCellType(CellType.STRING);
		         WebElement noofovers = driver.findElement(By.xpath("//*[@id=\"noofovers\"]"));
		         noofovers.clear();
		         Thread.sleep(500);
		         noofovers.sendKeys(cell.getStringCellValue());
		         System.out.println("No of overs is entered");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(7);
		         cell.setCellType(CellType.STRING);
		         WebElement ball_type = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div/div/div"));
		         a1.click(ball_type).build().perform();
		         Thread.sleep(500);
		         WebElement balltype_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(balltype_list).build().perform();
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(8);
		         cell.setCellType(CellType.STRING);
		         WebElement winner = driver.findElement(By.xpath("//*[@id=\"winner\"]"));
		         winner.clear();
		         Thread.sleep(500);
		         winner.sendKeys(cell.getStringCellValue());
		         System.out.println("winner prize amount is given");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(9);
		         cell.setCellType(CellType.STRING);
		         WebElement runner = driver.findElement(By.xpath("//*[@id=\"runnerup\"]"));
		         runner.clear();
		         runner.sendKeys(cell.getStringCellValue());
		         System.out.println("runner prize amount is given");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(10);
		         cell.setCellType(CellType.STRING);
		         WebElement Description = driver.findElement(By.xpath("//*[@id=\"description\"]"));
		         Description.clear();
		         Description.sendKeys(cell.getStringCellValue());
		         System.out.println("description is filled");
		         Thread.sleep(500);
		  
		    WebElement Nextbtn3 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]/span[1]"));
	  		Nextbtn3.click();
	  		System.out.println("Next button is clicked");
	  		Thread.sleep(1000);
	  		
	  		  boolean Age_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[1]/span")).size()!=0;
			  boolean typegame_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/span")).size()!=0;
			  boolean format_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/span")).size()!=0;
			  boolean description_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/p[1]")).size()!=0;
			  boolean winner_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/p[1]")).size()!=0;
			  boolean runner_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/p[1]")).size()!=0;
			  
	  		  
	  			if(Age_error==true || typegame_error==true || format_error==true || description_error==true || winner_error==true || runner_error==true)
  		  		{
	  				screenCapture();
				sheet.getRow(i).createCell(11).setCellValue(fail);  
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
				  sheet.getRow(i).createCell(11).setCellValue(pass);
				  System.out.println("Doesn't throw any alert in this page");
				    // Specify the file in which data needs to be written.
			        FileOutputStream fileOutput = new FileOutputStream(src);
			        // finally write content
			        workbook.write(fileOutput);
			         // close the file
			        fileOutput.close();
			        System.out.println("file is closed");
			  }
  		  System.out.println("if is closed");
		     }   
	  }
	  
	  
	  public void badmiton_commercial() throws InterruptedException, IOException
	  {
		// Import excel sheet.
		     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\Event.xls");
		      
		     // Load the file.
		     FileInputStream finput = new FileInputStream(src);
		      
		     // Load he workbook.
		     workbook = new HSSFWorkbook(finput);
		      
		     // Load the sheet in which data is stored.
		     sheet= workbook.getSheet("badmiton_commercial");
		     
		     for(int i=1; i<=sheet.getLastRowNum(); i++)
		     {
		    	 
		    	 cell = sheet.getRow(i).getCell(0);
		         cell.setCellType(CellType.STRING);
		         WebElement Age = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[1]/div/div/div/div"));
		         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Age);
		         //a1.moveToElement(Age).click().sendKeys(Keys.ENTER).build().perform();
		         a1.click(Age).build().perform();
		         Thread.sleep(1000);
		         WebElement Age_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(Age_list).build().perform();
		         System.out.println("Age category is selected");
		         Thread.sleep(1000);
		  
		         cell = sheet.getRow(i).getCell(1);
		         cell.setCellType(CellType.STRING);
		         WebElement Gender = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div/div"));
		         a1.click(Gender).build().perform();
		         Thread.sleep(1000);
		         WebElement Gender_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(Gender_list).build().perform();
		         System.out.println("Gender category is selected");
		         Thread.sleep(1000);
		  
		         cell = sheet.getRow(i).getCell(2);
		         cell.setCellType(CellType.STRING);
		         WebElement typeofgame = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div/div/div"));
		         a1.click(typeofgame).build().perform();
		         Thread.sleep(1000);
		         WebElement typeofgame_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(typeofgame_list).build().perform();
		         System.out.println("Type of game is selected");
		         Thread.sleep(1000);
		  
		         cell = sheet.getRow(i).getCell(3);
		         cell.setCellType(CellType.STRING);
		         WebElement Noofteams = driver.findElement(By.xpath("//*[@id=\"noofteams\"]"));
		         Noofteams.clear();
		         Thread.sleep(500);
		         Noofteams.sendKeys(cell.getStringCellValue());
		         System.out.println("No of team is mentioned");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(4);
		         cell.setCellType(CellType.STRING);
		         WebElement format = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div/div/div"));
		         a1.click(format).build().perform();
		         Thread.sleep(1000);
		         WebElement format_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(format_list).build().perform();
		         System.out.println("Format is mentioneds");
		         Thread.sleep(1000);

		         cell = sheet.getRow(i).getCell(5);
		         cell.setCellType(CellType.STRING);
		         WebElement entryfee = driver.findElement(By.xpath("//*[@id=\"entryfee\"]"));
		         entryfee.clear();
		         Thread.sleep(500);
		         entryfee.sendKeys(cell.getStringCellValue());
		         System.out.println("Entry fee is mentioned");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(6);
		         cell.setCellType(CellType.STRING);
		         WebElement Gameto = driver.findElement(By.xpath("//*[@id=\"gameto\"]"));
		         Gameto.clear();
		         Thread.sleep(500);
		         Gameto.sendKeys(cell.getStringCellValue());
		         System.out.println("Gameto is entered");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(7);
		         cell.setCellType(CellType.STRING);
		         WebElement Bestof = driver.findElement(By.xpath("//*[@id=\"bestof\"]"));
		         Bestof.clear();
		         Thread.sleep(500);
		         Bestof.sendKeys(cell.getStringCellValue());
		         System.out.println("Bestof is entered");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(8);
		         cell.setCellType(CellType.STRING);
		         WebElement winner = driver.findElement(By.xpath("//*[@id=\"winner\"]"));
		         winner.clear();
		         Thread.sleep(500);
		         winner.sendKeys(cell.getStringCellValue());
		         System.out.println("winner prize amount is given");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(9);
		         cell.setCellType(CellType.STRING);
		         WebElement runner = driver.findElement(By.xpath("//*[@id=\"runnerup\"]"));
		         runner.clear();
		         runner.sendKeys(cell.getStringCellValue());
		         System.out.println("runner prize amount is given");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(10);
		         cell.setCellType(CellType.STRING);
		         WebElement Description = driver.findElement(By.xpath("//*[@id=\"description\"]"));
		         Description.clear();
		         Description.sendKeys(cell.getStringCellValue());
		         System.out.println("description is filled");
		         Thread.sleep(500);
		 
		    WebElement Nextbtn3 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]/span[1]"));
	  		Nextbtn3.click();
	  		System.out.println("Next button is clicked");
	  		Thread.sleep(1000);
	  		
	  		  boolean Age_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[1]/span")).size()!=0;
			  boolean typegame_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/span")).size()!=0;
			  boolean format_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/span")).size()!=0;
			  boolean description_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/p[1]")).size()!=0;
			  boolean winner_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/p[1]")).size()!=0;
			  boolean runner_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/p[1]")).size()!=0;
			  
	  			if(Age_error==true || typegame_error==true || format_error==true || description_error==true || winner_error==true || runner_error==true)
  		  		{
	  			screenCapture();	
				sheet.getRow(i).createCell(11).setCellValue(fail);  
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
				  sheet.getRow(i).createCell(11).setCellValue(pass);
				  System.out.println("Doesn't throw any alert in this page");
				    // Specify the file in which data needs to be written.
			        FileOutputStream fileOutput = new FileOutputStream(src);
			        // finally write content
			        workbook.write(fileOutput);
			         // close the file
			        fileOutput.close();
			        System.out.println("file is closed");
			  }
  		  System.out.println("if is closed");
		     }
	  }
	  
	  public void Baskettball_commercial() throws InterruptedException, IOException
	  {
		// Import excel sheet.
		     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\Event.xls");
		      
		     // Load the file.
		     FileInputStream finput = new FileInputStream(src);
		      
		     // Load he workbook.
		     workbook = new HSSFWorkbook(finput);
		      
		     // Load the sheet in which data is stored.
		     sheet= workbook.getSheet("football_commercial");
		     
		     for(int i=1; i<=sheet.getLastRowNum(); i++)
		     {
		    	 
		    	 cell = sheet.getRow(i).getCell(0);
		         cell.setCellType(CellType.STRING);
		         WebElement Age = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[1]/div/div/div/div"));
		         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Age);
		         //a1.moveToElement(Age).click().sendKeys(Keys.ENTER).build().perform();
		         a1.click(Age).build().perform();
		         Thread.sleep(1000);
		         WebElement Age_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(Age_list).build().perform();
		         System.out.println("Age category is selected");
		         Thread.sleep(1000);
		  
		         cell = sheet.getRow(i).getCell(1);
		         cell.setCellType(CellType.STRING);
		         WebElement Gender = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div/div"));
		         a1.click(Gender).build().perform();
		         Thread.sleep(1000);
		         WebElement Gender_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(Gender_list).build().perform();
		         System.out.println("Gender category is selected");
		         Thread.sleep(1000);
		  
		         cell = sheet.getRow(i).getCell(2);
		         cell.setCellType(CellType.STRING);
		         WebElement typeofgame = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/div/div/div/div"));
		         a1.click(typeofgame).build().perform();
		         Thread.sleep(1000);
		         WebElement typeofgame_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(typeofgame_list).build().perform();
		         System.out.println("Type of game is selected");
		         Thread.sleep(1000);
		  
		         cell = sheet.getRow(i).getCell(3);
		         cell.setCellType(CellType.STRING);
		         WebElement Noofteams = driver.findElement(By.xpath("//*[@id=\"noofteams\"]"));
		         Noofteams.clear();
		         Thread.sleep(500);
		         Noofteams.sendKeys(cell.getStringCellValue());
		         System.out.println("No of team is mentioned");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(4);
		         cell.setCellType(CellType.STRING);
		         WebElement format = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/div/div/div/div"));
		         a1.click(format).build().perform();
		         Thread.sleep(1000);
		         WebElement format_list = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		         a1.click(format_list).build().perform();
		         System.out.println("Format is mentioneds");
		         Thread.sleep(1000);

		         cell = sheet.getRow(i).getCell(5);
		         cell.setCellType(CellType.STRING);
		         WebElement entryfee = driver.findElement(By.xpath("//*[@id=\"entryfee\"]"));
		         entryfee.clear();
		         Thread.sleep(500);
		         entryfee.sendKeys(cell.getStringCellValue());
		         System.out.println("Entry fee is mentioned");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(6);
		         cell.setCellType(CellType.STRING);
		         WebElement halflength = driver.findElement(By.xpath("//*[@id=\"halflength\"]"));
		         halflength.clear();
		         Thread.sleep(500);
		         halflength.sendKeys(cell.getStringCellValue());
		         System.out.println("halflength is mentioned");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(7);
		         cell.setCellType(CellType.STRING);
		         WebElement winner = driver.findElement(By.xpath("//*[@id=\"winner\"]"));
		         winner.clear();
		         Thread.sleep(500);
		         winner.sendKeys(cell.getStringCellValue());
		         System.out.println("winner prize amount is given");
		         Thread.sleep(500);
		  
		         cell = sheet.getRow(i).getCell(8);
		         cell.setCellType(CellType.STRING);
		  WebElement rollingsub = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/div/div/label["+cell.getStringCellValue()+"]/span[2]"));
		  rollingsub.click();
		  
		  WebElement rollfix_no = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[5]/div/fieldset/div/div/div/div/div"));
		  a1.click(rollfix_no).build().perform();
		  		cell = sheet.getRow(i).getCell(9);
	            cell.setCellType(CellType.STRING);
		  WebElement no_selection = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li["+cell.getStringCellValue()+"]"));
		  a1.click(no_selection).build().perform();
		  System.out.println("Number is selected");
		  Thread.sleep(2000);
		  
		  		cell = sheet.getRow(i).getCell(10);
	            cell.setCellType(CellType.STRING);
	            WebElement runner = driver.findElement(By.xpath("//*[@id=\"runnerup\"]"));
	            runner.clear();
	            runner.sendKeys(cell.getStringCellValue());
	            System.out.println("runner prize amount is given");
	            Thread.sleep(500);
		  
	            cell = sheet.getRow(i).getCell(11);
		        cell.setCellType(CellType.STRING);
		        WebElement Description = driver.findElement(By.xpath("//*[@id=\"description\"]"));
		        Description.clear();
		        Description.sendKeys(cell.getStringCellValue());
		        System.out.println("description is filled");
		        Thread.sleep(500);
		  
		        WebElement Nextbtn3 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[2]/button[3]/span[1]"));
		        Nextbtn3.click();
		        System.out.println("Next button is clicked");
		        Thread.sleep(5000);
	  		
	  		  boolean Age_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[1]/span")).size()!=0;
			  boolean typegame_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[2]/span")).size()!=0;
			  boolean format_error = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div[3]/span")).size()!=0;
			  boolean description_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/p[1]")).size()!=0;
			  boolean winner_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/p[1]")).size()!=0;
			  boolean runner_error = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/p[1]")).size()!=0;
			  
	  		  if(Age_error==true || typegame_error==true || format_error==true || description_error==true || winner_error==true || runner_error==true)
	  		  		{
	  			    screenCapture();
					sheet.getRow(i).createCell(12).setCellValue(fail);  
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
					  sheet.getRow(i).createCell(12).setCellValue(pass);
					  System.out.println("Doesn't throw any alert in this page");
					    // Specify the file in which data needs to be written.
				        FileOutputStream fileOutput = new FileOutputStream(src);
				        // finally write content
				        workbook.write(fileOutput);
				         // close the file
				        fileOutput.close();
				        System.out.println("file is closed");
				  }
	  		  System.out.println("if is closed");
		     }
  System.out.println("for loop is closed");
	  }
	  
	  public void bank() throws IOException, InterruptedException 
	  {
		  System.out.println("Bank method is started");
		  	 // Import excel sheet.
		     File src=new File("C:\\Users\\ashok\\OneDrive\\Documents\\Event.xls");
		      
		     // Load the file.
		     FileInputStream finput = new FileInputStream(src);
		      
		     // Load he workbook.
		     workbook = new HSSFWorkbook(finput);
		      
		     // Load the sheet in which data is stored.
		     sheet= workbook.getSheetAt(4);
		     
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

	  public void screenCapture() throws IOException{
		  
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  File screenshotName = new File ("C:\\Users\\ashok\\eclipse-workspace\\New_Turftown\\screenshots\\Error_screenshot.png");
		  FileUtils.copyFile(scrFile, screenshotName);
		  Reporter.log("<br><img src='"+screenshotName+"' height='300' width='300'/><br>");  
		 } 
	  
	  public void uploadFile(String fileLocation) throws AWTException
	  {
		     StringSelection stringSelection = new StringSelection(fileLocation);
		     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	      	
	          //native key strokes for CTRL, V and ENTER keys
	          Robot robot = new Robot();
		
	          robot.keyPress(KeyEvent.VK_CONTROL);
	          robot.keyPress(KeyEvent.VK_V);
	          robot.keyRelease(KeyEvent.VK_V);
	          robot.keyRelease(KeyEvent.VK_CONTROL);
	          robot.keyPress(KeyEvent.VK_ENTER);
	          robot.keyRelease(KeyEvent.VK_ENTER);
	      }

	  public void edit_button()
	  {
		  WebElement edit_btn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/ul/li[1]"));
		  a1.click(edit_btn).build().perform();
	  }
	  
	  public void delete_button()
	  {
		  WebElement delt_btn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/ul/li[2]"));
		  a1.click(delt_btn).build().perform();
	  }
	  
	  public void disable_button()
	  {
		  WebElement disable_btn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/ul/li[3]"));
		  a1.click(disable_btn).build().perform(); 
	  }
	  
	  
	  //@AfterTest
	  public void afterTest()
	  {
		  WebElement logout_btn = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/header/div/div[2]/div/button[2]"));
		  logout_btn.click();
		  
		  driver.close();

	  }
}
