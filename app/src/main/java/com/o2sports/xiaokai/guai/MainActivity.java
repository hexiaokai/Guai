package com.o2sports.xiaokai.guai;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;


public class MainActivity extends TabActivity {

    public static MobileServiceClient mClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("Global");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Blame");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Circle");

        tab1.setIndicator("Global");
        tab1.setContent(new Intent(this, GlobalActivity.class));
        tab2.setIndicator("Blame");
        tab2.setContent(new Intent(this, BlameActivity.class));
        tab3.setIndicator("Circle");
        tab3.setContent(new Intent(this, CircleActivity.class));


        try {
            mClient = new MobileServiceClient(
                    "https://o2service.azure-mobile.net/",
                    "qJNqJihCYMDTfwYsbHbfURxaOfUNwh32",
                    this
            );
        }
        catch (Exception e)
        {
            messageDialog("Cannot connect to service");
        }


        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

        tabHost.setCurrentTab(0);

    }

    public void messageDialog(String dialogMessage)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(dialogMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
