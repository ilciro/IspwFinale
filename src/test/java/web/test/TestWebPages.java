package web.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.util.logging.Level;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import laptop.utilities.ConnToDb;


class TestWebPages {
	
	private WebDriver driver;
	
	@Test
	void testAcquistaCashPayment() throws InterruptedException
	{
		String idE;
		 driver=new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("http://localhost:8087/IspwFinale/index.jsp");

		driver.findElement(By.xpath("//input[@id='buttonL']")).click();
		
		driver.findElement(By.xpath("//input[@id='genera lista']")).click();
		
		WebElement id=driver.findElement(By.xpath("//input[@id='idOgg']"));			
		id.sendKeys("4");		
		idE=id.getText();
		
		driver.findElement(By.xpath("//input[@id='procedi']")).click();	
		
		
		driver.findElement(By.xpath("//input[@id='quantita']")).clear();
		driver.findElement(By.xpath("//input[@id='quantita']")).sendKeys("6");
		
		driver.findElement(By.xpath("//input[@id='totaleB']")).click();	
		
		 WebElement input = driver.findElement(By.xpath("//input[@list='metodi']"));
		WebElement  option = driver.findElement(By.xpath("//*[@id='metodi']/option[1]"));
		String value = option.getAttribute("value");
		input.sendKeys(value);		
			
		driver.findElement(By.xpath("//input[@id='pdfB']")).click();
		
		
		driver.findElement(By.xpath("//input[@id='nomeL']")).sendKeys("franco");
		driver.findElement(By.xpath("//input[@id='cognomeL']")).sendKeys("rossi");
		driver.findElement(By.xpath("//input[@id='indirizzoL']")).sendKeys("via gelsomini 13");
		driver.findElement(By.id("com")).sendKeys("la consegna dopo le 12");
		
		WebElement buttonConferma=driver.findElement(By.xpath("//input[@id='buttonC']"));
		
		
		buttonConferma.click();
		
		
		driver.findElement(By.xpath("//input[@id='downloadB']")).click();

		driver.quit();
		assertNotNull(idE);
	

		
		
	}

	

	
	
	@AfterAll
	static void testRimuoviDB()
	{
		String query="drop schema ispw";
		int row=0;
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			row=prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("cancella db").log(Level.INFO,"errore nel db \n");

		}
		java.util.logging.Logger.getLogger("cancella db").log(Level.INFO,"db cancellato \n");


		assertEquals(11,row);
	}
}
