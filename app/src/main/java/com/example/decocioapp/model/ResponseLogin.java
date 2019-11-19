package com.example.decocioapp.model;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("hasil")
	private String hasil;

	public void setHasil(String hasil){
		this.hasil = hasil;
	}

	public String getHasil(){
		return hasil;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLogin{" + 
			"hasil = '" + hasil + '\'' + 
			"}";
		}
}