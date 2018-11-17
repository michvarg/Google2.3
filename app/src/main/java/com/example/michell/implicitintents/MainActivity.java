package com.example.michell.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText openLocation;
    private EditText share_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText websiteEdittext = findViewById(R.id.website_edittext);
        Button websiteButton = findViewById(R.id.open_location_button);

        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userWebsiteInput = websiteEdittext.getText().toString();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(userWebsiteInput));
                startActivity(intent);

            }
        });


    }

    public void openWebsite(View view){
        mWebsiteEditText = (EditText) findViewById(R.id.website_edittext);
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    public void openLocation(View view){
   openLocation = (EditText) findViewById(R.id.open_location_edittext);
   String loc = openLocation.getText().toString();
   Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
   Intent intenttwo = new Intent(Intent.ACTION_VIEW, addressUri);
   if (intenttwo.resolveActivity(getPackageManager()) != null){
       startActivity(intenttwo);
   }
    }

    public void shareText(View view){
        share_text = (EditText) findViewById(R.id.share_text);
        String txt =share_text.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.shareText)
                .setText(txt)
                .startChooser();

    }
}
