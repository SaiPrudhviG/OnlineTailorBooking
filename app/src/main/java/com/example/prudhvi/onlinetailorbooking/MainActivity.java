package com.example.prudhvi.onlinetailorbooking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInAccountCreator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsCreator;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.ByteArrayOutputStream;
import java.net.Authenticator;

public class MainActivity extends AppCompatActivity
{
    private static final int RC_SIGN_IN =2;
    private FirebaseAuth mAuth;
    private SignInButton signInButton;
    GoogleApiClient mGoogleSignInClient;
    String name,email_id;
    //String  image;

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()!=null){

                    Intent intent=new Intent(MainActivity.this,Main_Framelayout.class);
                    /*intent.putExtra("name",name);
                    intent.putExtra("email_id", email_id);*/
                    //Bitmap bmp = BitmapFactory.decodeResource(getResources(),image);
                    //ByteArrayOutputStream stream = new ByteArrayOutputStream();
                   // bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    //byte[] byteArray = stream.toByteArray();
                    //intent.putExtra("Image",byteArray);
                    //intent.putExtra("image",image);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton=findViewById(R.id.signinbtn);
        mAuth=FirebaseAuth.getInstance();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);

        mGoogleSignInClient=new GoogleApiClient.Builder(MainActivity.this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        Toast.makeText(MainActivity.this,"Something Went Wrong!!!",Toast.LENGTH_LONG).show();

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();


        //calling signIn method to signIN with google
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }
    public void signIn()
    {
        Intent n=Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
        startActivityForResult(n,RC_SIGN_IN);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess()){

                GoogleSignInAccount account=result.getSignInAccount();
                //calling method
                firebaseAuthWithGoogle(account);
                 name=account.getDisplayName();
                 email_id=account.getEmail();
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("email", email_id);
// set MyFragment Arguments
                User_profile myObj = new User_profile();
                myObj.setArguments(bundle);
             //   Glide.with(this).load(account.getPhotoUrl()).into()
                 //image=account.getPhotoUrl().toString();

            }
            else {
                Toast.makeText(MainActivity.this,"Something Error occurred!!!",Toast.LENGTH_LONG).show();
            }

        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct)
    {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");

                            Snackbar.make(findViewById(R.id.mainactivity), "Authentication Successful.", Snackbar.LENGTH_SHORT).show();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());

                            Snackbar.make(findViewById(R.id.mainactivity), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();

                        }

                    }

                });
    }
}
