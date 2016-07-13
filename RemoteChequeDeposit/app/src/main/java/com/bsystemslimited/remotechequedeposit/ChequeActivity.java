package com.bsystemslimited.remotechequedeposit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChequeActivity extends AppCompatActivity {

    Spinner spinBanks;
    LinearLayout linFront, linBack;
    TextView txtFront, txtBack;
    ImageView imgFront, imgBack;
    int pickedCam = 0;
    String fileDate, bFileDate;
    public static String fileName, bFileName, cheqAmount;
    public static EditText etAccNumberBank, etAmount, etConfAmount;
    Button bNext;

    @Override
    public void onBackPressed() {
        Intent myIntent3 = new Intent(ChequeActivity.this, DashboardActivity.class);
        startActivity(myIntent3);
        fileName = "";
        bFileName ="";
        ConfirmChequeActivity.imgFrontPath = "";
        ConfirmChequeActivity.imgBackPath = "";
        imgFront.setImageResource(android.R.color.transparent);
        imgBack.setImageResource(android.R.color.transparent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheque);

        linFront = (LinearLayout) findViewById(R.id.linFrontPic);
        linBack = (LinearLayout) findViewById(R.id.linBackPic);
        imgFront = (ImageView) findViewById(R.id.imgFront);
        imgBack = (ImageView) findViewById(R.id.imgBack);

        txtFront = (TextView) findViewById(R.id.txtFront);
        txtBack = (TextView) findViewById(R.id.txtBack);

        etAccNumberBank = (EditText) findViewById(R.id.etAccNumberBank);
        etAmount = (EditText) findViewById(R.id.etAmount);
        etConfAmount = (EditText) findViewById(R.id.etAmountConf);

        bNext = (Button) findViewById(R.id.btnCheqNext);

        if(!TextUtils.isEmpty(fileName)){
            String path = "sdcard/cheque_app/" + fileName;
            imgFront.setImageDrawable(Drawable.createFromPath(path));
            txtFront.setText("Front Picture");
        }

        if(!TextUtils.isEmpty(bFileName)){
            String path2 = "sdcard/cheque_app/" + bFileName;
            imgBack.setImageDrawable(Drawable.createFromPath(path2));
            txtBack.setText("Back Picture");
        }


        etAccNumberBank.setText("First Atlantic Bank - 123456789");


        /*spinBanks = (Spinner) findViewById(R.id.spinBanks);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.arrBanks, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinBanks.setAdapter(adapter);*/

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            linFront.setClickable(false);
            linBack.setClickable(false);
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }


        linFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickedCam = 1;
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, 100);
            }
        });


        linBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickedCam = 2;
                Intent camera_intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFileBack();
                camera_intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent2, 100);
            }
        });

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etAmount.getText().toString().equals(etConfAmount.getText().toString())){
                    cheqAmount = etAmount.getText().toString();
                    if(!TextUtils.isEmpty(bFileName) && !TextUtils.isEmpty(fileName)) {
                        ConfirmChequeActivity.imgFrontPath = fileName;
                        ConfirmChequeActivity.imgBackPath = bFileName;
                        ConfirmChequeActivity.accNumber = etAccNumberBank.getText().toString();
                        ConfirmChequeActivity.chequeAmount = cheqAmount;
                        Intent myIntent2 = new Intent(ChequeActivity.this, ConfirmChequeActivity.class);
                        startActivity(myIntent2);
                        imgFront.setImageResource(android.R.color.transparent);
                        imgBack.setImageResource(android.R.color.transparent);
                        finish();
                    }

                }
                else{
                    Toast.makeText(ChequeActivity.this, "The Amounts are different", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 0){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                linFront.setClickable(true);
                linBack.setClickable(true);
            }
        }
    }

    private File getFile(){

        File folder = new File("sdcard/cheque_app");

        if(!folder.exists()){
            folder.mkdir();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        fileDate = sdf.format(new Date());

        fileName = "front" + fileDate + ".jpg";

        File image_file = new File (folder, fileName);
        return image_file;
    }

    private File getFileBack(){
        File folder = new File("sdcard/cheque_app");

        if(!folder.exists()){
            folder.mkdir();
        }

        SimpleDateFormat bsdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        bFileDate = bsdf.format(new Date());

        bFileName = "back" + fileDate + ".jpg";

        File image_fileBack = new File (folder, bFileName);

        return image_fileBack;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(pickedCam == 1) {
                String path = "sdcard/cheque_app/" + fileName;
                imgFront.setImageDrawable(Drawable.createFromPath(path));
                txtFront.setText("Front Picture");
            }
            else if(pickedCam == 2){
                String path = "sdcard/cheque_app/" + bFileName;
                imgBack.setImageDrawable(Drawable.createFromPath(path));
                txtBack.setText("Back Picture");
            }
    }
}
