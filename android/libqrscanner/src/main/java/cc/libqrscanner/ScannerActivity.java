package cc.libqrscanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by CX on 2016/7/23.
 */
public class ScannerActivity extends FragmentActivity implements ZBarScannerView.ResultHandler {

    public static void launch(Context _context) {
        _context.startActivity(new Intent(_context, ScannerActivity.class));
    }

    private ZBarScannerView mScannerView;

    public static final String TAG = "ScannerActivity";

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.scanner_activity);                // Set the scanner view as the content view

        mScannerView = (ZBarScannerView) findViewById(R.id.zbar_scanner_view);    // Programmatically initialize the scanner view

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getContents()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        //结果赋值
        Constans.sArrayBlockingQueue.add(rawResult.getContents());
        finish();

        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
