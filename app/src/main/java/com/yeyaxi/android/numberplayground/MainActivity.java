package com.yeyaxi.android.numberplayground;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private NumberViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.model = ViewModelProviders.of(this).get(NumberViewModel.class);
        this.model.getSumValue().observe(this, this::onUpdateSum);
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
            this.model.updateValue(view.getId(), editable.toString());
        } catch (NumberFormatException nfe) {
            Toast.makeText(this, R.string.error_number_format, Toast.LENGTH_LONG).show();
        }
    }

    private void onUpdateSum(Double sumValue) {
        this.textView.setText(String.valueOf(sumValue));
    }
}
