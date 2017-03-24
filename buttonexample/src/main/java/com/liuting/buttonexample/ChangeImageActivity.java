package com.liuting.buttonexample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.liuting.buttonexample.widget.RotationView;

/**
 * Package:com.liuting.buttonexample
 * author:liuting
 * Date:2017/3/24
 * Desc:实现裁剪、缩放和旋转图片
 * 扩展：可以通过android.graphics.BitmapFactory类的静态方法从不同来源获得bitmap对象，如果从SD卡中获取则是：
 * Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/pic_head.jpg");
 * 转载大图片时可以采取缩小图片的方法：
 * Options options = new Options();
 * options.inSampleSize = 4;//缩小为原图的25%
 * Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/pic_head.jpg",options);
 */

public class ChangeImageActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private ImageView imgPrototype;//原图像
    private ImageView imgApart;//裁剪后的部分区域
    private SeekBar sbZoom;//缩放图像
    private SeekBar sbRotate;//旋转图像
    private int minWidth = 80;//最小宽度
    private int maxRotate = 360;//最大角度
    private LinearLayout layoutMain;//主控件
    private RotationView rotationView;//旋转View

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image_layout);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        imgPrototype = (ImageView) findViewById(R.id.change_img_prototype);
        imgApart = (ImageView) findViewById(R.id.change_img_apart);
        layoutMain = (LinearLayout) findViewById(R.id.change_layout_main);
        rotationView = (RotationView)findViewById(R.id.change_rotate_view);
        Bitmap bitmaps[] = new Bitmap[]{BitmapFactory.decodeResource(getResources(), R.mipmap.pic_head),BitmapFactory.decodeResource(getResources(), R.mipmap.pic_head)};
//        rotationView.setStartDegrees(0);
//        rotationView.setDet(10);
        rotationView.setImgs(bitmaps);
//        layoutMain.addView(rotationView);

        sbZoom = (SeekBar) findViewById(R.id.change_sb_zoom);
        sbRotate = (SeekBar) findViewById(R.id.change_sb_rotate);
        imgPrototype.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float scale = (float) 400 / getWindowManager().getDefaultDisplay().getWidth();
                int x = (int) (motionEvent.getX() * scale);
                int y = (int) (motionEvent.getY() * scale);
                int width = (int) (100 * scale);
                int height = (int) (100 * scale);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgPrototype.getDrawable();
                imgApart.setImageBitmap(Bitmap.createBitmap(bitmapDrawable.getBitmap(), x, y, width, height));
                return false;
            }
        });
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        sbZoom.setMax(displayMetrics.widthPixels - minWidth);
        sbZoom.setOnSeekBarChangeListener(this);
        sbRotate.setOnSeekBarChangeListener(this);
        sbRotate.setMax(maxRotate);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        if (seekBar.getId() == R.id.change_sb_zoom) {
            int newWidth = progress + minWidth;
            int newHeight = newWidth;
            imgPrototype.setLayoutParams(new LinearLayout.LayoutParams(newWidth, newHeight));
            Toast.makeText(ChangeImageActivity.this, "Width:" + newWidth + "Height:" + newHeight, Toast.LENGTH_SHORT).show();
        }
        else if (seekBar.getId() == R.id.change_sb_rotate) {
//            BitmapDrawable bitmapDrawable = (BitmapDrawable)imgPrototype.getDrawable();
//            Bitmap bitmap = bitmapDrawable.getBitmap();
            Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.pic_head)).getBitmap();

            imgPrototype.setImageBitmap(createBitmapRotate(bitmap,progress));
//            imgPrototype.setImageBitmap(drawBitmapRotate(bitmap, progress));

            Toast.makeText(ChangeImageActivity.this, "rotate:" + progress, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * 利用Bitmap.createBitmap来旋转，该方法在部分手机上面会有黑边
     *
     * @param bm                 图像
     * @param orientationDegree  旋转角度
     * @return bitmap
     */
    private Bitmap createBitmapRotate(Bitmap bm, final int orientationDegree) {
        /**可以利用Matrix实现旋转、缩放、平移、斜切等高级功能
         1、平移：matrix.setTranslate(bitmap.getWidth() / 2, bitmap.getHeight() / 2);
         2、缩放：matrix.setScale(1.0f, 1.0f);// 和原来的大小一样
         3、斜切：matrix.setSkew(0.5f, 0.5f);
         4、旋转：matrix.postRotate(45.0f);// 旋转45度 == matrix.setSinCos(0.5f, 0.5f);//
         */
        Matrix matrix = new Matrix();
//            matrix.setScale(1.0f, 1.0f);// 和原来的大小一样
        matrix.setRotate(orientationDegree);
        Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        return bitmap;
    }

    /**
     * 利用Canvas.drawBitmap来旋转，该方法是围绕中心点旋转，但是比较适用于圆形图片的旋转
     *
     * @param bm                  图像
     * @param orientationDegree   旋转角度
     * @return bitmap
     */
    private Bitmap drawBitmapRotate(Bitmap bm, final int orientationDegree) {
        //利用Matrix实现图片旋转
//        Matrix m = new Matrix();
//        m.setRotate(orientationDegree, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
//        float targetX, targetY;
//        if (orientationDegree == 90) {
//            targetX = bm.getHeight();
//            targetY = 0;
//        } else {
//            targetX = bm.getHeight();
//            targetY = bm.getWidth();
//        }
//
//        final float[] values = new float[9];
//        m.getValues(values);
//
//        float x1 = values[Matrix.MTRANS_X];
//        float y1 = values[Matrix.MTRANS_Y];
//
////        m.postTranslate(targetX - x1, targetY - y1);
//
//        Bitmap bm1 = Bitmap.createBitmap(bm.getHeight(), bm.getWidth(), Bitmap.Config.ARGB_8888);
//
//        Paint paint = new Paint();
//        Canvas canvas = new Canvas(bm1);
//        canvas.drawBitmap(bm, m, paint);
//        return bm1;

        //利用canvas重绘实现旋转
        Bitmap bitmap = Bitmap.createBitmap(bm.getHeight(), bm.getWidth(), Bitmap.Config.ARGB_8888);

        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmap);
        int x = canvas.getWidth() / 2;
        int y = canvas.getHeight() / 2;
        canvas.translate(x, y);
        canvas.rotate(orientationDegree);
        canvas.translate(-x, -y);
        canvas.drawBitmap(bm, 0, 0,paint);
//        canvas.drawBitmap(bm, m, paint);
        return bitmap;
    }

    /**
     * @param bitmap
     * @return
     */
    private int getMaxDiameter(Bitmap bitmap) {
        int maxD = 0;
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int r = (int) Math.sqrt(w * w + h * h);
        if (r > maxD)
            maxD = r;
        return maxD;

    }
}
