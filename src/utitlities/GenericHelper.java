package utitlities;

import java.io.FileInputStream;
import java.util.Properties;

public class GenericHelper {

	public static String readProperty(String key) {
		try {
			FileInputStream fis = new FileInputStream(".\\config.properties");
			Properties prop = new Properties();
			prop.load(fis);
			return prop.getProperty(key);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
