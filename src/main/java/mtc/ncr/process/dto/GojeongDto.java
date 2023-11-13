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
    private double trxAmt;
    private double nujkJan;
}

// "sno":0,
// "acno":"999999999",
// "trxdt":"20231112",
// "curC":"KRW",
// "upmuG":1,
// "aprvSno":"승인번호123",
// "trxAmt":10000,
// "nujkJan":100000
