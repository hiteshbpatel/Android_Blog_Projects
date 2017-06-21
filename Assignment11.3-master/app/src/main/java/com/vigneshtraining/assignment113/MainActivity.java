package com.vigneshtraining.assignment113;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,FileOperationListener {

    private EditText txt;
    private Button saveBtn,readBtn,deleteBtn;
    private FileOperation fileOperation;
    private Toast msgToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt= (EditText) findViewById(R.id.txt);
        saveBtn=(Button) findViewById(R.id.saveBtn);
        readBtn=(Button) findViewById(R.id.readBtn);
        deleteBtn=(Button) findViewById(R.id.deleteBtn);

        saveBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);


        txt.setMovementMethod(new ScrollingMovementMethod());

        EnableRuntimePermission();



    }

    private void EnableRuntimePermission() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
        if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 1);


        }

        String filesDirPath = "myFolder";

        String strSDCardPath = System.getenv("EXTERNAL_SDCARD_STORAGE");



        if(strSDCardPath != null) {

            if (strSDCardPath.contains(":")) {
                strSDCardPath = strSDCardPath.substring(0,strSDCardPath.indexOf(":"));
            }

            filesDirPath=strSDCardPath +"/" + "myFolder";


        }

        //String filesDirPath = Environment.getExternalStorageDirectory().toString() +"/" + "myFolder";
       // Toast.makeText(this,"filesDirPath:"+filesDirPath,Toast.LENGTH_LONG).show();

        fileOperation=new FileOperation(MainActivity.this,new File(getExternalFilesDir(filesDirPath),"myFile.txt"));
        fileOperation.checkSDCardStatus();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.readBtn:
                readAction();

                readBtn.setVisibility(View.GONE);
                saveBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.saveBtn:
                saveAction();
                break;
            case R.id.deleteBtn:
                deleteAction();
                break;

        }


    }

    private void saveAction(){
        fileOperation.save(txt.getText().toString());
        txt.setText("");
        saveBtn.setVisibility(View.GONE);
        readBtn.setVisibility(View.VISIBLE);
    }

    private void readAction(){
        fileOperation.read();
    }

    private void deleteAction(){
        fileOperation.delete();
        txt.setText("");
    }

    @Override
    public void onSDcardChecked(Boolean isReady, String status) {

        if(isReady){
            fileOperation.checkMemory();
        }else{


            onFileOpearionMsg(isReady+":::"+getText(R.string.memoryStatus)+status);
        }
    }

    @Override
    public void onMemoryChecked(long total, long free) {
        if(free<=1024){
            onFileOpearionMsg("Low memory ("+free+") Please increase the memory");
        }else{
            readAction();
        }


    }

    @Override
    public void onFileOpearionMsg(String msg) {

        if (msgToast!=null){
            msgToast.cancel();
        }

        msgToast=Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG);
        msgToast.show();

    }

    @Override
    public void onFileRead(String text) {
        txt.setText(text);
    }
}
