package mtc.ncr.process.apis;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface KafkaTestApi {
    @GetMapping("/insert")
    ResponseEntity<?> trxHistory() throws Exception;
}
