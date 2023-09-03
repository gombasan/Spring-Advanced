package logtrace.template.app.v4;

import org.springframework.stereotype.Repository;

import logtrace.template.trace.logtrace.LogTrace;
import logtrace.template.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

	private final LogTrace logTrace;

	public void save(String itemId) {

		AbstractTemplate<Void> template = new AbstractTemplate<>(logTrace) {
			@Override
			protected Void call() {
				if(itemId.equals("ex")) {
					throw new IllegalStateException("예외 발생");
				}
				sleep(1000);
				return null;
			}
		};
		template.execute("OrderRepository.save()");
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
