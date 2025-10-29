package thigk1.NguyenTanDat.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CongTrinhAdapter extends RecyclerView.Adapter<CongTrinhAdapter.CongTrinhViewHolder> {

    private Context context;
    private List<CongTrinhModel> congTrinhList;
    private OnItemClickListener listener;

    // Interface để xử lý click
    public interface OnItemClickListener {
        void onItemClick(CongTrinhModel congTrinh);
    }

    public CongTrinhAdapter(Context context, List<CongTrinhModel> congTrinhList, OnItemClickListener listener) {
        this.context = context;
        this.congTrinhList = congTrinhList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CongTrinhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cong_trinh, parent, false);
        return new CongTrinhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CongTrinhViewHolder holder, int position) {
        CongTrinhModel congTrinh = congTrinhList.get(position);

        holder.tvTenCongTrinh.setText(congTrinh.getTenCongTrinh());
        holder.tvThoiGianDang.setText(congTrinh.getThoiGianDang());
        holder.imgAnhDaiDien.setImageResource(congTrinh.getAnhDaiDien()); // Giả sử dùng ảnh từ drawable

        // Bắt sự kiện click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(congTrinh);
            }
        });
    }

    @Override
    public int getItemCount() {
        return congTrinhList.size();
    }

    // ViewHolder
    public static class CongTrinhViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAnhDaiDien;
        TextView tvTenCongTrinh;
        TextView tvThoiGianDang;

        public CongTrinhViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnhDaiDien = itemView.findViewById(R.id.imgAnhDaiDien);
            tvTenCongTrinh = itemView.findViewById(R.id.tvTenCongTrinh);
            tvThoiGianDang = itemView.findViewById(R.id.tvThoiGianDang);
        }
    }
}