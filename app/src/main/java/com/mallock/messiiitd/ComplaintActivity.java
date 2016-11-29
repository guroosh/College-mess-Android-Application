package com.mallock.messiiitd;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.opengl.EGLDisplay;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

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

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/html");
        final PackageManager pm = this.getPackageManager();
        final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
        String className = null;
        for (final ResolveInfo info : matches) {
            if (info.activityInfo.packageName.equals("com.google.android.gm")) {
                className = info.activityInfo.name;

                if(className != null && !className.isEmpty()){
                    break;
                }
            }
        }
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "chaudhary14032@iiitd.ac.in" });
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        emailIntent.setClassName("com.google.android.gm", className);
        startActivity(emailIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        content.setText("");
        subject.setText("");
        super.onActivityResult(requestCode, resultCode, data);
    }
}
