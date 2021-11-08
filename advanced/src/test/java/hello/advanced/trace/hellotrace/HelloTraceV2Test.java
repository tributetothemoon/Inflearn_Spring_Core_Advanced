package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {
    @Test
    void begin_end() {
        HelloTraceV2 traceV2 = new HelloTraceV2();
        TraceStatus status1 = traceV2.begin("hello");
        TraceStatus status2 = traceV2.beginSync(status1.getTraceId(), "hello2");
        traceV2.end(status2);
        traceV2.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 traceV2 = new HelloTraceV2();
        TraceStatus status1 = traceV2.begin("hello1");
        TraceStatus status2 = traceV2.beginSync(status1.getTraceId(), "hello2");
        traceV2.exception(status2, new IllegalStateException());
        traceV2.exception(status1, new IllegalStateException());
    }
}

