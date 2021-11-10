package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Slf4j
public class ProxyCastingTest {
    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);

        // JDK 프록시를 인터페이스로 캐스팅하는 것은 성공한다.
        assertDoesNotThrow(() -> {
            MemberService proxy = (MemberService) proxyFactory.getProxy();
        });
    }

    @Test
    void jdkProxyFalse() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);

        // JDK 프록시를 구체 클래스로 캐스팅하는 것은 실패한다.
        assertThatThrownBy(() -> {
            MemberServiceImpl proxy = (MemberServiceImpl) proxyFactory.getProxy();
        }).isInstanceOf(ClassCastException.class);
    }

    @Test
    void cglibProxySuccess() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);

        // CGLIB 프록시를 구체 클래스로 캐스팅하는 것은 성공한다.
        assertDoesNotThrow(() -> {
            MemberServiceImpl proxy = (MemberServiceImpl) proxyFactory.getProxy();
        });
    }
}
