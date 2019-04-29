package com.example.prudhvi.onlinetailorbooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class Main_Framelayout extends AppCompatActivity {

    /*ImageView imageView_user;
    TextView Name,Email_id;*/
    Button logout;
    private FirebaseAuth mAuth;
    //String imageUrl=intent.getExtras().getString("image");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__framelayout);
        mAuth=FirebaseAuth.getInstance();
        /*Intent intent=getIntent();
        String email=intent.getExtras().getString("email_id");
        String name=intent.getExtras().getString("name");*/
        logout=findViewById(R.id.logout);
        Fragment newFragment4 = new Tailor_Options();
        FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
        ft4.replace(R.id.mainframe_frame,newFragment4);
        ft4.commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId())
            {
                case R.id.navigation_profile:
                    Fragment newFragment = new User_profile();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainframe_frame,newFragment);
                    ft.commit();
                    break;
                case R.id.navigation_home:
                    Fragment newFragment2 = new Tailor_Options();
                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.mainframe_frame,newFragment2);
                    ft2.commit();
                    break;
                case R.id.navigation_slots:
                    Fragment newFragment3 = new Tailor_Options();
                    FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                    ft3.replace(R.id.mainframe_frame,newFragment3);
                    ft3.commit();
                    break;
                    default:
                        Fragment newFragment4 = new Tailor_Options();
                        FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                        ft4.replace(R.id.mainframe_frame,newFragment4);
                        ft4.commit();
                        break;

            }
            return true;
        }
    };

    public void Male(View view)
    {
        Fragment newFragment = new male_clothes();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainframe_frame,newFragment);
        ft.commit();
    }
    public void Female(View view)
    {
        Fragment newFragment = new Female_Clothes();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainframe_frame,newFragment);
        ft.commit();
    }
    public void Stiching(View view)
    {
        Fragment newFragment = new Gendertype();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainframe_frame,newFragment);
        ft.commit();
    }
    public void Alteration(View view)
    {
        Fragment newFragment = new Gendertype();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainframe_frame,newFragment);
        ft.commit();
    }
    public void OnLogout(View view)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //Uncomment the below code to Set the message and title from the strings.xml file
        builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

        //Setting message manually and performing action on button click
        builder.setMessage("Do you want to Logout from the Application ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mAuth.signOut();
                        Intent intent=new Intent(Main_Framelayout.this,MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Online Tailor Booking Services");
        alert.show();

    }

}
