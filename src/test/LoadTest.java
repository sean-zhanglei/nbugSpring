package test;

import org.junit.Test;

import com.nbug.ss.context.ApplicationContext;
import com.nbug.ss.context.ClassPathXmlApplicationContext;

public class LoadTest {
	@Test
	public void test() {
		// 1.∂¡»°≈‰÷√
		try {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc.xml");
	        //HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
	        //helloWorldService.helloWorld();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
