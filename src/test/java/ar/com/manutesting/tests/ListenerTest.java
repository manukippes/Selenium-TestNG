package ar.com.manutesting.tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import ar.com.manutesting.paginas.PaginaBase;
import io.qameta.allure.Attachment;

public class ListenerTest extends TestBase implements ITestListener {
	private Logger log = LoggerFactory.getLogger(PaginaBase.class);
	
	@Override		
    public void onFinish(ITestContext contexto) {					
		log.info("Finalizó el grupo de tests "+contexto.getName());	
    }		

    @Override		
    public void onStart(ITestContext contexto) {	
    	log.info("Comenzó el grupo de tests "+contexto.getName());				
    }		
    
    @Override		
    public void onTestStart(ITestResult resultado) {					
    	log.info("Comenzó el test "+resultado.getName());
    }

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult resultado) {	
    	adherirCapturaAlReporteAllure(resultado);
    	log.info("Finalizó con error pero dentro del porcentaje esperado el test "+resultado.getName());
    }	
    
    @Override		
    public void onTestFailedWithTimeout(ITestResult resultado) {
    	adherirCapturaAlReporteAllure(resultado);
    	log.info("Finalizó con error por timeout el test "+resultado.getName());
    }	

    @Override		
    public void onTestFailure(ITestResult resultado) {	
    	adherirCapturaAlReporteAllure(resultado);
    	log.info("Finalizó con error el test "+resultado.getName());
    }		

    @Override		
    public void onTestSkipped(ITestResult resultado) {					
    	log.info("El test "+resultado.getName()+" no se ejecutó.");
    }		

    @Override		
    public void onTestSuccess(ITestResult resultado) {	
    	adherirCapturaAlReporteAllure(resultado);
    	log.info("Finalizó correctamente el test "+resultado.getName());
    }	
    
    public void adherirCapturaAlReporteAllure(ITestResult resultado) {
		Object testClass = resultado.getInstance();
        WebDriver driver = ((TestBase) testClass).getDriver();
        realizarCapturaPNG(driver);
	}
    
    @Attachment(value = "Captura de pantalla", type = "image/png")
    public byte[] realizarCapturaPNG (WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}