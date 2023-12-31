package logtrace.template.app.v1;

import org.springframework.stereotype.Service;

import logtrace.template.trace.TraceStatus;
import logtrace.template.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

	private final OrderRepositoryV1 orderRepositoryV1;

	private final HelloTraceV1 helloTraceV1;

	public void orderItem(String itemId) {
		TraceStatus status = null;
		try {
			status = helloTraceV1.begin("OrderService.orderItem()");
			orderRepositoryV1.save(itemId);
			helloTraceV1.end(status);
		}catch (Exception e) {
			helloTraceV1.exception(status, e);
			throw e; // 예외를 꼭 다시 던져주어야 한다.
		}
	}

}
