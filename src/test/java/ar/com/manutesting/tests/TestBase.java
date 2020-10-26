package ar.com.manutesting.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ar.com.manutesting.paginas.Landing;
import ar.com.manutesting.paginas.ListadoProductos;
import ar.com.manutesting.paginas.PaginaBase;

public class TestBase {
	protected WebDriver driver;
	protected PaginaBase paginaBase;
	protected Landing paginaLanding;
	protected ListadoProductos paginaListadoProductos;
	protected Logger log = LoggerFactory.getLogger(PaginaBase.class);
	
	@BeforeMethod
	public void configuracionInicial() throws Exception {
		System.setProperty("webdriver.chrome.driver","./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		paginaLanding = new Landing(driver);
		paginaListadoProductos = new ListadoProductos(driver);
	}
	
	@AfterMethod
	public void finalizarTest() {
		paginaLanding.cerrarNavegador();
	}

	public WebDriver getDriver() {
		return driver;
	}
}
