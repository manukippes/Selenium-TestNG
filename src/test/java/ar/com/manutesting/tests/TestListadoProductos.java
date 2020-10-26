package ar.com.manutesting.tests;

import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import ar.com.manutesting.utiles.datosExcel.FabricaDatosExcel;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Suite Selenium-TestNG-Challenge")
@Feature("Test listados de productos")
public class TestListadoProductos extends TestBase{
	
	@Test(priority = 1, description = "Verificar productos listados según un criterio de búsqueda", dataProvider = "datosExcelProductos", dataProviderClass = FabricaDatosExcel.class)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verificar productos listados según un criterio de búsqueda")
	@Story("Verificar Productos")
	public void verificarProductosListados(String productoABuscar, String marcaAFiltrar, String marcaTitulo, String cantidadProductos, String breadcrumb) throws Exception {
		try {
			paginaLanding.navegarAFravega();
			paginaLanding.escribirProductoEnBarraBusqueda(productoABuscar);
			paginaLanding.clickBotonBusqueda();
			paginaListadoProductos.filtrarPorHeladeras();
			paginaListadoProductos.filtrarPorMarca(marcaAFiltrar);
			paginaListadoProductos.validarTituloDelProducto(marcaTitulo);
			paginaListadoProductos.validarCantidadDeProductos(Integer.valueOf(cantidadProductos));
			paginaListadoProductos.validarBreadcrumb(breadcrumb);
		} catch (Exception e) {
			log.error(e.toString());
			fail(e.toString());
		}
	}
}
