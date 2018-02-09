package com.gmail.kidusmt.adegareporter.ui.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.gmail.kidusmt.adegareporter.R;

public class DetailAccidentActivity extends AppCompatActivity {

    ImageView iv_accident_img;
    TextView accident_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_accident);

        iv_accident_img = findViewById(R.id.accident_img);

        accident_detail = findViewById(R.id.accident_detail);

    }
}
