/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class vexe {
    private int Mave;
    private Timestamp ThoiGianBatDau;
    private String Soghe;
    private int MaChuyen;
    private int MaKH;
    private int MaNV;
    private int MaXE;
    private Date Ngayin;
    
    
    public vexe() {
    }

    public vexe(int Mave, Timestamp ThoiGianBatDau, String Soghe, int MaChuyen, int MaKH, int MaNV, int MaXE, Date Ngayin) {
        this.Mave = Mave;
        this.ThoiGianBatDau = ThoiGianBatDau;
        this.Soghe = Soghe;
        this.MaChuyen = MaChuyen;
        this.MaKH = MaKH;
        this.MaNV = MaNV;
        this.MaXE = MaXE;
        this.Ngayin = Ngayin;
    }

    
    
    
    /**
     * @return the Mave
     */
    public int getMave() {
        return Mave;
    }

    /**
     * @param Mave the Mave to set
     */
    public void setMave(int Mave) {
        this.Mave = Mave;
    }

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

    /**
     * @return the ThoiGianBatDau
     */
    public Timestamp getThoiGianBatDau() {
        return ThoiGianBatDau;
    }

    /**
     * @param ThoiGianBatDau the ThoiGianBatDau to set
     */
    public void setThoiGianBatDau(Timestamp ThoiGianBatDau) {
        this.ThoiGianBatDau = ThoiGianBatDau;
    }

    /**
     * @return the MaKH
     */
    public int getMaKH() {
        return MaKH;
    }

    /**
     * @param MaKH the MaKH to set
     */
    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
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
     * @return the Ngayin
     */
    public Date getNgayin() {
        return Ngayin;
    }

    /**
     * @param Ngayin the Ngayin to set
     */
    public void setNgayin(Date Ngayin) {
        this.Ngayin = Ngayin;
    }
    
    /**
     * @return the Soghe
     */
    public String getSoghe() {
        return Soghe;
    }

    /**
     * @param Soghe the Soghe to set
     */
    public void setSoghe(String Soghe) {
        this.Soghe = Soghe;
    }

}
