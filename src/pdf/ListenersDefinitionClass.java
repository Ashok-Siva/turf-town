package pdf;
import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

 
public class ListenersDefinitionClass implements ITestListener{
 
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		try {
			after();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void after() throws InterruptedException
	 {
 		    // Create object of Property file
			Properties props = new Properties();
	 
			// this will set host of server- you can change based on your requirement 
			props.put("mail.smtp.host", "smtp.gmail.com");
	 
			// set the port of socket factory 
			props.put("mail.smtp.socketFactory.port", "465");
	 
			// set socket factory
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	 
			// set the authentication to true
			props.put("mail.smtp.auth", "true");
	 
			// set the port of SMTP server
			props.put("mail.smtp.port", "465");
	 
			// This will handle the complete authentication
			Session session = Session.getDefaultInstance(props,
	 
					new javax.mail.Authenticator() {
	 
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
	 
						return new PasswordAuthentication("sivaashok132@gmail.com", "testingmail");
	 
						}
	 
					});
	 
			try {
				Thread.sleep(8000);
	 
				// Create object of MimeMessage class
				Message message = new MimeMessage(session);
	 
				// Set the from address
				message.setFrom(new InternetAddress("sivaashok132@gmail.com"));
	 
				// Set the recipient address
				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("ashoksiva0906@gmail.com"));
	            
	            // Add the subject link
				message.setSubject("Automation Report");
	 
				// Create object to add multimedia type content
				BodyPart messageBodyPart1 = new MimeBodyPart();
	 
				// Set the body of email
				messageBodyPart1.setText("Kindly refer the excel report for detailed venue creation");
	 
				// Create another object to add another content
				MimeBodyPart messageBodyPart2 = new MimeBodyPart();
	 
				// Mention the file which you want to send
				String filename = "C:\\Users\\New\\Desktop\\Ashok\\Excel\\venueexcel.xlsx";
	 
				// Create data source and pass the filename
				DataSource source = new FileDataSource(filename);
	 
				// set the handler
				messageBodyPart2.setDataHandler(new DataHandler(source));
	 
				// set the file
				messageBodyPart2.setFileName(new File(filename).getName());
				
				// Create another object to add another content
				MimeBodyPart messageBodyPart3 = new MimeBodyPart();
	 
				// Mention the testngTest-report which you want to send
				String filename1 = "C:\\Users\\ashok\\eclipse-workspace\\New_Turftown\\test-output\\turftown\\testngTest.html";
	 
				// Create data source and pass the filename
				DataSource source1 = new FileDataSource(filename1);
	 
				// set the handler
				messageBodyPart3.setDataHandler(new DataHandler(source1));
	 
				// set the file
				messageBodyPart3.setFileName(new File(filename1).getName());
				
				// Create another object to add another content
				MimeBodyPart messageBodyPart4 = new MimeBodyPart();
	 
				// Mention the emailable-report which you want to send
				String filename2 = "C:\\Users\\ashok\\eclipse-workspace\\New_Turftown\\test-output\\emailable-report.html";
	 
				// Create data source and pass the filename
				DataSource source2 = new FileDataSource(filename2);
	 
				// set the handler
				messageBodyPart4.setDataHandler(new DataHandler(source2));
	 
				// set the file
				messageBodyPart4.setFileName(new File(filename2).getName());
				
				// Create another object to add another content
				MimeBodyPart messageBodyPart5 = new MimeBodyPart();
	 
				// Mention the pdf-report which you want to send
				String filename3 = "C:\\Users\\ashok\\eclipse-workspace\\New_Turftown\\TestReport\\pdf_report.pdf";
	 
				// Create data source and pass the filename
				DataSource source3 = new FileDataSource(filename3);
	 
				// set the handler
				messageBodyPart5.setDataHandler(new DataHandler(source3));
	 
				// set the file
				messageBodyPart5.setFileName(new File(filename3).getName());
				
	 
				// Create object of MimeMultipart class
				Multipart multipart = new MimeMultipart();
				
				//add body part 1
				multipart.addBodyPart(messageBodyPart5);
				
				//add body part 1
				multipart.addBodyPart(messageBodyPart4);
				
				// add body part 1
				multipart.addBodyPart(messageBodyPart3);
				
				// add body part 2
				multipart.addBodyPart(messageBodyPart2);
	 
				// add body part 3
				multipart.addBodyPart(messageBodyPart1);
	 
				// set the content
				message.setContent(multipart);
	 
				// finally send the email
				Transport.send(message);
	 
				System.out.println("=====Email Sent=====");
	 
			} catch (MessagingException e) {
	 
				throw new RuntimeException(e);
	 
			}
	 }
 
}
