package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class bonushw {


    public static void main(String[] args) {
        Company company = new Company();

        try (BufferedReader br = new BufferedReader(new FileReader("company_records.csv"))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Employees a = new Employees(values[0], values[1], values[2], values[3], Integer.parseInt(values[4]));
                company.addEmployee(a);
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(company.departmentCount());
        System.out.println((company.salariesMinMax()));

    }
}







