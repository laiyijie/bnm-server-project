package cn.bangnongmang.admin.util;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

public class PageResult<T> implements Serializable {

	private Integer totalPage;

	private Integer currentPage;

	private Long totalItem;

	private Integer pageSize;

	private List<T> result;

	public PageResult(){
		
	}
	
	public PageResult(Page<T> pages) {
		
		this.setCurrentPage(pages.getPageNum());
		this.setPageSize(pages.getPageSize());
		this.setResult((List<T>) pages);
		this.setTotalItem(pages.getTotal());
		this.setTotalPage(pages.getPages());
		
	}
	
	public PageResult(Page<?> pages,List<T> result){
		this.setCurrentPage(pages.getPageNum());
		this.setPageSize(pages.getPageSize());
		this.setResult(result);
		this.setTotalItem(pages.getTotal());
		this.setTotalPage(pages.getPages());
	}
	
	public PageResult(PageResult<?> pages,List<T> result){
		this.setCurrentPage(pages.getCurrentPage());
		this.setPageSize(pages.getPageSize());
		this.setResult(result);
		this.setTotalItem(pages.getTotalItem());
		this.setTotalPage(pages.getTotalPage());
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Long getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Long totalItem) {
		this.totalItem = totalItem;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "PageResult [totalPage=" + totalPage + ", currentPage=" + currentPage + ", totalItem=" + totalItem
				+ ", pageSize=" + pageSize + ", result=" + result + "]";
	}

}
