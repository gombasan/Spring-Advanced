package logtrace.template.app.v3;

import org.springframework.stereotype.Repository;

import logtrace.template.trace.TraceStatus;
import logtrace.template.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

	private final LogTrace logTrace;

	public void save(String itemId) {

		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderRepository.save()");
			if(itemId.equals("ex")) {
				throw new IllegalStateException("예외 발생");
			}
			sleep(1000);
			logTrace.end(status);
		}catch (Exception e) {
			logTrace.exception(status, e);
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
