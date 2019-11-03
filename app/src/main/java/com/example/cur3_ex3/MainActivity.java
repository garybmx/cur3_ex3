package com.example.cur3_ex3;

import com.example.cur3_ex3.Presenter.FilePresenter;
import com.example.cur3_ex3.Views.MainView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView {
    private FilePresenter presenter;
    private TextView infoText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       infoText = findViewById(R.id.info_text);

        if (savedInstanceState == null) {
            presenter = new FilePresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
    }

    public void showConverted(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                infoText.setText("Converted Completed!");
            }
        });

    }

    public void showConverting(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                infoText.setText("Converting....");
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

}
