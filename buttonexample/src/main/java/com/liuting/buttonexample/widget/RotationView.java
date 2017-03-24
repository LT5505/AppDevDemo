package com.liuting.buttonexample.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

import com.liuting.buttonexample.R;

/**
 * Package:com.liuting.buttonexample.widget
 * author:liuting
 * Date:2017/3/24
 * Desc:实现旋转去黑边的图像效果的自定义的View
 */

public class RotationView extends View {
    /**
     * 二分之一
     */
    private final float HALF = (float) 0.5;
    private Context mContext;
    private Bitmap[] bitmaps;
    private int canvasWidth = 0;
    private int canvasHeight = 0;
    private int centerX = 0;
    private int centerY = 0;
    private int testPic = 0;

    /**
     * 开始角度
     */
    private float startDegrees = 0;

    /**
     * 每张图片夹角
     */
    private float det = 20;
    /**
     * 创建新画布的直径
     */
    private int r;
    /**
     * 创建一个空白的 Bitmap，需要指定长和宽
     */
    private Bitmap bitmap;
    /**
     * 创建新的画布
     */
    private Canvas myCanvas;
    /**
     * 画笔
     */
    private Paint paint;

    public RotationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.myRoute);
        startDegrees = a.getFloat(R.styleable.myRoute_startDegrees, 0);
        det = a.getFloat(R.styleable.myRoute_det, 20);
        testPic = a.getResourceId(R.styleable.myRoute_pic, R.mipmap.ic_launcher);

        bitmaps = new Bitmap[3];
        bitmaps[0] = BitmapFactory.decodeResource(mContext.getResources(), testPic);
        bitmaps[1] = BitmapFactory.decodeResource(mContext.getResources(), testPic);
        bitmaps[2] = BitmapFactory.decodeResource(mContext.getResources(), testPic);

        a.recycle();

        init();
    }

    public RotationView(Context context) {
        super(context);
        this.mContext = context;
        bitmaps = new Bitmap[3];
        bitmaps[0] = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
        bitmaps[1] = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
        bitmaps[2] = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
        init();
    }

    private void init() {

        r = getMaxDiameter();
        bitmap = Bitmap.createBitmap(r, r, Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas(bitmap);
        // 抗锯齿
        PaintFlagsDrawFilter filter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        myCanvas.setDrawFilter(filter);

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

    }

    /**
     * 得到旋转的最大直径
     */
    private int getMaxDiameter() {
        int size = bitmaps.length;
        int maxD = 0;

        for (int i = 0; i < size; i++) {
            Bitmap bt = bitmaps[i];
            int w = bt.getWidth();
            int h = bt.getHeight();
            int r = (int) Math.sqrt(w * w + h * h);
            if (r > maxD)
                maxD = r;
        }

        return maxD;

    }

    // @Override
    protected void onDraw(Canvas canvas) {

        canvasWidth = getWidth();
        canvasHeight = getHeight();
        centerX = (int) (HALF * canvasWidth);
        centerY = (int) (HALF * canvasHeight);

        int size = bitmaps.length;

        for (int i = 0; i < size; i++) {
            Bitmap bt = bitmaps[i];
            int w = bt.getWidth();
            int h = bt.getHeight();
            myCanvas.save();
            myCanvas.translate(r / 2, r / 2);
            myCanvas.rotate(startDegrees + i * det);
            myCanvas.drawBitmap(bt, -w / 2, -h / 2, paint);
            myCanvas.restore();
        }

        if (r > canvasWidth || r > canvasHeight)
            bitmap = big(bitmap, canvasWidth, canvasHeight);

        int x = centerX - bitmap.getWidth() / 2;
        int y = centerY - bitmap.getHeight() / 2;
        // canvas.drawColor(Color.BLUE);
        canvas.drawBitmap(bitmap, x, y, null);
        // canvas.drawLine(0, canvasHeight / 2, canvasWidth, canvasHeight / 2,
        // paint);
        // canvas.drawLine(canvasWidth / 2, 0, canvasWidth / 2, canvasHeight,
        // paint);
        // canvas.drawLine(0, canvasHeight - 1, canvasWidth, canvasHeight - 1,
        // paint);

    }

    /**
     * 缩放图片
     *
     * @param b
     *            原图bitmap
     * @param x
     *            缩放宽度
     * @param y
     *            缩放高度
     * @return
     */
    public Bitmap big(Bitmap b, float x, float y) {
        int w = b.getWidth();
        int h = b.getHeight();
        float sx = (float) x / w;
        float sy = (float) y / h;

        if (x < y && w > x) {
            sy = sx;
        }

        if (x > y && w > y) {
            sx = sy;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(sx, sy); // 长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(b, 0, 0, w, h, matrix, true);

        return resizeBmp;
    }

    /**
     * 设置旋转的图片
     *
     * @param bitmaps
     */
    public void setImgs(Bitmap[] bitmaps) {
        if (bitmaps != null && bitmaps.length > 0) {
            this.bitmaps = bitmaps;
            init();
            this.invalidate();
        }
    }

    /**
     * 设置开始角度
     *
     * @param startDegrees
     */
    public void setStartDegrees(float startDegrees) {
        this.startDegrees = startDegrees;
    }

    /**
     * 设置相邻每张图片夹角
     *
     * @param det
     */
    public void setDet(float det) {
        this.det = det;
    }
}
