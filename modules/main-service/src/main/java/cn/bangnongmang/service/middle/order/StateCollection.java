package cn.bangnongmang.service.middle.order;

import java.util.Map;

public interface StateCollection<K, V> {
	V registerState(K key, V state);

	Map<K, V> getStateMap();

	<T> V getState(Class<T> clazz);
}
