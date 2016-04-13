package com.leethink.badger.impl;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;


import com.leethink.badger.Badger;

import java.util.Arrays;
import java.util.List;

public class ApexHomeBadger extends Badger {

    private static final String INTENT_UPDATE_COUNTER = "com.anddoes.launcher.COUNTER_CHANGED";
    private static final String PACKAGENAME = "package";
    private static final String COUNT = "count";
    private static final String CLASS = "class";

    @Override
    public void executeBadge(Context context, Notification notification, int notificationId, int thisNotificationCount, int count) {
        setNotification(notification, notificationId, context);
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }

        Intent intent = new Intent(INTENT_UPDATE_COUNTER);
        intent.putExtra(PACKAGENAME, context.getPackageName());
        intent.putExtra(COUNT, count);
        intent.putExtra(CLASS, launcherClassName);
        context.sendBroadcast(intent);
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.anddoes.launcher");
    }
}
