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

/**
 *
 * @author DC
 */
public class SupplierList {
    private ArrayList<Supplier> supplier;

    SupplierList() {
        supplier = new ArrayList<Supplier>();
        this.LoadFromFile();
    }
    
    public void Add(Supplier src) {
        supplier.add(src);
    }

    public void Remove(Supplier src) {
        supplier.remove(src);
    }
    
    public ArrayList name(){
        ArrayList<String> name;
        name = new ArrayList<String>();
        for(int i=0;i<supplier.size();i++){
            name.add(supplier.get(i).getSupplierName());
        }
        return name;
    }

    public Supplier getSupplier(String name) {
        int i;
        System.out.println("getProduct name :" +name);
        for(i=0;i<supplier.size();i++){
            if (supplier.get(i).getSupplierName().equals(name)) {
                break;
            }
        }
        return supplier.get(i);
    }

    public void SaveToFile() {
        try {
            FileWriter awriter = new FileWriter(new File("SupplierDetail.txt"), false);
            for (Supplier s : supplier) {
                awriter.write("##########################"+System.getProperty("line.separator"));
                s.SaveToFile(awriter);
            }
        } catch (IOException ioe) {
            System.out.println("Save to file prodoct list error");
        }
    }

    public void LoadFromFile() {
        FileReader reader;
        try {
            reader = new FileReader("SupplierDetail.txt");
            BufferedReader bin = new BufferedReader(reader);
            while ((bin.readLine()) != null) {
                Supplier foolSupplier = new Supplier();
                foolSupplier.LoadFromFile(bin);
                this.Add(foolSupplier);
            }
            bin.close();
        } catch (IOException ioe) {
            System.out.println("erroe");
        }
    }
}
