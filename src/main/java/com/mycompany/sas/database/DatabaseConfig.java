/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sas.database;
import io.github.cdimascio.dotenv.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ARTHURSANTOSTAVARESS
 */
public class DatabaseConfig {
   private final String url = Dotenv.load().get("DATABASE_URL");
    
    
    public  Connection initConnection() throws SQLException{
         return  DriverManager.getConnection(url);
    }
    
    
    
    
    
    
    
}
