package logtrace.template.app.v4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import logtrace.template.trace.logtrace.LogTrace;
import logtrace.template.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

	private final OrderServiceV4 orderServiceV4;

	private final LogTrace logTrace;


	@GetMapping("/v4/request")
	public String request(String itemId) {
		AbstractTemplate<String> template = new AbstractTemplate<>(logTrace) {
			@Override
			protected String call() {
				orderServiceV4.orderItem(itemId);
				return "ok";
			}
		};
		return template.execute("OrderController.request()");
	}
}
