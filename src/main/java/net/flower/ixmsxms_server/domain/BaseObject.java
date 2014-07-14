package net.flower.ixmsxms_server.domain;

import java.io.Serializable;

import net.flower.ixmsxms_server.utils.CoreUtil;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@SuppressWarnings("serial")
public class BaseObject implements Serializable {
	private Integer page;
	private Integer count;
	private Integer start;
	
	public Integer getPage() {
		if (this.page == null) {
			this.page = 1;
		}
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getCount() {
		if (this.count == null) {
			this.count = 10;
		}
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getStart() {
		if (this.start == null) {
			this.start = 0;
		} else {
			this.start = this.getCount() * (this.getPage() - 1);
		}
		return this.start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}
	
	public String toJSONString() {
		return CoreUtil.toJsonPrettyString(this);
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}