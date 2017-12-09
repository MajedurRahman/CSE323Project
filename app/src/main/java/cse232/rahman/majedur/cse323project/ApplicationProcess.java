package cse232.rahman.majedur.cse323project;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class ApplicationProcess extends AppCompatActivity {
    ActivityManager activitymanager;
    Context context;
    List<ActivityManager.RunningAppProcessInfo> RAP ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_process);


        context = getApplicationContext();

        activitymanager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);

        RAP = activitymanager.getRunningAppProcesses();

        for(ActivityManager.RunningAppProcessInfo processInfo: RAP ){
            Toast.makeText(context, ""+ RAP.size(), Toast.LENGTH_SHORT).show();
           Log.e("Application Process : " , processInfo.processName + "\n");

        }
    }
}
