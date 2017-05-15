package com.example.shriyanshu.wifisharefilesexampleandroid;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class WiFiClientIPTransferService extends IntentService {

public WiFiClientIPTransferService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
public WiFiClientIPTransferService() {
    super("WiFiClientIPTransferService");
}


Handler mHandler;
	

    @Override
    protected void onHandleIntent(Intent intent) {
        Context context = GlobalApplication.getGlobalAppContext();
        if (intent.getAction().equals(FileTransferService.ACTION_SEND_FILE)) {
            String host = intent.getExtras().getString(FileTransferService.EXTRAS_GROUP_OWNER_ADDRESS);
            String InetAddress =  intent.getExtras().getString(FileTransferService.inetaddress);
            CommonMethods.e("LocalIp Received while first connect","host address"+ host);

            Socket socket = new Socket();
            int port = intent.getExtras().getInt(FileTransferService.EXTRAS_GROUP_OWNER_PORT);

            try {
            	
                //Log.d(MainActivity.TAG, "Opening client socket for First tiime- ");
                socket.bind(null);
                socket.connect((new InetSocketAddress(host, port)), FileTransferService.SOCKET_TIMEOUT);
                //Log.d(MainActivity.TAG, "Client socket - " + socket.isConnected());
                OutputStream stream = socket.getOutputStream();
                ContentResolver cr = context.getContentResolver();
                InputStream is = null;
                
               /*
                * Object that is used to send file name with extension and recieved on other side.
                */
                ObjectOutputStream oos = new ObjectOutputStream(stream);
                WiFiTransferModal transObj = new WiFiTransferModal(InetAddress);
                
                oos.writeObject(transObj);
                System.out.println("Sending request to Socket Server");
                
                oos.close();	//close the ObjectOutputStream after sending data.
            } catch (IOException e) {
               // Log.e(MainActivity.TAG, e.getMessage());
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    if (socket.isConnected()) {
                        try {
                        	CommonMethods.e("WiFiClientIP Service", "First Connection service socket closed");
                            socket.close();
                        } catch (Exception e) {
                            // Give up
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

}
