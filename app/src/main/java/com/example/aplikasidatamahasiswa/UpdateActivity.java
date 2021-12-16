package com.example.aplikasidatamahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

	String namaMhs, nimMhs, alamatMhs, kelaminMhs, uktMhs, bahasaMhs, idMhs;
	EditText NamaMahasiswa, NimMahasiswa, AlamatMahasiswa;
	RadioGroup editKelamin;
	RadioButton editLaki, editPerempuan, kelaminTerpilih;
	SeekBar editUKT;
	CheckBox editBahasaC, editBahasaJava, editBahasaJavascript, editBahasaPHP;
	Button simpanPerubahan;

	Dialog dialogConfirm;

	TextView nama, nim, alamat, kelamin, ukt, bahasa;

	ImageView backButtonToolbar, homeButtonToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);

		idMhs = getIntent().getStringExtra("idMhs");

		dialogConfirm = new Dialog(this);

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

		NamaMahasiswa = findViewById(R.id.editNama);
		NimMahasiswa = findViewById(R.id.editNIM);
		AlamatMahasiswa = findViewById(R.id.editAlamat);
		editKelamin = findViewById(R.id.grupEditKelamin);
		editLaki = findViewById(R.id.editLaki);
		editPerempuan = findViewById(R.id.editPerempuan);
		editUKT = findViewById(R.id.editUKT);
		editBahasaC = findViewById(R.id.editBahasaC);
		editBahasaJava = findViewById(R.id.editBahasaJava);
		editBahasaPHP = findViewById(R.id.editBahasaPHP);
		editBahasaJavascript = findViewById(R.id.editBahasaJavascript);
		simpanPerubahan = findViewById(R.id.buttonSimpanPerubahan);

		backButtonToolbar = findViewById(R.id.backButtonToolbar);
		homeButtonToolbar = findViewById(R.id.homeButtonToolbar);


		simpanPerubahan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				validasiInput();
				openDialogConfirm();
			}
		});

		setDataToForm();

		backButtonToolbar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
				finish();
			}
		});

		homeButtonToolbar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent backToHome;
				backToHome = new Intent(UpdateActivity.this, HomeActivity.class);
				startActivity(backToHome);
				finish();
			}
		});
	}

	private void setDataToForm(){
		NamaMahasiswa.setText(namaMhs);
		NimMahasiswa.setText(nimMhs);
		AlamatMahasiswa.setText(alamatMhs);

		if(kelaminMhs.equals("Laki-laki")){
			editLaki.setChecked(true);
		}else{
			editPerempuan.setChecked(true);
		}

		editUKT.setProgress(Integer.valueOf(uktMhs)-1);

		if(bahasaMhs.contains("Python")){
			editBahasaJava.setChecked(true);
		}
		if(bahasaMhs.contains("PHP")){
			editBahasaPHP.setChecked(true);
		}
		if(bahasaMhs.contains("JavaScript")){
			editBahasaJavascript.setChecked(true);
		}
		if(bahasaMhs.contains("C++")){
			editBahasaC.setChecked(true);
		}

	}

	private void validasiInput(){
		namaMhs = NamaMahasiswa.getText().toString();
		nimMhs = NimMahasiswa.getText().toString();
		alamatMhs = AlamatMahasiswa.getText().toString();

		kelaminMhs = "";
		int idKelamin = editKelamin.getCheckedRadioButtonId();
		kelaminTerpilih = (RadioButton) findViewById(idKelamin);
		kelaminMhs = kelaminTerpilih.getText().toString();

		Integer uktTerpilih = editUKT.getProgress() + 1;
		uktMhs = uktTerpilih.toString();

		bahasaMhs = "";
		if (editBahasaC.isChecked()){
			bahasaMhs = bahasaMhs + editBahasaC.getText().toString() + ", ";
		}
		if (editBahasaJava.isChecked()) {
			bahasaMhs =  bahasaMhs + editBahasaJava.getText().toString() + ", ";
		}
		if (editBahasaJavascript.isChecked()) {
			bahasaMhs = bahasaMhs + editBahasaJavascript.getText().toString() + ", ";
		}
		if (editBahasaPHP.isChecked()){
			bahasaMhs = bahasaMhs + editBahasaPHP.getText().toString() + ", ";
		}
		if(!bahasaMhs.isEmpty()){
			bahasaMhs = bahasaMhs.substring(0, bahasaMhs.length() - 2);
		}

		if(namaMhs.isEmpty()){
			NamaMahasiswa.requestFocus();
			NamaMahasiswa.setError("Silahkan isi nama dahulu!");
		}else if(nimMhs.isEmpty()) {
			NimMahasiswa.requestFocus();
			NimMahasiswa.setError("Silahkan isi NIM dahulu!");
		}else if(nimMhs.length()!=10){
			NimMahasiswa.requestFocus();
			NimMahasiswa.setError("NIM harus 10 digit angka!");
		}else if(alamatMhs.isEmpty()){
			AlamatMahasiswa.requestFocus();
			AlamatMahasiswa.setError("Silahkan isi Alamat dahulu!");
		}else if(kelaminMhs.isEmpty()){
			editKelamin.requestFocus();
		}else if(uktMhs.isEmpty()) {
			editUKT.requestFocus();
		}else if(bahasaMhs.isEmpty()){
			Toast.makeText(getApplicationContext(), "Pilih minimal 1 bahasa pemrograman!", Toast.LENGTH_SHORT).show();
		}
	}

	private void openDialogConfirm(){

		dialogConfirm.setContentView(R.layout.custom_dialog);
		dialogConfirm.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

		Button buttonSimpanDialog, buttonKembaliDialog;

		buttonKembaliDialog = dialogConfirm.findViewById(R.id.buttonKembaliDialog);
		buttonSimpanDialog = dialogConfirm.findViewById(R.id.buttonSimpanDialog);

		nama = (TextView) dialogConfirm.findViewById(R.id.NamaDialog);
		nim = (TextView) dialogConfirm.findViewById(R.id.NIMDialog);
		alamat = (TextView) dialogConfirm.findViewById(R.id.AlamatDialog);
		kelamin = (TextView) dialogConfirm.findViewById(R.id.KelaminDialog);
		ukt = (TextView) dialogConfirm.findViewById(R.id.UKTDialog);
		bahasa = (TextView) dialogConfirm.findViewById(R.id.BahasaDialog);

		nama.setText(namaMhs);
		nim.setText(nimMhs);
		alamat.setText(alamatMhs);
		kelamin.setText(kelaminMhs);
		ukt.setText(uktMhs);
		bahasa.setText(bahasaMhs);


		buttonSimpanDialog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				updateDatabaseData();
				Toast.makeText(getApplicationContext(), "Data telah disimpan!", Toast.LENGTH_SHORT).show();
			}
		});

		buttonKembaliDialog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialogConfirm.dismiss();
			}
		});

		dialogConfirm.show();
	}

	private void updateDatabaseData(){
		MyDatabaseHelper db = new MyDatabaseHelper(getApplicationContext());
		db.updateData(idMhs, namaMhs, nimMhs, alamatMhs, kelaminMhs, uktMhs, bahasaMhs);

		Intent returnIntent = new Intent();
		setResult(RESULT_OK,returnIntent);

		onBackPressed();
		finish();
	}
}