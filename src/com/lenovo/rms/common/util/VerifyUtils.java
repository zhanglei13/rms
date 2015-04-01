package com.lenovo.rms.common.util;

import java.util.Properties;

import javax.naming.*;
import javax.naming.ldap.*;

public class VerifyUtils {
    private final static String LDAP_URL ="LDAP://lenovo.com:389";
    private final static String DOMAIN = "lenovo\\";
    
    public static boolean verify(String name,String passwd){
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        //set security credentials, note using simple cleartext authentication
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, DOMAIN+name);
        env.put(Context.SECURITY_CREDENTIALS, passwd);
        //connect to my domain controller
        env.put(Context.PROVIDER_URL, LDAP_URL);
    
        try{
           //Create the initial directory context
           LdapContext ctx = new InitialLdapContext(env, null);
           ctx.close();   
        }catch (NamingException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main (String[] args) {
       System.out.println(VerifyUtils.verify("lisq7", "250511lsq."));
     }
}
