package com.hdu.edu.questionutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionProcessUtil
{
    /**
     * 手机号验证
     * 
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str)
    {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    
    // 判断表示是否全为英文
    public static boolean strIsEnglish(String word)
    {
        word = word.replace(" ", "");
        for (int i = 0; i < word.length(); i++)
        {
            if (!(word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') && !(word.charAt(i) >= 'a' && word.charAt(i) <= 'z'))
            {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isNumeric(String str){ 
        Pattern pattern = Pattern.compile("[0-9]*"); 
        if(str.length()>=2){
            return false;
        }
        return pattern.matcher(str).matches();    
     } 
    
    
}
