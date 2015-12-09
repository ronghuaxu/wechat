package com.hdu.bean;

import com.hdu.wechat.mainchat;

public class User {
    private String name;
    private Integer open_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOpen_id() {
		return open_id;
	}

	public void setOpen_id(Integer open_id) {
		this.open_id = open_id;
	}

	

    public User(String name, Integer age) {
        super();
        this.name = name;
        this.open_id = age;
    }

    public User() {
        super();
    }
    
   
}
