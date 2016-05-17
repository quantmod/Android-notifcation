package cn.itcast.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    private EditText shorttitleText;
    private EditText titleText;
    private EditText contentText;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        shorttitleText = (EditText) this.findViewById(R.id.shorttitle);
        titleText = (EditText) this.findViewById(R.id.title);
        contentText = (EditText) this.findViewById(R.id.content);
    }
    
    public void send(View v){
    	String tickerText = shorttitleText.getText().toString();
    	String title = titleText.getText().toString();
    	String content = contentText.getText().toString();
    	//android.R系统自带资源,notification到来时状态栏通知图标和概要
    	Notification notification = new Notification(android.R.drawable.stat_notify_chat, tickerText, System.currentTimeMillis());
    	
    	Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:194949494"));
    
    	//延迟执行的intent,该语句的作用是定义了一个不是当即显示的activity，只有当用户拉下notify显示列表，并且单击对应的项的时候，跳转到该activity.
    	PendingIntent pendingIntent = PendingIntent.getActivity(this, 10, intent, 0);
    	//在此处设置在notify下拉列表里该notification的显示情况。
    	notification.setLatestEventInfo(this, title, content, pendingIntent);
    	notification.defaults = Notification.DEFAULT_SOUND;
    	notification.flags = Notification.FLAG_AUTO_CANCEL;
    	
    	NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	manager.notify(100, notification);
    }
}
