package demo.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import demo.common.DataSourceContextHolder;

@Aspect
@Component
public class DataSourceFactoryAspect {

	private static final String APP_MESSAGE_KEY = "SPRING";

	Logger LOG = Logger.getLogger(DataSourceFactoryAspect.class);

	private static int ctn = 0;

	public DataSourceFactoryAspect() {
		System.out.println("AspectDemo initialized count = " + ctn);
		DataSourceFactoryAspect.ctn++;
	}
	
	@Pointcut("execution(* demo.service.UserService.rollbackOper(..))")
	public void exeTest() {
	}

	@Around("exeTest()")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		DataSourceContextHolder.setDataSourceType("dataSource2");
		return "";

	}
}
