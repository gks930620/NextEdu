package com.next.classhomeworksanswer.vo;

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
import com.next.common.valid.ScoreType;
import com.next.common.vo.PagingVO;

@SuppressWarnings("serial")
public class ClasshomeworksanswerVO extends PagingVO implements Serializable {

	@NotNull(message = "글 번호가 비었습니다.", groups = ModifyType.class )
	@Positive(message = "글 번호가 비었습니다.", groups = {ModifyType.class} )
	private int chaNo;                 /* 과제 답 번호 */
	
	@NotBlank(message = "제목은 필수입니다.")
	@Size(min = 1, message = "최소 한 글자 이상 작성하세요")
	private String chaTitle;           /* 과제 답 제목 */
	
	@NotBlank(message = "내용은 필수입니다.")
	@Size(min = 1, message = "최소 한 글자 이상 입력하세요")
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
	
	// attaches
	private List<AttachVO> attaches;
	
	private int[] delAtchNos;
		
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

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

	public String getChaMemName() {
		return chaMemName;
	}

	public void setChaMemName(String chaMemName) {
		this.chaMemName = chaMemName;
	}
	
}
