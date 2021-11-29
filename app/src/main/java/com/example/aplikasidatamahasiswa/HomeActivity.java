package com.example.aplikasidatamahasiswa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

	BottomNavigationView bottomNavigationView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		bottomNavigationView = findViewById(R.id.BottomNavView);
		bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
	}

	private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			Fragment selectedFragment = null;

			switch (item.getItemId()){
				case R.id.homeSection:
					selectedFragment = new HomeFragment();
					break;
				case R.id.aboutSection:
					selectedFragment = new AboutFragment();
					break;
			}
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

			return true;
		}
	};
}