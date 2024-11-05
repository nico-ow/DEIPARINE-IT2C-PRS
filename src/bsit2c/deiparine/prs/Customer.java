
package bsit2c.deiparine.prs;
import java.util.Scanner;

public class Customer {
    
    public void cTransactions(){
        String response;
        
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        do{
        System.out.println("-------------------------------------");
        System.out.println("CUSTOMER PANEL");
        System.out.println("-------------------------------------");
        System.out.println("(1)ADD CUSTOMER");
        System.out.println("(2)VIEW CUSTOMER");
        System.out.println("(3)UPDATE CUSTOMER");
        System.out.println("(4)DELETE CUSTOMER");
        System.out.println("(5)EXIT");
        System.out.println("-------------------------------------");
        
        System.out.println("Enter Action:");
        int act = sc.nextInt();
        Customer cs = new Customer();
        switch(act){
            
            case 1:
                cs.addCustomer();
                cs.viewCustomer();
                break;
                
            case 2:
                cs.viewCustomer();
                break;
                
            case 3:
                cs.viewCustomer();
                cs.updateCustomer();
                cs.viewCustomer();
                
                break;
                
            case 4:
                cs.viewCustomer();
                cs.deleteCustomer();
                cs.viewCustomer();  
                break;
                
            case 5:
                System.out.println("Exiting Enter (yes) to continue:");
                response = sc.next();
                if(response.equalsIgnoreCase("yes")){
                  exit = false;  
                }
                break;
                
            default:
                System.out.println("Invalid Action!");                
            }      
            System.out.println("Do you want to continue?(yes/no):");
            response = sc.next();
            
        } while(response.equalsIgnoreCase("yes"));   
    }
    
    public void addCustomer(){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("First Name:");
        String fname = sc.next();
        System.out.println("Last Name:");
        String lname = sc.next();
        System.out.println("Email:");
        String email = sc.next();
        System.out.println("Status:");
        String status = sc.next();
        
        String qry = "INSERT INTO tbl_customer(f_name,l_name,c_email,c_status)VALUES(?,?,?,?)";
        Config conf = new Config();
        conf.addRecord(qry,fname,lname,email,status);
        
        
    }
    
    public  void viewCustomer(){
        
        String qry = "SELECT*FROM tbl_customer";
        String[] hdrs = {"ID","Firstname","Lastname","Email","Status"};
        String[] clms = {"c_id","f_name","l_name","c_email","c_status"};
        Config conf = new Config();
        conf.viewRecords(qry, clms, clms);
        
        
        
    }
    
    private void updateCustomer(){
        
        Scanner sc = new Scanner (System.in);
        Config conf = new Config();
        System.out.println("Enter ID To Update:");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id=?",id)==0){
            System.out.println("Selected ID Doesn't Exist!");
            System.out.println("Select ID Again: ");
            id = sc.nextInt();
        }
        
        
        System.out.println(" New First Name:");
        String fname = sc.next();
        System.out.println("New Last Name:");
        String lname = sc.next();
        System.out.println("New Email:");
        String email = sc.next();
        System.out.println("New Status:");
        String status = sc.next();
        
        String qry = "UPDATE tbl_customer SET f_name=?, l_name=?, c_email=?, c_status=? WHERE c_id = ?";
        
        conf.updateRecord(qry,fname,lname,email,status,id);
    }
    
    public void deleteCustomer(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID To Delete: ");
        Config conf = new Config();
        int id = sc.nextInt();
        
         while(conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id=?",id)==0){
            System.out.println("Selected ID Doesn't Exist!");
            System.out.println("Select ID Again: ");
            id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_customer WHERE c_id=?";
        
        conf.deleteRecord(qry,id);
    }
}
