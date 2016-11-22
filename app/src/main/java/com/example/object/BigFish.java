package com.example.object;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.eatfish.R;

import java.util.Random;

/**
 * Created by Administrator on 2016/11/16.
 */
public class BigFish extends EnemyFish {
    private static int currentCount = 0;   //����ǰ������
    private Bitmap bigFish;              //����ͼƬ
    public static int sumCount = 2;        //�����ܵ�����
    public BigFish(Resources resources) {
        super(resources);
        this.score = 800;
        this.setSize(8);
    }

    //��ʼ������

    @Override
    public void initial(int arg0, float arg1, float arg2) {
        isAlive = true;
        Random ran = new Random();
        speed = ran.nextInt(2) + 6 * arg0;
        object_y = ran.nextInt((int)(screen_height - object_height));
        object_x = -object_width * (currentCount*2 + 1);
        currentCount++;
        if(currentCount >= sumCount){
            currentCount = 0;
        }
    }


    //��ʼ��ͼƬ��Դ

    @Override
    protected void initBitmap() {
        bigFish = BitmapFactory.decodeResource(resources, R.drawable.bigfish);
        object_width = bigFish.getWidth();			//���ÿһ֡λͼ�Ŀ�
        object_height = bigFish.getHeight();		//���ÿһ֡λͼ�ĸ�
    }

    //����Ļ�ͼ����

    @Override
    public void drawSelf(Canvas canvas) {
        if(isAlive){
            if(isVisible){
                canvas.save();
                canvas.clipRect(object_x,object_y,object_x + object_width,object_y + object_height);
                canvas.drawBitmap(bigFish, object_x, object_y,paint);
                canvas.restore();
            }
            logic();
        }
        else{
            int y = (int) (currentFrame * object_height); // ��õ�ǰ֡�����λͼ��Y����
            canvas.save();
            canvas.clipRect(object_x,object_y,object_x + object_width,object_y + object_height);
                canvas.drawBitmap(bigFish, object_x, object_y - y,paint);
            canvas.restore();
            currentFrame++;
            if(currentFrame >= 4){
                currentFrame = 0;
                isAlive = false;
            }
        }
    }

    // �ͷ���Դ
    @Override
    public void release() {
        // TODO Auto-generated method stub
        if(!bigFish.isRecycled()){
            bigFish.recycle();
        }
    }
}