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
    private String cur_c;
    private int upmu_g;
    private int aprv_sno;
    private int trx_amt;
    private int nujk_jan;
}
