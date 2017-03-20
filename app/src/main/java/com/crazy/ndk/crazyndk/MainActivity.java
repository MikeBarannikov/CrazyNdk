package com.crazy.ndk.crazyndk;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.crazy.ndk.crazyndk.filter.GaussianBlurFilter;
import com.crazy.ndk.crazyndk.filter.SketchFilter;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        mImage = (ImageView) findViewById(R.id.image);

        Button blur = (Button) findViewById(R.id.blur);
        blur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bitmap bitmap = ((BitmapDrawable) mImage.getDrawable()).getBitmap();
                mImage.setImageBitmap(GaussianBlurFilter.changeToGaussianBlur(bitmap, 4));
            }
        });

        Button sketch = (Button) findViewById(R.id.sketch);
        sketch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bitmap bitmap = ((BitmapDrawable) mImage.getDrawable()).getBitmap();
                mImage.setImageBitmap(SketchFilter.changeToSketch(bitmap));
            }
        });

        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImage.setImageResource(R.mipmap.ic_launcher);
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
