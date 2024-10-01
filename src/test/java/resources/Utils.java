package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	int i = 1;
	public RequestSpecification requestSpecification() throws IOException {
		if (req == null) {
			PrintStream ps = new PrintStream(new FileOutputStream("logging"+i+".txt"));
			i++;
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(ps))
					.addFilter(ResponseLoggingFilter.logResponseTo(ps))
					.setContentType(ContentType.JSON).build();
			return req;
		}				
		return req;			
	}
	
	// D:\Java practice\APIFramework\src\test\java\resources\global.properties
	
	public static String getGlobalValue(String key) throws IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\global.properties");
		
		Properties prop = new Properties();
		prop.load(fis);
		
		String value = prop.getProperty(key);
		return value;
	}
	
	public static String getJsonPath(Response response, String key) {
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.getString(key);
		
	}
	
}
