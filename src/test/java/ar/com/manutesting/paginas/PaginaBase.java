package ar.com.manutesting.paginas;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.manutesting.utiles.soporte.PropertyManager;

public class PaginaBase {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;
	protected Logger log = LoggerFactory.getLogger(PaginaBase.class);

	public PaginaBase(WebDriver driver) throws Exception {
		this.driver = driver;
		wait = new WebDriverWait(driver, PropertyManager.getInstance().getTimeOut());
		js = (JavascriptExecutor) driver;
	}

	public void cerrarNavegador() {
		if(driver != null) driver.quit();
		log.info("Se cerró el navegador correctamente");
	}

	public void navegarA(String url) throws Exception {
		driver.get(url);
		log.info("Se ingresó a la url: "+url);
	}

	private WebElement encontrarElemento(String locator) throws Exception {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	protected List<WebElement> encontrarElementos(String locator) throws Exception {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
	}
	
	public void clickElemento(String locator, String nombreElemento) throws Exception {
		hacerScrollHaciaUnElemento(locator, nombreElemento);
		encontrarElemento(locator).click();
		log.info("Se hizo click en el elemento: "+nombreElemento);
	}
	
	public void escribirTextoEnElemento(String locator, String texto, String nombreElemento) throws Exception{
		hacerScrollHaciaUnElemento(locator, nombreElemento);
		encontrarElemento(locator).clear();
		encontrarElemento(locator).sendKeys(texto);
		log.info("Se escribió: "+texto+" sobre el elemento: "+nombreElemento);
	}
	
	public void validarElTextoDeUnElemento(String locator, String text, String nombreElemento) throws Exception {
		hacerScrollHaciaUnElemento(locator, nombreElemento);
		Assert.assertEquals("El valor: \'"+text+"\' no se encuentra dentro de: \'"+encontrarElemento(locator).getText()+"\'", text, encontrarElemento(locator).getText());
		log.info("Se validó que "+text+" se visualiza en el elemento: "+nombreElemento);
	}

	public void hacerScrollHaciaUnElemento(String locator, String nombreElemento) throws Exception {
		js.executeScript("arguments[0].scrollIntoView(false);", encontrarElemento(locator));
		log.info("Se hizo scroll hacia el elemento: "+nombreElemento);
	}
	
	public void validarCantidadDeElementosListados(int cantidadElementos, int cantidadEsperada) {
		Assert.assertEquals("La cantidad de elementos mostrada "+cantidadElementos+" no coincide con la cantidad esperada "+cantidadEsperada,cantidadElementos, cantidadEsperada);
		log.info("Se validó que la cantidad de elementos mostrada "+cantidadElementos+" es la esperada "+cantidadEsperada);
	}
	
	public void validarQueTextoContienePalabra(String texto, String palabraEsperada, String elemento) {
		Assert.assertTrue("El texto mostrado "+texto+" en el elemento "+elemento+" no contiene la palabra esperada "+palabraEsperada, texto.contains(palabraEsperada));
		log.info("Se validó que "+texto+" contiene la palabra "+palabraEsperada);
	}
	
}
