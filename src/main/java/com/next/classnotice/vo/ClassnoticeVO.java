package com.next.classnotice.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.next.attach.vo.AttachVO;


@SuppressWarnings("serial")
public class ClassnoticeVO implements Serializable {

	private int cnNo;                  /* 클래스 공지사항 번호 */
	
	@NotBlank(message = "제목은 필수입니다.")
	@Size(min = 1, message = "최소 한 글자 이상 작성하세요.")
	private String cnTitle;            /* 클래스 공지사항 제목 */
	
	@NotBlank(message = "내용은 필수입니다.")
	@Size(min = 1, message = "최소 한 글자 이상 입력하세요.")
	private String cnContent;          /* 클래스 공지사항 내용 */
	
	private String cnRegDate;          /* 클래스 공지사항 등록일 */
	private String cnModDate;          /* 클래스 공지사항 수정일 */
	private String cnTopYn;            /* 중요공지 여부 */
	private String cnAttaches;         /* 클래스 공지사항 첨부파일 */
	
	@NotBlank(message = "카테고리는 필수입니다.")
	private String cnCategory;         /* 클래스 공지사항 카테고리 */
	
	private String cnMemId;            /* 클래스 공지사항 작성자 아이디 */

	private List<AttachVO> attacheVO ;  /* 첨부파일 리스트 */
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getCnNo() {
		return cnNo;
	}

	public void setCnNo(int cnNo) {
		this.cnNo = cnNo;
	}

	public String getCnTitle() {
		return cnTitle;
	}

	public void setCnTitle(String cnTitle) {
		this.cnTitle = cnTitle;
	}

	public String getCnContent() {
		return cnContent;
	}

	public void setCnContent(String cnContent) {
		this.cnContent = cnContent;
	}

	public String getCnRegDate() {
		return cnRegDate;
	}

	public void setCnRegDate(String cnRegDate) {
		this.cnRegDate = cnRegDate;
	}

	public String getCnModDate() {
		return cnModDate;
	}

	public void setCnModDate(String cnModDate) {
		this.cnModDate = cnModDate;
	}

	public String getCnTopYn() {
		return cnTopYn;
	}

	public void setCnTopYn(String cnTopYn) {
		this.cnTopYn = cnTopYn;
	}

	public String getCnAttaches() {
		return cnAttaches;
	}

	public void setCnAttaches(String cnAttaches) {
		this.cnAttaches = cnAttaches;
	}

	public String getCnCategory() {
		return cnCategory;
	}

	public void setCnCategory(String cnCategory) {
		this.cnCategory = cnCategory;
	}

	public String getCnMemId() {
		return cnMemId;
	}

	public void setCnMemId(String cnMemId) {
		this.cnMemId = cnMemId;
	}

	public List<AttachVO> getAttacheVO() {
		return attacheVO;
	}

	public void setAttacheVO(List<AttachVO> attacheVO) {
		this.attacheVO = attacheVO;
	}
	
	
}
