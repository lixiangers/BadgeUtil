package com.leethink.badger.impl;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;


import com.leethink.badger.Badger;

import java.util.Arrays;
import java.util.List;


public class SolidHomeBadger extends Badger {

    private static final String INTENT_ACTION_UPDATE_COUNTER = "com.majeur.launcher.intent.action.UPDATE_BADGE";
    private static final String INTENT_EXTRA_PACKAGENAME = "com.majeur.launcher.intent.extra.BADGE_PACKAGE";
    private static final String INTENT_EXTRA_COUNT = "com.majeur.launcher.intent.extra.BADGE_COUNT";
    private static final String INTENT_EXTRA_CLASS = "com.majeur.launcher.intent.extra.BADGE_CLASS";

    @Override
    public void executeBadge(Context context, Notification notification, int notificationId, int thisNotificationCount, int count) {
        setNotification(notification, notificationId, context);
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }

        Intent intent = new Intent(INTENT_ACTION_UPDATE_COUNTER);
        intent.putExtra(INTENT_EXTRA_PACKAGENAME, context.getPackageName());
        intent.putExtra(INTENT_EXTRA_COUNT, count);
        intent.putExtra(INTENT_EXTRA_CLASS, launcherClassName);
        context.sendBroadcast(intent);
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.majeur.launcher");
    }
}
