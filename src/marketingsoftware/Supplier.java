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
public class Supplier {
    private String name;
    private Address address;
    
    public Supplier(){
        name = "";
        address = new Address();
    }
    
    public Supplier(String name,Address address){
        this.name = name;
        this.address = address;
    }
    
    public String ToString(){
        String str = "\nName of Supplier : "+this.name+"\n"+address.ToString();
        return str;
    }
    
    public void Display(JTextArea src){
        src.append(this.ToString());
    }
    
    public String getSupplierName(){
        return this.name;
    }
    
    public void SaveToFile(boolean append){
        FileWriter awriter;
        try{
            awriter = new FileWriter(new File("SupplierDetail.txt"),append);
            awriter.write("##########################"+System.getProperty("line.separator")+
                    this.name +System.getProperty("line.separator"));
            address.saveToFile(awriter);
            awriter.close();
            awriter = null;
        }catch(IOException ioe){
            System.out.println("Error while saving product details");
        }
    }
    
    public void SaveToFile(FileWriter awriter){
        try{
            awriter.write(
                    this.name +System.getProperty("line.separator"));
            address.saveToFile(awriter);
        }catch(IOException ioe){
            System.out.println("Error while saving product details");
        }
    }
    
    public void LoadFromFile(){
        FileReader reader;
        try {
            reader = new FileReader("SupplierDetail.txt");
            BufferedReader bin = new BufferedReader(reader);
            name = bin.readLine();
            address.LoadFromFile(bin);
            bin.close();
        }catch(IOException ioe){
            System.out.println("Error while loading product");
        }
    }
    
    public void LoadFromFile(BufferedReader bin){
    try{
        name = bin.readLine();
        address.LoadFromFile(bin);
        }
        catch(IOException ioe){
            System.out.println("Error while Loading supplier ");
        }
    }
}
