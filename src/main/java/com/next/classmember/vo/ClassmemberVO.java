package com.next.classmember.vo;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

@SuppressWarnings("serial")
public class ClassmemberVO implements Serializable {

	private String memId;              /* 아이디 */
	private String memPass;            /* 비밀번호 */
	private String memName;            /* 이름 */
	private String memSex;             /* 성별 */
	private String memBir;             /* 생년월일 */
	private String memZip;             /* 우편번호 */
	private String memAdd1;            /* 주소 */
	private String memAdd2;            /* 상세주소 */
	private String memHp;              /* 핸드폰번호 */
	private String memMail;            /* 메일 */
	private String memTli;             /* 교원자격증 */
	private String memRole;            /* 권한 */
	private String memScid;            /* 학교 아이디 */
	private String memGrade;           /* 학년 */
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
	
	
}
