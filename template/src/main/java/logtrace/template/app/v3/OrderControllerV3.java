package logtrace.template.app.v3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import logtrace.template.trace.TraceStatus;
import logtrace.template.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

	private final OrderServiceV3 orderServiceV3;

	private final LogTrace logTrace;


	@GetMapping("/v3/request")
	public String request(String itemId) {
		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderController.request()");
			orderServiceV3.orderItem(itemId);
			logTrace.end(status);
			return "ok";
		}catch (Exception e) {
			logTrace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져주어야 한다.
		}
	}
}
