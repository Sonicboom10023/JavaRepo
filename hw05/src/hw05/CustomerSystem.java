package hw05;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CustomerSystem{
    public Customer[] Customers = new Customer[5];
    public CustomerSystem() {
    }
    public Customer getCustomer(int i){
        return Customers[i];
    }
    public void setCustomers(){
        Customers[0] = new Customer("Eric", "Dollar", "eric6249@uab.edu", 2055414932);
        Customers[1] = new Customer("Gabriel", "McPherson", "gaberiel@uab.edu", 2055277825);
        Customers[2] = new Customer("Adam", "Pendry", "japendry@gt.edu",2052438049);
        Customers[3] = new Customer("Lawson","Brown","stradequit@gmail.com",2059602615);
        Customers[4] = new Customer("Taylor","Weeks","tweeks@gmail.com",2055856552);
        for (int i = 0; i < Customers.length; i++){
            Customers[i].setFullname();
        }

    }
    public Customer searchCustomers(String c){
        System.out.println(c);
        for (Customer customer : Customers) {
            if (customer.getFullname().contains(c) && c != null) {            //searches string for substring input
                return customer;
            }
            ;
        }
        return null;
    }
    public void setCustomersFile(){
        PrintWriter out = null;
        try {
            out = new PrintWriter("Customers.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < Customers.length; i++){
            out.println(Customers[i].toString());
            Customers[i].setFullname();
        }
        out.close();
    }

    public String toString(int i){
        return Customers[i].toString() + "\n";
    }

}
