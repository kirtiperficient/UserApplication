package com.perfi.UserApplication.homecontroller;

public class RegisterModel {
	private String email;
	private String psw;
	private String pswrepeat;
	private String remember;

	public RegisterModel(String email, String psw, String pswrepeat, String remember) {
		super();
		this.email = email;
		this.psw = psw;
		this.pswrepeat = pswrepeat;
		this.remember = remember;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getPswrepeat() {
		return pswrepeat;
	}

	public void setPswrepeat(String pswrepeat) {
		this.pswrepeat = pswrepeat;
	}

	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}

}
