package mtc.ncr.process.apis;


import lombok.RequiredArgsConstructor;
import mtc.ncr.process.dto.GojeongDto;
import mtc.ncr.process.queue.TempKafka;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafkatest")
@RequiredArgsConstructor
public class KafkaTestController implements KafkaTestApi{

    private final TempKafka kafka;

    @Override
    public ResponseEntity<?> trxHistory() throws Exception {
        GojeongDto dto = new GojeongDto(0, "999999999", "20231112", "KRW", 1, "승인번호123", 10000.0, 100000.0, "에러메시지지롱");
        kafka.produceMessage(dto);  // 1. kafka queue에 message produce
        return ResponseEntity.ok("kafka queue에 message produce");
    }
}
