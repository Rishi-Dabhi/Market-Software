/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketingsoftware;

import java.io.*;
import java.util.*;
import javax.swing.JTextArea;

/**
 *
 * @author DC
 */
public class ProductList {

    private ArrayList<Product> product;
    
    private User user = new User();
    private ArrayList<Product> wishList;

    ProductList() {
        product = new ArrayList<Product>();
        this.loadFromFile();
        
        wishList = new ArrayList<Product>();
        this.LoadFromFileWishList();
    }
    
    public void Add(Product src) {
        product.add(src);
    }
    
    public void add(Product src){
        wishList.add(src);
    }

    public void Remove(Product src) {
        product.remove(src);
    }
    
    public void remove(Product src) {
        wishList.remove(src);
    }
    
    public int Size(){
        return wishList.size();
    }
    
    public Product Get(int i){
        return wishList.get(i);
    }
    
    public void DisplayWishList(JTextArea jtextarea){
        for (int i = 0; i < wishList.size(); i++) {
            wishList.get(i).DisplayNamePrice(jtextarea);
        }
    }
    
    public ArrayList name(){
        ArrayList<String> name;
        name = new ArrayList<String>();
        for(int i=0;i<product.size();i++){
            name.add(product.get(i).getProductName());
        }
        return name;
    }

    public Product getProduct(String name) {
        int i;
        for(i=0;i<product.size();i++){
            if (product.get(i).getProductName().equals(name)) {
                break;
            }
        }
        return product.get(i);
    }

    public void SaveToFile() {
        try {
            FileWriter awriter = new FileWriter(new File("Product.txt"), false);
            for (Product p : product) {
                awriter.write("#######"+System.getProperty("line.separator"));
                p.SaveToFile(awriter);
            }
            awriter.close();
            awriter = null;
            
        } catch (IOException ioe) {
            System.out.println("Save to file prodoct list error");
        }
    }
        
    public void loadFromFile() {
        FileReader reader;
        try {
            reader = new FileReader("Product.txt");
            BufferedReader bin = new BufferedReader(reader);
            while ((bin.readLine()) != null) {
                Product foolProduct = new Product();
                foolProduct.LoadFromFile(bin);
                this.Add(foolProduct);
            }
            bin.close();
        } catch (IOException ioe) {
            System.out.println("erroe");
        }
    }
    
    public void SaveToFileWishList(boolean append){
        try{
            FileWriter awriter = new FileWriter(new File(user.userEmail+".txt"), append);
            for (Product p:wishList){
                awriter.write("#######"+System.getProperty("line.separator"));
                awriter.write(p.getProductName()+System.getProperty("line.separator"));
            }
            awriter.close();
            awriter = null;
        } catch (IOException ioe) {
            System.out.println("Save to file hashmap list error ");
        } 
    }
    
    public void LoadFromFileWishList(){
        FileReader reader;
        try {
            reader = new FileReader(user.userEmail+".txt");
            BufferedReader bin = new BufferedReader(reader);
            while(bin.readLine()!=null ){
                wishList.add(this.getProduct(bin.readLine()));
            }
            bin.close();
            } catch (IOException ioe) {
                System.out.println("erroe");
        }
    }

}
