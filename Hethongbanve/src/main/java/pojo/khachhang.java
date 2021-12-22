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
public class khachhang {
    private int MaKH;
    private String TenKH;
    private String CMND;
    private String SDT;

    public khachhang() {
    }

    public khachhang(int MaKH, String TenKH, String CMND, String SDT) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.CMND = CMND;
        this.SDT = SDT;
    }

    /**
     * @return the MaNV
     */
    public int getMaKH() {
        return MaKH;
    }

    /**
     * @param MaNV the MaNV to set
     */
    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    /**
     * @return the TenNV
     */
    public String getTenKH() {
        return TenKH;
    }

    /**
     * @param TenNV the TenNV to set
     */
    public void setTenKH(String TenNV) {
        this.TenKH = TenNV;
    }

    /**
     * @return the CMND
     */
    public String getCMND() {
        return CMND;
    }

    /**
     * @param CMND the CMND to set
     */
    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    /**
     * @return the SDT
     */
    public String getSDT() {
        return SDT;
    }

    /**
     * @param SDT the SDT to set
     */
    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
   
    
    
}
