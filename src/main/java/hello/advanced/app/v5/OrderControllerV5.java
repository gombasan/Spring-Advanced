package hello.advanced.app.v5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV5 {

	private final OrderServiceV5 orderServiceV5;

	private final LogTrace logTrace;


	@GetMapping("/v5/request")
	public String request(String itemId) {
		TraceTemplate traceTemplate = new TraceTemplate(logTrace);
		return traceTemplate.execute("OrderController.request", () -> {
			orderServiceV5.orderItem(itemId);
			return "ok";
		});
	}
}
