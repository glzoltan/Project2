package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private Context context;
    private DbHelper mydb;
    private EditText name, pwd;
    private Button login, signup;
    public static final String SHARED_PREFS="sharedPrefs";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login,container,false);
        mydb=new DbHelper(context);
        name=v.findViewById(R.id.nameEdit);
        pwd=v.findViewById(R.id.passwordEdit);
        login=v.findViewById(R.id.loginButton);
        signup=v.findViewById(R.id.signupButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "User is not exist!", Toast.LENGTH_SHORT).show();
                String username=name.getText().toString();
                String password=pwd.getText().toString();
                if(!mydb.login_check(username,password))
                {
                    Toast.makeText(getActivity(), "User name or password is incorrect!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("username",username);
                    editor.commit();
                    Intent intent = new Intent(getActivity().getApplicationContext(), Main2Activity.class);
                    startActivity(intent);
                }


            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction frag_trans = getFragmentManager().beginTransaction();
                frag_trans.replace(R.id.fragment_container,new SignupFragment());
                frag_trans.commit();
            }
        });


        return v;
    }


}
