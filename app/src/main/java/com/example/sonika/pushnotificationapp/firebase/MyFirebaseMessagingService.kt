package com.example.sonika.pushnotificationapp.firebase

import android.app.Notification.BADGE_ICON_SMALL
//import com.google.firebase.messaging.FirebaseMessagingService
//import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager
import android.media.RingtoneManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.core.app.NotificationCompat
import android.graphics.BitmapFactory
import androidx.core.content.ContextCompat
import java.net.HttpURLConnection
import java.net.URL
import com.example.sonika.pushnotificationapp.MainActivity
import com.example.sonika.pushnotificationapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


internal class MyFirebaseMessagingService : FirebaseMessagingService()
{
    var bitmap: Bitmap? = null
    companion object {
        @JvmField
        var notificationId: Int = 0
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
//        Log.d(TAG, "From: " + remoteMessage!!.getFrom())

        // Check if message contains a data payload.
        if (remoteMessage!!.data.isNotEmpty()) {
            val data = remoteMessage.data

            val title = data["title"]
            val message = data["message"]
            var image: String? = ""
            if (data.containsKey("image")) {
                image = data["image"]
                if (!image.isNullOrEmpty())
                    bitmap = getBitmapfromUrl(image)
            }
            sendNotification(message, title, bitmap)
            bitmap = null//reset bitmap
        }

    }


    private fun sendNotification(messageBody: String?, messageTitle: String?, bitmap: Bitmap?) {

        //take to notification list if logged in else take to splash screen
//        val intent: Intent
//        val resultIntent: PendingIntent

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val resultIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_ONE_SHOT)


        val intent1 = Intent()
        val dismissIntent = PendingIntent.getActivity(this, 0,
                intent1, PendingIntent.FLAG_ONE_SHOT)

        val notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val mNotificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setBadgeIconType(BADGE_ICON_SMALL)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .addAction(0,"REPLY", resultIntent)
                .addAction(0, "SNOOZE", dismissIntent)
                .setSound(notificationSoundURI)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setContentIntent(resultIntent)


        //show bitmap if present
        if (bitmap == null) {
            //make notification expandable
            mNotificationBuilder.setStyle(NotificationCompat.BigTextStyle().bigText(messageBody))
        } else {
            mNotificationBuilder.setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round))
                    .setStyle(NotificationCompat.BigPictureStyle()
                            .bigPicture(bitmap).setSummaryText(messageBody))
        }
        if (resultIntent == null) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancelAll()

        }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationId, mNotificationBuilder.build())
            notificationId++

    }
    fun getBitmapfromUrl(imageUrl: String?): Bitmap? {
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input = connection.getInputStream()
            return BitmapFactory.decodeStream(input)

        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
            return null

        }

    }

}






//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT)
//
//        val notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        val notificationBuilder = NotificationCompat.Builder(this)
//                .setLargeIcon(image)/*Notification icon image*/
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Firebase messaging")
//                .setContentText(messageBody)
////                .setStyle(NotificationCompat.BigPictureStyle()
////                        .bigPicture(image))/*Notification with Image*/
//                .setAutoCancel(true)
//                .setSound(notificationSound)
//                .setContentIntent(pendingIntent)
//
//        if (image == null) {
//            //make notification expandable
//            notificationBuilder.setStyle(NotificationCompat.BigTextStyle().bigText(messageBody))
//        } else {
//            notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
//                    .setStyle(NotificationCompat.BigPictureStyle()
//                            .bigPicture(image).setSummaryText(messageBody))
//        }
//
//
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
//    }



