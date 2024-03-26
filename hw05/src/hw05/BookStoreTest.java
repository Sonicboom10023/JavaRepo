package hw05;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;


public class BookStoreTest {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("UAB Book Store");
        // set close operation to when user clicks exit
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the look and feel to the computer
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        JPanel panel = createMainPanel();
        // add panel to frame
        frame.add(panel);
        frame.setSize(700,700);
        frame.setVisible(true);


        //initialization of bookstore and customers
        BookStore Books = new BookStore();
        Books.setBooks();
        CustomerSystem Customers = new CustomerSystem();
        Customers.setCustomers();

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");
                Customers.setCustomersFile();
                Books.rentedBooksFileCustomers(Customers,Books);
                try {
                    Books.updateStore();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                e.getWindow().dispose();
            }
        });
    }

    private static JPanel createMainPanel() throws FileNotFoundException {
        //initialization of bookstore and customers
        BookStore Books = new BookStore();
        Books.setBooks();
        CustomerSystem Customers = new CustomerSystem();
        Customers.setCustomers();
        Customers.setCustomersFile();
        Books.updateStore();
        JPanel panel = new JPanel();
        JPanel helper_panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, 1);
        panel.setLayout(layout);
        JLabel greetingLabel = new JLabel("Book Store System");
        greetingLabel.setFont(new Font("Times New Roman", 1, 15));
        helper_panel.add(greetingLabel);
        panel.add(helper_panel);
        helper_panel = new JPanel();
        JLabel inputLabel = new JLabel("Search: ");
        inputLabel.setHorizontalAlignment(0);
        helper_panel.add(inputLabel);
        JTextField text = new JTextField(8);
        text.setBackground(new Color(255, 255, 255));
        helper_panel.add(text);
        panel.add(helper_panel);
        JTextArea result = new JTextArea("");
        result.setFont(new Font("Times", 1, 12));
        result.setEditable(false);
        result.setBackground(new Color(255, 255, 255));
        // creates display books button
        helper_panel = new JPanel();
        JButton show_books = new JButton("Display all books");
        helper_panel.add(show_books);
        // creates display customers button
        JButton show_customers = new JButton("Display all customers");
        helper_panel.add(show_customers);
        // creates search books button
         JButton search_books =new JButton("Search books");  //crates new button object and adds to helper_panel
         helper_panel.add(search_books);
        // creates search customer button
        JButton search_people = new JButton("Search customer name");
        helper_panel.add(search_people);
        // creates rent book button
        JButton rent = new JButton("Rent a book");
        helper_panel.add(rent);
        // creates select customer button
        JButton choose_customer = new JButton("Select Customer");
        helper_panel.add(choose_customer);
        choose_customer.setVisible(false);
        // creates choose book button
        JButton choose_book = new JButton("Select Book");
        helper_panel.add(choose_book);
        choose_book.setVisible(false);






        show_books.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText("");
                for (int i = 0; i < 10; i++){
                    result.setText(result.getText() + Books.toString(i));
                }
            }
        });

        show_customers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText("");
                for (int i = 0; i < 5; i++){
                    result.setText(result.getText() + Customers.toString(i));
                }
            }
        });

        search_books.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.getText().length() > 0){
                    String i = text.getText();
                    try {
                        if (i.length() > 0) {
                            Book[] booksresult = Books.searchBooks(i);   //iterates through bookresult array and appends to it if the search book function finds a match
                            for (int j = 1; j < booksresult.length; j++) {
                                if (booksresult[j] != null) {
                                    result.setText(result.getText() + booksresult[j] + "\n");
                                }
                            }
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    text.setText("");
                }
                else{
                    result.setText("Please input a book title...\n");
                    text.setText("");
                }

            }
        });

        search_people.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText("");
                String i = text.getText();
                if (i.length() > 0) {
                    String searchresult = Customers.searchCustomers(i).toString();
                    result.setText(searchresult);
                }
                else{
                    result.setText("Please input a name...");
                }
                text.setText("");
            }
        });

        rent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    result.setText("Please type the customer name in the search box.");
                    String[] cust = new String[1];
                    rent.setVisible(false);
                    choose_customer.setVisible(true);
                    search_people.setVisible(false);
                    search_books.setVisible(false);
                    choose_customer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            result.setText("Please input a customer name...");
                            if (text.getText().length() > 0){
                                cust[0] = Customers.searchCustomers(text.getText()).getFullname();
                                result.setText(cust[0] + "is selected to rent a book.\nPlease type the name of the book");
                                text.setText("");
                                if (cust[0] != null){
                                    choose_customer.setVisible(false);
                                    choose_book.setVisible(true);
                                }}
                            result.setText("Please input a book ID number...");}});

                    choose_book.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int i = Integer.parseInt(text.getText());
                            System.out.println(i);
                            Customers.searchCustomers(cust[0]).rentBook(Books.searchID(i));
                            Books.rentedBooksFileCustomers(Customers,Books);
                            text.setText("");
                            rent.setVisible(true);
                            choose_book.setVisible(false);
                            result.setText("Book rented!");
//                            search_books.setVisible(true);
                            search_people.setVisible(true);
                        }
                    });
            }
        });


        panel.add(helper_panel);
        panel.add(result);
        return panel;


    }

}
