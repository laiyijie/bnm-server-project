package cn.bangnongmang.admin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomNumPool {

	private List<Integer> pool;
	
	public RandomNumPool() {
		initPool();
	}
	
	private void initPool(){
		
		Integer[] integers = {10,12,14,16,20,30,40,49,11,13,10,10,10,10,10,10,10,10,10,10};
		pool = new ArrayList<Integer>();
		
		for (int i = 0; i < integers.length; i++) {
			pool.add(integers[i]);
		}
	}
	
	public Integer getNum(){
		
		int afterPoint = getRandomNum(0, 99);
		
		if (afterPoint>99) {
			afterPoint = 0;
		}
		
		if (pool.isEmpty()) {
			
			initPool();
		}
		
		int index = getRandomNum(0, pool.size());
		
		int before = pool.get(index)*100;
		
		pool.remove(index);
		
		return before+afterPoint;
	}
	
	private int getRandomNum(int min, int max){
		Random random = new Random();
		
		return random.nextInt(max) + min;
	}
	
}
