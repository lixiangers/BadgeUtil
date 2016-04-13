package com.leethink.badger.impl;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;


import com.leethink.badger.Badger;

import java.util.Arrays;
import java.util.List;


public class NovaHomeBadger extends Badger {

    private static final String CONTENT_URI = "content://com.teslacoilsw.notifier/unread_count";
    private static final String COUNT = "count";
    private static final String TAG = "tag";

    @Override
    public void executeBadge(Context context, Notification notification, int notificationId, int thisNotificationCount, int count) {
        setNotification(notification, notificationId, context);

        ContentValues contentValues = new ContentValues();
        contentValues.put(TAG, context.getPackageName() + "/" +
                getLauncherClassName(context));
        contentValues.put(COUNT, count);
        try {
            context.getContentResolver().insert(Uri.parse(CONTENT_URI),
                    contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.teslacoilsw.launcher");
    }
}
