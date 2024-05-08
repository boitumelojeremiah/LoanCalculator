package com.jeremiah.loancalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EmailActivity extends AppCompatActivity {

    EditText youremailaddressEDT, recipientemailaddressEDT, subjectEDT, messageEDT;
    String youremailaddress, recipientemailadress, subject, message, name;
    AppCompatButton sendBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_email);

        youremailaddressEDT = findViewById(R.id.youremailaddressEDT);
        recipientemailaddressEDT = findViewById(R.id.recipientemailadressEDT);
        subjectEDT =  findViewById(R.id.subjectEDT);
        messageEDT = findViewById(R.id.messageEDT);
        sendBTN = findViewById(R.id.sendBTN);

        Intent intent = getIntent();
        message = intent.getStringExtra("body");
        name = intent.getStringExtra("name");

        recipientemailaddressEDT.setText("customercare@bayport.co.bw");
        recipientemailaddressEDT.setEnabled(false);
        subjectEDT.setText("Client Quote Request: "+name);
        subjectEDT.setEnabled(false);
        messageEDT.setText(message);
        messageEDT.setEnabled(false);


        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //strings get the text input in the editTexts
                youremailaddress = youremailaddressEDT.getText().toString();
                recipientemailadress = recipientemailaddressEDT.getText().toString();
                subject = subjectEDT.getText().toString();
                message = messageEDT.getText().toString();

                //Below intent calls upon the SEND action
                Intent emailsent = new Intent(Intent.ACTION_SEND);

                //the intent calls upon the email client but first it forwards the strings retrieved
                // from the editTexts using the intent.EXTRA function
                emailsent.putExtra(Intent.EXTRA_EMAIL,new String[]{recipientemailadress});
                emailsent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailsent.putExtra(Intent.EXTRA_TEXT, message);
                emailsent.setType("message/rfc822");
                //below the user is asked to choose an email client be it GMAIL, Outlook, Yahoo
                startActivity(Intent.createChooser(emailsent,"choose an email client: this"));



            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}