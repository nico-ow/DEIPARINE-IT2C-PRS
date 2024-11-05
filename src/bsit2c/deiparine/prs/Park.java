
package bsit2c.deiparine.prs;

import java.util.Scanner;


public class Park {
    public void pTransactions(){
        String response;
        
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        do{
        System.out.println("-------------------------------------");
        System.out.println("PARK PANEL");
        System.out.println("-------------------------------------");
        System.out.println("(1)ADD PARK");
        System.out.println("(2)VIEW PARK");
        System.out.println("(3)UPDATE PARK");
        System.out.println("(4)DELETE PARK");
        System.out.println("(5)EXIT");
        System.out.println("-------------------------------------");
        
        System.out.println("Enter Action:");
        int act = sc.nextInt();
        Park cs = new Park();
        switch(act){
            
            case 1:
                cs.addPark();
                cs.viewPark();
                break;
                
            case 2:
                cs.viewPark();
                break;
                
            case 3:
                cs.viewPark();
                cs.updatePark();
                cs.viewPark();
                
                break;
                
            case 4:
                cs.viewPark();
                cs.deletePark();
                cs.viewPark();  
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
     public void addPark(){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Plate No. : ");
        String plate = sc.next();
        System.out.println("Date: ");
        String date = sc.next();
        System.out.println("Time: ");
        String time = sc.next();
        System.out.println("Hours:" );
        String hours = sc.next();
        
        
        String qry = "INSERT INTO tbl_park(p_plate,p_date,p_time,p_hours)VALUES(?,?,?,?)";
        Config conf = new Config();
        conf.addRecord(qry,plate,date,time,hours);
        
        
    }
    
    public void viewPark(){
        
        String qry = "SELECT*FROM tbl_park";
        String[] hdrs = {"ID","PLATE","DATE","TIME","HOURS"};
        String[] clms = {"p_id","p_plate","p_date","p_time","p_hours"};
        Config conf = new Config();
        conf.viewRecords(qry, clms, clms);
        
        
        
    }
    
    private void updatePark(){
        
        Scanner sc = new Scanner (System.in);
        Config conf = new Config();
        System.out.println("Enter ID To Update:");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT p_id FROM tbl_park WHERE p_id=?",id)==0){
            System.out.println("Selected ID Doesn't Exist!");
            System.out.println("Select ID Again: ");
            id = sc.nextInt();
        }
        
        System.out.println("Plate No. : ");
        String plate = sc.next();
        System.out.println("Date: ");
        String date = sc.next();
        System.out.println("Time: ");
        String time = sc.next();
        System.out.println("Hours:" );
        String hours = sc.next();
        
        
       
        
        String qry = "UPDATE tbl_park SET p_plate=?, p_date=?, p_time=?, p_hours=? WHERE p_id = ?";
        
        conf.updateRecord(qry,plate,date,time,hours,id);
    }
    
    public void deletePark(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID To Delete: ");
        Config conf = new Config();
        int id = sc.nextInt();
        
         while(conf.getSingleValue("SELECT p_id FROM tbl_park WHERE p_id=?",id)==0){
            System.out.println("Selected ID Doesn't Exist!");
            System.out.println("Select ID Again: ");
            id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_park WHERE p_id=?";
        
        conf.deleteRecord(qry,id);
    }
    
}
