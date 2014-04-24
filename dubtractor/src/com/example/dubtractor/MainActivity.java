package com.example.dubtractor;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        haveNetworkConnection();
        
        WebView myWebView = (WebView) findViewById(R.id.webview);
        
      //not sure if i need to enable javascript
        myWebView.getSettings().setJavaScriptEnabled(true);
        
        myWebView.setVisibility(View.GONE);
        
        myWebView.loadUrl("http://myradiostream.com/mobile/dubtractor");
        // maybe i can link directly to the stream
        SystemClock.sleep(5000);
        myWebView.loadUrl("javascript:html5player.play()");
        
        //message if nothing being broadcast
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ImageView icon = (ImageView) findViewById(R.id.imageView1);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {           
                    
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected()) {
                	Log.v("MOBILE connectivity", "!");
                	icon.setImageResource(R.drawable.mobile);
                	haveConnectedMobile = true;
                	Toast.makeText(this, "Mobile Internet Connection", Toast.LENGTH_SHORT).show();
                }
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected()) {
                	Log.v("WIFI connectivity", "!");
                	icon.setImageResource(R.drawable.wifi);
                	haveConnectedWifi = true;
                }    
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}

