package com.example.object;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.eatfish.R;

import java.util.Random;

/**
 * Created by Administrator on 2016/11/18.
 */
public class SmallFishRight extends EnemyFish {
    private static int currentCount = 0;   //����ǰ������
    private Bitmap smallFishRight;              //����ͼƬ
    public static int sumCount = 8;        //�����ܵ�����

    public SmallFishRight(Resources resources) {
        super(resources);
        this.score = 100;
        this.setSize(1);
    }
    //��ʼ������
    @Override
    public void initial(int arg0, float arg1, float arg2) {
        isAlive = true;
        Random ran = new Random();
        speed = ran.nextInt(8) + 8 * arg0;
        object_y = ran.nextInt((int)(screen_height - object_height));
        object_x = screen_width + object_width * (currentCount * 2 + 1);
        currentCount++;
        if(currentCount >= sumCount)
            currentCount = 0;
    }
    //��ʼ��ͼƬ��Դ
    @Override
    protected void initBitmap() {
        smallFishRight = BitmapFactory.decodeResource(resources, R.drawable.smallfishright);
        object_width = smallFishRight.getWidth();
        object_height = smallFishRight.getHeight();
    }

    //����Ļ�ͼ����

    @Override
    public void drawSelf(Canvas canvas) {
        //�ж����Ƿ���
        if (isAlive) {
            if (isVisible) {
                canvas.save();
                canvas.clipRect(object_x, object_y, object_x + object_width, object_y + object_height);
                canvas.drawBitmap(smallFishRight, object_x, object_y, paint);
                canvas.restore();
            }
            logic();
        } else {
            int y = (int) (currentFrame * object_height); // ��õ�ǰ֡�����λͼ��Y����
            canvas.save();
            canvas.clipRect(object_x, object_y, object_x + object_width, object_y + object_height);
            canvas.drawBitmap(smallFishRight, object_x, object_y - y, paint);
            canvas.restore();
            currentFrame++;
            if (currentFrame >= 3) {
                currentFrame = 0;
                isAlive = false;
            }
        }
    }

    @Override
    public void logic() {
        if (object_x > -object_width) {
            isVisible = true;
            object_x -= speed;
        }
        else {
            isAlive = false;
            isVisible = false;
        }

    }

    // �ͷ���Դ
    @Override
    public void release() {
        // TODO Auto-generated method stub
        if(!smallFishRight.isRecycled()){
            smallFishRight.recycle();
        }
    }

}
