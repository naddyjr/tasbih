package com.somnest.tasbih;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.somnest.tasbih.Adapter.DuaAdapter;
import com.somnest.tasbih.Appcompany.Privacy_Policy_activity;
import com.somnest.tasbih.DatabaseHelper.DatabaseAccess;
import com.somnest.tasbih.Model.DuaModel;
import com.somnest.tasbih.OnItemClickListener.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;


public class DuaActivity extends AppCompatActivity implements OnItemClickListener {
    DatabaseAccess databaseAccess;
    DuaAdapter duaAdapter;
    RelativeLayout layout;
    RecyclerView rvDua;
    AppCompatTextView toolbar_text;
    int topicId;
    String topicName;
    List<Integer> getDuaIds = new ArrayList();
    List<DuaModel> duaModelList = new ArrayList();

    @Override 
    public void OnClick(View view, int i) {
    }

    
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dua);




        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.topicId = getIntent().getIntExtra("topicId", 0);
        this.topicName = getIntent().getStringExtra("topicName");
        this.rvDua = (RecyclerView) findViewById(R.id.rvDua);
        AppCompatTextView appCompatTextView = (AppCompatTextView) findViewById(R.id.toolbar_text);
        this.toolbar_text = appCompatTextView;
        appCompatTextView.setText(this.topicName);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        this.databaseAccess = databaseAccess;
        databaseAccess.open();
        this.getDuaIds = this.databaseAccess.getDuaTopicId(this.topicId);
        this.databaseAccess.close();
        this.databaseAccess.open();
        for (int i = 0; i < this.getDuaIds.size(); i++) {
            this.duaModelList.add(this.databaseAccess.getDua(this.getDuaIds.get(i).intValue()));
        }
        this.databaseAccess.close();
        this.rvDua.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.rvDua.setItemAnimator(new DefaultItemAnimator());
        DuaAdapter duaAdapter = new DuaAdapter(this, this.duaModelList, this);
        this.duaAdapter = duaAdapter;
        this.rvDua.setAdapter(duaAdapter);
    }

    @Override 
    public void onBackPressed() {
        super.onBackPressed();
        finish();
     
    }

   

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
