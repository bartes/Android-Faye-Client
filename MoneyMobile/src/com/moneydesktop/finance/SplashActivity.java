
package com.moneydesktop.finance;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.moneydesktop.finance.handset.activity.DashboardHandsetActivity;
import com.moneydesktop.finance.handset.activity.LoginHandsetActivity;
import com.moneydesktop.finance.model.User;
import com.moneydesktop.finance.shared.activity.BaseActivity;
import com.moneydesktop.finance.tablet.activity.DashboardTabletActivity;
import com.moneydesktop.finance.tablet.activity.LoginTabletActivity;

public class SplashActivity extends BaseActivity {
    
    public final String TAG = this.getClass().getSimpleName();

    protected int mSplashTime = 5000; // time to display the splash screen in ms
    private Handler mHandler;
    private Runnable mTask = new Runnable() {
        
        @Override
        public void run() {
            
            endSplash();
        }
    };
    
    private boolean mIsHandset = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().setBackgroundDrawable(null);
        
        ApplicationContext.setIsTablet(isTablet(this));

        if (User.getCurrentUser() != null) {
            endSplash();
            return;
        }

        int deviceResource = ApplicationContext.isTablet() ? R.layout.tablet_splash_view : R.layout.handset_splash_view;
        int resource = User.getCurrentUser() != null ? deviceResource : R.layout.splash_view;
        
        setContentView(resource);

        mIsHandset = false;
        
        if (ApplicationContext.isTablet()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        
        if (ApplicationContext.isTablet()) {
            ImageView image = (ImageView) findViewById(R.id.splash_screen);
            image.setBackgroundResource(R.drawable.default_landscape_tablet);
        }

        // Creates a CountDownTimer object
        mHandler = new Handler();
        mHandler.postDelayed(mTask, mSplashTime);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	
    	if (event.getPointerCount() >= 6 && !mIsHandset) {
    		mIsHandset = true;
    	}

        if (event.getAction() == MotionEvent.ACTION_UP) {
        	
            mHandler.removeCallbacks(mTask);
            endSplash();
        }

        return true;
    }

    private void endSplash() {

        Intent i = new Intent(getApplicationContext(), DashboardHandsetActivity.class);

        if (User.getCurrentUser() != null) {

            if (ApplicationContext.isTablet() && !mIsHandset) {
                i = new Intent(getApplicationContext(), DashboardTabletActivity.class);
            }

        } else {

            if (ApplicationContext.isTablet() && !mIsHandset) {
                i = new Intent(getApplicationContext(), LoginTabletActivity.class);
            } else {
                i = new Intent(getApplicationContext(), LoginHandsetActivity.class);
            }

        }
        
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.putExtras(getIntent());
        startActivity(i);
        overridePendingTransition(R.anim.fade_in_fast, R.anim.none);
        finish();
    }

    @Override
    public String getActivityTitle() {
        return null;
    }
}
