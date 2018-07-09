package com.test.app.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
/*import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;*/
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.test.app.entity.User;

@Aspect
public class LoggingAspect {

	/*
	 * @Before("execution(* com.test.app.dao.UserMapper.get*(..)) || execution(* com.test.app.dao.UserMapper.*(..))")
	 */
	@Before("execution(int com.test.app.dao.AtmCmdMapper.*(..)) || execution(int com.test.app.dao.FunctiongrpMapper.*(..)) || execution(int com.test.app.dao.FungrpFunMapper.*(..)) || "
			+ "execution(int com.test.app.dao.RoleFungrpMapper.*(..)) || execution(int com.test.app.dao.RoleMapper.*(..)) || execution(int com.test.app.dao.UserMapper.*(..)) || execution(int com.test.app.dao.AtmMasterMapper.*(..))")
	public void logBeforeACLModule(JoinPoint joinPoint) {
		String classname = "";
		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("target->class->name : " + joinPoint.getTarget().getClass().getName());
		Object[] args = joinPoint.getArgs();
		System.out.println("arguments : " + Arrays.toString(joinPoint.getArgs()));
		System.out.println("argument class : " + args[0].getClass().getSimpleName());
		for (int i = 0; i < args.length; i++) {
			classname = args[i].getClass().getSimpleName().trim();
			switch (classname) {
		    case "User":
		    	User user = (User)args[i];
		        System.out.println(user.getUserName());
		        break;
		    case "MyClass2":
		    	System.out.println();
		        break;
		    default:
		    	System.out.println();
		        break;
		}
		}
		System.out.println("******");
	}

	/*
	 * @Before("execution(* com.test.app.dao.cfg.*(..))") public void
	 * logBeforeCFGModule(JoinPoint joinPoint) {
	 * 
	 * System.out.println("logBefore() is running!");
	 * System.out.println("hijacked : " + joinPoint.getSignature().getName());
	 * System.out.println("target->class->name : " +
	 * joinPoint.getTarget().getClass().getName());
	 * System.out.println("arguments : " + Arrays.toString(joinPoint.getArgs()));
	 * System.out.println("******"); }
	 */

	/*
	 * @After("execution(* com.mkyong.customer.bo.CustomerBo.addCustomer(..))")
	 * public void logAfter(JoinPoint joinPoint) {
	 * 
	 * System.out.println("logAfter() is running!");
	 * System.out.println("hijacked : " + joinPoint.getSignature().getName());
	 * System.out.println("******");
	 * 
	 * }
	 */

	/*
	 * @AfterReturning( pointcut =
	 * "execution(* com.mkyong.customer.bo.CustomerBo.addCustomerReturnValue(..))",
	 * returning= "result") public void logAfterReturning(JoinPoint joinPoint,
	 * Object result) {
	 * 
	 * System.out.println("logAfterReturning() is running!");
	 * System.out.println("hijacked : " + joinPoint.getSignature().getName());
	 * System.out.println("Method returned value is : " + result);
	 * System.out.println("******");
	 * 
	 * }
	 */

	/*
	 * @AfterThrowing( pointcut =
	 * "execution(* com.mkyong.customer.bo.CustomerBo.addCustomerThrowException(..))"
	 * , throwing= "error") public void logAfterThrowing(JoinPoint joinPoint,
	 * Throwable error) {
	 * 
	 * System.out.println("logAfterThrowing() is running!");
	 * System.out.println("hijacked : " + joinPoint.getSignature().getName());
	 * System.out.println("Exception : " + error); System.out.println("******");
	 * 
	 * }
	 */

	/*
	 * @Around("execution(* com.mkyong.customer.bo.CustomerBo.addCustomerAround(..))"
	 * ) public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
	 * 
	 * System.out.println("logAround() is running!");
	 * System.out.println("hijacked method : " +
	 * joinPoint.getSignature().getName());
	 * System.out.println("hijacked arguments : " +
	 * Arrays.toString(joinPoint.getArgs()));
	 * 
	 * System.out.println("Around before is running!"); joinPoint.proceed();
	 * System.out.println("Around after is running!");
	 * 
	 * System.out.println("******");
	 * 
	 * }
	 */

}