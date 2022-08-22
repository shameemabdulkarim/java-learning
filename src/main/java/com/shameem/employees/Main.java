package com.shameem.employees;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

        public static void main(String[] args) {
            String people = """
            Flinstone, Fred, 1/1/1900,Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone2, Fred2, 1/1/1900,Programmer,{locpd=1300,yoe=14,iq=100}
            Flinstone2, Fred, 1/1/1900,Programmerzzzzzz,{locpd=1300,yoe=14,iq=100}
            Flinstone3, Fred, 1/1/1900,Programmer,{locpd=2300,yoe=8,iq=105}
            Flinstone4, Fred, 1/1/1900,Programmer,{locpd=1630,yoe=3,iq=115}
            Flinstone5, Fred, 1/1/1900,Programmer,{locpd=5,yoe=10,iq=100}
            Rubble, Barney, 2/2/1905, Manager,{orgSize=300,dr=10}
            Rubble2, Barney, 2/2/1905, Manager,{orgSize=100,dr=4}
            Rubble3, Barney3, 2/2/1905, Manager,{orgSize=200,dr=2}
            Rubble4, Barney, 2/2/1905, Manager,{orgSize=500,dr=8}
            Rubble5, Barney, 2/2/1905, Manager,{orgSize=175,dr=20}
            Flinstone, Wilma1, 3/3/1910, Analyst,{projectCount=4}
            Flinstone2, Wilma, 3/3/1910, Analyst,{projectCount=5}
            Flinstone3, Wilma, 3/3/1910, Analyst,{projectCount=6}
            Flinstone4, Wilma, 3/3/1910, Analyst,{projectCount=9}
            Flinstone5, Wilma, 3/3/1910, Analyst,{projectCount=7}
            Rubble, Betty, 4/4/1915,CEO,{avgStockPrice=300}
            """;

            Matcher PeopleMat = Employee.PEOPLE_PAT.matcher(people);
            //Flyer flyer = new CEO("");
            //flyer.fly();

            int totalSalary=0;
            IEmplotee employee=null;
            List<IEmplotee> employees = new LinkedList<>(); //Arraylist collection

            while (PeopleMat.find()) {
                employee=  Employee.createEmployee(PeopleMat.group());
                employees.add(employee);
            }



            List<String> removalNames= List.of("Wilma1","Barney3","Fred2");
//            removalNames.add("Wilma1");
//            removalNames.add("Barney3");
//            removalNames.add("Fred2");
            removeUndesirables(employees,removalNames);


            for(IEmplotee worker :employees){  //looping over collection
                System.out.println(worker.toString());
                totalSalary += worker.getSalary();
            }

            NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(); //To get the currencyformat based on the location
            System.out.printf("The total payout amount should be %s%n",currencyInstance.format(totalSalary));

        }

        private  static void removeUndesirables(List<IEmplotee> employees,List<String> removalNames){
            for (Iterator<IEmplotee> it =employees.iterator(); it.hasNext();){
                IEmplotee worker =it.next();
                if(worker instanceof Employee){
                    Employee tmpworker =(Employee) worker;
                    if(removalNames.contains(tmpworker.firstName)){
                        it.remove();
                    }
                }
            }
        }

    }

