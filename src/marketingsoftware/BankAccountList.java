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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DC
 */
public class BankAccountList {
    private ArrayList <BankAccount> accountList;
    
    public BankAccountList(){
        accountList = new ArrayList<BankAccount>();
        this.LoadFromFile();
    }
    
    public void Add(BankAccount src){
        accountList.add(src);
    }
    
    public BankAccount getAccount(int AccNo){
        int i;
        for(i=0;i<accountList.size();i++)
        {
            if (accountList.get(i).getAccountNumber() == AccNo)
            {
                break;
            }
        }
        return accountList.get(i);
    }
    
    public void SaveToFile(boolean append){
        try{
            File file = new File(System.getProperty("user.dir"));
            String filePath = String.valueOf(file.getParent())+"\\MyFirstApplication\\AccountDetails.txt";
            System.out.println("Account File SaveToFile:"+filePath);
            FileWriter awriter = new FileWriter(filePath,append);
            for (BankAccount a : accountList) {
                a.SaveToFile(awriter);
            }
            awriter.flush();
            awriter.close();
            awriter = null;
        } catch (IOException ex) {
            Logger.getLogger(BankAccountList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void LoadFromFile(){
        FileReader reader;
        try {
            File file = new File(System.getProperty("user.dir"));
            String filePath = String.valueOf(file.getParent())+"\\MyFirstApplication\\AccountDetails.txt";
            System.out.println("Account File LoadFromFile:"+filePath);
            reader = new FileReader(filePath);
            BufferedReader bin = new BufferedReader (reader); 
            
            while ((bin.readLine()) != null) {
                bin.readLine();
                BankAccount foolAccount = new BankAccount();
                foolAccount.LoadFromFile(bin);
                this.Add(foolAccount);
            }
            bin.close();
        }
        catch (IOException ioe) {
            System.out.println("erroe");
        }      
    }
}