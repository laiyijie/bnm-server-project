package cn.bangnongmang.service.middle.order;

import java.util.HashMap;
import java.util.Map;

public class DefaultStateCollection<K, V> implements StateCollection<K, V> {

	private Map<K, V> stateMap = new HashMap<>();

	private Map<Class, V> classMap = new HashMap<>();

	@Override
	public V registerState(K key, V state) {
		stateMap.put(key, state);
		classMap.put(state.getClass(), state);
		return state;
	}

	@Override
	public Map<K, V> getStateMap() {
		return stateMap;
	}

	@Override
	public <T> V getState(Class<T> clazz) {
		return classMap.get(clazz);
	}

}
