package clc65.nguyentandat.listviewversionrecyclerview;

import android.widget.TextView;

public class ListView {
    String tvSubject;

    public String getTvSubject() {
        return tvSubject;
    }

    public void setTvSubject(String tvSubject) {
        this.tvSubject = tvSubject;
    }

    public ListView(String tvSubject) {
        this.tvSubject = tvSubject;
    }
}
