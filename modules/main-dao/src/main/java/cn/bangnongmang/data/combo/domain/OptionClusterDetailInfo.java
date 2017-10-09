package cn.bangnongmang.data.combo.domain;

import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionDetail;

public class OptionClusterDetailInfo {
	private Long id;
	private OptionCluster optionCluster;
	private OptionDetail optionDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OptionCluster getOptionCluster() {
		return optionCluster;
	}

	public void setOptionCluster(OptionCluster optionCluster) {
		this.optionCluster = optionCluster;
	}

	public OptionDetail getOptionDetail() {
		return optionDetail;
	}

	public void setOptionDetail(OptionDetail optionDetail) {
		this.optionDetail = optionDetail;
	}

}
