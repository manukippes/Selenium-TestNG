package ar.com.manutesting.paginas;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ar.com.manutesting.utiles.enums.Marcas;
import io.qameta.allure.Step;

public class ListadoProductos extends PaginaBase{

	public ListadoProductos(WebDriver driver) throws Exception {super(driver);}

	private static String filtroHeladeras = "//h4[contains(text(),'Heladeras (')]";
	private static String verTodasMarcas = "//li[@name='brandsFilter']//a[text()='Ver todas']";
	private static String filtroMarcaSamsung = "//label[text()='Samsung']";
	private static String botonAplicar = "//button[text()=' Aplicar ']";
	private static String listadoProductos = "//ul[@name='itemsGrid']//li";
	private static String listadoTituloProductos = "//ul[@name='itemsGrid']//li//h4[@class='PieceTitle-sc-1eg7yvt-0 akEoc']";
	private static String breadcrumb = "//div[@name='breadcrumb']//a[@class='breadcrumbstyled__Anchor-vxt6er-1 cnlUAR']";	

	@Step("Filtrar por Heladeras")
	public void filtrarPorHeladeras() throws Exception {
		clickElemento(filtroHeladeras, "Filtro Heladeras");		
	}

	@Step("Filtrar por marca {0}")
	public void filtrarPorMarca(String marca) throws Exception {
		clickElemento(verTodasMarcas, "Ver Todas");
		if (Marcas.valueOf(marca).equals(Marcas.Samsung)) clickElemento(filtroMarcaSamsung, "Samsung");
		clickElemento(botonAplicar, "Aplicar");
	}

	@Step("Validar que en el titulo de los productos listados se visualice {0}")
	public void validarTituloDelProducto(String titulo) throws Exception {
		List<WebElement> listadoElementos = encontrarElementos(listadoTituloProductos);
		for (WebElement producto : listadoElementos) {
			validarQueTextoContienePalabra(producto.getText(), titulo, "Titulo");
		}
	}
	
	@Step("Validar que la cantidad de productos mostrados sea {0}")
	public void validarCantidadDeProductos(int cantidadEsperada) throws Exception {
		List<WebElement> listadoElementos = encontrarElementos(listadoProductos);
		validarCantidadDeElementosListados(listadoElementos.size(), cantidadEsperada);
	}

	@Step("Validar que en el breadcrumb se visualice {0}")
	public void validarBreadcrumb(String breadcrumbEsperado) throws Exception {
		validarElTextoDeUnElemento(breadcrumb, breadcrumbEsperado, "Breadcrumb");
	}

}
