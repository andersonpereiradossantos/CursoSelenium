import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium {
	WebDriver driver;
	
	@BeforeEach
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless");
		
		driver = new ChromeDriver(options);
		driver.get("https://treinamento.yazztecnologia.com.br");		
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}
		
	@Test
	public void acessarTela() {
		driver.get("https://g1.globo.com");
		String titulo = driver.getTitle();
				
		Assertions.assertEquals("g1 - O portal de notícias da Globo", titulo);
	}
	
	@Test
	public void devePreencherInputComId() {
		WebElement elemento = driver.findElement(By.id("campo-1")); 
		elemento.sendKeys("Anderson");
		
		String nome = elemento.getAttribute("value");

		Assertions.assertEquals("Anderson", nome);
	}
	
	@Test
	public void devePreencherInputComClassName() {
		List<WebElement> elementos = driver.findElements(By.className("form-control")); 
		elementos.get(1).sendKeys("Anderson");
		
		String nome = elementos.get(1).getAttribute("value");
		
		Assertions.assertEquals("Anderson", nome);
	}
	
	@Test
	public void devePreencherInputComName() {
		WebElement elemento = driver.findElement(By.name("campo")); 
		elemento.sendKeys("Anderson");
		
		String nome = elemento.getAttribute("value");
		
		Assertions.assertEquals("Anderson", nome);
	}
	
	
	@Test
	public void deveValidarInputDesabilitado() {
		WebElement elemento = driver.findElement(By.id("campo-4")); 
		
		Assertions.assertFalse(elemento.isEnabled());
	}
	
	@Test
	public void deveMarcarRadioButton() {
		WebElement elemento = driver.findElement(By.id("radio-1"));
		
		elemento.click();
		
		Assertions.assertTrue(elemento.isSelected());
	}
	
	@Test
	public void deveMarcarCheckbox() {
		WebElement elemento = driver.findElement(By.id("check-1"));
		
		elemento.click();
		
		Assertions.assertTrue(elemento.isSelected());
	}
	
	@Test
	public void deveSelecionarSelectUnico() {
		WebElement elemento = driver.findElement(By.id("selecao-unica")); 

		Select select = new Select(elemento);
		
		//select.selectByIndex(1);
		//select.selectByValue("4");
		select.selectByVisibleText("Opção 5");
		
		Assertions.assertEquals("Opção 5", select.getFirstSelectedOption().getText());
	}
	
	@Test
	public void deveSelecionarSelectMultiplo() {
		WebElement elemento = driver.findElement(By.id("selecao-multipla")); 

		Select select = new Select(elemento);
		
		select.selectByIndex(1);
		select.selectByValue("4");
		select.selectByVisibleText("Opção 3");
		
		WebElement option = select.getAllSelectedOptions().get(2);
		
		Assertions.assertEquals("Opção 4", option.getText());
		//Assertions.assertEquals(3, select.getAllSelectedOptions().size());
	}
	
	@Test
	public void deveAcionarAlert() {
		WebElement elemento = driver.findElement(By.id("botao-alerta"));
		
		elemento.click();
		
		Alert alert = driver.switchTo().alert();
		
		String textoAlerta = alert.getText();

		alert.accept();
				
		Assertions.assertEquals("Essa é uma mensagem de alerta!", textoAlerta);
	}
	
	@Test
	public void deveAcionarConfirmEConfirmar() {
		WebElement elemento = driver.findElement(By.id("botao-confirm"));
		
		elemento.click();
		
		Alert alert = driver.switchTo().alert();
		
		alert.accept();
				
		WebElement mensagem = driver.findElement(By.id("mensagem"));
		
		Assertions.assertEquals("Essa é a mensagem!", mensagem.getText());
	}	
	
	@Test
	public void deveAcionarConfirmECancelar() {
		WebElement elemento = driver.findElement(By.id("botao-confirm"));
		
		elemento.click();
		
		Alert alert = driver.switchTo().alert();
		
		alert.dismiss();
				
		WebElement mensagem = driver.findElement(By.id("mensagem"));
		
		Assertions.assertFalse(mensagem.isDisplayed());
	}
	
	@Test
	public void devePreencherPrompt() {
		WebElement elemento = driver.findElement(By.id("botao-prompt"));
		
		elemento.click();
		
		Alert alert = driver.switchTo().alert();
		
		alert.sendKeys("Anderson");
		
		alert.accept();
				
		WebElement mensagem = driver.findElement(By.id("mensagem"));
		
		Assertions.assertEquals("Anderson", mensagem.getText());
	}
		
	@Test
	public void deveObterOValorDaCelulaPorCssSelector() {
		WebElement elemento = driver.findElement(By.cssSelector("#tabela tbody tr:nth-child(3) td:nth-child(2)")); 
		
		Assertions.assertEquals("Linha 3 / Coluna 2", elemento.getText());
	}
	
	@Test
	public void deveObterOValorDaCelulaPorXPath() {
		WebElement elemento = driver.findElement(By.xpath("//*[@id=\"tabela\"]/tbody/tr[3]/td[2]")); 
		
		Assertions.assertEquals("Linha 3 / Coluna 2", elemento.getText());
	}
	
	/*------------------ESPERAS----------------*/
	
	@Test
	public void deveFazerEsperaFixa() throws InterruptedException {
		WebElement elemento = driver.findElement(By.id("novo"));
		
		elemento.click();
		
		//Ação do Java
		Thread.sleep(10000);
		
		Assertions.assertTrue(driver.findElement(By.id("novo-elemento")).isDisplayed());
	}
	
	@Test
	public void deveFazerEsperaImplicita() {
		WebElement elemento = driver.findElement(By.id("novo"));
		
		elemento.click();
		
		//Ação do Selenium
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		Assertions.assertTrue(driver.findElement(By.id("novo-elemento")).isDisplayed());
	}
	
	@Test
	public void deveFazerEsperaExplicita() {
		WebElement elemento = driver.findElement(By.id("novo"));
		
		elemento.click();
		
		//Ação do Selenium
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novo-elemento")));
		
		Assertions.assertTrue(driver.findElement(By.id("novo-elemento")).isDisplayed());
	}
}
