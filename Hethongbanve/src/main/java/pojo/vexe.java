/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.sql.Timestamp;
/**
 *
 * @author Admin
 */
public class vexe {
    private int MaVE;
    private Timestamp Thoigianbatdau;
    private String Soghe;
    private int MaChuyen;
    private int MaKH;
    private int MaNV;
    private int MaXE;
    private Timestamp Ngayin;
    private int Trangthai;

    public vexe() {
    }

    public vexe(int MaVE, Timestamp Thoigianbatdau, String Soghe, int MaChuyen, int MaKH, int MaNV, int MaXE, Timestamp Ngayin, int Trangthai) {
        this.MaVE = MaVE;
        this.Thoigianbatdau = Thoigianbatdau;
        this.Soghe = Soghe;
        this.MaChuyen = MaChuyen;
        this.MaKH = MaKH;
        this.MaNV = MaNV;
        this.MaXE = MaXE;
        this.Ngayin = Ngayin;
        this.Trangthai = Trangthai;
    }
    
    
    
    /**
     * @return the MaVE
     */
    public int getMaVE() {
        return MaVE;
    }

    /**
     * @param MaVE the MaVE to set
     */
    public void setMaVE(int MaVE) {
        this.MaVE = MaVE;
    }

    /**
     * @return the Thoigianbatdau
     */
    public Timestamp getThoigianbatdau() {
        return Thoigianbatdau;
    }

    /**
     * @param Thoigianbatdau the Thoigianbatdau to set
     */
    public void setThoigianbatdau(Timestamp Thoigianbatdau) {
        this.Thoigianbatdau = Thoigianbatdau;
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
    public Timestamp getNgayin() {
        return Ngayin;
    }

    /**
     * @param Ngayin the Ngayin to set
     */
    public void setNgayin(Timestamp Ngayin) {
        this.Ngayin = Ngayin;
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
