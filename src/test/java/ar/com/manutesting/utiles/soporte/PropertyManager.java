package ar.com.manutesting.utiles.soporte;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	
		private static PropertyManager instance;
		private static final Object lock = new Object();
		private static String propertyFilePath = "./src/test/resources/config.properties";
		private static String url;
		private static String timeOut;
		
		public static PropertyManager getInstance() throws FileNotFoundException, IOException {
			if(instance==null) {
				synchronized (lock) {
					instance = new PropertyManager();
					instance.loadData();
				}
			}
			return instance;
		}
		
		private void loadData() throws FileNotFoundException, IOException {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream(propertyFilePath));
			url = propiedades.getProperty("url");
			timeOut = propiedades.getProperty("time.out");
			
		}
		
		public String getUrl() {
			return url;
		}

		public long getTimeOut() {
			return Integer.parseInt(timeOut);
		}
}
