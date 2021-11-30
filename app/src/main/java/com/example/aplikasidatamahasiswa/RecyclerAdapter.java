package com.example.aplikasidatamahasiswa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

	Context context;
	ArrayList id_mhs, nama_mhs, nim_mhs, alamat_mhs, gender_mhs, ukt_mhs, bahasa_mhs;

	RecyclerAdapter(Context context,
									ArrayList id_mhs,
									ArrayList nama_mhs,
									ArrayList nim_mhs,
									ArrayList alamat_mhs,
									ArrayList gender_mhs,
									ArrayList ukt_mhs,
									ArrayList bahasa_mhs){
		this.context = context;
		this.id_mhs = id_mhs;
		this.nama_mhs = nama_mhs;
		this.nim_mhs = nim_mhs;
		this.alamat_mhs = alamat_mhs;
		this.gender_mhs = gender_mhs;
		this.ukt_mhs = ukt_mhs;
		this.bahasa_mhs = bahasa_mhs;
	}

	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.card_item_mhs, parent, false);
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
		holder.nama_mhs_txt.setText(String.valueOf(nama_mhs.get(position)));
		holder.nim_mhs_txt.setText(String.valueOf(nim_mhs.get(position)));
		holder.gender_mhs_txt.setText(String.valueOf(gender_mhs.get(position)));
	}

	@Override
	public int getItemCount() {
		return id_mhs.size();
	}

	public class MyViewHolder extends RecyclerView.ViewHolder {
		TextView id_mhs_txt, nama_mhs_txt, nim_mhs_txt, gender_mhs_txt;
		public MyViewHolder(@NonNull View itemView) {
			super(itemView);
			nama_mhs_txt = itemView.findViewById(R.id.namaMahasiswaCard);
			nim_mhs_txt = itemView.findViewById(R.id.nimMahasiswaCard);
			gender_mhs_txt = itemView.findViewById(R.id.genderMahasiswaCard);
		}
	}
}
