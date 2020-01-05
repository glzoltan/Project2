package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private Context context;
    Button selectImage,saveImage,savePassword;
    ImageView imageView;
    DbHelper db;
    EditText psd;
    public static final String SHARED_PREFS="sharedPrefs";
    private int REQUEST_CODE=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        selectImage = v.findViewById(R.id.selectButton);
        saveImage=v.findViewById(R.id.saveButton);
        savePassword=v.findViewById(R.id.button2);
        imageView = v.findViewById(R.id.imageView2);
        psd=v.findViewById(R.id.newpassEdit);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("username","");
        db=new DbHelper(context);
        Bitmap picture=db.getImage(user);
        if(picture==null){
            imageView.setImageResource(R.drawable.ic_person_black_24dp);
        }
        else
        {
            imageView.setImageBitmap(picture);
        }

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),REQUEST_CODE);

            }
        });
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=new DbHelper(context);
                imageView.setDrawingCacheEnabled(true);
                imageView.buildDrawingCache();
                Bitmap bitmap = imageView.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,80,baos);
                byte[] data = baos.toByteArray();
                SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                String user = sharedPreferences.getString("username","");
                db.addImage(user,data);
                Toast.makeText(getActivity(), "New Image Is Added!", Toast.LENGTH_SHORT).show();
            }
        });
        savePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=new DbHelper(context);
                String password=psd.getText().toString();
                SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                String user = sharedPreferences.getString("username","");
                db.changePassword(user,password);
                psd.setText("");
                Toast.makeText(getActivity(), "New Password Is Added!", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode ==REQUEST_CODE && resultCode ==  getActivity().RESULT_OK && data!=null && data.getData()!=null){
            Uri uri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
               // Bitmap bitmap1 = MediaStore.Images.Media.getContentUri
                imageView.setImageBitmap(bitmap);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public void addtodb(View view){

    }

}
