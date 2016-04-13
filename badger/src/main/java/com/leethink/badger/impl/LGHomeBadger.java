package com.leethink.badger.impl;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;


import com.leethink.badger.Badger;

import java.util.Arrays;
import java.util.List;


public class LGHomeBadger extends Badger {

    private static final String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
    private static final String INTENT_EXTRA_BADGE_COUNT = "badge_count";
    private static final String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";
    private static final String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";

    @Override
    public void executeBadge(Context context, Notification notification, int notificationId, int thisNotificationCount, int count) {
        setNotification(notification, notificationId, context);
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }

        Intent intent = new Intent(INTENT_ACTION);
        intent.putExtra(INTENT_EXTRA_BADGE_COUNT, count);
        intent.putExtra(INTENT_EXTRA_PACKAGENAME, context.getPackageName());
        intent.putExtra(INTENT_EXTRA_ACTIVITY_NAME, launcherClassName);
        context.sendBroadcast(intent);
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList(
                "com.lge.launcher",
                "com.lge.launcher2"
        );
    }
}
