package thigk1.NguyenTanDat.myapplication;


import java.io.Serializable;

public class CongTrinhModel implements Serializable{
    private String tenCongTrinh;
    private String thoiGianDang;
    private int anhDaiDien; // Dùng int để tham chiếu đến ảnh trong drawable

    // Constructor
    public CongTrinhModel(String tenCongTrinh, String thoiGianDang, int anhDaiDien) {
        this.tenCongTrinh = tenCongTrinh;
        this.thoiGianDang = thoiGianDang;
        this.anhDaiDien = anhDaiDien;
    }

    // Getters
    public String getTenCongTrinh() {
        return tenCongTrinh;
    }

    public String getThoiGianDang() {
        return thoiGianDang;
    }

    public int getAnhDaiDien() {
        return anhDaiDien;
    }
}
