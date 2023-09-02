package hello.advanced.app.v5;

import org.springframework.stereotype.Service;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV5 {

	private final OrderRepositoryV5 orderRepositoryV5;

	private final TraceTemplate template;

	public void orderItem(String itemId) {
		template.execute("OrderController.request", () -> {
			orderRepositoryV5.save(itemId);
			return null;
		});
	}
}
