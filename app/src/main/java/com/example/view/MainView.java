package com.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.constant.ConstantUtil;
import com.example.eatfish.R;
import com.example.factory.GameObjectFactory;
import com.example.object.BigFish;
import com.example.object.BigFishRight;
import com.example.object.EnemyFish;
import com.example.object.GameObject;
import com.example.object.MiddleFish;
import com.example.object.MiddleFishRight;
import com.example.object.MyFish;
import com.example.object.SmallFish;
import com.example.object.SmallFishRight;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/11/14.
 */
public class MainView extends BaseView {
    private int middleFishScore;     //������Ļ���
    private int bigFishScore;      //������Ļ���
    private int sumScore = 0;        //��Ϸ�ܵ÷�
    private int speedTime;        //��Ϸ�ٶȵı���
    private float bg_y;             //ͼƬ������
    private float bg_y2;
    private float play_bt_w;
    private float play_bt_h;
    private boolean isPlay;       //�����Ϸ����״̬
    private boolean isTouchFish;  //�ж�����Ƿ�����Ļ
    private Bitmap background;      //����ͼƬ
    private Bitmap playButton;
    private MyFish myFish;
    private List<EnemyFish> enemyFishs;
    private GameObjectFactory factory;
    private float last_x = 0;
    private float last_y = 0;

    public MainView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        isPlay = true;
        speedTime = 1;
        factory = new GameObjectFactory();    //������
        enemyFishs = new ArrayList<EnemyFish>();
        myFish = (MyFish) factory.createMyFish(getResources());   //������ҵ���
        myFish.setMainView(this);
        for (int i = 0; i < SmallFish.sumCount; i++) {
            //����С��
            SmallFish smallFish = (SmallFish) factory.createSmallFish(getResources());
            enemyFishs.add(smallFish);
        }
        for (int i = 0; i < MiddleFish.sumCount; i++) {
            //��������
            MiddleFish middleFish = (MiddleFish) factory.createMiddleFish(getResources());
            enemyFishs.add(middleFish);
        }
        for (int i = 0; i < BigFish.sumCount; i++) {
            //��������
            BigFish bigFish = (BigFish) factory.createBigFish(getResources());
            enemyFishs.add(bigFish);
        }
        for (int i = 0; i < BigFishRight.sumCount; i++) {
            //�����ұߴ���
            BigFishRight bigFishRight = (BigFishRight) factory.createBigFishRight(getResources());
            enemyFishs.add(bigFishRight);
        }
        for (int i = 0; i < MiddleFishRight.sumCount; i++) {
            //�����ұ�����
            MiddleFishRight middleFishRight = (MiddleFishRight) factory.createMiddleFishRight(getResources());
            enemyFishs.add(middleFishRight);
        }
        for (int i = 0; i < SmallFishRight.sumCount; i++) {
            //�����ұ�С��
            SmallFishRight smallFishRight = (SmallFishRight) factory.createSmallFishRight(getResources());
            enemyFishs.add(smallFishRight);
        }
        thread = new Thread(this);
    }

    // ��ͼ�ı�ķ���
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        super.surfaceChanged(arg0, arg1, arg2, arg3);
    }

    // ��ͼ�����ķ���
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        super.surfaceCreated(arg0);
        initBitmap(); // ��ʼ��ͼƬ��Դ
        for (GameObject obj : enemyFishs) {
            obj.setScreenWH(screen_width, screen_height);
        }
        myFish.setScreenWH(screen_width, screen_height);
        myFish.setAlive(true);
        if (thread.isAlive()) {
            thread.start();
        } else {
            thread = new Thread(this);
            thread.start();
        }
    }

    // ��ͼ���ٵķ���
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        super.surfaceDestroyed(arg0);
        release();
    }

    // ��Ӧ�����¼��ķ���
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            isTouchFish = false;
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            last_x = x;
            last_y = y;

            if (x < myFish.getObject_x())
                myFish.setDirection(1);
            if (x >= myFish.getObject_x())
                myFish.setDirection(0);
            if (x > 10 && x < 10 + play_bt_w && y > 10 && y < 10 + play_bt_h) {
                if (isPlay) {
                    isPlay = false;
                } else {
                    isPlay = true;
                    synchronized (thread) {
                        thread.notify();
                    }
                }
                return true;
            } else {
                move(x, y);
                return true;
            }

        }
        //��Ӧ��ָ����Ļ�ƶ����¼�
        else if (event.getAction() == MotionEvent.ACTION_MOVE && event.getPointerCount() == 1) {
            //�жϴ������Ƿ�Ϊ��ҵķɻ�
            float x = event.getX();
            float y = event.getY();
            last_x = last_y = 0;
            if (x < myFish.getObject_x())
                myFish.setDirection(1);
            if (x >= myFish.getObject_x())
                myFish.setDirection(0);
            if (x > myFish.getMiddle_x() + 20) {
                if (myFish.getMiddle_x() + myFish.getSpeed() <= screen_width) {
                    myFish.setMiddle_x(myFish.getMiddle_x() + myFish.getSpeed());
                }
            } else if (x < myFish.getMiddle_x() - 20) {
                if (myFish.getMiddle_x() - myFish.getSpeed() >= 0) {
                    myFish.setMiddle_x(myFish.getMiddle_x() - myFish.getSpeed());
                }
            }
            if (y > myFish.getMiddle_y() + 20) {
                if (myFish.getMiddle_y() + myFish.getSpeed() <= screen_height) {
                    myFish.setMiddle_y(myFish.getMiddle_y() + myFish.getSpeed());
                }
            } else if (y < myFish.getMiddle_y() - 20) {
                if (myFish.getMiddle_y() - myFish.getSpeed() >= 0) {
                    myFish.setMiddle_y(myFish.getMiddle_y() - myFish.getSpeed());
                }
            }
            return true;
        }
        return false;
    }

    public void move(float x,float y){
        //����
        if(x < myFish.getMiddle_x()){
            myFish.setMiddle_x(myFish.getMiddle_x() - myFish.getSpeed());
            if(myFish.getMiddle_x() < x)
                myFish.setMiddle_x(x);
        }

        //����
        if(x > myFish.getMiddle_x()){
            myFish.setMiddle_x(myFish.getMiddle_x() + myFish.getSpeed());
            if(x < myFish.getMiddle_x())
                myFish.setMiddle_x(x);
        }

        //����
        if(y < myFish.getMiddle_y()){
            myFish.setMiddle_y(myFish.getMiddle_y() - myFish.getSpeed());
            if(myFish.getMiddle_y() < y)
                myFish.setMiddle_y(y);
        }

        //����
        if(y > myFish.getMiddle_y()){
            myFish.setMiddle_y(myFish.getMiddle_y() + myFish.getSpeed());
            if(y < myFish.getMiddle_y())
                myFish.setMiddle_y(y);
        }
    }


    // ��ʼ��ͼƬ��Դ����
    @Override
    public void initBitmap() {
        // TODO Auto-generated method stub
        playButton = BitmapFactory.decodeResource(getResources(), R.drawable.play);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.bg_01);
        scalex = screen_width / background.getWidth();
        scaley = screen_height / background.getHeight();
        play_bt_w = playButton.getWidth();
        play_bt_h = playButton.getHeight() / 2;
        bg_y = 0;
        bg_y2 = bg_y - screen_height;
    }

    //��ʼ����Ϸ����
    public void initObject() {
        for (EnemyFish obj : enemyFishs) {
            //��ʼ��С����	
            if (obj instanceof SmallFish) {
                if (!obj.isAlive()) {
                    obj.initial(speedTime, 0, 0);
                    break;
                }
            }
            //��ʼ��������
            else if (obj instanceof MiddleFish) {
                if (!obj.isAlive()) {
                    obj.initial(speedTime, 0, 0);
                    break;
                }
            }
            //��ʼ��������
            else if (obj instanceof BigFish) {
                if (!obj.isAlive()) {
                    obj.initial(speedTime, 0, 0);
                    break;
                }
            }
            //��ʼ���ұߴ�����
            else if (obj instanceof BigFishRight) {
                if (!obj.isAlive()) {
                    obj.initial(speedTime, 0, 0);
                    break;
                }
            }
            //��ʼ���ұ�������
            else if (obj instanceof MiddleFishRight) {
                if (!obj.isAlive()) {
                    obj.initial(speedTime, 0, 0);
                    break;
                }
            }
            //��ʼ���ұ�С����
            else if (obj instanceof SmallFishRight) {
                if (!obj.isAlive()) {
                    obj.initial(speedTime, 0, 0);
                    break;
                }
            }
        }
        //�����ȼ�
        if (sumScore >= speedTime * 3000 && speedTime < 6) {
            speedTime++;
        }
    }

    // �ͷ�ͼƬ��Դ�ķ���
    @Override
    public void release() {
        // TODO Auto-generated method stub
        for (GameObject obj : enemyFishs) {
            obj.release();
        }
        myFish.release();

        if (!playButton.isRecycled()) {
            playButton.recycle();
        }
        if (!background.isRecycled()) {
            background.recycle();
        }
    }

    // ��ͼ����
    @Override
    public void drawSelf() {
        // TODO Auto-generated method stub
        try {
            canvas = sfh.lockCanvas();
            canvas.drawColor(Color.BLACK); // ���Ʊ���ɫ
            canvas.save();
            // ���㱳��ͼƬ����Ļ�ı���
            canvas.scale(scalex, scaley, 0, 0);
            canvas.drawBitmap(background, 0, bg_y, paint);   // ���Ʊ���ͼ
            canvas.restore();
            //���ư�ť
            canvas.save();
            canvas.clipRect(10, 10, 10 + play_bt_w, 10 + play_bt_h);
            if (isPlay) {
                canvas.drawBitmap(playButton, 10, 10, paint);
            } else {
                canvas.drawBitmap(playButton, 10, 10 - play_bt_h, paint);
            }
            canvas.restore();

            //������
            for (EnemyFish obj : enemyFishs) {
                if (obj.isAlive()) {
                    obj.drawSelf(canvas);
                    //������Ƿ�����ҵķɻ���ײ					
                    if (obj.isCanCollide() && myFish.isAlive()) {
                        if (obj.isCollide(myFish) && obj.getSize() > myFish.getSize() / 10) {
                            myFish.setAlive(false);
                        } else if (obj.isCollide(myFish) && obj.getSize() <= myFish.getSize() / 10) {
                            obj.setAlive(false);
                            myFish.setSize(myFish.getSize() + 1);
                            if (obj instanceof BigFish || obj instanceof BigFishRight) {
                                sumScore += 800;
                            }
                            if (obj instanceof MiddleFish || obj instanceof MiddleFishRight) {
                                sumScore += 300;
                            }
                            if (obj instanceof SmallFish || obj instanceof SmallFishRight) {
                                sumScore += 100;
                            }
                        }
                    }
                }
            }
            if (!myFish.isAlive()) {
                threadFlag = false;
            }
            myFish.drawSelf(canvas);    //������ҵķɻ�
            //���ƻ�������
            paint.setTextSize(30);
            paint.setColor(Color.rgb(235, 161, 1));
            canvas.drawText("����:" + String.valueOf(sumScore), 30 + play_bt_w, 40, paint);        //��������
            canvas.drawText("�ȼ� X " + String.valueOf(speedTime), screen_width - 220, 40, paint); //��������
            canvas.drawText("������:" + (myFish.getSize()-20),screen_width - 220,80,paint);
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            if (canvas != null)
                sfh.unlockCanvasAndPost(canvas);
        }
    }

    // ������Ϸ�����ķ���
    public void addGameScore(int score) {
        middleFishScore += score;    // ������Ļ���
        bigFishScore += score;        // ������Ļ���
        sumScore += score;            // ��Ϸ�ܵ÷�
    }

    // �߳����еķ���
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (threadFlag) {
            long startTime = System.currentTimeMillis();
            initObject();
            drawSelf();
            if(last_x != 0 && last_y !=0){
                if(last_x != myFish.getMiddle_x() || last_y != myFish.getMiddle_y())
                    move(last_x,last_y);
            }
            long endTime = System.currentTimeMillis();
            if (!isPlay) {
                synchronized (thread) {
                    try {
                        thread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                if (endTime - startTime < 100)
                    Thread.sleep(100 - (endTime - startTime));
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = ConstantUtil.TO_END_VIEW;
        message.arg1 = Integer.valueOf(sumScore);
        mainActivity.getHandler().sendMessage(message);
    }
}
