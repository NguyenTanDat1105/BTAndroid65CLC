package clc65.nguyentandat.quizmath;

public class CauHoiCongHaiSo {
    int id;
    String loiHoi;
    int daDung;
    int sai1;
    int sai2;
    int sai3;

    public CauHoiCongHaiSo(int id, String loiHoi, int daDung, int sai1, int sai2, int sai3) {
        this.id = id;
        this.loiHoi = loiHoi;
        this.daDung = daDung;
        this.sai1 = sai1;
        this.sai2 = sai2;
        this.sai3 = sai3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoiHoi() {
        return loiHoi;
    }

    public void setLoiHoi(String loiHoi) {
        this.loiHoi = loiHoi;
    }

    public int getDaDung() {
        return daDung;
    }

    public void setDaDung(int daDung) {
        this.daDung = daDung;
    }

    public int getSai1() {
        return sai1;
    }

    public void setSai1(int sai1) {
        this.sai1 = sai1;
    }

    public int getSai2() {
        return sai2;
    }

    public void setSai2(int sai2) {
        this.sai2 = sai2;
    }

    public int getSai3() {
        return sai3;
    }

    public void setSai3(int sai3) {
        this.sai3 = sai3;
    }
}
