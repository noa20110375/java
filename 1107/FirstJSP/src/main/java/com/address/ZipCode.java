package com.address;

public class ZipCode {
	private String zipcode;
	private String sido;
	private String gugun;
	private String dong;
	private String bunji;
	private int seq;
	
	//setter, getter
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getSido() {
		return sido==null? "" : sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getGugun() {
		return gugun==null? "" : gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong ==null ? "" : dong;
	}
	public String getBunji() {
		return bunji==null ? "" : bunji.trim();
	}
	public void setBunji(String bunji) {
		this.bunji = bunji;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	

}
