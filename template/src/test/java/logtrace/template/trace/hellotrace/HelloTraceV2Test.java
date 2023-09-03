package logtrace.template.trace.hellotrace;

import org.junit.jupiter.api.Test;

import logtrace.template.trace.TraceStatus;

public class HelloTraceV2Test {

	@Test
	void begin_end() {
		HelloTraceV2 traceV2 = new HelloTraceV2();
		TraceStatus status1 = traceV2.begin("hello");
		TraceStatus status2 = traceV2.beginSync(status1.getTraceId(), "hello2");
		traceV2.end(status2);
		traceV2.end(status1);
	}

	@Test
	void begin_exception() {
		HelloTraceV2 traceV2 = new HelloTraceV2();
		TraceStatus status1 = traceV2.begin("hello");
		TraceStatus status2 = traceV2.beginSync(status1.getTraceId(),"hello");
		traceV2.exception(status2, new IllegalStateException());
		traceV2.exception(status1, new IllegalStateException());

	}
}
