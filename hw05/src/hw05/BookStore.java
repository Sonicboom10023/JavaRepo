package hw05;
import java.io.*;

public class BookStore {
    public Book[] Books = new Book[10];
    public BookStore(){}


    public void setBooks(){
        Books[0] = new Book("Don Quixote","Miguel de Cervantes",100,"AA",10,0);
        Books[1] = new Book("A Tale of Two Cities", "Charles Dickens", 99, "BB",2,1);
        Books[2] = new Book("The Lord of the Rings","JRR Tolkien",953,"RR",4,2);
        Books[3] = new Book("The Little Prince","Antoine de Saint-Exupery", 52,"CC",1,3);
        Books[4] = new Book("Harry Potter and the Sorcerer's Stone", "JK Rowling",582,"JK",3,4);
        Books[5] = new Book("And Then There Were None","Agatha Christie",230,"DD",1,5);
        Books[6] = new Book("Charlotte's Web","EB White",69,"EB",7,6);
        Books[7] = new Book("Harry Potter and the Prisoner of Azkaban","JK Rowling",529,"JK",1,7);
        Books[8] = new Book("The Hobbit","JRR Tolkien",774,"RR",2,8);
        Books[9] = new Book("Harry Potter and the Goblet of Fire", "JK Rowling",723,"JK",1,9);
    }
    public void updateStore() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("Bookstore.txt");
        for (int i = 0; i < Books.length-1; i++){
            out.println(Books[i].toString());
        }
        out.close();
    }


    public Book[] searchBooks(String t) throws IOException {
        Book[] searchResults = new Book[5];
        int count = 0;
        for (int j = 0; j < Books.length-1; j++){
            if (Books[j].getTitle().contains(t) && searchResults[4] == null){
                searchResults[count] = Books[j];
                count++;
            }
        }
        return searchResults;
    }



    public void rentedBooksFileCustomers(CustomerSystem a, BookStore b){
        PrintWriter out = null;
        try {
            out = new PrintWriter("RentedBooks.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 5; i++){
            if (a.getCustomer(i).getRentedbooks()!= null){
                out.println(a.getCustomer(i).getLastname() + " " + a.getCustomer(i).getRentedbooks()+ ", ");
            }
        }
        out.close();
    }

    public String toString(int i){
        return Books[i].toString() + "\n";
    }

    public Book searchID(int id){
        for (int i = 0; i < Books.length; i++) {
            if (id == Books[i].getId()) {
                return Books[i];
            }
        }
        return null;
    }

}
