package logtrace.template.app.v4;

import org.springframework.stereotype.Service;

import logtrace.template.trace.logtrace.LogTrace;
import logtrace.template.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

	private final OrderRepositoryV4 orderRepositoryV4;

	private final LogTrace logTrace;

	public void orderItem(String itemId) {

		AbstractTemplate<Void> template = new AbstractTemplate<>(logTrace) {
			@Override
			protected Void call() {
				orderRepositoryV4.save(itemId);
				return null;
			}
		};
		template.execute("OrderService.orderItem()");
	}
}
