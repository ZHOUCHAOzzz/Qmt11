package com.miracle.qmt.contact;

public class PersonBean {

	private String Name;
	private String PinYin;
	private String FirstPinYin;
	private String id;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPinYin() {
		return PinYin;
	}

	public void setPinYin(String pinYin) {
		PinYin = pinYin;
	}

	public String getFirstPinYin() {
		return FirstPinYin;
	}

	public void setFirstPinYin(String firstPinYin) {
		FirstPinYin = firstPinYin;
	}

	public String toString() {
		return "������" + getName() + "   ƴ����" + getPinYin() + "    ����ĸ��"
				+ getFirstPinYin();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public PersonBean(String name, String id) {
        Name = name;
        this.id = id;
    }

    public PersonBean() {
    }
}
