package com.appspot.bdaywisher.datastore;

import java.util.Calendar;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/*
 * @author Shravana Aadith <aaadith@gmail.com>
 */


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Birthday {
	
	
	public Birthday(String name, String nickname, String email, int birthDate,
			int birthMonth) {
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.birthDate = birthDate;
		this.birthMonth = birthMonth;
		//this.key = KeyFactory.createKey(Birthday.class.getSimpleName(), email);
	}

	
	
	public Birthday() {
		
	}



	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	String name;
	
	@Persistent
	String nickname;
	
	@Persistent
	String email;
	

	@Persistent
	int birthDate;
	
	@Persistent
	int birthMonth;
	

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(int birthDate) {
		this.birthDate = birthDate;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}


}
