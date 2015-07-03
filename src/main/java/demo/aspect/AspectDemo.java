package demo.aspect;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import demo.util.AESAPPUtils;
import demo.util.GzipAESUtil;
import demo.util.JsonUtil;

@Aspect
@Component
public class AspectDemo {

	private static final String APP_MESSAGE_KEY = "SPRING";

	Logger LOG = Logger.getLogger(AspectDemo.class);

	private static int ctn = 0;

	public AspectDemo() {
		System.out.println("AspectDemo initialized count = " + ctn);
		AspectDemo.ctn++;
	}

	@Pointcut("execution(* demo.controller.UserController.getDynamicUsers(..))")
	public void exeTest() {
	}

	@Around("exeTest()")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		LOG.info("我进来 了");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String methodName = joinPoint.getSignature().getName();
		LOG.info("拦截到路径: " + request.getRequestURL());

		String parameter = request.getParameter(APP_MESSAGE_KEY);
		if(parameter != null){
			String recive = AESAPPUtils.decryptAES(parameter);
			
			Map<String, Object> map = JsonUtil.jsonStringToMap(recive);
			System.out.println(map);
			
			request.setAttribute("SPRING", map);
		}
        
		Object target = joinPoint.getTarget();
		Object[] args = joinPoint.getArgs();
		Class []cls = new Class[args.length];
		HttpServletRequest in = null;
		HttpServletResponse out = null;
		for(int i=0; i< args.length; i++){
			if (args[i] instanceof HttpServletRequest) {
				in = (HttpServletRequest) args[i];
				cls[i] = HttpServletRequest.class;
			} else if (args[i] instanceof HttpServletResponse) {
				out = (HttpServletResponse) args[i];
				cls[i] = HttpServletResponse.class;
			}
		}
//		Method method = joinPoint.getSignature().getDeclaringTypeName().getDeclaringType().getMethod(methodName, cls);
//		Class<?> rType = method.getReturnType();
		Object o = joinPoint.proceed();
//		Object o = method.invoke(target, args);
		
		LOG.info("我要出去了");
		String enString = GzipAESUtil.compressThenEncryptAES((String)o);
		return enString;

	}
}
