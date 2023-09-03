package logtrace.template.app.v3;

import org.springframework.stereotype.Service;

import logtrace.template.trace.TraceStatus;
import logtrace.template.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

	private final OrderRepositoryV3 orderRepositoryV3;

	private final LogTrace logTrace;

	public void orderItem(String itemId) {
		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderService.orderItem()");
			orderRepositoryV3.save(itemId);
			logTrace.end(status);
		}catch (Exception e) {
			logTrace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져주어야 한다.
		}
	}

}
