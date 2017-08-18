package com.shpt.app.main.activity;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.blankj.utilcode.util.ToastUtils;
import com.shpt.app.main.R;
import com.shpt.lib.kernel.Base;

import java.io.IOException;

/**
 * Created by poovarasanv on 17/8/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 17/8/17 at 2:53 PM
 */

public class ScannerActivity extends AppCompatActivity {

    private RelativeLayout       baseViewScanner;
    private CardView             scanLayout;
    private FloatingActionButton fabScan;
    private CameraSource         cameraSource;
    private SurfaceView          scannerView;
    private BarcodeDetector      barcodeDetector;
    private TextView             resultText;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);


        initView();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, com.shpt.lib.kernel.R.color.colorPrimary));
        }
        //CommunityMaterial.Icon.cmd_barcode_scan
        fabScan.setImageDrawable(Base.getIcon("cmd_barcode_scan", 30, Color.WHITE));

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ISBN)//QR_CODE)
                .build();

        ViewTreeObserver vto = scannerView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // this.scannerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width  = scannerView.getMeasuredWidth();
                int height = scannerView.getMeasuredHeight();

                cameraSource = new CameraSource
                        .Builder(ScannerActivity.this, barcodeDetector)
                        .setRequestedPreviewSize(width, height)
                        .build();
            }
        });


        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this,
                new String[]{Manifest.permission.CAMERA}, new PermissionsResultAction() {

                    @Override
                    public void onGranted() {
                        accessCamera();
                    }

                    @Override
                    public void onDenied(String permission) {
                        ToastUtils
                                .showLong("Sorry, we need the Camera Permission to do that");
                        onBackPressed();
                    }

                });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void accessCamera() {
        scannerView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(ScannerActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        cameraSource.start(surfaceHolder);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    resultText.post(new Runnable() {    // Use the post method of the TextView
                        public void run() {
                            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(500);
                            resultText.setText(    // Update the TextView
                                    barcodes.valueAt(0).displayValue
                            );
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        baseViewScanner = (RelativeLayout) findViewById(R.id.baseViewScanner);
        scanLayout = (CardView) findViewById(R.id.scanLayout);
        fabScan = (FloatingActionButton) findViewById(R.id.fabScan);
        scannerView = (SurfaceView) findViewById(R.id.scannerView);
        resultText = (TextView) findViewById(R.id.resultText);
    }
}
