/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketingsoftware;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author DC
 */
public class BankAccount {

    private String type;
    private String name;
    private String surname;
    private String sortCode;
    private int accountNo;
    private double balance;
    private String NameOfBank;
    private double rate;
    private LocalDate lastReportedDate;
    private double overdraft;
    private String condition;
    private double  AvailableBalance = balance;
    private double depositedThisYear ;
    
    public String getAccountType(){
        return this.type;
    }
    
    public int getAccountNumber(){
        return this.accountNo;
    }
    
    public double getBalance(){
        return balance;
    }
    
    public boolean deposit(int accNo, double newAmount){
        if(this.accountNo == accNo){
            balance+=newAmount;
            return true;
        }
        return false;
    }
    
    public boolean withdraw(int accNo,double amount){
        if(this.accountNo == accNo){
            balance-=amount;
            return true;
        }
        return false;
    }
    
    public boolean transfer(BankAccount toAcc, double amt){
        if ((this.getBalance()>amt) && ("Current Account".equals(type))){
            toAcc.deposit(toAcc.getAccountNumber(),amt);
            this.withdraw(accountNo,amt);
            AvailableBalance = balance;
            return true;
        }
        return false;
    }
    
    public void SaveToFile(FileWriter awriter){
        try{
            awriter.write("#####"+System.getProperty("line.separator"));
            if ("Current Account".equals(type)){
                awriter.write("Current Account"+System.getProperty("line.separator"));
            }
            else if("ISA Account".equals(type)){
                awriter.write("ISA Account"+System.getProperty("line.separator"));
            }
            else if("Saving Account".equals(type)){
                awriter.write("Saving Account"+System.getProperty("line.separator"));
            }
            awriter.write(name+System.getProperty("line.separator")+
                    surname+System.getProperty("line.separator")+
                    type+System.getProperty("line.separator")+
                    sortCode+System.getProperty("line.separator")+
                    accountNo+System.getProperty("line.separator")+
                    balance+System.getProperty("line.separator")+
                    NameOfBank+System.getProperty("line.separator")+
                    rate+System.getProperty("line.separator")+
                    lastReportedDate+System.getProperty("line.separator"));
            if ("Current Account".equals(type)){
                awriter.write(overdraft+System.getProperty("line.separator")+
                condition+System.getProperty("line.separator")+
                AvailableBalance+System.getProperty("line.separator"));
            }
            else if("ISA Account".equals(type)){
                awriter.write(depositedThisYear+System.getProperty("line.separator"));
            }
        }catch(IOException ioe){
            System.out.println("Error while saving");
        }
    }
    
    public void LoadFromFile(BufferedReader bin){
       try{
            name = bin.readLine();
            surname = bin.readLine();
            type = bin.readLine();
            sortCode = bin.readLine();
            accountNo =Integer.valueOf(bin.readLine());
            balance = Double.valueOf(bin.readLine());
            NameOfBank = bin.readLine();
            rate = Double.valueOf(bin.readLine());
            lastReportedDate = LocalDate.parse(bin.readLine());
            
            System.out.println("Name :"+name);
            System.out.println("last reported date"+lastReportedDate);
            
            if ("Current Account".equals(type)){
                overdraft = Double.valueOf(bin.readLine());
                condition = bin.readLine();
                AvailableBalance = Double.valueOf(bin.readLine());
                System.out.println("avail bal :"+AvailableBalance);
            }
            else if("ISA Account".equals(type)){
                depositedThisYear = Double.valueOf(bin.readLine());
                System.out.println("deposited this year :"+depositedThisYear);
            }
            
        }
        catch(IOException ioe){
            System.out.println("Error");
        }
    }
}
