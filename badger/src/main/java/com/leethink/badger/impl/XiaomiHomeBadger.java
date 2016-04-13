package com.leethink.badger.impl;

import android.app.Notification;
import android.content.Context;


import com.leethink.badger.Badger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class XiaomiHomeBadger extends Badger {

    @Override
    public void executeBadge(Context context, Notification notification, int notificationId, int thisNotificationCount, int count) {
        try {
            Field field = notification.getClass().getDeclaredField("extraNotification");
            Object extraNotification = field.get(notification);
            Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
            method.invoke(extraNotification, thisNotificationCount);//小米这里只要这个notificationId 对应的count，而不是所有notification count
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            setNotification(notification, notificationId, context);
        }
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList(
                "com.miui.miuilite",
                "com.miui.home",
                "com.miui.miuihome",
                "com.miui.miuihome2",
                "com.miui.mihome",
                "com.miui.mihome2"
        );
    }
}
