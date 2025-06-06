/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sas;

import com.mycompany.sas.dao.UserDao;
import com.mycompany.sas.database.DatabaseConfig;
import com.mycompany.sas.models.User;
import java.sql.SQLException;

/**
 *
 * @author ARTHURSANTOSTAVARESS
 */
public class Sas {

    public static void main(String[] args)  {
        try {
            UserDao.createTable();

        }
        catch(Error e) {
            System.err.println(e.getMessage());
        }
//       
        

    }
}


