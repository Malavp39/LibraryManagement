package com.demo.wrapper;

import org.apache.commons.lang.StringUtils;
import com.demo.model.User;


public class UserWrapper {

	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private Integer age;
	private String gender;
	private String phone;
	private String zip;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public User unwrap() {
		User user = new User();
		user.setAge(this.age);
		user.setFirstName(this.firstName);
		user.setMiddleName(StringUtils.isEmpty(this.middleName) ? "" : this.middleName);
		user.setGender(this.gender);
		user.setId(this.id);
		user.setLastName(this.lastName);
		user.setPhone(this.phone);
		user.setZip(StringUtils.isEmpty(this.zip) ? "" : this.zip);
		return user;
	}

	/*public User unwrapForUpdate(User user) {
		user.setAge(this.age);
		user.setFirstName(this.firstName);
		user.setGender(this.gender);
		user.setId(this.id);
		user.setLastName(this.lastName);
		user.setMiddleName(StringUtils.isEmpty(this.middleName) ? "" : this.middleName);
		user.setPhone(this.phone);
		user.setZip(StringUtils.isEmpty(this.zip) ? "" : this.zip);
		return user;
	}*/

	public void wrap(User user) {
		this.age = user.getAge();
		this.firstName = user.getFirstName();
		this.gender = user.getGender();
		this.id = user.getId();
		this.lastName = user.getLastName();
		this.middleName = StringUtils.isEmpty(user.getMiddleName()) ? "" : user.getMiddleName();
		this.phone = user.getPhone();
		this.zip = StringUtils.isEmpty(user.getZip()) ? "" : user.getZip();
	}

	@Override
	public String toString() {
		return "UserWrapper [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName + ", age=" + age + ", gender=" + gender + ", phone=" + phone + ", zip=" + zip + "]";
	}
}
