package org.androidtown.bookalarm;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{

    final static private String URL = "http://bk66.cafe24.com/Register.php";
    private Map<String,String> parameters;

    public RegisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
        parameters.put("userName",userName);
        parameters.put("userAge",userAge+""); //정수형은 " "을 넣어서 문자열 형태로 변환
    }

    @Override
    public Map<String,String>getParams(){
        return parameters;
    }
}
