package com.example.coffeequotes;

import java.util.HashMap;

public class QuotesConstants {
	private static  QuotesConstants constants=new QuotesConstants();
	public HashMap<String, LeaderHelper> hashMap=new HashMap<String, LeaderHelper>();
	public HashMap<String, LeaderHelper> hashMap20=new HashMap<String, LeaderHelper>();
	public HashMap<String, FilmyHelper> hashMapfilmy=new HashMap<String, FilmyHelper>();

	private QuotesConstants(){
	}
	
	public static QuotesConstants getInstance(){
		return constants;	
	}
	

}
