package com.shameem.employees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Streamsstuff {

    public static void main(String[] args) {
         String peopleText = """
               Flinstone, Fred, 1/1/1900,Programmer, {locpd=2000,yoe=10,iq=140}
               Flinstone2, Fred2, 1/1/1900,Programmer,{locpd=1300,yoe=14,iq=100}
               Flinstone2, Fred, 1/1/1900,Programmerzzzzzz,{locpd=1300,yoe=14,iq=100}
               Flinstone3, Fred, 1/1/1900,Programmer,{locpd=2300,yoe=8,iq=105}
               Rubble, Barney, 2/2/1905, Manager,{orgSize=300,dr=10}
               Rubble2, Barney, 2/2/1905, Manager,{orgSize=100,dr=4}
               """;

//       Stream<String>  data= peopleText.lines();
//       data.forEach(System.out::println);

      int sum = peopleText
               .lines()
               .map(e->Employee.createEmployee(e))
               .mapToInt(Streamsstuff::showEmpAndSalary)
                .sum();
       System.out.println(sum);
//
//      try{
//        Files.lines(Path.of("/Users/shameemsuzy/Documents/Java Project-Starter/Employee/src/main/java/com/shameem/employees/employees"))
//               .map(Employee::createEmployee)
//                .forEach(System.out::println);
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
    }
    public static int showEmpAndSalary(IEmplotee e) {
        System.out.println(e);
        return e.getSalary();
    }

}
