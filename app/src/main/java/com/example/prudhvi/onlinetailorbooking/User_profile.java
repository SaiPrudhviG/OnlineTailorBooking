package com.example.prudhvi.onlinetailorbooking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class User_profile extends Fragment {

TextView name,email;
    public User_profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
View view=inflater.inflate(R.layout.fragment_user_profile, container, false);
name=view.findViewById(R.id.Username);
email=view.findViewById(R.id.Email);
Bundle bundle=getArguments();
if(getArguments()!=null) {
    String a_name = (String) bundle.get("name");
    String a_email = (String) bundle.get("email_id");
    name.setText(a_name);
    email.setText(a_email);
}
        // Inflate the layout for this fragmen

        return view;
    }

}
