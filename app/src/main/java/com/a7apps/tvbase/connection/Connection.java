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

    public Connection(Context context) {
        this.context = context;
    }

    @Override
    public void getPosters(String url, final ArrayList<String> dataArray) {
        RequestQueue mQueue = Volley.newRequestQueue(context);
        for (int i = 1; i < 4; i++){
            String numPage = String.valueOf(i);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url+numPage, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("results");

                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject res = jsonArray.getJSONObject(i);

                                    String posterPath = res.getString("poster_path");

                                    dataArray.add(posterPath);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    AssistantMethods assistant = new AssistantMethods(context);
                    assistant.printConnectionError(error);
                }
            });

            mQueue.add(request);
        }
    }

}
