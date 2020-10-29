package com.next.classhomeworks.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.next.attach.vo.AttachVO;
import com.next.common.valid.ModifyType;


@SuppressWarnings("serial")
public class ClasshomeworksVO implements Serializable {

	// classhomeworks
	
	@NotNull(message = "글 번호가 비었습니다.", groups = ModifyType.class )
	@Positive(message = "글 번호가 비었습니다.", groups = {ModifyType.class} )
	private int chNo;                  /* 과제 번호 */
	
	@NotBlank(message = "제목은 필수입니다.")
	@Size(min = 1, message = "최소 한 글자 이상 작성하세요.")
	private String chTitle;            /* 과제 제목 */
	
	@NotBlank(message = "내용은 필수입니다.")
	@Size(min = 1, message = "최소 한 글자 이상 입력하세요.")
	private String chContent;          /* 과제 내용 */
	
	private String chRegDate;          /* 과제 등록일 */
	private String chModDate;          /* 과제 수정일 */
	
	@NotBlank(message = "마감일은 필수입니다. yyyy-mm-dd 형식으로 작성하세요.")
	private String chDeadline;         /* 과제 제출일 */
	
	@NotBlank(message = "과목 선택은 필수입니다.")
	private String chCategory;         /* 과제 카테고리 */
	private String chMemId;            /* 멤버 아이디 */
	private String chMemName;          /* 멤버 이름 */
	
	// attaches
	private List<AttachVO> attaches;
	private int[] delAtchNos;
	
	// classhomeworks answer
	private int chaNo;                 /* 과제 답 번호 */
	private String chaTitle;           /* 과제 답 제목 */
	private String chaContent;         /* 과제 답 내용 */
	private String chaRegDate;         /* 과제 답 등록일 */
	private String chaModDate;         /* 과제 답 수정일 */
	private String chaDeadline;        /* 과제 답 제출일 */
	private String chaAttaches;        /* 과제 답 첨부파일 */
	private String chaSubmitYn;        /* 과제 제출여부 */
	private int chaScore;              /* 과제 점수 */
	private String chaMemId;           /* 멤버 아이디 */
	private String chaMemName;         /* 멤버 이름 */
	private String chaCategory;        /* 과제 카테고리 */
	
	private int chaChNo;               /* 과제(부모) 번호 */
	
	
	// classhomeworks
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getChNo() {
		return chNo;
	}

	public void setChNo(int chNo) {
		this.chNo = chNo;
	}

	public String getChTitle() {
		return chTitle;
	}

	public void setChTitle(String chTitle) {
		this.chTitle = chTitle;
	}

	public String getChContent() {
		return chContent;
	}

	public void setChContent(String chContent) {
		this.chContent = chContent;
	}

	public String getChRegDate() {
		return chRegDate;
	}

	public void setChRegDate(String chRegDate) {
		this.chRegDate = chRegDate;
	}

	public String getChModDate() {
		return chModDate;
	}

	public void setChModDate(String chModDate) {
		this.chModDate = chModDate;
	}

	public String getChDeadline() {
		return chDeadline;
	}

	public void setChDeadline(String chDeadline) {
		this.chDeadline = chDeadline;
	}
	
	public String getChCategory() {
		return chCategory;
	}

	public void setChCategory(String chCategory) {
		this.chCategory = chCategory;
	}

	public String getChMemId() {
		return chMemId;
	}

	public void setChMemId(String chMemId) {
		this.chMemId = chMemId;
	}
	
	public String getChMemName() {
		return chMemName;
	}

	public void setChMemName(String chMemName) {
		this.chMemName = chMemName;
	}
	
	
	
	// classhomeworks answer
	public int getChaNo() {
		return chaNo;
	}

	public void setChaNo(int chaNo) {
		this.chaNo = chaNo;
	}

	public String getChaTitle() {
		return chaTitle;
	}

	public void setChaTitle(String chaTitle) {
		this.chaTitle = chaTitle;
	}

	public String getChaContent() {
		return chaContent;
	}

	public void setChaContent(String chaContent) {
		this.chaContent = chaContent;
	}

	public String getChaRegDate() {
		return chaRegDate;
	}

	public void setChaRegDate(String chaRegDate) {
		this.chaRegDate = chaRegDate;
	}

	public String getChaModDate() {
		return chaModDate;
	}

	public void setChaModDate(String chaModDate) {
		this.chaModDate = chaModDate;
	}

	public String getChaDeadline() {
		return chaDeadline;
	}

	public void setChaDeadline(String chaDeadline) {
		this.chaDeadline = chaDeadline;
	}

	public String getChaAttaches() {
		return chaAttaches;
	}

	public void setChaAttaches(String chaAttaches) {
		this.chaAttaches = chaAttaches;
	}

	public String getChaSubmitYn() {
		return chaSubmitYn;
	}

	public void setChaSubmitYn(String chaSubmitYn) {
		this.chaSubmitYn = chaSubmitYn;
	}

	public int getChaScore() {
		return chaScore;
	}

	public void setChaScore(int chaScore) {
		this.chaScore = chaScore;
	}

	public String getChaMemId() {
		return chaMemId;
	}

	public void setChaMemId(String chaMemId) {
		this.chaMemId = chaMemId;
	}

	public String getChaMemName() {
		return chaMemName;
	}

	public void setChaMemName(String chaMemName) {
		this.chaMemName = chaMemName;
	}
	
	public String getChaCategory() {
		return chaCategory;
	}

	public void setChaCategory(String chaCategory) {
		this.chaCategory = chaCategory;
	}

	public int getChaChNo() {
		return chaChNo;
	}

	public void setChaChNo(int chaChNo) {
		this.chaChNo = chaChNo;
	}

	public int[] getDelAtchNos() {
		return delAtchNos;
	}

	public void setDelAtchNos(int[] delAtchNos) {
		this.delAtchNos = delAtchNos;
	}

	public List<AttachVO> getAttaches() {
		return attaches;
	}

	public void setAttaches(List<AttachVO> attaches) {
		this.attaches = attaches;
	}
}
