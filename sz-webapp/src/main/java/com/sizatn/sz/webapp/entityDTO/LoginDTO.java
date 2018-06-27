package com.sizatn.sz.webapp.entityDTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginDTO implements Serializable {

	private static final long serialVersionUID = 4158609667236748612L;

	private String username;
	private String password;

}
