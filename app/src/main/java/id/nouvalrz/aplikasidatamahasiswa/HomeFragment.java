package id.nouvalrz.aplikasidatamahasiswa;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.nouvalrz.aplikasidatamahasiswa.R;

import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment {

	private RecyclerView recyclerView;
	private MyDatabaseHelper myDB;
	ArrayList<String> id_mhs, nama_mhs, nim_mhs, alamat_mhs, gender_mhs, ukt_mhs, bahasa_mhs;
	RecyclerAdapter recyclerAdapter;
	LinearLayoutManager layoutManager;

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

		Collections.reverse(id_mhs);
		Collections.reverse(nama_mhs);
		Collections.reverse(nim_mhs);
		Collections.reverse(alamat_mhs);
		Collections.reverse(gender_mhs);
		Collections.reverse(ukt_mhs);
		Collections.reverse(bahasa_mhs);

		recyclerAdapter = new RecyclerAdapter(getContext(), id_mhs, nama_mhs, nim_mhs, alamat_mhs, gender_mhs, ukt_mhs, bahasa_mhs);
		layoutManager = new LinearLayoutManager(view.getContext());
		recyclerView = view.findViewById(R.id.recycler_mhs);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(recyclerAdapter);

		return view;
	}

	void storeDataInArrays(){
		Cursor cursor = myDB.readAllData();
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

	@Override
	public void onResume() {
		super.onResume();
		recyclerAdapter.notifyDataSetChanged();
	}
}


