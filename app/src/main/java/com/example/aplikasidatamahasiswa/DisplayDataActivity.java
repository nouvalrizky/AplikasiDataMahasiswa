package com.example.aplikasidatamahasiswa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayDataActivity extends AppCompatActivity {

		String namaMhs, nimMhs, alamatMhs, kelaminMhs, uktMhs, bahasaMhs, idMhs;
		TextView NamaMahasiswa, NimMahasiswa, AlamatMahasiswa, JenisKelaminMahasiswa, UKTMahasiswa, BahasaMahasiswa;
		Button btnEditMhs, btnHapusMhs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_data);

//		namaMhs = getIntent().getStringExtra("namaMhs");
//		nimMhs = getIntent().getStringExtra("nimMhs");
//		alamatMhs = getIntent().getStringExtra("alamatMhs");
//		kelaminMhs = getIntent().getStringExtra("kelaminMhs");
//		uktMhs = getIntent().getStringExtra("uktMhs");
//		bahasaMhs = getIntent().getStringExtra("bahasaMhs");

		idMhs = getIntent().getStringExtra("idMhs");

		MyDatabaseHelper db = new MyDatabaseHelper(this);
		Cursor cursor = db.readSpesificData(idMhs);

		while (cursor.moveToNext()) {
			namaMhs = cursor.getString(1);
			nimMhs = cursor.getString(2);
			alamatMhs = cursor.getString(3);
			kelaminMhs = cursor.getString(4);
			uktMhs = cursor.getString(5);
			bahasaMhs = cursor.getString(6);
		}

		NamaMahasiswa = findViewById(R.id.NamaMahasiswa);
		NimMahasiswa = findViewById(R.id.NimMahasiswa);
		AlamatMahasiswa = findViewById(R.id.AlamatMahasiswa);
		JenisKelaminMahasiswa = findViewById(R.id.JenisKelaminMahasiswa);
		UKTMahasiswa = findViewById(R.id.UKTMahasiswa);
		BahasaMahasiswa = findViewById(R.id.BahasaMahasiswa);
		btnEditMhs = findViewById(R.id.tombolUpdateMhs);
		btnHapusMhs = findViewById(R.id.tombolDeleteMhs);

		NamaMahasiswa.setText(namaMhs);
		NimMahasiswa.setText(nimMhs);
		AlamatMahasiswa.setText(alamatMhs);
		JenisKelaminMahasiswa.setText(kelaminMhs);
		UKTMahasiswa.setText(uktMhs);
		BahasaMahasiswa.setText(bahasaMhs);

		btnEditMhs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intentToUpdate = new Intent(DisplayDataActivity.this, UpdateActivity.class);
				intentToUpdate.putExtra("namaMhs", String.valueOf(namaMhs));
				intentToUpdate.putExtra("nimMhs", String.valueOf(nimMhs));
				intentToUpdate.putExtra("alamatMhs", String.valueOf(alamatMhs));
				intentToUpdate.putExtra("kelaminMhs", String.valueOf(kelaminMhs));
				intentToUpdate.putExtra("uktMhs", String.valueOf(uktMhs));
				intentToUpdate.putExtra("bahasaMhs", String.valueOf(bahasaMhs));
				intentToUpdate.putExtra("idMhs", String.valueOf(idMhs));
				startActivityForResult(intentToUpdate, 1);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				recreate();
			}
		}
	}

		@Override
		protected void onStart () {
			Toast.makeText(this, "Activity : On Start! || Activity di mulai", Toast.LENGTH_SHORT).show();
			super.onStart();
		}

		@Override
		protected void onResume () {
			Toast.makeText(this, "Activity : On Resume! || Anda melanjutkan aplikasi", Toast.LENGTH_SHORT).show();
			super.onResume();
		}

		@Override
		protected void onPause () {
			Toast.makeText(this, "Activity : On Pause! || Anda keluar sementara", Toast.LENGTH_SHORT).show();
			super.onPause();
		}

		@Override
		protected void onStop () {
			Toast.makeText(this, "Activity : On Stop! || Activity di berhentikan", Toast.LENGTH_SHORT).show();
			super.onStop();
		}


		@Override
		public void onBackPressed () {
			Intent intentToForm = new Intent(DisplayDataActivity.this, HomeActivity.class);
			startActivity(intentToForm);
		}
	}