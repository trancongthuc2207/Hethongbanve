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
public class xe {
    private int MaXE;
    private String TenXe;
    private String Bienso;
    private int Trangthai;          // 1 xa da chay, 0 xe chua chay
    private int MaChuyen;
    /**
     * @return the MaChuyen
     */
    public int getMaChuyen() {
        return MaChuyen;
    }

    /**
     * @param MaChuyen the MaChuyen to set
     */
    public void setMaChuyen(int MaChuyen) {
        this.MaChuyen = MaChuyen;
    }

    public xe() {
    }

    public xe(int MaXE, String TenXe, String Bienso, int Trangthai, int MaChuyen) {
        this.MaXE = MaXE;
        this.TenXe = TenXe;
        this.Bienso = Bienso;
        this.Trangthai = Trangthai;
        this.MaChuyen = MaChuyen;
    }
    
    /**
     * @return the MaXE
     */
    public int getMaXE() {
        return MaXE;
    }

    /**
     * @param MaXE the MaXE to set
     */
    public void setMaXE(int MaXE) {
        this.MaXE = MaXE;
    }

    /**
     * @return the TenXe
     */
    public String getTenXe() {
        return TenXe;
    }

    /**
     * @param TenXe the TenXe to set
     */
    public void setTenXe(String TenXe) {
        this.TenXe = TenXe;
    }

    /**
     * @return the Bienso
     */
    public String getBienso() {
        return Bienso;
    }

    /**
     * @param Bienso the Bienso to set
     */
    public void setBienso(String Bienso) {
        this.Bienso = Bienso;
    }

    /**
     * @return the Trangthai
     */
    public int getTrangthai() {
        return Trangthai;
    }

    /**
     * @param Trangthai the Trangthai to set
     */
    public void setTrangthai(int Trangthai) {
        this.Trangthai = Trangthai;
    }
    
    
    
}
