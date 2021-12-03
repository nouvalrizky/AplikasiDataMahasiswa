package com.example.aplikasidatamahasiswa;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

	EditText inputNama, inputAlamat, inputNIM;
	RadioGroup inputKelamin;
	RadioButton kelaminTerpilih, inputLaki;
	SeekBar inputUKT;
	CheckBox inputBahasaC, inputBahasaJava, inputBahasaJavascript, inputBahasaPHP;
	Button buttonSimpan;
	String namaMhs, alamatMhs, nimMhs, kelaminMhs, uktMhs, bahasaMhs;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		Toolbar toolbar = findViewById(R.id.toolbar);
//		setSupportActionBar(toolbar);
//
//		//      Hapus title bawaan toolbar
//		getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//		drawerLayout = findViewById(R.id.drawer_layout);
//
//		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//		drawerLayout.addDrawerListener(actionBarDrawerToggle);
//		actionBarDrawerToggle.syncState();

		inputNama = findViewById(R.id.inputNama);
		inputAlamat = findViewById(R.id.inputAlamat);
		inputNIM = findViewById(R.id.inputNIM);
		inputUKT = findViewById(R.id.inputUKT);
		inputKelamin = findViewById(R.id.grupKelamin);
		inputLaki = findViewById(R.id.inputLaki);
		inputBahasaC = findViewById(R.id.inputBahasaC);
		inputBahasaJava = findViewById(R.id.inputBahasaJava);
		inputBahasaJavascript = findViewById(R.id.inputBahasaJavascript);
		inputBahasaPHP = findViewById(R.id.inputBahasaPHP);
		buttonSimpan = findViewById(R.id.buttonSimpan);

		buttonSimpan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				validasiInput();
			}
		});
	}

	private void validasiInput(){
		namaMhs = inputNama.getText().toString();
		nimMhs = inputNIM.getText().toString();
		alamatMhs = inputAlamat.getText().toString();

		kelaminMhs = "";
		int idKelamin = inputKelamin.getCheckedRadioButtonId();
		kelaminTerpilih = (RadioButton) findViewById(idKelamin);
		kelaminMhs = kelaminTerpilih.getText().toString();

		Integer uktTerpilih = inputUKT.getProgress() + 1;
		uktMhs = uktTerpilih.toString();

		bahasaMhs = "";
		if (inputBahasaC.isChecked()){
			bahasaMhs = bahasaMhs + inputBahasaC.getText().toString() + ", ";
		}
		if (inputBahasaJava.isChecked()) {
			bahasaMhs =  bahasaMhs + inputBahasaJava.getText().toString() + ", ";
		}
		if (inputBahasaJavascript.isChecked()) {
			bahasaMhs = bahasaMhs + inputBahasaJavascript.getText().toString() + ", ";
		}
		if (inputBahasaPHP.isChecked()){
			bahasaMhs = bahasaMhs + inputBahasaPHP.getText().toString() + ", ";
		}
		if(!bahasaMhs.isEmpty()){
			bahasaMhs = bahasaMhs.substring(0, bahasaMhs.length() - 2);
		}

		if(namaMhs.isEmpty()){
			inputNama.requestFocus();
			inputNama.setError("Silahkan isi nama dahulu!");
		}else if(nimMhs.isEmpty()) {
			inputNIM.requestFocus();
			inputNIM.setError("Silahkan isi NIM dahulu!");
		}else if(nimMhs.length()!=10){
			inputNIM.requestFocus();
			inputNIM.setError("NIM harus 10 digit angka!");
		}else if(alamatMhs.isEmpty()){
			inputAlamat.requestFocus();
			inputAlamat.setError("Silahkan isi Alamat dahulu!");
		}else if(kelaminMhs.isEmpty()){
			inputKelamin.requestFocus();
		}else if(uktMhs.isEmpty()) {
			inputUKT.requestFocus();
		}else if(bahasaMhs.isEmpty()){
			Toast.makeText(getApplicationContext(), "Pilih minimal 1 bahasa pemrograman!", Toast.LENGTH_SHORT).show();
		}else{
			simpanDataMahasiswa();
		}
	}

	private void simpanDataMahasiswa(){

		StringBuilder rekapData = new StringBuilder();
		rekapData.append("Nama : " + namaMhs + "\n\n");
		rekapData.append("NIM : " + nimMhs + "\n\n");
		rekapData.append("Alamat : " + alamatMhs + "\n\n");
		rekapData.append("Kelamin : " + kelaminMhs + "\n\n");
		rekapData.append("Tingkat UKT : " + uktMhs + "\n\n");
		rekapData.append("Bahasa Pemrograman : " + bahasaMhs + "\n\n");

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			this);

		// set title dialog
		alertDialogBuilder.setTitle("Apakah data sudah benar?");

		// set pesan dari dialog
		alertDialogBuilder
			.setMessage(rekapData.toString())
			.setCancelable(false)
			.setPositiveButton("Simpan",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					resetForm();
					saveForm();
					Toast.makeText(getApplicationContext(), "Data telah disimpan!", Toast.LENGTH_SHORT).show();
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

	private void resetForm(){
		inputNama.getText().clear();
		inputNIM.getText().clear();
		inputAlamat.getText().clear();
		inputLaki.setChecked(true);
		inputUKT.setProgress(0);
		inputBahasaC.setChecked(false);
		inputBahasaJava.setChecked(false);
		inputBahasaJavascript.setChecked(false);
		inputBahasaPHP.setChecked(false);
	}

	public void saveForm(){
		Intent intentToDisplay = new Intent(this, DisplayDataActivity.class);
		intentToDisplay.putExtra("namaMhs", namaMhs);
		intentToDisplay.putExtra("nimMhs", nimMhs);
		intentToDisplay.putExtra("alamatMhs", alamatMhs);
		intentToDisplay.putExtra("kelaminMhs", kelaminMhs);
		intentToDisplay.putExtra("uktMhs", uktMhs);
		intentToDisplay.putExtra("bahasaMhs", bahasaMhs);

		MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
		myDatabaseHelper.tambahMahasiswa(namaMhs, nimMhs, alamatMhs, kelaminMhs, uktMhs, bahasaMhs);

		startActivity(intentToDisplay);
		finish();
	}

	@Override
	public void onBackPressed() {
			super.onBackPressed();
			Intent intentToHome = new Intent(FormActivity.this, HomeActivity.class);
			startActivity(intentToHome);
	}
}