package com.example.object;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.eatfish.R;

import java.util.Random;

/**
 * Created by Administrator on 2016/11/17.
 */
public class BigFishRight extends EnemyFish {
    private static int currentCount = 0;   //对象当前的数量
    private Bitmap bigFishRight;              //对象图片
    public static int sumCount = 2;        //对象总的数量
    public BigFishRight(Resources resources) {
        super(resources);
        this.score = 800;
        this.setSize(8);
    }

    //初始化数据

    @Override
    public void initial(int arg0, float arg1, float arg2) {
        isAlive = true;
        Random ran = new Random();
        speed = ran.nextInt(2) + 6 * arg0;
        object_y = ran.nextInt((int)(screen_height - object_height));
        object_x = screen_width + object_width * (currentCount*2 + 1);
        currentCount++;
        if(currentCount >= sumCount){
            currentCount = 0;
        }
    }


    //初始化图片资源

    @Override
    protected void initBitmap() {
        bigFishRight = BitmapFactory.decodeResource(resources, R.drawable.bigfishright);
        object_width = bigFishRight.getWidth();			//获得每一帧位图的宽
        object_height = bigFishRight.getHeight();		//获得每一帧位图的高
    }

    //对象的绘图函数

    @Override
    public void drawSelf(Canvas canvas) {
        if(isAlive){
            if(isVisible){
                canvas.save();
                canvas.clipRect(object_x,object_y,object_x + object_width,object_y + object_height);
                canvas.drawBitmap(bigFishRight, object_x, object_y,paint);
                canvas.restore();
            }
            logic();
        }
        else{
            int y = (int) (currentFrame * object_height); // 获得当前帧相对于位图的Y坐标
            canvas.save();
            canvas.clipRect(object_x,object_y,object_x + object_width,object_y + object_height);
            canvas.drawBitmap(bigFishRight, object_x, object_y - y,paint);
            canvas.restore();
            currentFrame++;
            if(currentFrame >= 4){
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

    // 释放资源
    @Override
    public void release() {
        // TODO Auto-generated method stub
        if(!bigFishRight.isRecycled()){
            bigFishRight.recycle();
        }
    }
}
