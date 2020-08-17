package com.example.mvvmpracticeandroidxjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


public class MainActivity extends AppCompatActivity {
    private TextView txvNum;
    private EditText edtNum;
    private Button btnStart, btnStop;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //***UI
        txvNum = findViewById(R.id.txvNum);
        edtNum = findViewById(R.id.edtNum);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        //***
        //***ViewModel
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.init();
        setOnClickListener();
        //***
        setViewModelObserver();

    }


    private void setViewModelObserver() {

        viewModel.seconds().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                txvNum.setText(integer.toString());
            }
        });
        viewModel.finished().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(getBaseContext(), "Finished", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setOnClickListener() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNum.getText().toString() == "" || edtNum.length() < 4) {
                    Toast.makeText(getBaseContext(), "invalid number", Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.timerValue.setValue(Long.valueOf(edtNum.getText().toString()));
                    viewModel.startTimer();
                }
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txvNum.setText("0");
                viewModel.stopTimer();
            }
        });
    }
}
