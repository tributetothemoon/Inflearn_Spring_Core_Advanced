package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV3 {
    private InternalService internalService;

    public CallServiceV3(InternalService internalService) {
        this.internalService = internalService;
    }

    public void external() {
        log.info("call external");
        internalService.internal();
    }
}
