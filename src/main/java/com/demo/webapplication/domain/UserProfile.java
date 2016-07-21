package com.demo.webapplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="USER_PROFILE")
public class UserProfile extends AbstractEntity {

	@Column(name="TYPE", length=15, unique=true, nullable=false)
	private String type = UserProfileType.USER.getUserProfileType();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public int hashCode() {
		return ((type == null) ? 0 : type.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserProfile))
			return false;
		UserProfile other = (UserProfile) obj;
		if (getId() != other.getId())
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + this.getId() + ",  type=" + type	+ "]";
	}
	

}
