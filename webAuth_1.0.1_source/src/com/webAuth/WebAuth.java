package com.webAuth;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.bouncycastle.util.encoders.Base64;

import com.webAuth.util.AESCoder;
import com.webAuth.util.RSAUtils;
import com.webAuth.util.RandomUtils;

public  class WebAuth {
    private  WebAuthInterface action;
    /**
     * 判断用户的登录是否成功
     * @param request
     * @param password
     * @return
     */
    public static boolean loginAble(HttpServletRequest request,String password){
        String pwd  = RSAUtils.decryptStringByJs(password);
        System.out.println("pwd="+pwd);
        String r2=(String) request.getSession().getAttribute("R2");
        String R2=pwd.substring(0, r2.length());
        System.out.println("R2="+R2);
        String R3=null;
        if(r2.equals(R2)){
          R3=pwd.substring(r2.length(),pwd.length());
        } 
      
        if(R3!=null)
            return true;
        else return false;
    }
    /**
     * 清除状态
     * @param request
     */
    public static void removeStatus(HttpServletRequest request){
        request.getSession().removeAttribute("status");
    }
    /**
     * 做准备工作，主要是完成前期的几次密钥协商
     * @param request
     * @param response
     */
   public  void prepare(HttpServletRequest request, HttpServletResponse response) {
        String status=(String) request.getSession().getAttribute("status");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        JSONArray json=null;
        try {
            json = new JSONArray();
            out=response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        JSONObject jo = new JSONObject();
        if(status==null){
            jo.put("publicKey",RSAUtils.getPublicKeyMap());
            json.add(jo);
           
            System.out.println("modulus="+RSAUtils.getPublicKeyMap().getModulus());
            System.out.println("exponent="+RSAUtils.getPublicKeyMap().getExponent());
            request.getSession().setAttribute("status", "step1");
            out.print(json.toString());
         
        }else if(status.equals("step1")){
            //随机数R1
            String R1;
            try {
                R1 = RandomUtils.getRandom();
                System.out.println("R1="+R1);
                System.out.println(action);
                String UserPassword=action.getUserPassword(request);
                if(UserPassword==null)
                    UserPassword=RandomUtils.getRandom();
                //主密钥MK  采用对称加密
                String MK=AESCoder.Encrypt192(UserPassword, R1);
                System.out.println("MK="+MK);
               
                //随机数R2
                String R2=RandomUtils.getRandom();
                System.out.println("R2="+R2);
                request.getSession().setAttribute("R2",  R2);
                //采用对称加密   MK前十六位为密�?
                String mi=AESCoder.Encrypt192(R2, MK.substring(0, 16));
                System.out.println(mi);
                jo.put("R1", R1);
                jo.put("mi", mi);
                json.add(jo); 
                out.print(json.toString());
                removeStatus(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
           
        } 

    }
     
   /**
    * 初始化公钥
    */
   public static void init(){
       System.out.println("in init");
           RSAUtils.getDefaultPublicKey();  
       }
   /**
    * 用私钥解出用户的用户名密码等信息
    * @param information
    * @return
    * @throws UnsupportedEncodingException 
    */
   public static Map<String,String> getUser(HttpServletRequest request,String information){
       removeStatus(request);
       Map<String,String> map=new HashMap<String,String>();

       System.out.println("information="+information);
       String pwd  = RSAUtils.decryptStringByJs(information);
       System.out.println(pwd);
       String userName=pwd.substring(0, pwd.indexOf(":"));
    
       try {
           userName=new String(Base64.decode(userName.getBytes("UTF-8")),"UTF-8");
       } catch (UnsupportedEncodingException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       String password=pwd.substring(pwd.indexOf(":")+1, pwd.lastIndexOf(":"));
       System.out.println(userName);
       System.out.println(password);
       map.put("userName", userName);
       map.put("password", password);
       return map;

   }
   
  
   /**
    * 判断用户是否能解出的R2是否正确，如果正确则返回
    * @param password
    * @param user_R2
    * @param user_R3
    * @return
    */
   public static String getR3(String password,String user_R2,String user_R3){
       String pwd  = RSAUtils.decryptStringByJs(password);
       System.out.println("pwd="+pwd);
       String R2=pwd.substring(0, user_R2.length());
       System.out.println("R2="+R2);
       String R3=pwd.substring(user_R2.length(),pwd.length());
       if(R2.equals(user_R2)&&!R3.equals(user_R3)){
               System.out.println("R3="+R3);
               return R3;
  
       }
       return null;
   
   }

    public void setAction(WebAuthInterface action) {
    this.action = action;
}
    /**
     * 在用户修改密码时，获得用户的旧密码，新密码
     * @param information
     * @param request
     * @return
     */
    public static Map<String,String> getPassword(String information,HttpServletRequest request){
        Map<String,String> map=new HashMap<String,String>();
        removeStatus(request);
        System.out.println("information="+information);
        String pwd  = RSAUtils.decryptStringByJs(information);
        System.out.println(pwd);
        String oldPassword=pwd.substring(0, pwd.indexOf(":"));
        pwd=pwd.substring(pwd.indexOf(":")+1, pwd.lastIndexOf(":"));
      
        String newPassword1=pwd.substring(0,pwd.indexOf(":"));
        String newPassword2=pwd.substring(pwd.indexOf(":")+1,pwd.length());
        request.getSession().removeAttribute("status");
        map.put("oldPassword", oldPassword);
        map.put("newPassword1", newPassword1);
        map.put("newPassword2", newPassword2);
        return map;
    }
}
