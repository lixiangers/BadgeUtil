package com.leethink.badger.impl;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;


import com.leethink.badger.Badger;

import java.util.Arrays;
import java.util.List;


public class SonyHomeBadger extends Badger {

    private static final String INTENT_ACTION = "com.sonyericsson.home.action.UPDATE_BADGE";
    private static final String INTENT_EXTRA_PACKAGE_NAME = "com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME";
    private static final String INTENT_EXTRA_ACTIVITY_NAME = "com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME";
    private static final String INTENT_EXTRA_MESSAGE = "com.sonyericsson.home.intent.extra.badge.MESSAGE";
    private static final String INTENT_EXTRA_SHOW_MESSAGE = "com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE";

    @Override
    public void executeBadge(Context context, Notification notification, int notificationId, int thisNotificationCount, int count) {
        setNotification(notification, notificationId, context);
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        Intent intent = new Intent(INTENT_ACTION);
        intent.putExtra(INTENT_EXTRA_PACKAGE_NAME, context.getPackageName());
        intent.putExtra(INTENT_EXTRA_ACTIVITY_NAME, launcherClassName);
        intent.putExtra(INTENT_EXTRA_MESSAGE, String.valueOf(count));
        intent.putExtra(INTENT_EXTRA_SHOW_MESSAGE, count > 0);
        context.sendBroadcast(intent);
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.sonyericsson.home");
    }
}
