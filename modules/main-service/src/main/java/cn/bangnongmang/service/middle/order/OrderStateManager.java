package cn.bangnongmang.service.middle.order;

public interface OrderStateManager<T> {
	void setCurrentState(T state);
}
