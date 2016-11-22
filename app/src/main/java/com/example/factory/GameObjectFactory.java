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

/*��Ϸ����Ĺ�����*/
public class GameObjectFactory {
	//����С�͵л��ķ���
	public GameObject createSmallFish(Resources resources){
		return new SmallFish(resources);
	}
	//�������͵л��ķ���
	public GameObject createMiddleFish(Resources resources){
		return new MiddleFish(resources);
	}
	//�������͵л��ķ���
	public GameObject createBigFish(Resources resources){
		return new BigFish(resources);
	}
	//������ҷɻ��ķ���
	public GameObject createMyFish(Resources resources){
		return new MyFish(resources);
	}

	public GameObject createBigFishRight(Resources resources){return new BigFishRight(resources);}
	public GameObject createMiddleFishRight(Resources resources){return new MiddleFishRight(resources);}
	public GameObject createSmallFishRight(Resources resources){return new SmallFishRight(resources);}
}
