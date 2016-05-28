package com.yiping.util;

public class StringUtil {
	
	public static String toUnderscore(String s){
		String regex = "([A-Z])";
        String replacement = "_$1";
        s = s.replaceAll(regex, replacement).toUpperCase();
        if(s.startsWith("_")){
        	s = s.substring(1);
        }
        return s;
	}
	
	public static String toCamelCase(String s){
		   String[] parts = s.split("_");
		   String camelCaseString = "";
		   for (String part : parts){
		      camelCaseString = camelCaseString + toProperCase(part);
		   }
		   return camelCaseString.substring(0, 1).toLowerCase() + 
				   camelCaseString.substring(1);
	}
	
	public static String toProperCase(String s) {
	    return s.substring(0, 1).toUpperCase() +
	               s.substring(1).toLowerCase();
	}
	
	public static void main(String[] args){
		System.out.println(toCamelCase("CUS_NAME"));
	}
}
