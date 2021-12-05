package com.DAO;

import com.DTO.DetailMatchDTO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class DetailMatchDAO {
    MyConnectUnit connect;
    String strTableName = "useraccount";

    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<DetailMatchDTO> readDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();

        ResultSet result = this.connect.Select(strTableName, condition, orderBy);
        ArrayList<DetailMatchDTO> DTOs = new ArrayList<>();
        while (result.next()) {
            DetailMatchDTO taokhoan = new DetailMatchDTO();
            taokhoan.setStrUid(result.getString("UID"));
//            taokhoan.setStrIdRoom(result.getString("UID"));
//            taokhoan.setStrUid(result.getString("UID"));
//            taokhoan.setStrUid(result.getString("UID"));

            DTOs.add(taokhoan);
        }
        connect.Close();
        return DTOs;
    }

    public ArrayList<DetailMatchDTO> readDB(String condition) throws Exception {
        return readDB(condition, null);
    }

    public ArrayList<DetailMatchDTO> readDB() throws Exception {
        return readDB(null);
    }

    /**
     * Tạo thêm 1 tài khoản dựa theo đã có thông tin trước
     *
     * @return true nếu thành công
     */
    public Boolean add(DetailMatchDTO tk) throws Exception {
        connect = new MyConnectUnit();

        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("UID", tk.getStrUid());

        Boolean check = connect.Insert(strTableName, insertValues);

        connect.Close();
        return check;
    }

    /**
     * @param tk chuyền vào dữ liệu tài khoản để xóa
     * @return true nếu thành công
     */
    public Boolean delete(DetailMatchDTO tk) throws Exception {
        connect = new MyConnectUnit();
        String condition = " UID = '" + tk.getStrUid() + "'";

        Boolean check = connect.Delete(strTableName, condition);

        connect.Close();
        return check;
    }

    /**
     * @param tk truyền vào dữ liệu tài khoản mới
     *           Sửa thông tin đăng nhập hoặc là cấp bậc của 1 tài khoản
     * @return true nếu thành công
     */
    public Boolean update(DetailMatchDTO tk) throws Exception {
        connect = new MyConnectUnit();

        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
//        insertValues.put("matkhau", tk.getStrMatKhau());
//        insertValues.put("capbac", tk.getiCapBac());

        String condition = " UID = '" + tk.getStrUid() + "'";

        Boolean check = connect.Update(strTableName, insertValues, condition);

        connect.Close();
        return check;
    }
}