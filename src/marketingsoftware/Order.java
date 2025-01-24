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
import java.time.LocalDate;
import javax.swing.JTextArea;

/**
 *
 * @author DC
 */
public class Order {
    private Product product;
    private int numberProduct;
    private LocalDate expectedDelivery;
    private Supplier supplier;
    
    public Order(){
        this.product = new Product();
        this.numberProduct = 0;
        this.expectedDelivery = LocalDate.parse("1900-09-09");
        this.supplier = new Supplier();
    }
    
    public Order(Product product, int numberProduct, String expectedDelivery, Supplier supplier){
        this.product = product;
        this.numberProduct = numberProduct;
        this.expectedDelivery = LocalDate.parse(expectedDelivery);
        this.supplier = supplier;
    }
    public String ToString(){
        String str = "\nName Product : "+this.product.getProductName()+"\nNumber of Product :"+this.numberProduct
                +"\nExpected Delivery : "+this.expectedDelivery+"\nSupplier Name :" +this.supplier.getSupplierName();
        return str;
    }
    
    public void Display(JTextArea src){
        src.append(this.ToString());
    }
    
    public void SaveToFile(boolean append){
        FileWriter awriter;
        try{
            awriter = new FileWriter(new File("SupplierOrder.txt"),append);
            awriter.write("##########################" + System.getProperty("line.separator"));
            this.product.SaveToFile(awriter);
            awriter.write(this.numberProduct + System.getProperty("line.separator")
                    + this.expectedDelivery + System.getProperty("line.separator"));
            this.supplier.SaveToFile(awriter);
            awriter.close();
            awriter = null;
        }catch(IOException ioe){
            System.out.println("Error while saving order details");
        }
    }
    
    public void SaveToFile(FileWriter awriter){
        try {
            awriter.write("##########################" + System.getProperty("line.separator"));
            this.product.SaveToFile(awriter);
            awriter.write(this.numberProduct + System.getProperty("line.separator")
                    + this.expectedDelivery + System.getProperty("line.separator"));
            this.supplier.SaveToFile(awriter);
        } catch (IOException ex) {
            System.out.println("Error while saving order details");
        }
    }
    
    public void LoadFromFile(){
        FileReader reader;
        try {
            reader = new FileReader("SupplierOrder.txt");
            BufferedReader bin = new BufferedReader(reader);
            this.product.LoadFromFile(bin);
            this.numberProduct = Integer.valueOf(bin.readLine());
            this.expectedDelivery = LocalDate.parse(bin.readLine());
            this.supplier.LoadFromFile(bin);
            bin.close();
        }catch(IOException ioe){
            System.out.println("Error while loading order");
        }
    }
    
    public void LoadFromFile(BufferedReader bin){
        try{
            this.product.LoadFromFile(bin);
            this.numberProduct = Integer.valueOf(bin.readLine());
            this.expectedDelivery = LocalDate.parse(bin.readLine());
            this.supplier.LoadFromFile(bin);
        }catch(IOException ioe){
            System.out.println("Error while loading order");
        }
    }
}
