package com.example.quotes;

import java.util.HashMap;

public class QuotesConstants {
	private static  QuotesConstants constants=new QuotesConstants();
	public HashMap<String, LeaderHelper> hashMap=new HashMap<String, LeaderHelper>();

	private QuotesConstants(){
	}
	
	public static QuotesConstants getInstance(){
		return constants;	
	}
	

}
