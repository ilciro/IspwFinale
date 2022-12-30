package web.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import laptop.utilities.ConnToDb;


class TestWebPages {
	
	
	@Test
	void testAcquistaCashPayment() throws InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8090/IspwFinale/index.jsp");

		driver.findElement(By.xpath("//input[@id='buttonL']")).click();
		
		driver.findElement(By.xpath("//input[@id='genera lista']")).click();
		
		WebElement id=driver.findElement(By.xpath("//input[@id='idOgg']"));			
		id.sendKeys("4");		
		
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

		assertNotNull(id);
		
		driver.quit();
	}

	@Test
	void testLoginAdAdmin() throws InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8090/IspwFinale/index.jsp");
		driver.findElement(By.xpath("//input[@id='buttonLogin']")).click();
		driver.findElement(By.xpath("//input[@id='emailL']")).sendKeys("admin@admin.com");
		driver.findElement(By.xpath("//input[@id='passL']")).sendKeys("admin874");
		driver.findElement(By.xpath("//input[@id='loginB']")).click();
		driver.findElement(By.xpath("//input[@id='reportB']")).click();
		driver.findElement(By.id("totaleC")).click();
		driver.findElement(By.xpath("//input[@id='scelta']")).click();


		assertNotEquals("",driver.findElement(By.id("tArea")).getAttribute("value"));


		
		driver.quit();

		
	}
	
	  @Test
	  void testLoginAsWriter() throws InterruptedException
	  {
		  WebDriver driver=new ChromeDriver();
			driver.get("http://localhost:8090/IspwFinale/index.jsp");
			driver.findElement(By.xpath("//input[@id='buttonLogin']")).click();
			driver.findElement(By.xpath("//input[@id='emailL']")).sendKeys("zerocalcare@gmail.com");
			driver.findElement(By.xpath("//input[@id='passL']")).sendKeys("Zerocalcare21");
			driver.findElement(By.xpath("//input[@id='loginB']")).click();
			
			driver.findElement(By.xpath("//input[@id='buttonGestione']")).click();
			
			driver.findElement(By.xpath("//input[@id='buttonG']")).click();
			
			driver.findElement(By.xpath("//input[@id='buttonGenera']")).click();
			
			driver.findElement(By.xpath("//input[@id='buttonAdd']")).click();
			
			driver.findElement(By.xpath("//input[@id='titoloG']")).sendKeys("insert giornale prova");
			driver.findElement(By.xpath("//input[@id='linguaG']")).sendKeys("italiano");
			driver.findElement(By.xpath("//input[@id='editoreG']")).sendKeys("editore prova insert");
			driver.findElement(By.xpath("//input[@id='dataG']")).sendKeys("2023/08/08");
			driver.findElement(By.xpath("//input[@id='copieG']")).sendKeys("150");
			driver.findElement(By.xpath("//input[@id='dispG']")).sendKeys("0");
			driver.findElement(By.xpath("//input[@id='prezzoG']")).sendKeys("3.52");
			driver.findElement(By.xpath("//input[@id='confermaB']")).click();
			driver.findElement(By.xpath("//input[@id='buttonGenera']")).click();
			assertNotNull(driver.findElement(By.xpath("//input[@id='buttonGenera']")).getAttribute("value"));
			driver.close();

	  }
	 
	@Test
	void testLoginAsEditor() throws InterruptedException
	{
		 WebDriver driver=new ChromeDriver();
			driver.get("http://localhost:8090/IspwFinale/index.jsp");
			driver.findElement(By.xpath("//input[@id='buttonLogin']")).click();
			driver.findElement(By.xpath("//input[@id='emailL']")).sendKeys("baoPublishing@gmail.com");
			driver.findElement(By.xpath("//input[@id='passL']")).sendKeys("BaoPub2021");
			driver.findElement(By.xpath("//input[@id='loginB']")).click();
			
			driver.findElement(By.xpath("//input[@id='buttonGestione']")).click();
			
			driver.findElement(By.xpath("//input[@id='buttonR']")).click();
		

			driver.findElement(By.xpath("//input[@id='buttonGenera']")).click();
			driver.findElement(By.xpath("//input[@id='idL']")).sendKeys("5");
			driver.findElement(By.xpath("//input[@id='buttonMod']")).click();
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//input[@id='listaB']")).click();
			
	       
			
			driver.findElement(By.xpath("//input[@id='titoloNR']")).sendKeys("Rivista B Modificata1");
			driver.findElement(By.xpath("//input[@id='categoriaNR']")).sendKeys("ANNUALE");
			driver.findElement(By.xpath("//input[@id='autoreNR']")).sendKeys("Bao Publishing");
			driver.findElement(By.xpath("//input[@id='linguaNR']")).sendKeys("italiano");
			driver.findElement(By.xpath("//input[@id='editoreNR']")).sendKeys("Bao Publishing");
			driver.findElement(By.id("descNR")).sendKeys("provo a modificare la rivista nr.5 ");
			driver.findElement(By.xpath("//input[@id='dataNR']")).sendKeys("2023/01/24 ");
			driver.findElement(By.xpath("//input[@id='dispNR']")).sendKeys("1");
			driver.findElement(By.xpath("//input[@id='prezzoNR']")).sendKeys("5.21");
			driver.findElement(By.xpath("//input[@id='copieNR']")).sendKeys("250");
			driver.findElement(By.xpath("//input[@id='buttonI']")).click();

			driver.findElement(By.xpath("//input[@id='buttonGenera']")).click();

			assertNotNull(driver.findElement(By.xpath("//input[@id='buttonGenera']")).getAttribute("value"));


			driver.close();



			
			
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
