package logtrace.template.trace.strategy;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1Test {

	@Test
	void templateMethodV0() {
		logic1();
		logic2();
	}

	private void logic2() {
		long startTime = System.currentTimeMillis();
		// 비즈니르 로직 실행
		log.info("비즈니스 로직2 실행");
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}

	private void logic1() {
		long startTime = System.currentTimeMillis();
		// 비즈니르 로직 실행
		log.info("비즈니스 로직1 실행");
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}

	@Test
	void strategy() {
		StrategyLogic1 strategyLogic1 = new StrategyLogic1();
		ContextV1 context1 = new ContextV1(strategyLogic1);
		context1.execute();

		StrategyLogic1 strategyLogic2 = new StrategyLogic1();
		ContextV1 context2 = new ContextV1(strategyLogic1);
		context2.execute();
	}

	@Test
	void strategyV2() {
		Strategy strategy1 = new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		};
		ContextV1 context1 = new ContextV1(strategy1);
		context1.execute();

		Strategy strategy2 = new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		};
		ContextV1 context2 = new ContextV1(strategy2);
		context2.execute();
	}
}
