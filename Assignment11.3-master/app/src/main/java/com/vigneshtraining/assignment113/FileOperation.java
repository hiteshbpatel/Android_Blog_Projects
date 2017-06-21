package com.vigneshtraining.assignment113;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by vimadhavan on 4/22/2017.
 */

public class FileOperation {

    private File myFile;

    public FileOperation(FileOperationListener listener,File myFile) {

        this.listener = listener;
        this.myFile= myFile;
    }

    private FileOperationListener listener;

    public File getMyFile() {
        return myFile;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }



    @SuppressLint("LongLogTag")
    public void checkSDCardStatus() {
        String SDCardStatus = Environment.getExternalStorageState();
        Log.d("Debug:MEDIA_MOUNTED:", String.valueOf(Environment.MEDIA_MOUNTED.equals(SDCardStatus)));
        Log.d("Debug:MEDIA_MOUNTED_READ_ONLY:", String.valueOf(Environment.MEDIA_MOUNTED_READ_ONLY.equals(SDCardStatus)));

        if (Environment.MEDIA_MOUNTED.equals(SDCardStatus) & !Environment.MEDIA_MOUNTED_READ_ONLY.equals(SDCardStatus) ) {

            listener.onSDcardChecked(true,SDCardStatus);
        }else{
            listener.onSDcardChecked(false,SDCardStatus);
        }


    }

    public void checkMemory(){
        File external = Environment.getExternalStorageDirectory();
        listener.onMemoryChecked(external.getTotalSpace(),external.getFreeSpace());
    }

    public void save(String msg){
        try{

            FileOutputStream fos=new FileOutputStream(myFile);
            fos.write(msg.getBytes());
            fos.close();
            listener.onFileOpearionMsg("Saved in "+myFile.getAbsolutePath());
        }catch (IOException ex){
            ex.printStackTrace();
            listener.onFileOpearionMsg("Erron on saving: "+ex.getMessage());
        }
    }

    public void read(){
        try{
            if(myFile.exists()){
                FileInputStream fis=new FileInputStream(myFile);
                DataInputStream dis=new DataInputStream(fis);
                BufferedReader br=new BufferedReader(new InputStreamReader(dis));
                String strLine="";
                String myData = "";
                while ((strLine = br.readLine()) != null) {
                    if(myData!=""){
                        myData = myData + "\r\n";
                    }
                    myData = myData + strLine;
                }

                br.close();
                dis.close();
                fis.close();


                //listener.onFileOpearionMsg("Read: "+Environment.getExternalStorageDirectory().getFreeSpace()+ " Free");
                listener.onFileRead(myData);
            }



        }catch (IOException ex){
            ex.printStackTrace();
            listener.onFileOpearionMsg(myFile.exists()+"::Erron on read: "+ex.getMessage());
        }
    }

    public void delete(){
        try{
            if(myFile.delete()){
                listener.onFileOpearionMsg("Deleted Sucessfully!");
            }else{
                listener.onFileOpearionMsg("Not able to Delete");
            }

        }catch (Exception ex){
            ex.printStackTrace();
            listener.onFileOpearionMsg("Erron on delete: "+ex.getMessage());
        }
    }



}
