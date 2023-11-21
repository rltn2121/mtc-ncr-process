package mtc.ncr.process.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogDto {
    private String gid;
    private int sno;
    private String svcid;
    private String inputJson;
    private String outputJson;
}
