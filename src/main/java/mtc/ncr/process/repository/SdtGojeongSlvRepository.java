package mtc.ncr.process.repository;

import lombok.extern.slf4j.Slf4j;
import mtc.ncr.process.db.DBConnectionUtil;
import mtc.ncr.process.dto.GojeongDto;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Slf4j
@Repository
public class SdtGojeongSlvRepository {

    public GojeongDto insert(String acno, String trxdt, String cur_c, int upmu_g, String aprv_sno, Double trx_amt, Double nujk_jan, String err_msg) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql1 = "insert into chl_sdt_gojeong_slv (acno, trxdt, cur_c, upmu_g, aprv_sno, trx_amt, nujk_jan, err_msg) values(?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql1);
            pstmt.setString(1, acno);
            pstmt.setString(2, trxdt);
            pstmt.setString(3, cur_c);
            pstmt.setInt(4, upmu_g);
            pstmt.setString(5, aprv_sno);
            pstmt.setDouble(6, trx_amt);
            pstmt.setDouble(7, nujk_jan);
            pstmt.setString(8, err_msg);
            pstmt.executeUpdate();

//            pstmt = con.prepareStatement(sql2);
//            pstmt.setString(1, acno);
//            pstmt.setString(2, trxdt);
//            pstmt.setString(3, cur_c);
//            pstmt.setInt(4, upmu_g);
//            pstmt.setInt(5, aprv_sno);
//            pstmt.setInt(6, trx_amt);
//            pstmt.setInt(7, nujk_jan);
//            pstmt.executeUpdate();
            return new GojeongDto(0, acno, trxdt, cur_c, upmu_g, aprv_sno, trx_amt, nujk_jan, err_msg);
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }
    private void close(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
    }
    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
