package dms.com.automaticcallrecordmaster;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;


public class CallRecorder
    extends TabActivity
{
    private static final int MENU_UPDATE = Menu.FIRST;
    private static final int MENU_PREFERENCES = Menu.FIRST+1;

    private static final int SHOW_PREFERENCES = 1;

    private static final String[] TABS = { "Preferences", "CallLog" };

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setDefaultTab(0);

        TabHost tabs = getTabHost();

        for (int i=0; i<TABS.length; i++) {
            TabHost.TabSpec tab = tabs.newTabSpec(TABS[i]);
            //ComponentName n = new ComponentName("dms.com.automaticcallrecordmaster.", "dms.com.automaticcallrecordmaster." + TABS[i]);
//            ComponentName componentName = new ComponentName("dms.com.automaticcallrecordmaster",TABS[i]);
            if(i == 0)
            tab.setContent(new Intent(this,Preferences.class));
            else
                tab.setContent(new Intent(this, CallLog.class));
            tab.setIndicator(TABS[i]);

            tabs.addTab(tab);
        }
    }

    public static class MyTabIndicator extends LinearLayout
    {
        public MyTabIndicator(Context context, String label)
        {
            super(context);
            
            View tab = LayoutInflater.from(context).inflate(R.layout.tab_indicator, this);

            TextView tv = (TextView)tab.findViewById(R.id.tab_label);
            tv.setText(label);
        }
    }
}
