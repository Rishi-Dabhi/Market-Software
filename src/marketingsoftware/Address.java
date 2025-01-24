/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketingsoftware;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextArea;

/**
 *
 * @author DC
 */
public class Address {
    private String street;
    private int houseNumber;
    private String area;
    private String postCode;
    private String town;
    private String country;
    private JTextArea textareaCopy;
        
    public Address()
    {
        street ="";
        houseNumber = 0;
        area = "";
        postCode = "";
        town = "";
        country = "";
    }
    
    public Address( int houseNumber, String street, String area, String postCode,String town, String country)
    {
        this.street=street;
        this.houseNumber = houseNumber;
        this.area = area;
        this.postCode = postCode;
        this.town = town;
        this.country = country;
        
    }
    
    public void Edit(int newhouseNumber, String newstreet,  String newarea, String newpostCode,String newTown,String newcountry){
        
        street = newstreet;
        houseNumber = newhouseNumber;
        area = newarea;
        postCode = newpostCode;
        town = newTown;
        country = newcountry;
    }
   
    public String ToString()
    {
        String show ="HouseNumber = "+String.valueOf(houseNumber)+
                "\nStreet = "+street+
                "\nArea = "+area+
                "\nPostCode = "+postCode+
                "\nTown = "+town+
                "\nCountry = "+country;
        return show;
    }

    public void display(JTextArea jtextarea)
    {
        jtextarea.append(toString()); 
        textareaCopy = jtextarea;
    }
    
    public void SaveToFile(boolean append){
        FileWriter awriter;
        try{
            awriter = new FileWriter(new File("Address.txt"),append);
            awriter.write("##########################"+System.getProperty("line.separator")+
                    houseNumber+System.getProperty("line.separator")+
                    street+System.getProperty("line.separator")+area+System.getProperty("line.separator")+
                    postCode+System.getProperty("line.separator")+town+System.getProperty("line.separator")+
                    country+System.getProperty("line.separator"));
            awriter.close();
            awriter = null;
        }catch(IOException ioe){
            System.out.println("Error while saving product details");
        }
    }
    
    public void saveToFile(FileWriter awriter) {
        try{
            awriter.write(houseNumber+System.getProperty("line.separator")+
                    street+System.getProperty("line.separator")+area+System.getProperty("line.separator")+
                    postCode+System.getProperty("line.separator")+town+System.getProperty("line.separator")+
                    country+System.getProperty("line.separator"));
            
        }catch(IOException ioe){
            System.out.println("Failed to Save");
        }
    }
    
    public void LoadFromFile(){
        FileReader reader;
        try {
            reader = new FileReader("Address.txt");
            BufferedReader bin = new BufferedReader(reader);
            houseNumber = Integer.valueOf(bin.readLine());
            street = bin.readLine();
            area = bin.readLine();
            postCode = bin.readLine();
            town = bin.readLine();
            country = bin.readLine();
            bin.close();
        }catch(IOException ioe){
            System.out.println("Error while loading product");
        }
    }
    
    public void LoadFromFile(BufferedReader bin){
    try{
        houseNumber = Integer.valueOf(bin.readLine());
        street = bin.readLine();
        area = bin.readLine();
        postCode = bin.readLine();
        town = bin.readLine();
        country = bin.readLine();
        }
        catch(IOException ioe){
            System.out.println("Error");
        }
    }
}