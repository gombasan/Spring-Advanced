package logtrace.template.app.v5;

import org.springframework.stereotype.Service;

import logtrace.template.trace.callback.TraceTemplate;
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
