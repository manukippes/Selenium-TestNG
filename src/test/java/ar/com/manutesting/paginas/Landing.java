package ar.com.manutesting.paginas;

import org.openqa.selenium.WebDriver;

import ar.com.manutesting.utiles.soporte.PropertyManager;
import io.qameta.allure.Step;

public class Landing extends PaginaBase {
	
	public Landing(WebDriver driver) throws Exception {super(driver);}

	private final static String barraBusqueda = "//input[@class='InputBar__SearchInput-t6v2m1-1 iJaFAt']";
	private final static String botonBusqueda = "//button[@class='InputBar__SearchButton-t6v2m1-2 jRChuZ']";
	
	@Step("Navegar a la pagina de Fravega")
	public void navegarAFravega() throws Exception {
		String url = PropertyManager.getInstance().getUrl(); 
		navegarA(url);
		driver.manage().window().maximize();
	}
	
	@Step("Escribir en la barra de búsqueda el texto {0}")
	public void escribirProductoEnBarraBusqueda(String productoABuscar) throws Exception {
		escribirTextoEnElemento(barraBusqueda, productoABuscar, "Barra de búsqueda");
	}
	
	@Step("Hacer click en el boton de búsqueda")
	public void clickBotonBusqueda() throws Exception {
		clickElemento(botonBusqueda, "Botón de búsqueda");
	}
}
