package com.example.factory;

import android.content.res.Resources;

import com.example.object.BigFish;
import com.example.object.BigFishRight;
import com.example.object.GameObject;
import com.example.object.MiddleFish;
import com.example.object.MiddleFishRight;
import com.example.object.MyFish;
import com.example.object.SmallFish;
import com.example.object.SmallFishRight;

/*游戏对象的工厂类*/
public class GameObjectFactory {
	//创建小型敌机的方法
	public GameObject createSmallFish(Resources resources){
		return new SmallFish(resources);
	}
	//创建中型敌机的方法
	public GameObject createMiddleFish(Resources resources){
		return new MiddleFish(resources);
	}
	//创建大型敌机的方法
	public GameObject createBigFish(Resources resources){
		return new BigFish(resources);
	}
	//创建玩家飞机的方法
	public GameObject createMyFish(Resources resources){
		return new MyFish(resources);
	}

	public GameObject createBigFishRight(Resources resources){return new BigFishRight(resources);}
	public GameObject createMiddleFishRight(Resources resources){return new MiddleFishRight(resources);}
	public GameObject createSmallFishRight(Resources resources){return new SmallFishRight(resources);}
}
