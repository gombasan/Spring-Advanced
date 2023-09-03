package logtrace.template.app.v5;

import org.springframework.stereotype.Repository;

import logtrace.template.trace.callback.TraceTemplate;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV5 {

	private final TraceTemplate template;

	public void save(String itemId) {
		template.execute("OrderController.request", ()-> {
			if(itemId.equals("ex")) {
				throw new IllegalStateException("예외 발생");
			}
			sleep(1000);
			return null;
		});
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
