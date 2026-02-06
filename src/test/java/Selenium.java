import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Selenium {
	WebDriver driver;
	
	@BeforeEach
	public void setup() {
		driver = new ChromeDriver();
		driver.get("https://treinamento.yazztecnologia.com.br");		
	}
	
	@AfterEach
	public void tearDown() {
		//driver.quit();
	}
		
	@Test
	@Disabled
	public void acessarTela() {
		driver.get("https://g1.globo.com");
		String titulo = driver.getTitle();
				
		Assertions.assertEquals("g1 - O portal de notícias da Globo", titulo);
	}
	
	@Test
	@Disabled
	public void devePreencherInputComId() {
		WebElement elemento = driver.findElement(By.id("campo-1")); 
		elemento.sendKeys("Anderson");
		
		String nome = elemento.getAttribute("value");
		//driver.quit();
		
		Assertions.assertEquals("Anderson", nome);
	}
	
	@Test
	@Disabled
	public void devePreencherInputComClassName() {
		List<WebElement> elementos = driver.findElements(By.className("form-control")); 
		elementos.get(1).sendKeys("Anderson");
		
		String nome = elementos.get(1).getAttribute("value");
		//driver.quit();
		
		Assertions.assertEquals("Anderson", nome);
	}
	
	@Test
	public void devePreencherInputComName() {
		WebElement elemento = driver.findElement(By.name("campo")); 
		elemento.sendKeys("Anderson");
		
		String nome = elemento.getAttribute("value");
		//driver.quit();
		
		Assertions.assertEquals("Anderson", nome);
	}
}
