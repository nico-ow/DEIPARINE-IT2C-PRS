
package bsit2c.deiparine.prs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Records {
    
    public void rTransactions(){
        String response;
        
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        do{
        System.out.println("-------------------------------------");
        System.out.println("RECORDS PANEL");
        System.out.println("-------------------------------------");
        System.out.println("(1)ADD RECORD");
        System.out.println("(2)VIEW RECORD");
        System.out.println("(3)UPDATE RECORD");
        System.out.println("(4)DELETE RECORD");
        System.out.println("(5)EXIT");
        System.out.println("-------------------------------------");
        
        System.out.println("Enter Action:");
        int act = sc.nextInt();
        Records or = new Records();
        switch(act){
            
            case 1:
                or.addRecords();
                break;
                
            case 2:
               
                break;
                
            case 3:
               
                
                break;
                
            case 4:
                
                
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
    
    private void addRecords(){
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();
        Customer cs = new Customer();
        cs.viewCustomer();
        
        System.out.println("Enter The Customer ID");
        int cid = sc.nextInt();
        
        String csql = "SELECT c_id FROM tbl_customer WHERE c_id=?";
        while(conf.getSingleValue(csql,cid)==0){
            System.out.println("Customer does not exist, Enter again:");
            cid = sc.nextInt();
        }
        Park pk = new Park();
        pk.viewPark();
        
        System.out.print("Enter the ID of the Park: ");
        int pid = sc.nextInt();
        
        String psql = "SELECT p_id FROM tbl_park WHERE p_id=?";
        while(conf.getSingleValue(psql,pid)==0){
            System.out.println("Customer does not exist, Enter again:");
            pid = sc.nextInt();
        }
        System.out.print("Enter Hourly Price: ");
        double hours = sc.nextDouble();
        
        String priceqry = "SELECT p_hours FROM tbl_park WHERE p_id = ?";
        double pr = conf.getSingleValue(priceqry, pid);
        double due = hours * pr;
        
        System.out.println("-----------------------------");
        System.out.println("Total Due: "+due);
        System.out.println("-----------------------------");
        
        System.out.println("");
        
        System.out.print("Cash Recieved: ");
        double cashr = sc.nextDouble();
        while(cashr<due){
        System.out.println("Inavlid Amount, Please Enter Again: ");
        cashr = sc.nextDouble();
        

        }
        
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
        String status = "Pending";
        
        String orderqry = "INSERT INTO tbl_records (c_id, p_id, r_hours, r_due, r_cashr, r_date, r_status)" + "VALUES (?,?,?,?,?,?,?,?)";
        
        conf.addRecord(orderqry, cid, pid, hours, due, cashr, date, status);
    }
    
}
