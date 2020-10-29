package com.next.sitenotice.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.next.common.valid.ModifyType;

@SuppressWarnings("serial")
public class SitenoticeVO implements Serializable {

	@NotNull(message = "글 번호가 비었습니다.", groups = ModifyType.class )
	@Positive(message = "글 번호가 비었습니다.", groups = {ModifyType.class} )
	private int snNo;                  /* 공지사항 번호 */
	
	@NotBlank(message = "제목은 필수입니다.")
	@Size(min = 1, message = "최소 한 글자 이상 작성하세요")
	private String snTitle;            /* 공지사항 제목 */
	
	@NotBlank(message = "내용은 필수입니다.")
	@Size(min = 1, message = "최소 한 글자 이상 입력하세요")
	private String snContent;          /* 공지사항 내용 */
	
	private String snRegDate;          /* 공지사항 등록일 */
	private String snModDate;          /* 공지사항 수정일 */
	
	private String snTopYn;            /* 중요공지 여부 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getSnNo() {
		return snNo;
	}

	public void setSnNo(int snNo) {
		this.snNo = snNo;
	}

	public String getSnTitle() {
		return snTitle;
	}

	public void setSnTitle(String snTitle) {
		this.snTitle = snTitle;
	}

	public String getSnContent() {
		return snContent;
	}

	public void setSnContent(String snContent) {
		this.snContent = snContent;
	}

	public String getSnRegDate() {
		return snRegDate;
	}

	public void setSnRegDate(String snRegDate) {
		this.snRegDate = snRegDate;
	}

	public String getSnModDate() {
		return snModDate;
	}

	public void setSnModDate(String snModDate) {
		this.snModDate = snModDate;
	}

	public String getSnTopYn() {
		return snTopYn;
	}

	public void setSnTopYn(String snTopYn) {
		this.snTopYn = snTopYn;
	}
	
	
}
