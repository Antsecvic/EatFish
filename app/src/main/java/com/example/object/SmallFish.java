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
public class SmallFish extends EnemyFish {
    private static int currentCount = 0;   //对象当前的数量
    private Bitmap smallFish;              //对象图片
    public static int sumCount = 8;        //对象总的数量

    public SmallFish(Resources resources) {
        super(resources);
        this.score = 100;
        this.setSize(1);
    }
    //初始化数据
    @Override
    public void initial(int arg0, float arg1, float arg2) {
        isAlive = true;
        Random ran = new Random();
        speed = ran.nextInt(8) + 8 * arg0;
        object_y = ran.nextInt((int)(screen_height - object_height));
        object_x = -object_width * (currentCount * 2 + 1);
        currentCount++;
        if(currentCount >= sumCount)
            currentCount = 0;
    }
    //初始化图片资源
    @Override
    protected void initBitmap() {
        smallFish = BitmapFactory.decodeResource(resources, R.drawable.smallfish);
        object_width = smallFish.getWidth();
        object_height = smallFish.getHeight();
    }

    //对象的绘图函数

    @Override
    public void drawSelf(Canvas canvas) {
        //判断鱼是否存活
        if (isAlive) {
            if (isVisible) {
                canvas.save();
                canvas.clipRect(object_x, object_y, object_x + object_width, object_y + object_height);
                canvas.drawBitmap(smallFish, object_x, object_y, paint);
                canvas.restore();
            }
            logic();
        } else {
            int y = (int) (currentFrame * object_height); // 获得当前帧相对于位图的Y坐标
            canvas.save();
            canvas.clipRect(object_x, object_y, object_x + object_width, object_y + object_height);
            canvas.drawBitmap(smallFish, object_x, object_y - y, paint);
            canvas.restore();
            currentFrame++;
            if (currentFrame >= 3) {
                currentFrame = 0;
                isAlive = false;
            }
        }
    }

    // 释放资源
    @Override
    public void release() {
        // TODO Auto-generated method stub
        if(!smallFish.isRecycled()){
            smallFish.recycle();
        }
    }

}
