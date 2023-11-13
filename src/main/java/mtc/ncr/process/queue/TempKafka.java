package mtc.ncr.process.queue;

import lombok.RequiredArgsConstructor;
import mtc.ncr.process.dto.GojeongDto;
import mtc.ncr.process.repository.SdtGojeongSlvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class TempKafka {

    private final static Logger log = LoggerFactory.getLogger(TempKafka.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final SdtGojeongSlvRepository repository = new SdtGojeongSlvRepository();
    public void produceMessage(GojeongDto dto) {
        log.info(" ----> 카프카 큐에 넣을 메시지 produce 하는중!!!");
        kafkaTemplate.send("mtctopictest5", "INSERT", dto);
        log.info(" ----> 카프카 큐에 send 했음!!!");
    }

//    @KafkaListener(topics = "mtctopictest5", groupId = "test3")
//    public void consumeMessage(@Payload GojeongDto data,
//                               @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
//                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
//                               @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
//                               @Header(KafkaHeaders.OFFSET) long offset) throws SQLException {
//
//        log.info("@@@@@ key: {}", key);
//        if("INSERT".equals(key)) {
//            repository.insert(data.getAcno(), data.getTrxdt(), data.getCurC(), data.getUpmuG(), data.getAprvSno(), data.getTrxAmt(), data.getNujkJan());
//        }
//    }
}
