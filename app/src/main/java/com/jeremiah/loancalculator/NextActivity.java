package com.jeremiah.loancalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NextActivity extends AppCompatActivity {

    TextView tv_capital, tv_rate, tv_term, tv_monthly, tv_totalInterest, tv_type;
    AppCompatButton shareBtn;
    String capital, rate, term, monthly, totalInterest, type, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_next);

        tv_capital = findViewById(R.id.tv_loan_amount);
        tv_rate = findViewById(R.id.tv_rate);
        tv_term = findViewById(R.id.tv_duration);
        tv_monthly = findViewById(R.id.tv_monthly);
        tv_totalInterest = findViewById(R.id.tv_total_interest);
        tv_type = findViewById(R.id.tv_loan_type);
        shareBtn = findViewById(R.id.share_btn);

        Intent prevScreen = getIntent();
        capital = prevScreen.getStringExtra("amount");
        tv_capital.setText("Loan Amount: P"+capital);
        rate = prevScreen.getStringExtra("rate");
        tv_rate.setText("Interest Rate: "+rate);
        term = prevScreen.getStringExtra("term");
        tv_term.setText("Duration: "+term+" Months");
        monthly = prevScreen.getStringExtra("monthly");
        tv_monthly.setText("Monthly Installment: P"+monthly);
        totalInterest = prevScreen.getStringExtra("total");
        tv_totalInterest.setText("Total Interest: P"+totalInterest);
        type = prevScreen.getStringExtra("type");
        name = prevScreen.getStringExtra("name");
        switch (type){
            case "short":
                tv_type.setText("Short Term Loan");
                break;
            case "mid":
                tv_type.setText("Mid Term Loan");
                break;
            case "long":
                tv_type.setText("Long Term Loan");
                break;
            default:
                tv_type.setText("Bayport Loan");
                break;
        }
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String body = "Application quote: \n"+tv_capital.getText().toString()+"\n"
                        +tv_rate.getText().toString()+"\n"+tv_monthly.getText().toString()+"\n"
                        +tv_term.getText().toString()+"\n"+tv_totalInterest.getText().toString()+"\n";
                Intent emailIntent = new Intent(getApplicationContext(), EmailActivity.class);
                emailIntent.putExtra("name", name);
                emailIntent.putExtra("body", body);
                /*emailIntent.putExtra(Intent.EXTRA_EMAIL, "customercare@bayport.co.bw");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Quote from Client");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Application quote: \n"+tv_capital.getText().toString()+"\n"
                +tv_rate.getText().toString()+"\n"+tv_monthly.getText().toString()+"\n"
                +tv_term.getText().toString()+"\n"+tv_totalInterest.getText().toString()+"\n");
                emailIntent.setType("message/rfc822");*/
                startActivity(emailIntent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}