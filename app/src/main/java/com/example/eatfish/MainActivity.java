package com.example.eatfish;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.example.constant.ConstantUtil;
import com.example.view.EndView;
import com.example.view.MainView;
import com.example.view.ReadyView;

public class MainActivity extends Activity {
    private EndView endView;
    private MainView mainView;
    private ReadyView readyView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == ConstantUtil.TO_MAIN_VIEW){
                toMainView();
            }
            else  if(msg.what == ConstantUtil.TO_END_VIEW){
                toEndView(msg.arg1);
            }
            else  if(msg.what == ConstantUtil.END_GAME){
                endGame();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        readyView = new ReadyView(this);
        setContentView(readyView);

    }
    //��ʾ��Ϸ��������
    public void toMainView(){
        if(mainView == null){
            mainView = new MainView(this);
        }
        setContentView(mainView);
        readyView = null;
        endView = null;
    }
    //��ʾ��Ϸ�����Ľ���?
    public void toEndView(int score){
        if(endView == null){
            endView = new EndView(this);
            endView.setScore(score);
        }
        setContentView(endView);
        mainView = null;
    }
    //������Ϸ
    public void endGame(){
        if(readyView != null){
            readyView.setThreadFlag(false);
        }
        else if(mainView != null){
            mainView.setThreadFlag(false);
        }
        else if(endView != null){
            endView.setThreadFlag(false);
        }
        this.finish();
    }
    //getter��setter����
    public Handler getHandler() {
        return handler;
    }
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
