package edu.study.pasudo123.redisstream.sync;

import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class CapturingStreamListener implements StreamListener<String, MapRecord<String, String, String>> {

    private AtomicInteger counter = new AtomicInteger(0);
    private BlockingDeque<MapRecord<String, String, String>> deque = new LinkedBlockingDeque<>();

    private CapturingStreamListener(){}

    @Override
    public void onMessage(MapRecord<String, String, String> message) {

    }

    public static CapturingStreamListener create() {
        return new CapturingStreamListener();
    }

    /**
     * 전체로 받은 레코드 수 반환
     */
    public int recordsReceived() {
        return counter.get();
    }

    public MapRecord<String, String, String> take() throws InterruptedException {
        return deque.take();
    }
}
