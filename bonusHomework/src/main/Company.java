package main;

import java.util.Arrays;

public class Company extends Employees{
    public Employees[] company = new Employees[25];
    public Company() {

    }

    public int totalSalary(Employees[] company, String department){
        int total = 0;
        for(int i = 0; i < company.length; i++){
            if (department == company[i].getDepartment() && company[i].getFirstname() != null){
                total += company[i].getSalary();
            }
        }
        return total;
    }

    public String departmentCount(){
        String [] dp = new String[this.company.length];
        for (int i = 0; i < this.company.length; i++){
            dp[i] = this.company[i].getDepartment();
        }
        String[] departments = Arrays.stream(dp).distinct().toArray(String[]::new);
        int count = departments.length;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < departments.length; i++){
            if (departments[i] != null){
                if (i != departments.length-1){
                    sb.append(departments[i] + ", ");
                }
                else{
                    sb.append(departments[i] + ".");
                }
            }
        }
        String dep_str = sb.toString();
        return "There are a total of " + count + " departments. These departments include: " + dep_str;
    }

    public void addEmployee(Employees a){
        for(int i = 0; i < this.company.length; i++){
            if (this.company[i] == null){
                this.company[i] = a;
                break;
            }
        }
    }

    public Company salariesMinMax(){
        int max = 0;
        //determines the max salary by iterating
        //sets max value to current highest salary
        for (int i = 0; i < this.company.length; i++){
            if (this.company[i].getSalary() > max){
                max = this.company[i].getSalary();
            }
        }
        int min = max;
        //determines min salary by setting it to max salary
        //iterates through setting min value as the lower salary
        for (int i = 0; i < this.company.length; i++){
            if (this.company[i].getSalary() < min){
                min = this.company[i].getSalary();
            }
        }
        int maxcount = 0;
        Company maxminE = new Company();
        for (int i = 0; i < this.company.length; i++){
            if (this.company[i].getSalary() == max){
                maxminE.addEmployee(this.company[i]);
                maxcount++;
            }
            if (this.company[i].getSalary() == min){
                maxminE.addEmployee(this.company[i]);
                maxcount++;
            }
        }
        return maxminE;
    }
    public String toString(){
        String[] str = new String[this.company.length];
        for (int i = 0; i < this.company.length; i++){
            if(this.company[i] != null){
                str[i] = this.company[i].toString();
            }
        }
        String[] fstr = Arrays.stream(str).toArray(String[]::new);
        return Arrays.toString(fstr);
    }

//    public String[] departmentTotalEmployees(){
//
//        for (int i = 0; i < this.company.length; i++){
//
//        }
//    }
}
