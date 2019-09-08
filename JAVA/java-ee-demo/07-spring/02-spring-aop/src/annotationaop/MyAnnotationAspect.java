package annotationaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <!-- 配置切面类 -->
 * <bean id="myAnnotationAspect" class="annotationaop.MyAnnotationAspect" />
 * 
 *    <!-- 配置AOP -->
 *    <aop:config>
 *    	<aop:aspect ref="myAnnotationAspect">
 *    		<aop:before method="log" 	pointcut="execution( public  void annotationaop.MyAnnotationAspect.log() )" />
 *    	</aop:aspect>
 *    </aop:config>
 *
 */

@Component(value = "myAnnotationAspect")  //<bean id="myAnnotationAspect" class="annotationaop.MyAnnotationAspect" />
@Aspect                                                       //<aop:aspect ref="myAnnotationAspect">
public class MyAnnotationAspect {

	/*
	 *前置通知 
	 */
	@Before(value = "execution( public *  annotationaop.CustomerService.save() ) " ) //<aop:before method="log" 	pointcut="execution( public  void annotationaop.MyAnnotationAspect.log() )" />
	public void log() {
		System.out.println("logging~~~~~~~~~~~~~~");
	}

	/*
	 * 最终通知
	 */
	@After(value = "execution(public * annotationaop.CustomerService.update() )")
	public void log1() {
		System.out.println("最终通知");
	}
	
	/*
	 * 定义切入点
	 */
	@Pointcut(value = "execution(public * annotationaop.CustomerService.update() )")
	public void point() {}
	
	/*
	 * 环绕
	 */
	@Around(value = "annotationaop.MyAnnotationAspect.point()")
	public void aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("begin>>>>>>>>>>");
		
		joinPoint.proceed();//执行目标方法
		
		System.out.println("end>>>>>>>>>>");
	}
}
