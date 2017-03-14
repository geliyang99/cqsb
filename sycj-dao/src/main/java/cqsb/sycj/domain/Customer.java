package cqsb.sycj.domain;

import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import cqsb.tech.domain.base.BaseEntity;


@SuppressWarnings("serial")
@Table(name="t_customer")
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends BaseEntity{

	@Column(length = 32)
	private String name;
	
	@Column(length = 32)
	private String password;
	
	@Column
	private String headpic;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeadpic() {
		return headpic;
	}

	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}
	
	
}
