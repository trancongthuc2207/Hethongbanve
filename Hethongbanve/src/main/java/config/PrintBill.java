/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import Service.Login_nhanvien;
import Service.Sv_CheckOption;
import Service.Sv_chuyendi;
import Service.Sv_khachhang;
import Service.Sv_xe;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import pojo.vexe;

/**
 *
 * @author Admin
 */
public class PrintBill {
    private final static String FILE_URL = "src/main/resources/HOADON/HoaDon.txt";
    
    public static void PrintBill(vexe vx) throws IOException, SQLException{
        Sv_chuyendi sv_cd = new Sv_chuyendi();
        Sv_khachhang sv_kh = new Sv_khachhang();
        Sv_xe sv_xe = new Sv_xe();
        Login_nhanvien sv_nv = new Login_nhanvien();
        File file = new File(FILE_URL);
        OutputStream outputStream = new FileOutputStream(file,true);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"utf8");
        
        String tenChuyenDi = sv_cd.getMaToChuyen(vx.getMaChuyen()).getTenChuyen();
        String[] CD = tenChuyenDi.split("-");
        String from = CD[0];
        String To = CD[1];
        double GiaChuyen = sv_cd.getMaToChuyen(vx.getMaChuyen()).getGia();
        
        if(vx.getTrangthai() == 2 && vx != null){
            outputStreamWriter.write("-------------------------------------\n");
            outputStreamWriter.write("     THẺ LÊN XE/BOARDING PASS    \n");
            outputStreamWriter.write("       " + tenChuyenDi +"    \n");
            outputStreamWriter.write("     XE/BUS: " + sv_xe.getMaToXE(vx.getMaXE(), vx.getMaChuyen()).getBienso() +"   \n");
            outputStreamWriter.write("  NƠI ĐI/FROM: "+ from + "\n");
            outputStreamWriter.write("  NƠI ĐẾN/TO: "+ To + "    MÃ VÉ/CODE: " + vx.getMaVE() + "\n");
            outputStreamWriter.write("  KHỞI HÀNH/DEPARTURE TIME: "+ vx.getThoigianbatdau() + "\n");
            outputStreamWriter.write("  GHẾ/SEAT: "+ vx.getSoghe()+ "    GIÁ: "+ GiaChuyen+ "\n");
            outputStreamWriter.write("  HỌ TÊN/NAME: "+ sv_kh.getMaToKH(vx.getMaKH()).getTenKH()+"\n");
            outputStreamWriter.write("  ĐIỆN THOẠI/TEL: "+ sv_kh.getMaToKH(vx.getMaKH()).getSDT()+"\n");
            outputStreamWriter.write("          MỜI LÊN XE: "+ sv_xe.getMaToXE(vx.getMaXE(), vx.getMaChuyen()).getTenXe()+"\n");
            outputStreamWriter.write("  Hành khách có mặt tại văn phòng     trước giờ khởi hành\n"
                                    +"  To be present at this office        before daparture time\n"   );
            outputStreamWriter.write("  NHÂN VIÊN/EMPLOYEE: "+ sv_nv.getNhanVienFromMa(vx.getMaNV()).getTenNV()+"\n");
            outputStreamWriter.write("  NGÀY IN/PRINTED DATE: "+ vx.getNgayin()+"\n");
            outputStreamWriter.write("-------------------------------------\n");
            Utils.getBox("VÉ ĐƯỢC IN THANH CÔNG!!", Alert.AlertType.INFORMATION).show();
        }
        outputStreamWriter.flush();
        outputStreamWriter.close();  
    }
    
}
