package com.leethink.badgerdemo;

import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leethink.badger.BadgeUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int NOTIFY_ID = 100;
    private EditText tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCount = (EditText) findViewById(R.id.tv_count);
        findViewById(R.id.bt_set).setOnClickListener(this);
        findViewById(R.id.bt_clear).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_set:
                try {
                    final int count = Integer.parseInt(tvCount.getText().toString());
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(getApplicationInfo().icon)
                            .setWhen(System.currentTimeMillis())
                            .setAutoCancel(true);

                    mBuilder.setContentTitle("test");
                    mBuilder.setTicker("test");
                    mBuilder.setContentText("test");

                    //点击set 后，app退到桌面等待3s看效果（有的launcher当app在前台设置未读数量无效）
                    final Notification notification = mBuilder.build();
                    new Handler(getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            BadgeUtil.sendBadgeNotification(notification, NOTIFY_ID, getApplicationContext(), count, count);
                            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                        }
                    }, 3 * 1000);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_clear:
                //XIAO_MI 当进入app后就自动清除了未读数量.有些需要主动清除。可以所有的都清除，没什么影响
                BadgeUtil.resetBadgeCount(getApplicationContext());
                break;
        }
    }
}
