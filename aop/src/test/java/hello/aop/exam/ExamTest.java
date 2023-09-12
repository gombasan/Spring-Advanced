package hello.aop.exam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import hello.aop.exam.aop.RetryAspect;
import hello.aop.exam.aop.TraceAspect;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Import({TraceAspect.class, RetryAspect.class})
public class ExamTest {

	@Autowired
	private ExamRepository examRepository;

	@Test
	void test() {
		for (int i = 0; i < 5; i++) {
			log.info("client request i={}", i);
			examRepository.save("data" + i);
		}
	}
}
