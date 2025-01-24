/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketingsoftware;
import java.awt.Image;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author DC
 */
public class Product {
    private int id;
    private String name;
    private double weight;
    private double price;
    private LocalDate expiryDate;
    private LocalDate estimatedDatetoOrder;
    private String image;
    private int number;
    
    private User user = new User();
    private String getselectedImage;
    private String storedLocation;
    
    public Product(){
        this.id = 0;
        this.name = "";
        this.weight = 0;
        this.price = 0;
        this.expiryDate = LocalDate.parse("1900-09-09");
        this.estimatedDatetoOrder = LocalDate.parse("1900-09-09");
        this.image = "Images/";
        this.number = 0;
    }
    
    public Product(int id,String name,double weight,double price,String expiryDate,String estimatedDatetoOrder, int number){
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.expiryDate = LocalDate.parse(expiryDate);
        this.estimatedDatetoOrder = LocalDate.parse(estimatedDatetoOrder);
        this.VAT();
        this.image = "Images/";
        this.number = number;
    }
    
    public void Edit(int id,String name,double weight,double price,String expiryDate,String estimatedDatetoOrder,int number){
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.expiryDate = LocalDate.parse(expiryDate);
        this.estimatedDatetoOrder = LocalDate.parse(estimatedDatetoOrder);
        this.number = number;
        this.VAT();
    }
    public void EditPrice(double price){
        this.price = price;
        this.VAT();
    }
    
    private void VAT(){
        this.price  += this.price*0.02;
    }
    
    public String ToString(){
        String str = "Product Id :"+this.id+"\nProduct Name :"+this.name+"\nWeight :"+this.weight+
                "\nPrice :"+this.price+"\nExpiry/Gaurentee Date :"+this.expiryDate+"\nEstimated Delivery :"+
                this.estimatedDatetoOrder+"\n";
        System.out.println("\n\n"+user.login +"\n\n");
        if("Customer".equals(user.login)){
            str = str+"Items in Stock :"+this.number+"\n";
        }
        return str;
    }
    
    public void Display (JTextArea jtextarea){
        jtextarea.append(this.ToString());
    }
    
    public void DisplayNamePrice(JTextArea jtextarea){
        jtextarea.append("Name :"+name+"\nPrice :"+price+"\n\n");
    }
    
    public String getProductName(){
        return this.name;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public void ShowImage(JLabel jShowImageLabel){
        ImageIcon imIco = new ImageIcon(this.storedLocation);
        Image imFit = imIco.getImage();
        Image imgFit = imFit.getScaledInstance(jShowImageLabel.getWidth(), jShowImageLabel.getHeight(), Image.SCALE_SMOOTH);
        jShowImageLabel.setIcon(new ImageIcon(imgFit));
    }
    
    public void UploadImage(JLabel jUploadImageLabel){
    JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("3 Extensions Supported", "jpg", "png", "peg");
        fileChooser.setFileFilter(filter);
        int selected = fileChooser.showOpenDialog(null);
        if (selected == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            getselectedImage = file.getAbsolutePath();
            ImageIcon imIco = new ImageIcon(getselectedImage);
            Image imFit = imIco.getImage();
            Image imgFit = imFit.getScaledInstance(jUploadImageLabel.getWidth(), jUploadImageLabel.getHeight(), Image.SCALE_SMOOTH);
            jUploadImageLabel.setIcon(new ImageIcon(imgFit));
        }
    }
    
    public void SaveImage() throws IOException{
        File directory = new File(this.image);
        if(!directory.exists()){
            directory.mkdir();
        }
        File sourceFile = new File(getselectedImage);
        String extention = getselectedImage.substring(getselectedImage.lastIndexOf('.')+1);
        File destinationFile= new File(this.image+this.id+"_"+this.name+"."+extention);
        storedLocation = String.valueOf(destinationFile.toPath());
        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath());
        } catch (IOException ioe) {
            System.out.println("Failed saving image");
        }
        
    }
    
    public void SaveToFile(boolean append){
        FileWriter awriter;
        try{
            awriter = new FileWriter(new File("Product.txt"),append);
            awriter.write("##########################"+System.getProperty("line.separator")+
                    this.id+System.getProperty("line.separator")+
                    this.name+System.getProperty("line.separator")+
                    this.weight+System.getProperty("line.separator")+
                    this.price+System.getProperty("line.separator")+
                    this.expiryDate+System.getProperty("line.separator")+
                    this.estimatedDatetoOrder+System.getProperty("line.separator")+
                    this.storedLocation+System.getProperty("line.separator")+
                    this.number+System.getProperty("line.separator"));
            awriter.close();
            awriter = null;
        }catch(IOException ioe){
            System.out.println("Error while saving product details");
        }
    }
    
    public void LoadFromFile(){
        FileReader reader;
        try {
            reader = new FileReader("Product.txt");
            BufferedReader bin = new BufferedReader(reader);
            this.id = Integer.valueOf(bin.readLine());
            this.name = bin.readLine();
            this.weight = Double.valueOf(bin.readLine());
            this.price = Double.valueOf(bin.readLine());
            this.expiryDate = LocalDate.parse(bin.readLine());
            this.estimatedDatetoOrder = LocalDate.parse(bin.readLine());
            this.storedLocation = bin.readLine();
            this.number = Integer.valueOf(bin.readLine());
            bin.close();
        }catch(IOException ioe){
            System.out.println("Error while loading product");
        }
    }
    
    public void SaveToFile(FileWriter awriter){
        try{
            awriter.write(
                    this.id+System.getProperty("line.separator")+
                    this.name+System.getProperty("line.separator")+
                    this.weight+System.getProperty("line.separator")+
                    this.price+System.getProperty("line.separator")+
                    this.expiryDate+System.getProperty("line.separator")+
                    this.estimatedDatetoOrder+System.getProperty("line.separator")+
                    this.storedLocation+System.getProperty("line.separator")+
                    this.number+System.getProperty("line.separator"));
        }catch(IOException ioe){
            System.out.println("Error while saving product list");
        }
    }
    
    public void LoadFromFile(BufferedReader bin){
    try{
        this.id = Integer.valueOf(bin.readLine());
        this.name = bin.readLine();
        this.weight = Double.valueOf(bin.readLine());
        this.price = Double.valueOf(bin.readLine());
        this.expiryDate = LocalDate.parse(bin.readLine());
        this.estimatedDatetoOrder = LocalDate.parse(bin.readLine());
        this.storedLocation = bin.readLine();
        this.number = Integer.valueOf(bin.readLine());
        }
        catch(IOException ioe){
            System.out.println("Error while Loading productlist ");
        }
    }
}