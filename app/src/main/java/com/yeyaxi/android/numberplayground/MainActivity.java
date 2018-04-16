package com.yeyaxi.android.numberplayground;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.editText5)
    EditText editText5;
    @BindView(R.id.editText6)
    EditText editText6;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    private NumberViewModel model;
    private Snackbar errMsg;
    private Animation blinkAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupErrorHandling();
        setupViewModel();
    }

    @OnClick(R.id.textView)
    public void onClick(View view) {
        if (this.blinkAnim != null) {
            view.clearAnimation();
            this.blinkAnim = null;
        } else {
            startBlinkAnimation(view);
        }
    }

    @OnTextChanged(value = {R.id.editText1, R.id.editText2, R.id.editText3,
            R.id.editText4, R.id.editText5, R.id.editText6},
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onValueChanged(Editable editable) {
        View view = getCurrentFocus();
        if (view == null) {
            return;
        }
        try {
            this.errMsg.dismiss();
            this.model.updateValue(view.getId(), editable.toString());
        } catch (NumberFormatException nfe) {
            this.errMsg.show();
        }
    }

    private void setupViewModel() {
        this.model = ViewModelProviders.of(this).get(NumberViewModel.class);
        this.model.getSumValue().observe(this, this::onUpdateSum);
    }

    private void setupErrorHandling() {
        this.errMsg = Snackbar.make(this.coordinatorLayout, R.string.error_number_format, Snackbar.LENGTH_SHORT);
    }

    private void onUpdateSum(BigDecimal sumValue) {
        this.textView.setText(sumValue.toPlainString());
    }

    private void startBlinkAnimation(View view) {
        this.blinkAnim = AnimationUtils.loadAnimation(this, R.anim.blink);
        view.startAnimation(this.blinkAnim);
    }
}
