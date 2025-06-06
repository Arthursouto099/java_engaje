/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sas.controllers;
import com.mycompany.sas.dao.UserDao;
import com.mycompany.sas.models.User;
import com.mycompany.sas.utils.Response;
import java.sql.SQLException;



/**
 *
 * @author ARTHURSANTOSTAVARESS
 */
public class UserController {
    
    
    public  static Response login(String email, String password ) {
        
        try { 
            int responseFromLogin = UserDao.authLogin(new User(email, password));
            
            switch (responseFromLogin) {
                case UserDao.LOGIN_SUCCESS -> {
                    return  new Response("Login feito com sucesso", 200);
                }
                case UserDao.EMAIL_OR_PASSWORD_FAILLED -> {
                    return new Response("Senha ou usuario inválidos", 400);
                }
                    
                default -> { return new Response("Erro ao logar", 500);}
            }
  
            
        }
        
        catch(SQLException e) {
            return  new Response(e.getMessage(), 500);
        }
    }
    
       
}
