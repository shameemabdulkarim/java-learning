package com.shameem.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Employee {

    private int organizationSize = 0;
    private int directReporting = 0;


    private final String managerRegex = "\\w+=(?<orgSize>\\w+),\\w+=(?<dr>\\w+)";
    private final Pattern managerPat = Pattern.compile((managerRegex));

    public Manager(String personText) {
        super(personText);
            Matcher managerMat = managerPat.matcher(peopleMat.group("details"));
            if (managerMat.matches()) {
                this.organizationSize = Integer.parseInt(managerMat.group("orgSize"));
                this.directReporting = Integer.parseInt(managerMat.group("dr"));
            }

    }
    public int getSalary(){
        return 3500+ organizationSize *directReporting;
    }

}

