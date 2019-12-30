package com.example.project2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {
    private Context context;
    private DbHelper mydb;
    private EditText name, pwd1,pwd2;
    private Button login, signup;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signup,container,false);
        mydb=new DbHelper(context);
        name=v.findViewById(R.id.nameEditS);
        pwd1=v.findViewById(R.id.passwordEditS);
        pwd2=v.findViewById(R.id.passwordEdit2S);
        signup=v.findViewById(R.id.signupButtonS);
        //final String username=name.getText().toString();
       // final String password1=pwd1.getText().toString();
        //final String password2=pwd2.getText().toString();
        //Toast.makeText(getActivity(), password1, Toast.LENGTH_SHORT).show();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "User is not exist!", Toast.LENGTH_SHORT).show();
                String username=name.getText().toString();
                String password1=pwd1.getText().toString();
                String password2=pwd2.getText().toString();
                if(mydb.is_exist_user(username))
                {
                    Toast.makeText(getActivity(), "User is exist!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if(password1.equals(password2)){
                        //System.out.println(password1 );
                        Toast.makeText(getActivity(), "Added!"+password1+password2, Toast.LENGTH_SHORT).show();
                        mydb.InsertData_User(username,password1);
                        System.out.println(password1 + password2);
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "The two password are not equals!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


            }
        });


        return v;
    }
}
