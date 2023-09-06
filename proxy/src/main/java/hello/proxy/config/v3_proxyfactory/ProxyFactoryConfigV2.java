package hello.proxy.config.v3_proxyfactory;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

	@Bean
	public OrderControllerV2 orderControllerV2_factory(LogTrace logTrace){
		OrderControllerV2 orderController = new OrderControllerV2(orderServiceV2_factory(logTrace));
		ProxyFactory proxyFactory = new ProxyFactory(orderController);
		proxyFactory.addAdvisor(getAdvisor(logTrace));
		OrderControllerV2 proxy = (OrderControllerV2)proxyFactory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderController.getClass());
		return proxy;
	}


	@Bean
	public OrderServiceV2 orderServiceV2_factory(LogTrace logTrace) {
		OrderServiceV2 orderService = new OrderServiceV2(orderRepositoryV2_factory(logTrace));
		ProxyFactory proxyFactory = new ProxyFactory(orderService);
		proxyFactory.addAdvisor(getAdvisor(logTrace));
		OrderServiceV2 proxy = (OrderServiceV2)proxyFactory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderService.getClass());
		return proxy;
	}

	@Bean
	public OrderRepositoryV2 orderRepositoryV2_factory(LogTrace logTrace) {
		OrderRepositoryV2 orderRepository = new OrderRepositoryV2();
		ProxyFactory proxyFactory = new ProxyFactory(orderRepository);
		proxyFactory.addAdvisor(getAdvisor(logTrace));
		OrderRepositoryV2 proxy = (OrderRepositoryV2)proxyFactory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderRepository.getClass());
		return proxy;
	}

	private Advisor getAdvisor(LogTrace logTrace) {
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("request*", "order*", "save*");
		LogTraceAdvice advice = new LogTraceAdvice(logTrace);
		return new DefaultPointcutAdvisor(pointcut, advice);
	}
}