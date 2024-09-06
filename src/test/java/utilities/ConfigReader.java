package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties prop;

	// this method is uesd to load the properties from config.properties file

	public Properties init_prop() {
		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream("./src/test/resources/Config/config.properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return prop;
	}

}
