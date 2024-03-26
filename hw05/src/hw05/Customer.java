package hw05;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Customer {
    private String firstname;
    private String lastname;
    private String email;
    private int phonenumber;
    private String fullname;
    private String rentedbooks;

    public Customer(String firstname, String lastname, String email, int phonenumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.rentedbooks = null;
    }

    public Customer() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname() {
        this.fullname = getFirstname() + " " + getLastname();
    }

    public String getRentedbooks() {
        return rentedbooks;
    }

    public void setRentedbooks(String rentedbooks) {
        this.rentedbooks = rentedbooks;
    }
    public String toString(){
        return getFullname() + ", " + getEmail() + ", " + getPhonenumber();
    }


    public void rentBook(Book a){

        if (a.getCopies() > 0){
            a.setCopies(a.getCopies()-1);
            System.out.println("Copies" + a.getCopies());
            if (getRentedbooks() != null){
                setRentedbooks(getRentedbooks()+a.getTitle() + ",");
            }
            else{
                setRentedbooks((a.getTitle()) + " " + a.getAuthor());
            }
        }
    }


}


