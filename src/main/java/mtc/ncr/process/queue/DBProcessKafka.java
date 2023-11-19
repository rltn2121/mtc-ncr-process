package mtc.ncr.process.queue;

import lombok.RequiredArgsConstructor;
import mtc.ncr.process.dto.GojeongDto;
import mtc.ncr.process.repository.SdtGojeongSlvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class DBProcessKafka {
    private final static Logger log = LoggerFactory.getLogger(DBProcessKafka.class);
    private final SdtGojeongSlvRepository repository = new SdtGojeongSlvRepository();

    @KafkaListener(topics = "mtc.dbs.insertGojeong")
    public void consumeMessage(@Payload GojeongDto data,
                               @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                               @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
                               @Header(KafkaHeaders.OFFSET) long offset) throws SQLException {
        log.info("listener --- [{}][{}][{}][{}][{}]", topic, timestamp, offset, key, data.toString());
        // sno 만들어서 넣기
        int nxtSno = repository.getMaxSno(data.getAcno()) + 1;
        GojeongDto gojeongdto = new GojeongDto(nxtSno, data.getAcno(), data.getTrxdt(), data.getCurC(), data.getUpmuG(), data.getAprvSno(), data.getTrxAmt(), data.getNujkJan(), data.getErrMsg());
        repository.insert(gojeongdto);
    }
}