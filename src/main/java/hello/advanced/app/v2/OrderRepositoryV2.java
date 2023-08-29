package hello.advanced.app.v2;

import org.springframework.stereotype.Repository;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

	private final HelloTraceV2 helloTraceV2;

	public void save(String itemId, TraceId traceId) {

		TraceStatus status = null;
		try {
			status = helloTraceV2.beginSync(traceId,"OrderRepository.save()");
			if(itemId.equals("ex")) {
				throw new IllegalStateException("예외 발생");
			}
			sleep(1000);
			helloTraceV2.end(status);
		}catch (Exception e) {
			helloTraceV2.exception(status, e);
			throw e; // 예외를 꼭 다시 던져주어야 한다.
		}

		// 저장 로직
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
