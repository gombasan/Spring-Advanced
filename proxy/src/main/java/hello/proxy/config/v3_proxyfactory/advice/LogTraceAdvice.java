package hello.proxy.config.v3_proxyfactory.advice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogTraceAdvice implements MethodInterceptor {

	private final LogTrace trace;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		TraceStatus status = null;
		Method method = invocation.getMethod();
		String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
		try {
			status = trace.begin(message);
			Object result = invocation.proceed();
			trace.end(status);
			return result;
		}catch (Exception e){
			trace.exception(status, e);
			throw e;
		}
	}
}
