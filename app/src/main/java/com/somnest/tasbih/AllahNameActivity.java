package com.somnest.tasbih;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.somnest.tasbih.Adapter.Allah99NameAdapter;
import com.somnest.tasbih.Appcompany.Privacy_Policy_activity;
import com.somnest.tasbih.DatabaseHelper.DatabaseAccess;
import com.somnest.tasbih.Model.AllahNameModel;
import com.somnest.tasbih.OnItemClickListener.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;


public class AllahNameActivity extends AppCompatActivity implements OnItemClickListener {
    Allah99NameAdapter allah99NameAdapter;
    List<AllahNameModel> allahNameModelList = new ArrayList();
    DatabaseAccess databaseAccess;
    RelativeLayout layout;
    RecyclerView rv99AllahName;

    @Override 
    public void OnClick(View view, int i) {
    }

    
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP, Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        setContentView(R.layout.activity_99_allah_name);


        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.databaseAccess = DatabaseAccess.getInstance(this);
        this.rv99AllahName = (RecyclerView) findViewById(R.id.rv99AllahName);
        this.databaseAccess.open();
        this.allahNameModelList = this.databaseAccess.getDua99AllhaName();
        this.databaseAccess.close();
        this.rv99AllahName.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.rv99AllahName.setItemAnimator(new DefaultItemAnimator());
        Allah99NameAdapter allah99NameAdapter = new Allah99NameAdapter(this, this.allahNameModelList, this);
        this.allah99NameAdapter = allah99NameAdapter;
        this.rv99AllahName.setAdapter(allah99NameAdapter);
    }

    @Override 
    public void onBackPressed() {
        super.onBackPressed();
        finish();
     
    }

    

    @Override 
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
