/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sas.dao;

import com.mycompany.sas.database.DatabaseConfig;
import com.mycompany.sas.models.User;
import  java.util.UUID;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ARTHURSANTOSTAVARESS
 */
public class UserDao {
    
    public  static  final int LOGIN_SUCCESS = 1;
    public  static  final int EMAIL_OR_PASSWORD_FAILLED = 2;
    public static  final int EMAIL_NOT_FOUND = 3;
    
    

    public static DatabaseConfig database = new DatabaseConfig();

    public static void createTable() {
        String sql = "CREATE TABLE user_cm (\n"
                + "    id CHAR(36) PRIMARY KEY,     \n"
                + "    name VARCHAR(100) NOT NULL,\n"
                + "    email VARCHAR(150) NOT NULL UNIQUE,\n"
                + "    password VARCHAR(255) NOT NULL,\n"
                + "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n"
                + ")";

        try (Connection con = database.initConnection(); Statement statment = con.createStatement()) {
            statment.execute(sql);
            System.out.println("TABELA CRIADA COM SUCESSO");
        } catch (SQLException e) {
            System.out.println("ERRO AO CRIAR TABELA: " + e.getMessage());
        }

    }
    
    
    public  static  void insert(User user) throws SQLException{
        String sql = "INSERT INTO user_cm (id, name, email, password) values(?,?,?,?) ";
        String confirmSQL = "SELECT id FROM user_cm WHERE  email = ?";
        
        try(PreparedStatement preparedStatement = database.initConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            preparedStatement.executeUpdate();
            
            
            try(PreparedStatement p2 = database.initConnection().prepareStatement(confirmSQL)) {
                p2.setString(1, user.getEmail());
                ResultSet result = p2.executeQuery();
                if(result.next()) {user.setId(result.getString("id"));}
            }   
        }
    }
    
        public  static  void update(User user) throws SQLException{
        String sql = "UPDATE user_cm SET name = ?, email = ?, password = ? WHERE id = ? ";

        
        try(PreparedStatement preparedStatement = database.initConnection().prepareStatement(sql)) {
        
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            preparedStatement.setString(4, user.getId());
            preparedStatement.executeUpdate();
          
  
        }
    }
    
    public static User findById(String id) throws SQLException {
        String sql = "SELECT * FROM user_cm WHERE id = ?";
        
        try(Connection conn = database.initConnection(); PreparedStatement p =  conn.prepareStatement(sql)) {
            p.setString(1, id);
            
            try(ResultSet rs = p.executeQuery()) {
                if(rs.next()) {
                    return new User(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
                    );
                }
            } 
        }
        
        return  null;
    }
    
    
    public  static int authLogin(User user) throws SQLException{
        String sql = "SELECT email, password  FROM user_cm WHERE email = ?";
        
        try(PreparedStatement psmt = database.initConnection().prepareStatement(sql)) {
            psmt.setString(1, user.getEmail());
            try(ResultSet result = psmt.executeQuery()) {
                if(result.next()) {
                    if(BCrypt.checkpw(user.getPassword(), result.getString("password"))) {
                        return  LOGIN_SUCCESS;
                    }
                    
                    return  EMAIL_OR_PASSWORD_FAILLED;
                }
                
                return  EMAIL_NOT_FOUND;
            }
            
            
        }
        
        
    }

}
