package mtc.ncr.process.apis;


import lombok.RequiredArgsConstructor;
import mtc.ncr.process.dto.GojeongDto;
import mtc.ncr.process.queue.DBProcessKafka;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafkatest")
@RequiredArgsConstructor
public class KafkaTestController implements KafkaTestApi{

    private final DBProcessKafka kafka;

    @Override
    public ResponseEntity<?> trxHistory() throws Exception {
        GojeongDto dto = new GojeongDto(0, "777", "20231113", "USD", 2, "카프카테스트", 10000.0, 100000.0, "");

        // kafka.produceMessage(dto);  // 1. kafka queue에 message produce
        return ResponseEntity.ok("kafka queue에 message produce");
    }
}
