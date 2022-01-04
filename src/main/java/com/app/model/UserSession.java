package com.app.model;

import com.app.entity.bean.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSession {
	private User user;
	private String currentLangCode;
}
