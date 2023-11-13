package mtc.ncr.process.repository;

import lombok.extern.slf4j.Slf4j;
import mtc.ncr.process.db.DBConnectionUtil;
import mtc.ncr.process.dto.GojeongDto;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Slf4j
@Repository
public class SdtGojeongSlvRepository {
    public int getMaxSno(String acno) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select max(sno) from chl_sdt_gojeong_slv where acno = ?;";

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, acno);
            rs = pstmt.executeQuery();
            while(rs.next()){
                return rs.getInt("max");
            }
            return 0;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }

    public GojeongDto insert(GojeongDto gojeongdto) throws SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql1 = "insert into chl_sdt_gojeong_slv (acno, trxdt, cur_c, upmu_g, aprv_sno, trx_amt, nujk_jan, err_msg) values(?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql1);
            pstmt.setString(1, gojeongdto.getAcno());
            pstmt.setString(2, gojeongdto.getTrxdt());
            pstmt.setString(3, gojeongdto.getCurC());
            pstmt.setInt(4, gojeongdto.getUpmuG());
            pstmt.setString(5, gojeongdto.getAprvSno());
            pstmt.setDouble(6, gojeongdto.getTrxAmt());
            pstmt.setDouble(7, gojeongdto.getNujkJan());
            pstmt.setString(8, gojeongdto.getErrMsg());
            pstmt.executeUpdate();

            return gojeongdto;
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
