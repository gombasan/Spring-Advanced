package hello.aop.exam;

import org.springframework.stereotype.Service;

import hello.aop.exam.annotation.Trace;

@Service
public class ExamService {

	public final ExamRepository examRepository;

	public ExamService(ExamRepository examRepository) {
		this.examRepository = examRepository;
	}

	@Trace
	public void request(String itemId) {
		examRepository.save(itemId);
	}
}
