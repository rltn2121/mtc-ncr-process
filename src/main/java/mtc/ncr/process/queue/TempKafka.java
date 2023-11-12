package mtc.ncr.process.queue;

import lombok.RequiredArgsConstructor;
import mtc.ncr.process.dto.GojeongDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TempKafka {

    private final static Logger log = LoggerFactory.getLogger(TempKafka.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void produceMessage(GojeongDto dto) {
        log.info(" ----> 카프카 큐에 넣을 메시지 produce 하는중!!!");
        kafkaTemplate.send("mtc.ncr.process", "INSERT", dto);
        log.info(" ----> 카프카 큐에 send 했음!!!");
    }

    @KafkaListener(topics = "mtc.ncr.process", groupId = "consumerGroupId")
    public void consumeMessage(@Payload GojeongDto data,
                               @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                               @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
                               @Header(KafkaHeaders.OFFSET) long offset) {
        if("INSERT".equals(key)) {
            String response = "성공? 실패?";


            kafkaTemplate.send("mtc.ncr.process", "INSERT", response)
                    .whenCompleteAsync((result, ex) -> {
                        if(ex == null) {
                            log.info("카프카 큐에 send 성공!! {}", result.getProducerRecord().key());
                        } else {
                            log.error("카프카 큐에 send 실패...");
                        }
                    });
        }
    }

}
