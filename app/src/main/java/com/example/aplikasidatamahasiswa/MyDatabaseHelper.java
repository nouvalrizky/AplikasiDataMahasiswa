package com.example.aplikasidatamahasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	private Context context;
	private static final String DATABASE_NAME = "Mahasiswa.db";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_NAME = "tb_mahasiswa";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_NAMA = "nama_mhs";
	private static final String COLUMN_NIM = "nim_mhs";
	private static final String COLUMN_ALAMAT = "alamat_mhs";
	private static final String COLUMN_GENDER = "gender_mhs";
	private static final String COLUMN_UKT = "ukt_mhs";
	private static final String COLUMN_BAHASA = "bahasa_mhs";



	public MyDatabaseHelper(@Nullable Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAMA + " TEXT, " + COLUMN_NIM + " TEXT, " + COLUMN_ALAMAT + " TEXT, " + COLUMN_GENDER + " TEXT, " + COLUMN_UKT + " TEXT, " + COLUMN_BAHASA + " TEXT);";
		sqLiteDatabase.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(sqLiteDatabase);
	}

	void tambahMahasiswa(String namaMahasiswa, String nimMahasiswa, String alamatMahasiswa, String genderMahasiswa, String uktMahasiswa, String bahasaMahasiswa){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues	cv = new ContentValues();

		cv.put(COLUMN_NAMA, namaMahasiswa);
		cv.put(COLUMN_NIM, nimMahasiswa);
		cv.put(COLUMN_ALAMAT, alamatMahasiswa);
		cv.put(COLUMN_GENDER, genderMahasiswa);
		cv.put(COLUMN_UKT, uktMahasiswa);
		cv.put(COLUMN_BAHASA, bahasaMahasiswa);

		long result = db.insert(TABLE_NAME, null, cv);
		if (result==-1){
			Toast.makeText(context, "Gagal menambah data!", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(context, "Berhasil menambah data!", Toast.LENGTH_SHORT).show();
		}
	}

	Cursor readAllData(){
		String query = "SELECT * FROM " + TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = null;
		if(db != null){
			cursor = db.rawQuery(query, null);
		}
		return cursor;
	}

	Cursor readSpesificData(String id_mhs_selected){
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + id_mhs_selected;
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = null;
		if(db != null){
			cursor = db.rawQuery(query, null);
		}
		return cursor;
	}

	Cursor readLastRowID(){
		String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1";
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = null;
		if(db != null){
			cursor = db.rawQuery(query, null);
		}
		return cursor;
	}

	void updateData(String row_id, String namaMhs, String nimMhs, String alamatMhs, String genderMhs, String uktMhs, String bahasaMhs){
		 SQLiteDatabase db = this.getWritableDatabase();
		 ContentValues cv = new ContentValues();
		 cv.put(COLUMN_NAMA, namaMhs);
		 cv.put(COLUMN_NIM, nimMhs);
		 cv.put(COLUMN_ALAMAT, alamatMhs);
		 cv.put(COLUMN_GENDER, genderMhs);
		 cv.put(COLUMN_UKT, uktMhs);
		 cv.put(COLUMN_BAHASA, bahasaMhs);

		 long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
		 if (result == -1){
		 		Toast.makeText(context, "Gagal memperbarui data!", Toast.LENGTH_SHORT).show();
		 }else {
			 Toast.makeText(context, "Berhasil memperbarui data!", Toast.LENGTH_SHORT).show();
		 }
	}
}
