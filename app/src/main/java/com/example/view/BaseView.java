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
/*视图基类*/
public class BaseView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    protected int currentFrame;				// ��ǰ����֡
    protected float scalex;					//  ����ͼƬ�����ű���
    protected float scaley;
    protected float screen_width;			// ��ͼ�Ŀ��
    protected float screen_height;			// ��ͼ�ĸ߶�
    protected boolean threadFlag;			// �߳����еı��
    protected Paint paint; 					// ���ʶ���
    protected Canvas canvas; 				//  ��������
    protected Thread thread; 				//  ��ͼ�߳�
    protected SurfaceHolder sfh;
    protected MainActivity mainActivity;
    // 构�?�方�?
    public BaseView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.mainActivity = (MainActivity) context;
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
    }
    //��ͼ�ı�ķ���?
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }
    //  ��ͼ�����ķ���?
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        screen_width = this.getWidth();		//获得视图的宽�?
        screen_height = this.getHeight();	//获得视图的高�?
        threadFlag = true;
    }
    // ��ͼ���ٵķ���
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        threadFlag = false;
    }
    // ��ʼ��ͼƬ��Դ����?
    public void initBitmap() {}
    // �ͷ�ͼƬ��Դ�ķ���?
    public void release() {}
    // ��ͼ����
    public void drawSelf() {}
    // �߳����еķ���
    @Override
    public void run() {
        // TODO Auto-generated method stub

    }
    public void setThreadFlag(boolean threadFlag){
        this.threadFlag = threadFlag;
    }

}
