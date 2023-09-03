package logtrace.template.trace.strategy;

import org.junit.jupiter.api.Test;

import logtrace.template.trace.strategy.code.template.Callback;
import logtrace.template.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateCallbackTest {

	@Test
	void callbackV1() {
		TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
		timeLogTemplate.execute(new Callback() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		});

		timeLogTemplate.execute(new Callback() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		});
	}
	@Test
	void callbackV2() {
		TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
		timeLogTemplate.execute(() -> log.info("비즈니스 로직1 실행"));

		timeLogTemplate.execute(() -> log.info("비즈니스 로직2 실행"));
	}
}
