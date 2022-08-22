package com.shameem.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee implements IEmplotee {
    protected final NumberFormat moneyFormat= NumberFormat.getCurrencyInstance();
    protected final DateTimeFormatter dtFormatter =DateTimeFormatter.ofPattern("M/d/yyyy");
    private static  String PEOPLE_REGEX = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    public static final Pattern PEOPLE_PAT = Pattern.compile(Employee.PEOPLE_REGEX);
    protected final Matcher peopleMat;
    protected String lastName;
    protected String firstName;
    protected LocalDate dob;

    private Employee(){
        peopleMat=null;
        this.firstName="N/A";
        this.lastName="N/A";
        this.dob=null;
    }


    public Employee(String personText) {
        peopleMat = PEOPLE_PAT.matcher(personText);
        if (peopleMat.matches()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
        }
    }

    public abstract int  getSalary(); //When a class is abstract then the implementation has to be done in the subclass

    public double getBonus(){
        return getSalary()*1.10;
    }

    public static final IEmplotee createEmployee(String employeeText){
        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(employeeText);
        if(peopleMat.find()){
            return switch (peopleMat.group("role")){
                case "Programmer" -> new Programmer(employeeText);
                case "Analyst" -> new Analyst(employeeText);
                case "Manager" -> new Manager(employeeText);
                case "CEO" -> new CEO(employeeText);
                default -> () -> 0; //This is a lamba experssion in Java. It looks for method without any arguments and returns an int in this case and finds this in the interface
            };
        } else {
            return new DummyEmployee();
        }
    }

    @Override
    public String toString() {
        return String.format("%s ,%s : %s - %s",lastName,firstName,moneyFormat.format(getSalary()),moneyFormat.format(getBonus()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return lastName.equals(employee.lastName) && firstName.equals(employee.firstName) && dob.equals(employee.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, dob);
    }

    private static final class DummyEmployee extends  Employee {
        @Override
        public  int getSalary(){
            return 0;
        }
    }
}
