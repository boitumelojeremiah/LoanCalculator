package com.jeremiah.loancalculator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    CardView short_card, mid_card, long_card;
    ImageView short_image, mid_image, long_image;
    TextView tv_long, tv_mid, tv_short, tv_card_selected;
    View shortView, midView, longView;
    AppCompatButton calc_button, confirm_button;
    float capital, principal, monthly_installment, rate;
    int duration;
    String term = "";
    EditText edt_fullname, edt_loan_amount, edt_prin_amount, edt_monthly, edt_interest, edt_duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tv_card_selected = findViewById(R.id.tv_card_selected);
        tv_long = findViewById(R.id.tv_long);
        tv_mid = findViewById(R.id.tv_mid);
        tv_short = findViewById(R.id.tv_short);
        short_card = findViewById(R.id.card_short);
        mid_card = findViewById(R.id.card_mid);
        long_card = findViewById(R.id.card_long);
        short_image = findViewById(R.id.image_short);
        mid_image = findViewById(R.id.image_mid);
        long_image = findViewById(R.id.image_long);
        shortView = findViewById(R.id.view_short);
        midView = findViewById(R.id.view_mid);
        longView = findViewById(R.id.view_long);
        edt_fullname = findViewById(R.id.edt_fullname);
        edt_loan_amount = findViewById(R.id.edt_loan_amount);
        edt_prin_amount = findViewById(R.id.edt_prin_amount);
        edt_monthly = findViewById(R.id.edt_monthly_installment);
        edt_interest = findViewById(R.id.edt_interest);
        edt_duration = findViewById(R.id.edt_duration);
        calc_button = findViewById(R.id.btn_calculate);
        confirm_button = findViewById(R.id.btn_next);

        short_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                term = "short";
                tv_card_selected.setText(term);
                updateCard();
                edt_loan_amount.setText("");
                edt_interest.setText("");
                edt_duration.setText("");
                edt_monthly.setText("");
                edt_prin_amount.setText("");
                confirm_button.setVisibility(View.GONE);
            }
        });
        mid_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                term = "mid";
                tv_card_selected.setText(term);
                updateCard();
                edt_loan_amount.setText("");
                edt_interest.setText("");
                edt_duration.setText("");
                edt_monthly.setText("");
                edt_prin_amount.setText("");
                confirm_button.setVisibility(View.GONE);
            }
        });
        long_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                term = "long";
                tv_card_selected.setText(term);
                updateCard();
                edt_loan_amount.setText("");
                edt_interest.setText("");
                edt_duration.setText("");
                edt_monthly.setText("");
                edt_prin_amount.setText("");
                confirm_button.setVisibility(View.GONE);
            }
        });
        calc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_loan_amount.getText().toString().isEmpty() || edt_interest.getText().toString().isEmpty() || edt_duration.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill in the fields", Toast.LENGTH_LONG).show();
                }else {
                    calculateLoan();
                    confirm_button.setVisibility(View.VISIBLE);
                }
            }
        });
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new Intent(getApplicationContext(), NextActivity.class);
                nextScreen.putExtra("amount", edt_loan_amount.getText().toString());
                nextScreen.putExtra("rate", edt_interest.getText().toString());
                nextScreen.putExtra("term", edt_duration.getText().toString());
                nextScreen.putExtra("monthly", edt_monthly.getText().toString());
                nextScreen.putExtra("total", edt_prin_amount.getText().toString());
                nextScreen.putExtra("type",tv_card_selected.getText().toString());
                nextScreen.putExtra("name", edt_fullname.getText().toString());
                startActivity(nextScreen);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void calculateLoan() {
        String loanAmt = edt_loan_amount.getText().toString();
        String inter = edt_interest.getText().toString();
        String months = edt_duration.getText().toString();
        capital = Float.parseFloat(loanAmt);
        rate = Float.parseFloat(inter);
        duration = Integer.parseInt(months);
        switch (term){
            case "short":
                float int_rate = (rate/12/100);
                float term = Float.parseFloat(months);
                float dvdnt = (float) Math.pow(1+int_rate, term);
                float final_dvdnt = (float) (capital*int_rate*dvdnt);
                float divider = (float) (dvdnt-1);
                float emi = (float) (final_dvdnt/divider);
                float ta = (float) (emi*term);
                float totalInterest = (float) (ta - capital);
                edt_prin_amount.setText(String.valueOf(totalInterest));
                edt_monthly.setText(String.valueOf(emi));
                break;
            case "mid":
                int_rate = (rate/12/100);
                term = Float.parseFloat(months);
                dvdnt = (float) Math.pow(1+int_rate, term);
                final_dvdnt = (float) (capital*int_rate*dvdnt);
                divider = (float) (dvdnt-1);
                 emi = (float) (final_dvdnt/divider);
                ta = (float) (emi*term);
                totalInterest = (float) (ta - capital);
                edt_prin_amount.setText(String.valueOf(totalInterest));
                edt_monthly.setText(String.valueOf(emi));
                break;
            case "long":
                int_rate = (rate/12/100);
                term = Float.parseFloat(months);
                dvdnt = (float) Math.pow(1+int_rate, term);
                final_dvdnt = (float) (capital*int_rate*dvdnt);
                divider = (float) (dvdnt-1);
                emi = (float) (final_dvdnt/divider);
                ta = (float) (emi*term);
                totalInterest = (float) (ta - capital);
                edt_prin_amount.setText(String.valueOf(totalInterest));
                edt_monthly.setText(String.valueOf(emi));
                break;
            default:
                int_rate = (rate/12/100);
                term = Float.parseFloat(months);
                dvdnt = (float) Math.pow(1+int_rate, term);
                final_dvdnt = (float) (capital*int_rate*dvdnt);
                divider = (float) (dvdnt-1);
                emi = (float) (final_dvdnt/divider);
                ta = (float) (emi*term);
                totalInterest = (float) (ta - capital);
                edt_prin_amount.setText(String.valueOf(totalInterest));
                edt_monthly.setText(String.valueOf(emi));
                break;
        }
    }

    private void updateCard() {
        edt_monthly.setEnabled(false);
        edt_prin_amount.setEnabled(false);
        switch(term){
            case "short":
                short_card.setCardBackgroundColor(Color.parseColor("#F44336"));
                short_image.setBackgroundResource(R.drawable.baseline_watch_later);
                tv_short.setTextColor(Color.parseColor("#FFFFFF"));
                shortView.setBackgroundColor(Color.parseColor("#FFFFFF"));

                mid_card.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                mid_image.setBackgroundResource(R.drawable.twotone_watch_later_mid);
                tv_mid.setTextColor(Color.parseColor("#757575"));
                midView.setBackgroundColor(Color.parseColor("#512DA8"));

                long_card.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                long_image.setBackgroundResource(R.drawable.twotone_watch_later_long);
                tv_long.setTextColor(Color.parseColor("#757575"));
                longView.setBackgroundColor(Color.parseColor("#388E3C"));
                break;
            case "mid":
                mid_card.setCardBackgroundColor(Color.parseColor("#512DA8"));
                mid_image.setBackgroundResource(R.drawable.baseline_watch_later);
                tv_mid.setTextColor(Color.parseColor("#FFFFFF"));
                midView.setBackgroundColor(Color.parseColor("#FFFFFF"));

                long_card.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                long_image.setBackgroundResource(R.drawable.twotone_watch_later_long);
                tv_long.setTextColor(Color.parseColor("#757575"));
                longView.setBackgroundColor(Color.parseColor("#388E3C"));

                short_card.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                short_image.setBackgroundResource(R.drawable.twotone_watch_later_short);
                tv_short.setTextColor(Color.parseColor("#757575"));
                shortView.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case "long":
                long_card.setCardBackgroundColor(Color.parseColor("#388E3C"));
                long_image.setBackgroundResource(R.drawable.baseline_watch_later);
                tv_long.setTextColor(Color.parseColor("#FFFFFF"));
                longView.setBackgroundColor(Color.parseColor("#FFFFFF"));

                short_card.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                short_image.setBackgroundResource(R.drawable.twotone_watch_later_short);
                tv_short.setTextColor(Color.parseColor("#757575"));
                shortView.setBackgroundColor(Color.parseColor("#F44336"));

                mid_card.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                mid_image.setBackgroundResource(R.drawable.twotone_watch_later_mid);
                tv_mid.setTextColor(Color.parseColor("#757575"));
                midView.setBackgroundColor(Color.parseColor("#512DA8"));
                break;
        }
    }
}