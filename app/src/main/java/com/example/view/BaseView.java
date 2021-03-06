package com.example.view;

/**
 * Created by Administrator on 2016/11/14.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.eatfish.MainActivity;
/*瑙嗗浘鍩虹被*/
public class BaseView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    protected int currentFrame;				// 当前动画帧
    protected float scalex;					//  背景图片的缩放比例
    protected float scaley;
    protected float screen_width;			// 视图的宽度
    protected float screen_height;			// 视图的高度
    protected boolean threadFlag;			// 线程运行的标记
    protected Paint paint; 					// 画笔对象
    protected Canvas canvas; 				//  画布对象
    protected Thread thread; 				//  绘图线程
    protected SurfaceHolder sfh;
    protected MainActivity mainActivity;
    // 鏋勯?犳柟娉?
    public BaseView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.mainActivity = (MainActivity) context;
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
    }
    //视图改变的方法?
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }
    //  视图创建的方法?
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        screen_width = this.getWidth();		//鑾峰緱瑙嗗浘鐨勫搴?
        screen_height = this.getHeight();	//鑾峰緱瑙嗗浘鐨勯珮搴?
        threadFlag = true;
    }
    // 视图销毁的方法
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        threadFlag = false;
    }
    // 初始化图片资源方法?
    public void initBitmap() {}
    // 释放图片资源的方法?
    public void release() {}
    // 绘图方法
    public void drawSelf() {}
    // 线程运行的方法
    @Override
    public void run() {
        // TODO Auto-generated method stub

    }
    public void setThreadFlag(boolean threadFlag){
        this.threadFlag = threadFlag;
    }

}
