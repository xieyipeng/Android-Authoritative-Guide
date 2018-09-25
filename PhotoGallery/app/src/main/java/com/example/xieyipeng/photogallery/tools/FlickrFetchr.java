package com.example.xieyipeng.photogallery.tools;

import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xieyipeng on 2018/9/25.
 */

public class FlickrFetchr {

    //TODO: fetchItems: Failed to connect to api.flickr.com/74.86.118.24:443

    private static final String TAG = "FlickrFetchr";
    private static final String API_KEY = "b66ed50f14c22d1c7a2b8e2ee8c5b203";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            InputStream inputStream = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseCode() + ": with " + urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            return outputStream.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public void fetchItems() {
        try {
            String url = Uri.parse("https://api.flickr.com/service/rest")
                    .buildUpon()
                    .appendQueryParameter("method", "flickr.photo.getRecent")
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s")
                    .build()
                    .toString();
            String jsonString = getUrlString(url);
            Log.e(TAG, "fetchItems: " + jsonString);
        } catch (IOException e) {
            Log.e(TAG, "fetchItems: " + e.getMessage());
        }
    }
}
