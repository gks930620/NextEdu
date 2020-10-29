package com.next.sitequestion.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.next.attach.vo.AttachVO;
import com.next.common.valid.ModifyType;
import com.next.common.valid.RegistType;
import com.next.siteanswer.vo.SiteanswerVO;

@SuppressWarnings("serial")
public class SitequestionVO implements Serializable {

	/* @Valid 어노테이션을 이용하면 @Validated 혹은 @Validated(Default.class)와 동일 */
	
	private int sqNo;                  /* 사이트1:1 번호 */
	
	@NotBlank(message = "제목을 입력해주세요")
	@Size(min = 1, message = "최소 한글자 이상 입력해주세요")
	private String sqTitle;            /* 사이트1:1 제목 */
	
	@NotBlank(message = "내용을 입력해주세요")
	@Size(min = 1, message = "최소 한글자 이상 입력해주세요")
	private String sqContent;          /* 사이트1:1 내용 */
	
	private String sqRegDate;          /* 사이트1:1 등록일 */
	private String sqModDate;          /* 사이트1:1 수정일 */
	private String sqAnswerYn;         /* 사이트1:1 답변여부 */
	private String sqAttaches;         /* 사이트1:1 첨부파일 */
	
	@NotBlank(message = "분류는 필수입니다")
	private String sqCategory;         /* 사이트1:1 카테고리 */
	private String sqMemId;            /* 사이트1:1 작성자 아이디 */
	private String sqWriter;           /* 사이트1:1 작성자 이름 */
	
	// 1:1 관계
	private SiteanswerVO siteanswerVO;
	
	// attaches
	private List<AttachVO> attaches;
	
	private int[] delAtchNos;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getSqNo() {
		return sqNo;
	}

	public void setSqNo(int sqNo) {
		this.sqNo = sqNo;
	}

	public String getSqTitle() {
		return sqTitle;
	}

	public void setSqTitle(String sqTitle) {
		this.sqTitle = sqTitle;
	}

	public String getSqContent() {
		return sqContent;
	}

	public void setSqContent(String sqContent) {
		this.sqContent = sqContent;
	}

	public String getSqRegDate() {
		return sqRegDate;
	}

	public void setSqRegDate(String sqRegDate) {
		this.sqRegDate = sqRegDate;
	}

	public String getSqModDate() {
		return sqModDate;
	}

	public void setSqModDate(String sqModDate) {
		this.sqModDate = sqModDate;
	}

	public String getSqAnswerYn() {
		return sqAnswerYn;
	}

	public void setSqAnswerYn(String sqAnswerYn) {
		this.sqAnswerYn = sqAnswerYn;
	}

	public String getSqAttaches() {
		return sqAttaches;
	}

	public void setSqAttaches(String sqAttaches) {
		this.sqAttaches = sqAttaches;
	}

	public String getSqCategory() {
		return sqCategory;
	}

	public void setSqCategory(String sqCategory) {
		this.sqCategory = sqCategory;
	}

	public String getSqMemId() {
		return sqMemId;
	}

	public void setSqMemId(String sqMemId) {
		this.sqMemId = sqMemId;
	}

	public SiteanswerVO getSiteanswerVO() {
		return siteanswerVO;
	}

	public void setSiteanswerVO(SiteanswerVO siteanswerVO) {
		this.siteanswerVO = siteanswerVO;
	}

	public String getSqWriter() {
		return sqWriter;
	}

	public void setSqWriter(String sqWriter) {
		this.sqWriter = sqWriter;
	}

	public List<AttachVO> getAttaches() {
		return attaches;
	}

	public void setAttaches(List<AttachVO> attaches) {
		this.attaches = attaches;
	}

	public int[] getDelAtchNos() {
		return delAtchNos;
	}

	public void setDelAtchNos(int[] delAtchNos) {
		this.delAtchNos = delAtchNos;
	}
	
	
}
