package com.example.cardiacreader;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    private List<Records>recordsList;

    public MyAdapter(List<Records> measurementList) {
        this.recordsList = measurementList;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemDeleteClick(int position);
        void onItemEditClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Records records = recordsList.get(position);

        // Bind measurement data to the ViewHolder's views
        holder.tvSystolic.setText(String.valueOf(records.getSystolic()));
        holder.tvDiastolic.setText(String.valueOf(records.getDiastolic()));
        holder.tvHeartRate.setText(String.valueOf(records.getHeartRate()));
        holder.tvDate.setText(records.getDate());
        holder.tvTime.setText(records.getTime());
        holder.tvComment.setText(records.getComment());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemDeleteClick(position);
                }
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemEditClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSystolic, tvDiastolic, tvHeartRate, tvDate, tvTime, tvComment;
        Button btnDelete,btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSystolic = itemView.findViewById(R.id.textView8);
            tvDiastolic = itemView.findViewById(R.id.textView10);
            tvHeartRate = itemView.findViewById(R.id.textView9);
            tvDate = itemView.findViewById(R.id.textView3);
            tvTime = itemView.findViewById(R.id.textView4);
            tvComment = itemView.findViewById(R.id.textView11);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}


