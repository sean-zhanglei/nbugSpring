package test;

import org.junit.Test;

import com.nbug.ss.aop.AdvisedSupport;
import com.nbug.ss.aop.Cglib2AopProxy;
import com.nbug.ss.aop.TargetSource;
import com.nbug.ss.context.ApplicationContext;
import com.nbug.ss.context.ClassPathXmlApplicationContext;

/**
 */
public class Cglib2AopProxyTest {

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

		Cglib2AopProxy cglib2AopProxy = new Cglib2AopProxy(advisedSupport);
		HelloWorldService helloWorldServiceProxy = (HelloWorldService) cglib2AopProxy.getProxy();

		helloWorldServiceProxy.helloWorld();
	}
}
