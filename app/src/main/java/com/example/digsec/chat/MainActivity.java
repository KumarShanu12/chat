package com.example.digsec.chat;

import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

//    TextView textView;
    EditText editText;
    Button button;
    TextView textView;

    public Vibrator vibrator;



    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootref = db.getReference();
    DatabaseReference gref = rootref.child("game");
//    DatabaseReference uref = gref.child("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);

        editText=(EditText) findViewById(R.id.editText);
        button=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gref.setValue(editText.getText().toString());
                editText.setText(null);
//                editText.setText(null);
//                gref.removeValue();


            }
        });
    }
            @Override
            protected void onStart() {
                super.onStart();

                gref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String text = dataSnapshot.getValue().toString();
//                         editText.setText(text);
                        textView.setText(text);
//                        vibrator.vibrate(40);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        Log.i("tag", "missing");

                    }
                });
            }

    }