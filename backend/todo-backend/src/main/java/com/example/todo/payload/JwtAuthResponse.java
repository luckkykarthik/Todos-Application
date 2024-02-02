package com.example.todo.payload;

public class  JwtAuthResponse {
	private String token;
	private String tokenType="Bearer";
	public JwtAuthResponse(String token) {
		this.token = token;
	}
	public String getToken() {
		return token;
	}
	public String getTokenType() {
		return tokenType;
	}
	@Override
	public String toString() {
		return "JwtAuthResponse [token=" + token + ", tokenType=" + tokenType + "]";
	}

}
