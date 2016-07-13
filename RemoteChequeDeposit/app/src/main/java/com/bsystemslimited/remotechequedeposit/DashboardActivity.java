package com.bsystemslimited.remotechequedeposit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    LinearLayout linChequeDep, linTransactions, linAccount, linLogOut;

    @Override
    public void onBackPressed() {
        Intent myIntent5 = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(myIntent5);
        Toast.makeText(DashboardActivity.this, "You Have Signed Out", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        linChequeDep = (LinearLayout) findViewById(R.id.linDepCheq);
        linTransactions = (LinearLayout) findViewById(R.id.linTrans);
        linAccount = (LinearLayout) findViewById(R.id.linAccSet);
        linLogOut = (LinearLayout) findViewById(R.id.linLogOut);


        linChequeDep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DashboardActivity.this, ChequeActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        linTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2 = new Intent(DashboardActivity.this, TransactionsActivity.class);
                startActivity(myIntent2);
                finish();
            }
        });

        linLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent3 = new Intent(DashboardActivity.this, MenuActivity.class);
                startActivity(myIntent3);
                finish();
            }
        });

    }
}
