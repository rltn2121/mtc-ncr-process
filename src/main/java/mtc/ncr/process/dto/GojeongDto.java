package mtc.ncr.process.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GojeongDto {
    private int sno;
    private String acno;
    private String trxdt;
    private String curC;
    private int upmuG;
    private String aprvSno;
    private Double trxAmt;
    private Double nujkJan;
    private String errMsg;
}
