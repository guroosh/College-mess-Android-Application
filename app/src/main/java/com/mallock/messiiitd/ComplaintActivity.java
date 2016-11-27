package com.mallock.messiiitd;

import android.content.Intent;
import android.net.Uri;
import android.opengl.EGLDisplay;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ComplaintActivity extends AppCompatActivity {
    EditText content;
    EditText subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_complaint);
        content = (EditText) findViewById(R.id.content);
        subject = (EditText) findViewById(R.id.subject);
        Button button = (Button) findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail(subject.getText().toString(), content.getText().toString());
            }
        });
    }

    private void sendMail(String subject, String body) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setType("plain/text");
        sendIntent.setData(Uri.parse("chaudhary14032@iiitd.ac.in"));
        sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "chaudhary14032@iiitd.ac.in" });
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, body);
        startActivityForResult(sendIntent,CONTEXT_INCLUDE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        content.setText("");
        subject.setText("");
        super.onActivityResult(requestCode, resultCode, data);
    }
}
