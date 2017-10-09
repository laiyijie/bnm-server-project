package cn.bangnongmang.data.combo.domain;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.data.domain.AccountPortrait;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.StarUser;

public class UserBasicInfoShow {
	private Long id;
	private Account account;
	private AccountGeo accountGeo;
	private AccountRealNameAuth accountRealNameAuth;
	private AccountPortrait accountPortrait;
	private StarUser starUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AccountGeo getAccountGeo() {
		return accountGeo;
	}

	public void setAccountGeo(AccountGeo accountGeo) {
		this.accountGeo = accountGeo;
	}

	public AccountRealNameAuth getAccountRealNameAuth() {
		return accountRealNameAuth;
	}

	public void setAccountRealNameAuth(AccountRealNameAuth accountRealNameAuth) {
		this.accountRealNameAuth = accountRealNameAuth;
	}

	public AccountPortrait getAccountPortrait() {
		return accountPortrait;
	}

	public void setAccountPortrait(AccountPortrait accountPortrait) {
		this.accountPortrait = accountPortrait;
	}

	public StarUser getStarUser() {
		return starUser;
	}

	public void setStarUser(StarUser starUser) {
		this.starUser = starUser;
	}

	@Override
	public String toString() {
		return "UserBasicInfoShow [id=" + id + ", account=" + account + ", accountGeo=" + accountGeo
				+ ", accountRealNameAuth=" + accountRealNameAuth + ", accountPortrait=" + accountPortrait
				+ ", starUser=" + starUser + "]";
	}

}
