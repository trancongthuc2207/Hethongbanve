/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author Admin
 */
public class nhanvien_taikhoan {
    private int MaNV;
    private String Taikhoan;
    private String Matkhau;
    private int Chucvu;
    
    public nhanvien_taikhoan(){
    }

    public nhanvien_taikhoan(int MaNV, String Taikhoan, String Matkhau, int Chucvu) {
        this.MaNV = MaNV;
        this.Taikhoan = Taikhoan;
        this.Matkhau = Matkhau;
        this.Chucvu = Chucvu;
    }

    @Override
    public String toString() {
        return this.Taikhoan; //To change body of generated methods, choose Tools | Templates.
    }
    
   
   
  
    /**
     * @return the MaNV
     */
    public int getMaNV() {
        return MaNV;
    }

    /**
     * @param MaNV the MaNV to set
     */
    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    /**
     * @return the Taikhoan
     */
    public String getTaikhoan() {
        return Taikhoan;
    }

    /**
     * @param Taikhoan the Taikhoan to set
     */
    public void setTaikhoan(String Taikhoan) {
        this.Taikhoan = Taikhoan;
    }

    /**
     * @return the Matkhau
     */
    public String getMatkhau() {
        return Matkhau;
    }

    /**
     * @param Matkhau the Matkhau to set
     */
    public void setMatkhau(String Matkhau) {
        this.Matkhau = Matkhau;
    }

    /**
     * @return the Chucvu
     */
    public int getChucvu() {
        return Chucvu;
    }

    /**
     * @param Chucvu the Chucvu to set
     */
    public void setChucvu(int Chucvu) {
        this.Chucvu = Chucvu;
    }
    
}
