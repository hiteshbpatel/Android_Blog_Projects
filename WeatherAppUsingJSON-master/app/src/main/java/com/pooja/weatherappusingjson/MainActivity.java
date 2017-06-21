package com.pooja.weatherappusingjson;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.URLUtil;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pooja.weatherappusingjson.Adapter.MyAdapter;
import com.pooja.weatherappusingjson.model.InfoWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<InfoWeather> listmodel=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_weather);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.loadmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid=item.getItemId();
        switch (itemid)
        {
            case R.id.load_menu:
                new MyAsynctask().execute("http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=d7b900681c37193223281142bd919019");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    class MyAsynctask extends AsyncTask<String,Void,List<InfoWeather>>
    {
        @Override
        protected void onPreExecute() {

            //checking status of connection
            SupplicantState supState;
            WifiManager wifiManager = (WifiManager) getSystemService(MainActivity.this.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            Toast.makeText(MainActivity.this, "Status:"+wifiInfo, Toast.LENGTH_SHORT).show();
            supState = wifiInfo.getSupplicantState();
            Toast.makeText(MainActivity.this, "Status:"+supState, Toast.LENGTH_SHORT).show();
            ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(MainActivity.this.CONNECTIVITY_SERVICE);
            NetworkInfo activenetwork=connectivityManager.getActiveNetworkInfo();
            if(activenetwork!=null && activenetwork.isConnected()==true )
            {
                Toast.makeText(MainActivity.this, "connected", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(MainActivity.this, "Connect to internet", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected List<InfoWeather> doInBackground(String... strings) {
            HttpURLConnection connection=null;
            InputStream inputStream=null;
            BufferedReader reader=null;
            try {
                URL url=new URL(strings[0]);
                connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                inputStream=connection.getInputStream();
                reader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer=new StringBuffer();
                String line="";
                while((line=reader.readLine())!=null)
                {
                    buffer.append(line);
                }
                String json=buffer.toString();


                JSONObject jsonObject=new JSONObject(json);
                InfoWeather myData = new InfoWeather();
                JSONObject coordJsononject=jsonObject.getJSONObject("coord");
                myData.setLon("Latitude:"+coordJsononject.getString("lat"));
                myData.setLat("Longitude"+coordJsononject.getString("lon"));

                JSONArray weatherarray=jsonObject.getJSONArray("weather");
                for(int i=0;i<weatherarray.length();i++) {
                    myData.setId(weatherarray.getJSONObject(i).getInt("id"));
                    myData.setMain(weatherarray.getJSONObject(i).getString("main"));
                    myData.setDescription(weatherarray.getJSONObject(i).getString("description"));
                    myData.setIcon(weatherarray.getJSONObject(i).getString("icon"));
                    listmodel.add(myData);
                }
                myData.setBase(jsonObject.getString("base"));

                JSONObject mainJsonobject=jsonObject.getJSONObject("main");
                myData.setTemp(mainJsonobject.getDouble("temp"));
                myData.setPressure(mainJsonobject.getDouble("pressure"));
                myData.setHumidity(mainJsonobject.getDouble("humidity"));
                myData.setTemp_min(mainJsonobject.getDouble("temp_min"));
                myData.setTemp_max(mainJsonobject.getDouble("temp_max"));
                myData.setSea_level(mainJsonobject.getDouble("sea_level"));
                myData.setGrnd_level(mainJsonobject.getDouble("grnd_level"));

                JSONObject windjson=jsonObject.getJSONObject("wind");
                myData.setSpeed(windjson.getDouble("speed"));
                myData.setDeg(windjson.getDouble("deg"));

                JSONObject rainjson=jsonObject.getJSONObject("rain");
                myData.setRainvalue3h(rainjson.getDouble("3h"));

                JSONObject coludsjson=jsonObject.getJSONObject("clouds");
                myData.setAll(coludsjson.getInt("all"));

                myData.setDt(jsonObject.getInt("dt"));

                JSONObject sysjsonobject=jsonObject.getJSONObject("sys");
                myData.setMessage(sysjsonobject.getDouble("message"));
                myData.setCountry(sysjsonobject.getString("country"));
                myData.setSunrise(sysjsonobject.getInt("sunrise"));
                myData.setSunset(sysjsonobject.getInt("sunset"));

                myData.setObj_id(jsonObject.getLong("id"));
                myData.setName(jsonObject.getString("name"));
                myData.setCod(jsonObject.getInt("cod"));
                return listmodel;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection!=null)
                {
                    connection.disconnect();
                }
                if(reader!=null)
                {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<InfoWeather> listmodel) {
            super.onPostExecute(listmodel);
            if(listmodel!=null)
            {
                Toast.makeText(MainActivity.this,"Displaying items"+listmodel.size(),Toast.LENGTH_LONG).show();
                MyAdapter myadapter=new MyAdapter(MainActivity.this,listmodel);
                recyclerView.setAdapter(myadapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }else
            {
                Toast.makeText(MainActivity.this,"Try again later.....Nothing to display!!!",Toast.LENGTH_LONG).show();
            }

        }

    }

        }


