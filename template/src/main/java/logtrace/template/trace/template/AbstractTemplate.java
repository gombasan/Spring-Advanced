package logtrace.template.trace.template;

import logtrace.template.trace.TraceStatus;
import logtrace.template.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate <T> {
	private final LogTrace logTrace;

	public AbstractTemplate(LogTrace trace) {
		this.logTrace = trace;
	}

	public T execute(String message) {
		TraceStatus status = null;
		try {
			status = logTrace.begin(message);
			// 로직 실행
			T result = call();

			logTrace.end(status);
			return result;
		}catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}

	protected abstract T call();
}
