/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.mycompany.sas.dao.UserDao;
import com.mycompany.sas.models.User;
import java.sql.SQLException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author ARTHURSANTOSTAVARESS
 */
public class UserDaoTest {
    
    
    
    @BeforeAll 
    
    public static void setup() {
        
    }
    
    @Test
    public  void insertInto() throws SQLException{
        User user = new User("juliano", "juliano@gmail.com", "1");
        UserDao.insert(user);
        assertTrue(user.getId() != null ,  "ID deve ser Gerado");
        
    }
    
    
    
    @Test 
    public  void findUserById() throws SQLException {
        User user = new User("daniel", "daniel@gmail.com", "1");
        UserDao.insert(user);
        
        User userFinded =  UserDao.findById(user.getId());
        
        assertNotNull(userFinded);
        assertEquals(user.getEmail(), userFinded.getEmail());
        assertEquals(user.getName(), userFinded.getName());
    }
    
    
    @Test
    
    
    public  void  login() throws SQLException {
        User user = new User("luis","luis@gmail.com", "1");
        UserDao.insert(user);
        
        assertEquals(UserDao.LOGIN_SUCCESS,UserDao.authLogin(new User(user.getEmail(),user.getPassword())));  
    }
    
    
    @Test 
    public void updateUserById() throws SQLException {
        User user = new User("Arthur Tavares", "arthurSANTOS@gmail.com", "1");
        UserDao.insert(user);
        User newUser = new User(user.getId(), "Arthur Souto", "arthurtavares@gmail.com", "2");
        UserDao.update(newUser);
        
        User userFinded = UserDao.findById(user.getId());
        
        assertNotNull(userFinded);
        assertEquals(newUser.getEmail(), userFinded.getEmail());
        assertEquals(newUser.getName(), userFinded.getName());
 
        
    }
    
    
   
    
    
}
