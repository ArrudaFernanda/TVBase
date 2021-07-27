package com.a7apps.tvbase.connection;

import android.content.Context;
import com.a7apps.tvbase.assistant.AssistantMethods;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Connection implements Connect {
    private Context context;
    private RequestQueue mQueue;

    public Connection(Context context) {
        this.context = context;
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void connect(final String url, final ArrayList<String> dataArray) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    for (int i = 1; i < 5; i++){
                        String numPage = String.valueOf(i);

                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url + numPage, null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        try {
                                            JSONArray jsonArray = response.getJSONArray("results");

                                            for (int i = 0; i < jsonArray.length(); i++){
                                                JSONObject res = jsonArray.getJSONObject(i);
                                                String posterPath = res.getString("poster_path");
                                                dataArray.add(posterPath); //tenho que conactenar com IMGURL depois

                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                AssistantMethods assistantMethods = new AssistantMethods(context);
                                assistantMethods.printConnectionError(error);
                            }
                        });
                        mQueue.add(request);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
