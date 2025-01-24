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
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author DC
 */
public class OrderList {
    private ArrayList<Order> order;
    
    OrderList() {
        order = new ArrayList<Order>();
        this.LoadFromFile();
        
        
    }
    
    public void Add(Order src) {
        order.add(src);
    }
    
    public void Display(JTextArea src){
        for (Order o:order){
            o.Display(src);
            src.append("\n");
        }
    }
    
    public void Remove(Order src){
        order.remove(src);
    }
    
    public void ClearOrderList(){
        while(!order.isEmpty()){
            this.Remove(order.get(0));
        }
    }
    
    public void SaveToFile(boolean append) {
        try {
            FileWriter awriter = new FileWriter(new File("SupplierOrder.txt"), append);
            for (Order s : order) {
                s.SaveToFile(awriter);
            }
        } catch (IOException ioe) {
            System.out.println("Save to file order list error");
        }
    }

    public void LoadFromFile() {
        FileReader reader;
        try {
            reader = new FileReader("SupplierOrder.txt");
            BufferedReader bin = new BufferedReader(reader);
            while ((bin.readLine()) != null) {
                Order foolorder = new Order();
                foolorder.LoadFromFile(bin);
                this.Add(foolorder);
            }
            bin.close();
        }catch(IOException ioe){
            System.out.println("error");
        }
    }
}