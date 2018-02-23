package com.gmail.kidusmt.adegareporter.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.kidusmt.adegareporter.R;
import com.gmail.kidusmt.adegareporter.base.view.BaseActivity;
import com.gmail.kidusmt.adegareporter.ui.home.HomePresenter;

public class DetailAccidentActivity extends BaseActivity implements DetailAccidentContract.View{

    ImageView iv_accident_img;
    TextView accident_detail;

    DetailAccidentContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_accident);

        presenter = new DetailAccidentPresenter(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("");

        iv_accident_img = findViewById(R.id.accident_img);

        accident_detail = findViewById(R.id.accident_detail);

    }

    @Override
    public void showDetailAccidentActivity() {

    }

    @Override
    public void closeDetailAccidentActivity() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                presenter.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
