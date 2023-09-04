package hello.proxy.config.v2_dynamicproxy;

import java.lang.reflect.Proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import hello.proxy.trace.logtrace.LogTrace;

@Configuration
public class DynamicProxyFilterConfig {

	private static final String[] PATTERNS = {"request*", "order*", "save*"};

	@Bean
	public OrderControllerV1 orderControllerV1Dynamic(LogTrace logTrace) {
		OrderControllerV1Impl orderController = new OrderControllerV1Impl(orderServiceV1Dynamic(logTrace));
		LogTraceFilterHandler target = new LogTraceFilterHandler(orderController, logTrace, PATTERNS);
		return (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(), new Class[] {OrderControllerV1.class}, target);
	}

	private OrderServiceV1 orderServiceV1Dynamic(LogTrace logTrace) {
		OrderServiceV1Impl orderService = new OrderServiceV1Impl(orderRepositoryV1Dynamic(logTrace));
		LogTraceFilterHandler target = new LogTraceFilterHandler(orderService, logTrace, PATTERNS);
		return (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(), new Class[] {OrderServiceV1.class}, target);
	}

	private OrderRepositoryV1 orderRepositoryV1Dynamic(LogTrace logTrace) {
		OrderRepositoryV1Impl orderRepository = new OrderRepositoryV1Impl();
		LogTraceFilterHandler target = new LogTraceFilterHandler(orderRepository, logTrace, PATTERNS);
		return (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(), new Class[] {OrderRepositoryV1.class},
			target);
	}
}
