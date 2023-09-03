package logtrace.template.trace.logtrace;

import logtrace.template.trace.TraceStatus;

public interface LogTrace {

	TraceStatus begin(String message);

	void end(TraceStatus status);

	void exception(TraceStatus status, Exception e);
}
