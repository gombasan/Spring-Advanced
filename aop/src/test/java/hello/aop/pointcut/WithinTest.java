package hello.aop.pointcut;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WithinTest {
	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	Method helloMethod;

	@BeforeEach
	public void init() throws NoSuchMethodException {
		helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
	}

	@Test
	void withinExact() {
		pointcut.setExpression("within(hello.aop.member.MemberServiceImpl)");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void withinStar() {
		pointcut.setExpression("within(hello.aop.member.MemberServ*)");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void withinSubPackage() {
		pointcut.setExpression("within(hello.aop..*)");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void withinSuperTypeFalse() {
		pointcut.setExpression("within(hello.aop.member.MemberService)");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}
}
