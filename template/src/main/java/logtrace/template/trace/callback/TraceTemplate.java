package logtrace.template.trace.callback;

import logtrace.template.trace.TraceStatus;
import logtrace.template.trace.logtrace.LogTrace;

public class TraceTemplate {

	private final LogTrace trace;

	public TraceTemplate(LogTrace trace) {
		this.trace = trace;
	}

	public <T> T execute(String message, TraceCallback<T> traceCallback) {
		TraceStatus status = null;
		try {
			status = trace.begin(message);
			// 로직 실행
			T result = traceCallback.call();

			trace.end(status);
			return result;
		}catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}
