package web.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


class TestIndex {
	
	@Test
	void test()   {
		
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8090/IspwFinale/");
		WebElement buttonSubmit=driver.findElement(By.id("buttonA"));

				assertEquals("click",buttonSubmit.getAttribute("value"));

		buttonSubmit.click();
		
			
		
	}

}
