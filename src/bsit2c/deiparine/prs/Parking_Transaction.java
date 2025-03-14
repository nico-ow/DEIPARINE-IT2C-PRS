
package bsit2c.deiparine.prs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Parking_Transaction {
   public void ParkTrans(){
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
        Parking_Transaction ts = new Parking_Transaction();
        switch(act){
            
            case 1:
                ts.addTransaction();
                break;
                
            case 2:
                ts.viewTransaction();
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
    
    private void addTransaction(){
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();
        Vehicle cs = new Vehicle();
        cs.viewVehicle();
        
        System.out.println("Enter The Vehicle ID");
        int vid = sc.nextInt();
        
        String csql = "SELECT v_id FROM tbl_vehicle WHERE v_id=?";
        while(conf.getSingleValue(csql,vid)==0){
            System.out.println("Vehicle ID does not exist, Enter again:");
            vid = sc.nextInt();
        }
        Parking_Space pk = new Parking_Space();
        pk.viewSpace();
        
        System.out.print("Enter the ID of the Parking Space: ");
        int sid = sc.nextInt();
        
        String psql = "SELECT s_id FROM tbl_space WHERE s_id=?";
        while(conf.getSingleValue(psql,sid)==0){
            System.out.println("Space ID does not exist, Enter again:");
            sid = sc.nextInt();
        }
        System.out.print("Hourly price: ");
        double price = sc.nextDouble();
        
        System.out.print("Number of hours parked: ");
        double duration = sc.nextDouble();
        double due = duration * price;
        
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
        
        String qry = "INSERT INTO tbl_transactions (v_id, s_id, t_duration, t_total, t_cash, t_date, t_status)" + "VALUES (?,?,?,?,?,?,?)";
        
        conf.addRecord(qry, vid, sid, duration, due, cashr, date, status);
    }  
    
     public  void viewTransaction(){
        
        String qry = "SELECT t_id, v_owner, v_plate, t_duration, t_cash, t_date, t_status FROM tbl_transactions " + "LEFT JOIN tbl_vehicle";
        String[] hdrs = {"TRANSACTION ID","OWNER","VEHICLE","DURATION","CASH RECIEVED","DATE","STATUS"};
        String[] clms = {"t_id","v_owner","v_plate","t_duration","t_cash","t_date","t_status"};
        Config conf = new Config();
        conf.viewRecords(qry, clms, clms);
        
        
        
    }
}
