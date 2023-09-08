package hello.aop.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AspectV3 {

	@Pointcut("execution(* hello.aop.order..*(..))")
	private void allOrder() {} //pointcut signature

	@Pointcut("execution(* *..*Service.*(..))")
	private void allService(){}

	@Around("allOrder()")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
		log.info("[log] {}", joinPoint.getSignature());
		return joinPoint.proceed();
	}

	//hello.aop.order 패키지와 하위패키지 이면서 클래스 이름 패턴이 *Service
	@Around("allService() && allOrder()")
	public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
			Object proceed = joinPoint.proceed();
			log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
			return proceed;
		} catch (Exception e) {
			log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
			throw e;
		} finally {
			log.info("[리소스 릴리스] {}", joinPoint.getSignature());
		}
	}
}
