package com.bsystemslimited.remotechequedeposit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class TransactionsActivity extends AppCompatActivity {

    View view;
    ListView theList;
    TransactionCardsList_Adapter myAdapter;
    ArrayList<TransactionCardObject> myObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        //
        myObjects = new ArrayList<TransactionCardObject>();
        //
        myObjects.add(new TransactionCardObject(1, "CH01294", "25585424", "11 Dec 2014", "400.00", "Pending"));
        myObjects.add(new TransactionCardObject(2, "CH02245", "24555878", "5 Feb 2014", "300.00", "Confirmed"));
        myObjects.add(new TransactionCardObject(3, "CH01296", "25585424", "11 Dec 2014", "400.00", "Pending"));
        myObjects.add(new TransactionCardObject(4, "CH02247", "24555878", "5 Feb 2014", "300.00", "Confirmed"));


        myAdapter = new TransactionCardsList_Adapter(this, myObjects);

        theList = (ListView) findViewById(R.id.transactionCardsListView);
        registerForContextMenu(theList);
        //
        theList.setAdapter(myAdapter);

        theList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        );


    }
}



