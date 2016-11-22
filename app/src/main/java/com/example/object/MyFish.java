package com.example.object;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.eatfish.R;
import com.example.factory.GameObjectFactory;
import com.example.interfaces.IMyFish;
import com.example.view.MainView;

/**
 * Created by Administrator on 2016/11/14.
 */
public class MyFish extends GameObject implements IMyFish{
    private float middle_x;         //�����������
    private float middle_y;
    private float scale = 1;
    private boolean tag = true;



    private int direction =0 ;    //0�����ң�1������


    private long startTime;          //��ʼ��ʱ��
    private long endTime;           //������ʱ��?
    private Bitmap myfish,myfish1;          //���ͼƬ
    private MainView mainView;
    private GameObjectFactory factory;



    public MyFish(Resources resources) {
        super(resources);
        initBitmap();
        this.speed = 30;
        this.setSize(20);
        factory = new GameObjectFactory();
    }

    public void setMainView(MainView mainView){
        this.mainView = mainView;
    }

    // ������Ļ��Ⱥ͸߶�
    @Override
    public void setScreenWH(float screen_width, float screen_height) {
        super.setScreenWH(screen_width, screen_height);
        object_x = screen_width/2 - object_width/2;
        object_y = screen_height/2 - object_height/2;
        middle_x = object_x + object_width/2;
        middle_y = object_y + object_height/2;
    }
    // ��ʼ��ͼƬ��Դ��
    @Override
    protected void initBitmap() {
        myfish = BitmapFactory.decodeResource(resources, R.drawable.myfish);
        myfish1 = BitmapFactory.decodeResource(resources,R.drawable.myfish1);
        object_width = myfish.getWidth();   //���ÿһ֡λͼ�Ŀ�?
        object_height = myfish.getHeight();  //���ÿһ֡λͼ�ĸ�?
    }

    // ����Ļ�ͼ����?
    @Override
    public void drawSelf(Canvas canvas) {
        if(isAlive){
            //int x = (int) (currentFrame * object_width); // ��õ�ǰ֡�����λͼ��X����
            canvas.save();
            if((getSize()+1)/10 > getSize()/10 && tag){
                scale += 0.1f;
                object_x += object_width + object_x * scale;
                object_y += object_height + object_y * scale;
                tag = false;
            }else if((getSize()+1)/10 == getSize()/10 && !tag){
                tag = true;
            }
            canvas.scale(scale,scale,middle_x,middle_y);
            canvas.clipRect(object_x, object_y, object_x + object_width, object_y + object_height);
            if(direction == 0)
                canvas.drawBitmap(myfish, object_x, object_y, paint);
            else
                canvas.drawBitmap(myfish1,object_x,object_y,paint);
            canvas.restore();
            currentFrame++;
            if (currentFrame >= 2) {
                currentFrame = 0;
            }
        }
    }

    //�ͷ���Դ�ķ���
    @Override
    public void release() {
        if(!myfish.isRecycled()){
            myfish.recycle();
        }
    }

    @Override
    public float getMiddle_x() {
        return middle_x;
    }

    @Override
    public void setMiddle_x(float middle_x) {
        this.middle_x = middle_x;
        this.object_x = middle_x - object_width/2;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public float getMiddle_y() {
        return middle_y;
    }

    @Override
    public void setMiddle_y(float middle_y) {
        this.middle_y = middle_y;
        this.object_y = middle_y - object_height/2;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
