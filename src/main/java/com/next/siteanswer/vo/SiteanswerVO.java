package com.next.siteanswer.vo;


import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
public class SiteanswerVO implements Serializable {

	private int saNo;                  /* 사이트 답변 글번호 */
	private int saSqNo;                /* 사이트 문의(부모) 글번호 */
	
	@NotBlank(message = "내용을 입력해주세요")
	@Size(min = 1, message = "최소 한글자 이상 입력해주세요")
	private String saContent;          /* 사이트 답변 내용 */
	
	private String saRegDate;          /* 사이트 답변 등록일 */
	private String saModDate;          /* 사이트 답변 수정일 */
	private String saAttaches;         /* 사이트 답변 첨부파일 */
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getSaNo() {
		return saNo;
	}

	public void setSaNo(int saNo) {
		this.saNo = saNo;
	}

	public int getSaSqNo() {
		return saSqNo;
	}

	public void setSaSqNo(int saSqNo) {
		this.saSqNo = saSqNo;
	}

	public String getSaContent() {
		return saContent;
	}

	public void setSaContent(String saContent) {
		this.saContent = saContent;
	}

	public String getSaRegDate() {
		return saRegDate;
	}

	public void setSaRegDate(String saRegDate) {
		this.saRegDate = saRegDate;
	}

	public String getSaModDate() {
		return saModDate;
	}

	public void setSaModDate(String saModDate) {
		this.saModDate = saModDate;
	}

	public String getSaAttaches() {
		return saAttaches;
	}

	public void setSaAttaches(String saAttaches) {
		this.saAttaches = saAttaches;
	}
	
	
}
