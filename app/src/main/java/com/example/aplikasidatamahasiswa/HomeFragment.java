package com.example.aplikasidatamahasiswa;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

	private RecyclerView recyclerView;
	private MyDatabaseHelper myDB;
	ArrayList<String> id_mhs, nama_mhs, nim_mhs, alamat_mhs, gender_mhs, ukt_mhs, bahasa_mhs;
	RecyclerAdapter recyclerAdapter;
	RecyclerView.LayoutManager layoutManager;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_home, container, false);

		myDB = new MyDatabaseHelper(getContext());
		id_mhs = new ArrayList<>();
		nama_mhs = new ArrayList<>();
		nim_mhs = new ArrayList<>();
		alamat_mhs = new ArrayList<>();
		gender_mhs = new ArrayList<>();
		ukt_mhs = new ArrayList<>();
		bahasa_mhs = new ArrayList<>();

		storeDataInArrays();

		recyclerView = view.findViewById(R.id.recycler_mhs);
		recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
		recyclerView.setAdapter(new RecyclerAdapter(getContext(), id_mhs, nama_mhs, nim_mhs, alamat_mhs, gender_mhs, ukt_mhs, bahasa_mhs));

		return view;
	}

	void storeDataInArrays(){
		Cursor cursor = myDB.readAllData();
		Log.d("alasan", String.valueOf(cursor.getCount()));
		if(cursor.getCount()==0){
			Toast.makeText(getContext(), "Tidak ada data!", Toast.LENGTH_SHORT).show();
		}else {
			while (cursor.moveToNext()) {
				id_mhs.add(cursor.getString(0));
				nama_mhs.add(cursor.getString(1));
				nim_mhs.add(cursor.getString(2));
				alamat_mhs.add(cursor.getString(3));
				gender_mhs.add(cursor.getString(4));
				ukt_mhs.add(cursor.getString(5));
				bahasa_mhs.add(cursor.getString(6));
			}
		}
	}
}


