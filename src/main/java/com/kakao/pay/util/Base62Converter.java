package com.kakao.pay.util;

public class Base62Converter {
	
    private final int SHORTENING_KEY_SIZE=7;
    
    public String convertTo62Base(long inputStr) {
    	
        String[] baseElements = {
	        "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
	        "p","q","r","s","t","u","v","w","x","y","z","0","A","B","C",
	        "D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R",
	        "S","T","U","V","W","X","Y","Z","1","2","3","4","5","6","7",
	        "8","9"
        };
        
        String resultStr = "";
        int baseSize = baseElements.length;
        
        // Case1. ID 값이 base62 요소 개수보다 작을 때 
        if(inputStr < baseSize) {
            resultStr = baseElements[(int) (inputStr)];
        }
        // Case2. ID 값이 base62 요소 개수보다 클 때
        else if(inputStr > baseSize) {
            long mod = 0;
            long multiplier = 0;
            boolean isFianl = false;
            
            // 8자리 base62 변환 
            for(int i=SHORTENING_KEY_SIZE; i>=0; i--) {
                multiplier = (long) (inputStr / Math.pow(baseSize, i));
                
                if(multiplier > 0 && inputStr >= baseSize) {

                    resultStr += baseElements[(int) multiplier];
                    isFianl = true;
                } 
                
                else if(isFianl && multiplier == 0) {
                    resultStr += baseElements[0];
                }
                
                else if(inputStr < baseSize) {
                    resultStr += baseElements[(int) mod];
                }
                
                mod = (long) (inputStr % Math.pow(baseSize, i));
                
                inputStr = mod;                
            }
            
        }
        System.out.println(">>> resultStr: " + resultStr); 
        return resultStr;
    }
}





