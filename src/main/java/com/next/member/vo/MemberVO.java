
package com.next.member.vo;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;




@SuppressWarnings("serial")
public class MemberVO implements Serializable {

	@NotEmpty(message = "아이디는 필수입니다")	
	private String memId;              /* 아이디 */
	
	@NotBlank(message = "비밀번호는 필수입니다")
	private String memPass;            /* 비밀번호 */
	
	@NotBlank(message = "이름은 필수입니다")
	private String memName;            /* 이름 */
	
	@NotBlank(message="성별은 필수입니다")
	private String memSex;             /* 성별 */
	
	@NotBlank(message="생년월일은 필수입니다")
	private String memBir;             /* 생년월일 */
	
	
	private String memZip;             /* 우편번호 */
	
	
	@NotBlank(message = "주소는 필수입니다")
	private String memAdd1;            /* 주소 */
	
	@NotBlank(message = "상세주소는 필수입니다")
	private String memAdd2;            /* 상세주소 */
	

	@NotBlank(message = "핸드폰번호는 필수입니다")
	private String memHp;              /* 핸드폰번호 */
	
	@NotBlank(message = "이메일은 필수입니다.")
	@Email(message = "이메일 형식이 아닙니다."	)	
	private String memMail;            /* 메일 */
	
	//@NotBlank
	//@Pattern(regexp = "Y",message="메일인증해라")
	private String emailCheck;		 /* email 인증 절차 */
	
	@NotBlank
	@Pattern(regexp = "Y",message="중복체크해라")
	private String idCheck;			/* idCheck 인증 절차 */ 
	
	
	private String memTli;             /* 교원자격증 */
	
	
	private String memRole;            /* 권한 */
	
	@NotBlank(message = "학교ID는 필수입니다")
	private String memScid;            /* 학교 아이디 */
	
	
	private String memScnm;   			 /* 학교 이름 */
	
	
	@NotBlank(message = "학년은 필수입니다")
	private String memGrade;           /* 학년 */
	
	@NotBlank(message = "반은 필수입니다")
	private String memClass;           /* 반 */
	
	
	private int memStuno;              /* 학생 번호 */
	
	
	private String memPnm;             /* 부모님 이름 */
	
	
	private String memPhp;             /* 부모님 번호 */
	
	
	private String memGrantYn;         /* 학생 승인 여부 */
	
	
	
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemSex() {
		return memSex;
	}

	public void setMemSex(String memSex) {
		this.memSex = memSex;
	}

	public String getMemBir() {
		return memBir;
	}

	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}

	public String getMemZip() {
		return memZip;
	}

	public void setMemZip(String memZip) {
		this.memZip = memZip;
	}

	public String getMemAdd1() {
		return memAdd1;
	}

	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}

	public String getMemAdd2() {
		return memAdd2;
	}

	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}

	public String getMemHp() {
		return memHp;
	}

	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}

	public String getMemMail() {
		return memMail;
	}

	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}

	public String getMemTli() {
		return memTli;
	}

	public void setMemTli(String memTli) {
		this.memTli = memTli;
	}

	public String getMemRole() {
		return memRole;
	}

	public void setMemRole(String memRole) {
		this.memRole = memRole;
	}

	public String getMemScid() {
		return memScid;
	}

	public void setMemScid(String memScid) {
		this.memScid = memScid;
	}

	public String getMemGrade() {
		return memGrade;
	}

	public void setMemGrade(String memGrade) {
		this.memGrade = memGrade;
	}

	public String getMemClass() {
		return memClass;
	}

	public void setMemClass(String memClass) {
		this.memClass = memClass;
	}

	public int getMemStuno() {
		return memStuno;
	}

	public void setMemStuno(int memStuno) {
		this.memStuno = memStuno;
	}

	public String getMemPnm() {
		return memPnm;
	}

	public void setMemPnm(String memPnm) {
		this.memPnm = memPnm;
	}

	public String getMemPhp() {
		return memPhp;
	}

	public void setMemPhp(String memPhp) {
		this.memPhp = memPhp;
	}

	public String getMemGrantYn() {
		return memGrantYn;
	}

	public void setMemGrantYn(String memGrantYn) {
		this.memGrantYn = memGrantYn;
	}

	public String getMemScnm() {
		return memScnm;
	}

	public void setMemScnm(String memScnm) {
		this.memScnm = memScnm;
	}

	public String getEmailCheck() {
		return emailCheck;
	}

	public void setEmailCheck(String emailCheck) {
		this.emailCheck = emailCheck;
	}

	public String getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(String idCheck) {
		this.idCheck = idCheck;
	}
	
	
}
