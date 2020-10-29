package com.next.classquestion.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.next.attach.vo.AttachVO;

@SuppressWarnings("serial")
public class ClassquestionVO implements Serializable {

	private int cqNo;                  /* 클래스 문의 번호 */
	
	@NotBlank(message = "제목은 필수입니다.")
	@Size(min = 1, message = "최소 한 글자 이상 작성하세요.")
	private String cqTitle;            /* 클래스 문의 제목 */
	
	@NotBlank(message = "내용은 필수입니다.")
	@Size(min = 1, message = "최소 한 글자 이상 입력하세요.")
	private String cqContent;          /* 클래스 문의 내용 */
	private String cqRegDate;          /* 클래스 문의 등록일 */
	private String cqModDate;          /* 클래스 문의 수정일 */
	private String cqAnswerYn;         /* 클래스 문의 답변 여부 */
	private String cqAttaches;         /* 클래스 문의 첨부파일 */
	
	@NotBlank(message = "카테고리는 필수입니다.")
	private String cqCategory;         /* 클래스 문의 카테고리 */
	
	private String cqMemId;            /* 클래스 문의 작성자 아이디 */
	
	private String caMemId;
	private String caContent;
	private String caRegDate;
	
	// 테이블 컬럼 이외에 멤버 변수들
	private List<AttachVO> attaches;		// 첨부파일보관용
	private String cqMemName;		// 질문자 이름
	private String caMemName;		// 답변자 이름
	
	
	public String getCaMemId() {
		return caMemId;
	}

	public void setCaMemId(String caMemId) {
		this.caMemId = caMemId;
	}

	public String getCaContent() {
		return caContent;
	}

	public void setCaContent(String caContent) {
		this.caContent = caContent;
	}

	public String getCaRegDate() {
		return caRegDate;
	}

	public void setCaRegDate(String caRegDate) {
		this.caRegDate = caRegDate;
	}

	public String getCqMemName() {
		return cqMemName;
	}

	public void setCqMemName(String cqMemName) {
		this.cqMemName = cqMemName;
	}

	public String getCaMemName() {
		return caMemName;
	}

	public void setCaMemName(String caMemName) {
		this.caMemName = caMemName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getCqNo() {
		return cqNo;
	}

	public void setCqNo(int cqNo) {
		this.cqNo = cqNo;
	}

	public String getCqTitle() {
		return cqTitle;
	}

	public void setCqTitle(String cqTitle) {
		this.cqTitle = cqTitle;
	}

	public String getCqContent() {
		return cqContent;
	}

	public void setCqContent(String cqContent) {
		this.cqContent = cqContent;
	}

	public String getCqRegDate() {
		return cqRegDate;
	}

	public void setCqRegDate(String cqRegDate) {
		this.cqRegDate = cqRegDate;
	}

	public String getCqModDate() {
		return cqModDate;
	}

	public void setCqModDate(String cqModDate) {
		this.cqModDate = cqModDate;
	}

	public String getCqAnswerYn() {
		return cqAnswerYn;
	}

	public void setCqAnswerYn(String cqAnswerYn) {
		this.cqAnswerYn = cqAnswerYn;
	}

	public String getCqAttaches() {
		return cqAttaches;
	}

	public void setCqAttaches(String cqAttaches) {
		this.cqAttaches = cqAttaches;
	}

	public String getCqCategory() {
		return cqCategory;
	}

	public void setCqCategory(String cqCategory) {
		this.cqCategory = cqCategory;
	}

	public String getCqMemId() {
		return cqMemId;
	}

	public void setCqMemId(String cqMemId) {
		this.cqMemId = cqMemId;
	}

	public List<AttachVO> getAttaches() {
		return attaches;
	}

	public void setAttaches(List<AttachVO> attaches) {
		this.attaches = attaches;
	}

	

}
