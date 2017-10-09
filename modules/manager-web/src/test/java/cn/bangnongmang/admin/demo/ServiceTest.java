package cn.bangnongmang.admin.demo;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceTest {
//	@Autowired
//	private OrderService orderService;
//
//	@Before(value = "execution(* cn.bangnongmang.admin.service.OrderService.updateDesireNum(..))")
//	public void testName(JoinPoint point) throws BusinessException {
//		
//	
//		
//		
//		System.err.println("-----------------------");
//		 System.out.println("@Before：模拟权限检查...");
//	        System.out.println("@Before：目标方法为：" + 
//	                point.getSignature().getDeclaringTypeName() + 
//	                "." + point.getSignature().getName());
//	        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
//	        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
//	}
//	
//	@AfterReturning(pointcut = "execution(* cn.bangnongmang.admin.service.OrderService.updateDesireNum(..))", returning="returnValue")
//	public void testName1(JoinPoint point,Object returnValue) throws BusinessException {
//		
//	     System.out.println("@AfterReturning：模拟日志记录功能...");
//	        System.out.println("@AfterReturning：目标方法为：" + 
//	                point.getSignature().getDeclaringTypeName() + 
//	                "." + point.getSignature().getName());
//	        System.out.println("@AfterReturning：参数为：" + 
//	                Arrays.toString(point.getArgs()));
//	        System.out.println("@AfterReturning：返回值为：" + returnValue);
//	        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
//	}
//	
//	
//	

}