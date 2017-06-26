package com.acadgild.android.volley.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.acadgild.android.volley.App;
import com.acadgild.android.volley.utils.CommonUtilities;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by Preetika on 6/17/2016.
 */
public class CallAddrVolley extends AsyncTask<Void, Void, Void> {

    private static String TAG= "CallAddr";
    Context context;
    Map<String, String> paramss;
    OnWebServiceResult resultListener;
    CommonUtilities.SERVICE_TYPE Servicetype;
    String url;
    int method;
    private String tag_json_obj = "jobj_req";


    public CallAddrVolley(Context context, Map<String, String> params, int method, String url, CommonUtilities.SERVICE_TYPE Servicetype, OnWebServiceResult resultListener){
        this.context= context;
        this.paramss = params;
        this.url= url;
        this.resultListener= resultListener;
        this.Servicetype= Servicetype;
        this.method= method;
        Log.e("size", "size= "+ paramss.size());
    }


   @Override
    protected Void doInBackground(Void... params) {
     /*  JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
               url, null,
               new Response.Listener<JSONObject>() {

                   @Override
                   public void onResponse(JSONObject response) {
                       Log.d(TAG, response.toString());
                       try {
                           resultListener.getWebResponse(response.toString(), Servicetype);
                       }catch (Exception e){
                           e.printStackTrace();
                       }
                   }
               }, new Response.ErrorListener() {

           @Override
           public void onErrorResponse(VolleyError error) {
               VolleyLog.d(TAG, "Error: " + error.getMessage());
           }
       }) {

           *//**
            * Passing some request headers
            * *//*
           @Override
           public Map<String, String> getHeaders() throws AuthFailureError {
               HashMap<String, String> headers = new HashMap<String, String>();
               headers.put("Content-Type", "application/json");
               return headers;
           }

           @Override
           protected Map<String, String> getParams() {
               Log.e("params", "params= "+ paramss.size());
               Log.e("params", "params= "+ paramss.get(Constants.USER_ID));
               return paramss;
           }

       };*/
       StringRequest myReq = new StringRequest(method,
               url,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Log.e(TAG, response.toString());
                       try {
                           resultListener.getWebResponse(response.toString(), Servicetype);
                       }catch (Exception e){
                           e.printStackTrace();
                       }

                   }
               },
               new Response.ErrorListener() {

                   @Override
                   public void onErrorResponse(VolleyError error) {
                       VolleyLog.d(TAG, "Error: " + error.getMessage());
                   }
               }) {

           protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {

               Log.e("params", "params= "+ paramss.size());
               Log.e(TAG, "Url= "+ url+ paramss.toString());
               return paramss;
           };
       };
       // Adding request to request queue
       App.getmInstance().addToRequestQueue(myReq,
               tag_json_obj);
        return null;
    }

}
