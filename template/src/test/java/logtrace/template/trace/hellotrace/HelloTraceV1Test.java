package logtrace.template.trace.hellotrace;

import org.junit.jupiter.api.Test;

import logtrace.template.trace.TraceStatus;

public class HelloTraceV1Test {

	@Test
	void begin_end() {
		HelloTraceV1 traceV1 = new HelloTraceV1();
		TraceStatus status = traceV1.begin("hello");
		traceV1.end(status);
	}

	@Test
	void begin_exception() {
		HelloTraceV1 traceV1 = new HelloTraceV1();
		TraceStatus status = traceV1.begin("hello");
		traceV1.exception(status, new IllegalStateException());

	}
}
