package com.example.aplikasidatamahasiswa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
	public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
		holder.nama_mhs_txt.setText(String.valueOf(nama_mhs.get(position)));
		holder.nim_mhs_txt.setText(String.valueOf(nim_mhs.get(position)));
		holder.gender_mhs_txt.setText(String.valueOf(gender_mhs.get(position)));
		holder.cardViewMhs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intentToDisplay = new Intent(view.getContext(), DisplayDataActivity.class);
				intentToDisplay.putExtra("namaMhs", String.valueOf(nama_mhs.get(position)));
				intentToDisplay.putExtra("nimMhs", String.valueOf(nim_mhs.get(position)));
				intentToDisplay.putExtra("alamatMhs", String.valueOf(alamat_mhs.get(position)));
				intentToDisplay.putExtra("kelaminMhs", String.valueOf(gender_mhs.get(position)));
				intentToDisplay.putExtra("uktMhs", String.valueOf(ukt_mhs.get(position)));
				intentToDisplay.putExtra("bahasaMhs", String.valueOf(bahasa_mhs.get(position)));
				view.getContext().startActivity(intentToDisplay);
			}
		});
	}

	@Override
	public int getItemCount() {
		return id_mhs.size();
	}

	public class MyViewHolder extends RecyclerView.ViewHolder {
		TextView id_mhs_txt, nama_mhs_txt, nim_mhs_txt, gender_mhs_txt;
		CardView cardViewMhs;
		public MyViewHolder(@NonNull View itemView) {
			super(itemView);
			nama_mhs_txt = itemView.findViewById(R.id.namaMahasiswaCard);
			nim_mhs_txt = itemView.findViewById(R.id.nimMahasiswaCard);
			gender_mhs_txt = itemView.findViewById(R.id.genderMahasiswaCard);
			cardViewMhs = itemView.findViewById(R.id.cardMhs);
		}
	}

}
