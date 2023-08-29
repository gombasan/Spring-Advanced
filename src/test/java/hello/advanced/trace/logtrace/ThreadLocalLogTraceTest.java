package hello.advanced.trace.logtrace;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.TraceStatus;

public class ThreadLocalLogTraceTest {
	LogTrace trace = new ThreadLocalTrace();

	@Test
	void begin_end_level2() {
		TraceStatus status1 = trace.begin("hello1");
		TraceStatus status2 = trace.begin("hello2");

		trace.end(status2);
		trace.end(status1);
	}

	@Test
	void begin_exceptions_level2() {
		TraceStatus status2 = trace.begin("hello1");
		TraceStatus status = trace.begin("ex");

		trace.exception(status, new IllegalStateException());
		trace.end(status2);

	}
}
