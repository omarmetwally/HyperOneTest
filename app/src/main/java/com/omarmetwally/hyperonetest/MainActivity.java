package com.omarmetwally.hyperonetest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.omarmetwally.hyperonetest.ui.ViewAll.ViewAllFragment;
import com.omarmetwally.hyperonetest.ui.Downloaded.DownloadedFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    int ItemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.navigation);
        navView.setOnNavigationItemSelectedListener(this);
        loadFragment(new ViewAllFragment());

        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this).
                setMessage("Tips: Incrementation of the progress bar depends on the ID of the element \nEx. if ID=12 then the progress bar will increment by 12% ").
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        b.create();
        b.show();
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();


            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;


        if (ItemID != menuItem.getItemId())
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    fragment = new ViewAllFragment();
                    ItemID = menuItem.getItemId();
                    break;

                case R.id.navigation_dashboard:
                    fragment = new DownloadedFragment();
                    ItemID = menuItem.getItemId();
                    break;
            }

        return loadFragment(fragment);
    }


}