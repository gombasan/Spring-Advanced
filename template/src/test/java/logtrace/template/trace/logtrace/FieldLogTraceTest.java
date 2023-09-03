package logtrace.template.trace.logtrace;

import org.junit.jupiter.api.Test;

import logtrace.template.trace.TraceStatus;

public class FieldLogTraceTest {
	FieldLogTrace trace = new FieldLogTrace();

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
