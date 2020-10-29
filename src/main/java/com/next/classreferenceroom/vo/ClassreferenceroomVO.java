
package com.next.classreferenceroom.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.next.attach.vo.AttachVO;

@SuppressWarnings("serial")
public class ClassreferenceroomVO implements Serializable {

	private int crNo;                  /* 학습자료 번호 */
	
	@NotBlank(message = "제목은 필수입니다.")
	@Size(min = 1, message = "최소 한 글자 이상 작성하세요.")
	private String crTitle;            /* 학습자료 제목 */
	
	@NotBlank(message = "내용은 필수입니다.")
	@Size(min = 1, message = "최소 한 글자 이상 입력하세요.")
	private String crContent;          /* 학습자료 내용 */
	
	private String crRegDate;          /* 학습자료 등록일 */
	private String crModDate;          /* 학습자료 수정일 */
	private String crAttaches;         /* 학습자료 첨부파일 */
	
	@NotBlank(message = "카테고리는 필수입니다.")
	private String crCategory;         /* 학습자료 카테고리 */
	
	private String crMemId;            /* 학습자료 작성자 아이디 */
	private List<AttachVO> attaches;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getCrNo() {
		return crNo;
	}

	public void setCrNo(int crNo) {
		this.crNo = crNo;
	}

	public String getCrTitle() {
		return crTitle;
	}

	public void setCrTitle(String crTitle) {
		this.crTitle = crTitle;
	}

	public String getCrContent() {
		return crContent;
	}

	public void setCrContent(String crContent) {
		this.crContent = crContent;
	}

	public String getCrRegDate() {
		return crRegDate;
	}

	public void setCrRegDate(String crRegDate) {
		this.crRegDate = crRegDate;
	}

	public String getCrModDate() {
		return crModDate;
	}

	public void setCrModDate(String crModDate) {
		this.crModDate = crModDate;
	}

	public String getCrAttaches() {
		return crAttaches;
	}

	public void setCrAttaches(String crAttaches) {
		this.crAttaches = crAttaches;
	}

	public String getCrCategory() {
		return crCategory;
	}

	public void setCrCategory(String crCategory) {
		this.crCategory = crCategory;
	}

	public String getCrMemId() {
		return crMemId;
	}

	public void setCrMemId(String crMemId) {
		this.crMemId = crMemId;
	}

	public List<AttachVO> getAttaches() {
		return attaches;
	}

	public void setAttaches(List<AttachVO> attaches) {
		this.attaches = attaches;
	}

	
	
	
}
