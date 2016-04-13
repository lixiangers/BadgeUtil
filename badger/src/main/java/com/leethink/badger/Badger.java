package com.leethink.badger;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

public abstract class Badger {

    /**
     * @param context
     * @param notification          更新角标一般都是和发送notification并行的。如果不想发notification只是更新角标，这里传null
     * @param notificationId
     * @param thisNotificationCount
     * @param count
     */
    public abstract void executeBadge(Context context, Notification notification, int notificationId, int thisNotificationCount, int count);

    public abstract List<String> getSupportLaunchers();

    /**
     * 获取当前app 的启动页面activity的classname
     *
     * @param context
     * @return
     */
    protected static String getLauncherClassName(Context context) {
        PackageManager packageManager = context.getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        // To limit the components this Intent will resolve to, by setting an
        // explicit package name.
        intent.setPackage(context.getPackageName());
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        // All Application must have 1 Activity at least.
        // Launcher activity must be found!
        ResolveInfo info = packageManager
                .resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);

        // get a ResolveInfo containing ACTION_MAIN, CATEGORY_LAUNCHER
        // if there is no Activity which has filtered by CATEGORY_DEFAULT
        if (info == null) {
            info = packageManager.resolveActivity(intent, 0);
        }

        return info.activityInfo.name;
    }

    protected void setNotification(Notification notification, int notificationId, Context context) {
        if (notification != null) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId, notification);
        }
    }
}
