package com.o2sports.xiaokai.guai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.o2sports.xiaokai.guai.adapter.KeywordAdapter;
import com.o2sports.xiaokai.guai.entity.Keyword;

import java.net.MalformedURLException;
import java.util.List;

import android.os.AsyncTask;
import android.widget.ListView;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;




public class GlobalActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);

        final MobileServiceTable<Keyword> mKeywordTable = MainActivity.mClient.getTable(Keyword.class);
        final KeywordAdapter mAdapter = new KeywordAdapter(this, R.layout.list_view_button);

        ListView listViewKeyword = (ListView) findViewById(R.id.keywordListView);
        listViewKeyword.setAdapter(mAdapter);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    final MobileServiceList<Keyword> result = mKeywordTable.execute().get();

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mAdapter.clear();
                            for (Keyword item : result) {
                                mAdapter.add(item);
                            }
                        }
                    });
                } catch (Exception exception) {

                    messageDialog(exception.getMessage());
                }
                return null;
            }
        }.execute();

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
