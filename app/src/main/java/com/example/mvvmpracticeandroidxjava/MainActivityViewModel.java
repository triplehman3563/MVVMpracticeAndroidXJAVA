package com.example.mvvmpracticeandroidxjava;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MainActivityViewModel extends ViewModel {
    private CountDownTimer timer;
    MutableLiveData<Long> timerValue= new MutableLiveData<Long>();;
    private MutableLiveData<Boolean> _finished = new MutableLiveData<Boolean>();;
    private MutableLiveData<Integer> _seconds= new MutableLiveData<Integer>();;
    void init(){
        timerValue.setValue((long) 0);
        _finished.setValue(true);
        _seconds.setValue(0);
    }

    LiveData<Boolean> finished() {
        return _finished;
    }

    LiveData<Integer> seconds() {
        return _seconds;
    }

    void startTimer() {
        timer = new CountDownTimer(timerValue.getValue(), 1000) {
            @Override
            public void onTick(long l) {
                long timeleft = l/1000;
                _seconds.setValue((int) timeleft);
            }

            @Override
            public void onFinish() {
                _finished.setValue(true);
            }
        }.start();
    }

    void stopTimer() {
        timer.cancel();
    }
}
