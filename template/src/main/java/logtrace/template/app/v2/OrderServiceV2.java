package logtrace.template.app.v2;

import org.springframework.stereotype.Service;

import logtrace.template.trace.TraceId;
import logtrace.template.trace.TraceStatus;
import logtrace.template.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

	private final OrderRepositoryV2 orderRepositoryV2;

	private final HelloTraceV2 helloTraceV2;

	public void orderItem(String itemId, TraceId traceId) {
		TraceStatus status = null;
		try {
			status = helloTraceV2.beginSync(traceId,"OrderService.orderItem()");
			orderRepositoryV2.save(itemId, status.getTraceId());
			helloTraceV2.end(status);
		}catch (Exception e) {
			helloTraceV2.exception(status, e);
			throw e; // 예외를 꼭 다시 던져주어야 한다.
		}
	}

}
