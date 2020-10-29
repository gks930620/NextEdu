package com.next.sitequestion.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.next.common.vo.PagingVO;

@SuppressWarnings("serial")
public class SitequestionSearchVO extends PagingVO {

	private String sitequestionSearchType;
		
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getSitequestionSearchType() {
		return sitequestionSearchType;
	}

	public void setSitequestionSearchType(String sitequestionSearchType) {
		this.sitequestionSearchType = sitequestionSearchType;
	}
}
