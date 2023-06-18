package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNum1, etNum2, etOperation;
    private TextView tvText1, tvText2, tvText3, tvResult;
    private Button btn;
    private Toast toastError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = findViewById(R.id.edit1);
        etOperation = findViewById(R.id.edit2);
        etNum2 = findViewById(R.id.edit3);
        tvText1 = findViewById(R.id.txt1);
        tvText2 = findViewById(R.id.txt2);
        tvText3 = findViewById(R.id.txt3);
        tvResult = findViewById(R.id.txt4);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        float Num1, Num2, Result = 0;
        String operation = "";
        boolean correctOperation = true;
        try {
            Num1 = Float.parseFloat(etNum1.getText().toString());
            Num2 = Float.parseFloat(etNum2.getText().toString());
            operation = etOperation.getText().toString();
            switch (operation) {
                case "+":
                    Result = Num1 + Num2;
                    break;
                case "-":
                    Result = Num1 - Num2;
                    break;
                case "*":
                    Result = Num1 * Num2;
                    break;
                case "/":
                    if(Num2 == 0) throw new ArithmeticException();
                    Result = Num1 / Num2;
                    break;
                default:
                    correctOperation = false;
                    break;
            }
            
        } catch (ArithmeticException e) {
            int duration = Toast.LENGTH_SHORT;
            if (toastError != null) {
                toastError.cancel();
            }
            toastError = Toast.makeText(this, R.string.divide_zero, duration);
            toastError.show();
            return;
        }
        catch (NullPointerException e){
            int duration = Toast.LENGTH_SHORT;
            if (toastError != null) {
                toastError.cancel();
            }
            toastError = Toast.makeText(this, R.string.null_data, duration);
            toastError.show();
            return;
        }
        catch (NumberFormatException e){
            int duration = Toast.LENGTH_SHORT;
            if (toastError != null) {
                toastError.cancel();
            }
            toastError = Toast.makeText(this, R.string.wrong_format, duration);
            toastError.show();
            return;
        }

        if(correctOperation) {
            tvResult.setText(Num1 + " " + operation + " " + Num2 + "=" + Result);
        } else {
            int duration = Toast.LENGTH_SHORT;
            if (toastError != null) {
                toastError.cancel();
            }
            toastError = Toast.makeText(this, R.string.wrong_operation, duration);
            toastError.show();
        }
    }
}