package cse232.rahman.majedur.cse323project;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import com.jaredrummler.android.processes.models.Stat;
import com.jaredrummler.android.processes.models.Statm;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int i = 0;
    int j = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActivityManager actvityManager = (ActivityManager)
                this.getSystemService(this.ACTIVITY_SERVICE);
        Timer timer = new Timer(" - Thread 1");

      /*  timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.e("Period ", " : " + i++ + Thread.currentThread().getName());

                // Toast.makeText(MainActivity.this, ""+i, Toast.LENGTH_SHORT).show();
            }
        }, 1000, 1000);

        Timer timer2 = new Timer(" - Thread 2");
        timer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.e("Task Period 2 ", " : " + j++ + Thread.currentThread().getName());

                // Toast.makeText(MainActivity.this, ""+i, Toast.LENGTH_SHORT).show();
            }
        }, 1000, 1000);
        */
        
        final List<ActivityManager.RunningAppProcessInfo> procInfos = actvityManager.getRunningAppProcesses();

        findViewById(R.id.taskButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (ActivityManager.RunningAppProcessInfo runningProInfo : procInfos) {

                    Log.d("Running Processes", "()()" + runningProInfo.pid);
                }

            }
        });
        
        
        findViewById(R.id.taskApplication).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ApplicationProcess.class));
            }
        });

        findViewById(R.id.permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AppPermissionActivity.class));
            }
        });
    }


    private void getProcess() {
        List<AndroidAppProcess> processes = AndroidProcesses.getRunningAppProcesses();

        for (AndroidAppProcess process : processes) {
            // Get some information about the process
            String processName = process.name;
            try {
                Stat stat = process.stat();
                int pid = stat.getPid();
                int parentProcessId = stat.ppid();
                long startTime = stat.stime();
                int policy = stat.policy();
                char state = stat.state();

                Statm statm = process.statm();
                long totalSizeOfProcess = statm.getSize();
                long residentSetSize = statm.getResidentSetSize();

                PackageInfo packageInfo = process.getPackageInfo(this, 0);
               // String appName = packageInfo.applicationInfo.loadLabel(pm).toString();

                Log.e("Process : " , " Library : " + pid);
            } catch (Exception ex) {


            }

        }
    }
}
