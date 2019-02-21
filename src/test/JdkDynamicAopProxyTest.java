package test;

import org.junit.Test;

import com.nbug.ss.aop.AdvisedSupport;
import com.nbug.ss.aop.JdkDynamicAopProxy;
import com.nbug.ss.aop.TargetSource;
import com.nbug.ss.context.ApplicationContext;
import com.nbug.ss.context.ClassPathXmlApplicationContext;


/**
 */
public class JdkDynamicAopProxyTest {

	@Test
	public void testInterceptor() throws Exception {
		// --------- helloWorldService without AOP
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc.xml");
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
//		helloWorldService.helloWorld();

		// --------- helloWorldService with AOP
		AdvisedSupport advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class,
				HelloWorldService.class);
		advisedSupport.setTargetSource(targetSource);

		TimerInterceptor timerInterceptor = new TimerInterceptor();
		advisedSupport.setMethodInterceptor(timerInterceptor);

		JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
		HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();
		
		helloWorldServiceProxy.helloWorld();
	}
}
