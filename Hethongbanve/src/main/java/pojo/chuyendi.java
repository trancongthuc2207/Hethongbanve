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
public class chuyendi {
    private int MaChuyen;
    private String TenChuyen;
    private double gia;
    private Timestamp ThoiGianBatDau;
    private Timestamp ThoiGianKetThuc;

    public chuyendi() {
    }

    public chuyendi(int MaChuyen, String TenChuyen, double gia, Timestamp ThoiGianBatDau, Timestamp ThoiGianKetThuc) {
        this.MaChuyen = MaChuyen;
        this.TenChuyen = TenChuyen;
        this.gia = gia;
        this.ThoiGianBatDau = ThoiGianBatDau;
        this.ThoiGianKetThuc = ThoiGianKetThuc;
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
     * @return the TenChuyen
     */
    public String getTenChuyen() {
        return TenChuyen;
    }

    /**
     * @param TenChuyen the TenChuyen to set
     */
    public void setTenChuyen(String TenChuyen) {
        this.TenChuyen = TenChuyen;
    }

    /**
     * @return the gia
     */
    public double getGia() {
        return gia;
    }

    /**
     * @param gia the gia to set
     */
    public void setGia(double gia) {
        this.gia = gia;
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
     * @return the ThoiGianKetThuc
     */
    public Timestamp getThoiGianKetThuc() {
        return ThoiGianKetThuc;
    }

    /**
     * @param ThoiGianKetThuc the ThoiGianKetThuc to set
     */
    public void setThoiGianKetThuc(Timestamp ThoiGianKetThuc) {
        this.ThoiGianKetThuc = ThoiGianKetThuc;
    }
    
    
    
}
