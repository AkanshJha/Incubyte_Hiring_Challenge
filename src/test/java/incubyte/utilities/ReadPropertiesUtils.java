package incubyte.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class ReadPropertiesUtils {

	public static Properties loadPropertiesFile(String propertiesFilePath) {

		Properties properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream(propertiesFilePath);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			System.exit(0);

		} catch (IOException e) {
			System.out.println("Could not read the file. Please make sure properties file is ready to be used.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Some unexpected error occured. Please make sure properties file is ready to be used.");
			e.printStackTrace();
		}

		return properties;
	}

	/**
	 * 
	 * @param property     : Pass the object the Properties class
	 * @param propertyName : Name of the property, you want to get the Integer value
	 *                     of
	 * @return : the Interger value of that property.
	 */
	public static int getIntegerPropertyValue(Properties property, String propertyName) {
		int result = -1;
		try {
			result = Integer.valueOf(property.getProperty(propertyName));
			System.out.println("'" + result + "' value is fetched for property '" + propertyName + "'.");
			return result;
		} catch (NumberFormatException e) {
			System.out.println("We fetched an invalid value for '" + propertyName
					+ "' property. Please provide the valid integer value for this property. Please check both the Property Name and its value are correct.");
			e.printStackTrace();
			return result;
		}

	}

	/**
	 * 
	 * @param property     : Pass the object the Properties class
	 * @param propertyName : Name of the property, you want to get the String value
	 *                     of
	 * @return : the String value of that property.
	 */
	public static String getStringPropertyValue(Properties property, String propertyName) {
		String result = null;
		result = property.getProperty(propertyName);
		System.out.println("'" + result + "' value is fetched for property '" + propertyName + "'.");
		return result;
	}

	/**
	 * 
	 * @param properties : Pass the object of Properties class.
	 * @return : Set of Keys available in this Properties object.
	 */
	public static Set<Object> getAllPropertiesKeys(Properties properties) {
		return properties.keySet();
	}

	
}
