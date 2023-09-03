package logtrace.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import logtrace.template.trace.callback.TraceTemplate;
import logtrace.template.trace.logtrace.LogTrace;
import logtrace.template.trace.logtrace.ThreadLocalTrace;

@Configuration
public class LogTraceConfig {

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalTrace();
	}

	@Bean
	public TraceTemplate template() {
		return new TraceTemplate(logTrace());
	}
}
