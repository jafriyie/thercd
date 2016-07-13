package com.bsystemslimited.remotechequedeposit;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ConfirmChequeActivity extends AppCompatActivity {

    public static String chequeAmount, accNumber, imgFrontPath, imgBackPath;
    ImageView imgConfFront, imgConfBack;
    EditText etConfCheqAmount, etAccBank;
    Button btnConfAccept;

    @Override
    public void onBackPressed() {
        ChequeActivity.fileName = imgFrontPath;
        ChequeActivity.bFileName = imgBackPath;
        ChequeActivity.cheqAmount = chequeAmount;
        Intent myIntent3 = new Intent(ConfirmChequeActivity.this, ChequeActivity.class);
        startActivity(myIntent3);
        imgFrontPath = "";
        imgBackPath = "";
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_cheque);

        imgConfFront = (ImageView) findViewById(R.id.imgConfFront);
        imgConfBack = (ImageView) findViewById(R.id.imgConfBack);

        etAccBank = (EditText) findViewById(R.id.etAccNumber);
        etConfCheqAmount = (EditText) findViewById(R.id.etAmount);
        btnConfAccept = (Button) findViewById(R.id.btnConfAccept);

        etAccBank.setText(accNumber);
        etConfCheqAmount.setText(chequeAmount);


        String frontPath = "sdcard/cheque_app/" + imgFrontPath;
        imgConfFront.setImageDrawable(Drawable.createFromPath(frontPath));

        String backPath = "sdcard/cheque_app/" + imgBackPath;
        imgConfBack.setImageDrawable(Drawable.createFromPath(backPath));

        btnConfAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgFrontPath = "";
                imgBackPath = "";
                ChequeActivity.fileName = "";
                ChequeActivity.bFileName = "";
                Intent myIntent = new Intent(ConfirmChequeActivity.this, DashboardActivity.class);
                startActivity(myIntent);
                finish();
            }
        });



    }
}
