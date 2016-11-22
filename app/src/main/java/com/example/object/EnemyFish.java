package com.example.object;

import android.content.res.Resources;
import android.graphics.Canvas;

/**
 * Created by Administrator on 2016/11/16.
 */
public class EnemyFish extends GameObject {

    protected int score;          //����ķ�ֵ?
    protected boolean isVisible;   //�����Ƿ�Ϊ�ɼ�״̬?

    public EnemyFish(Resources resources) {
        super(resources);
        initBitmap();
    }

    //��ʼ������?
    @Override
    public void initial(int arg0, float arg1, float arg2) {

    }

    //��ʼ��ͼƬ��Դ?
    @Override
    protected void initBitmap() {

    }

    //����Ļ�ͼ����?
    @Override
    public void drawSelf(Canvas canvas) {

    }

    //�ͷ���Դ
    @Override
    public void release() {

    }

    //������߼�����
    @Override
    public void logic() {
        super.logic();
        if (object_x < screen_width) {
            object_x += speed;
        }
        else {
            isAlive = false;
        }
        if(object_x + object_width > 0){
            isVisible = true;
        }
        else{
            isVisible = false;
        }
    }

    //�����ײ?
    @Override
    public boolean isCollide(GameObject obj) {
        // ����1λ�ھ���2�����?
        if (object_x <= obj.getObject_x()
                && object_x + object_width <= obj.getObject_x()) {
            return false;
        }
        // ����1λ�ھ���2���Ҳ�?
        else if (obj.getObject_x() <= object_x
                && obj.getObject_x() + obj.getObject_width() <= object_x) {
            return false;
        }
        // ����1λ�ھ���2���Ϸ�?
        else if (object_y <= obj.getObject_y()
                && object_y + object_height <= obj.getObject_y()) {
            return false;
        }
        // ����1λ�ھ���2���·�
        else if (obj.getObject_y() <= object_y
                && obj.getObject_y() + obj.getObject_height() <= object_y) {
            return false;
        }
        return true;
    }

    //�ж��ܷ񱻼����ײ
    public boolean isCanCollide(){
        // TODO Auto-generated method stub
        return isAlive && isVisible;
    }

    //getter��setter����
    public int getScore() {
        // TODO Auto-generated method stub
        return score;
    }
    public void setScore(int score) {
        // TODO Auto-generated method stub
        this.score = score;
    }
}
