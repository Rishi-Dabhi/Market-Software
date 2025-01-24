/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketingsoftware;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author DC
 */
public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String confirmPassword;
    private String role;
    
    public static String login;
    public static String userEmail;
    
    public User(){
        name="";
        surname="";
        email="";
        password = "";
        role="";
    }
    public User(String name,String surname,String email,String password,String confirmPassword,String role){
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.password = password;
        this.role=role;
        this.confirmPassword=confirmPassword;
    }
    public boolean SaveUser(){
        boolean found=false;
        
        if(this.password == null ? this.confirmPassword != null : !this.password.equals(this.confirmPassword)){
            return found;
        }
        else{
            FileWriter writer;
            try{
                writer = new FileWriter("Login.txt",true);
                writer.write("####"+System.getProperty("line.separator")+
                        this.name+System.getProperty("line.separator")+
                        this.surname+System.getProperty("line.separator")+
                        this.email+System.getProperty("line.separator")+
                        this.password+System.getProperty("line.separator")+
                        this.role+System.getProperty("line.separator"));
                writer.close();
                writer = null;            
                found = true;
            }
            catch(IOException ioe){
                found = false; 
                System.out.println("Error saving new User");
            }
        }
        return found;
    }
    
    public boolean isUser(String email, String password, String role){
        boolean isFound = false;
        this.email = email;
        this.password = password;
        this.role = role;
        try{
            BufferedReader bin = new BufferedReader(new FileReader("Login.txt"));
            while((bin.readLine())!= null){
                bin.readLine();
                bin.readLine();
                String em = bin.readLine();
                String pw = bin.readLine();
                String r = bin.readLine();
                if (this.email.contentEquals(em))  {
                    if (this.password.contentEquals(pw))  {
                        if (this.role.contentEquals(r)) {
                            login = r;
                            userEmail = em;
                            isFound = true;
                        }
                    }
                }
            }
            bin.close();
            bin = null;
        }
        catch(IOException ioe){
           isFound = false; 
        }
        return isFound;
    }
}
