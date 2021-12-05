package com.example.aplikasidatamahasiswa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
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
				intentToUpdate.putExtra("idMhs", String.valueOf(idMhs));
				startActivityForResult(intentToUpdate, 1);
			}
		});

		btnHapusMhs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					DisplayDataActivity.this);

				// set title dialog
				alertDialogBuilder.setTitle("Apakah anda yakin menghapus data?");

				// set pesan dari dialog
				alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("Hapus",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							MyDatabaseHelper db = new MyDatabaseHelper(DisplayDataActivity.this);
							db.deleteSpesificData(idMhs);
							Intent intentToHome = new Intent(DisplayDataActivity.this, HomeActivity.class);
							intentToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intentToHome);
							finish();
						}
					})
					.setNegativeButton("Kembali",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});

				// membuat alert dialog dari builder
				AlertDialog alertDialog = alertDialogBuilder.create();

				// menampilkan alert dialog
				alertDialog.show();
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