package cc.libqrscanner.modules;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

import cc.libqrscanner.Constans;

/**
 * Created by cx on 9/20/16.
 */

public class ScannerModule extends ReactContextBaseJavaModule {

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    public ScannerModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ScannerModule";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }

    @ReactMethod
    public void show(String message, int duration) {
        Toast.makeText(getReactApplicationContext(), message, duration).show();

        //去扫码
//        ScannerActivity.launch(getCurrentActivity());

    }

    @ReactMethod
    public void startActivityForResult(String activityName,int requestCode,Callback successCallback,Callback erroCallback){
        try {
            Activity currentActivity = getCurrentActivity();
            if ( null!= currentActivity) {
                Class aimActivity = Class.forName(activityName);
                Intent intent = new Intent(currentActivity,aimActivity);
                currentActivity.startActivityForResult(intent,requestCode);
                //阻塞取数据
                String result = Constans.sArrayBlockingQueue.take();
                successCallback.invoke(result);
            }
        } catch (Exception e) {
            erroCallback.invoke(e.getMessage());
            throw new JSApplicationIllegalArgumentException(
                    "Could not open the activity : " + e.getMessage());
        }
    }

    @Override
    public boolean canOverrideExistingModule() {
        return true;
    }
}