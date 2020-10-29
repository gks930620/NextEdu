package com.next.siteanswer.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SiteanswerSearchVO {

	private int saSqNo;                /* 사이트 문의(부모) 글번호 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getSaSqNo() {
		return saSqNo;
	}

	public void setSaSqNo(int saSqNo) {
		this.saSqNo = saSqNo;
	}
	
}
