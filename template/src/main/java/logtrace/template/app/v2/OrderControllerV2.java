package logtrace.template.app.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import logtrace.template.trace.TraceStatus;
import logtrace.template.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

	private final OrderServiceV2 orderServiceV2;

	private final HelloTraceV2 helloTraceV2;


	@GetMapping("/v2/request")
	public String request(String itemId) {
		TraceStatus status = null;
		try {
			status = helloTraceV2.begin("OrderController.request()");
			orderServiceV2.orderItem(itemId, status.getTraceId());
			helloTraceV2.end(status);
			return "ok";
		}catch (Exception e) {
			helloTraceV2.exception(status, e);
			throw e; // 예외를 꼭 다시 던져주어야 한다.
		}
	}
}
