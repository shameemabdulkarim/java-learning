package com.shameem.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee {

    private int linesOfCode=0;
    private int yearsOfExp=0;
    private int iq=0;

    private final String pgmRegex = "\\w+=(?<locpd>\\w+),\\w+=(?<yoe>\\w+),\\w+=(?<iq>\\w+)";
    private final Pattern pgmPat =Pattern.compile((pgmRegex));

    public Programmer(String personText) {
            super(personText);
            Matcher pgmMat =pgmPat.matcher(peopleMat.group("details"));
            if (pgmMat.matches()){
                this.linesOfCode=Integer.parseInt(pgmMat.group("locpd"));
                this.yearsOfExp=Integer.parseInt(pgmMat.group("yoe"));
                this.iq=Integer.parseInt(pgmMat.group("iq"));
            }
        }

    public int getSalary(){
        return 3000+ linesOfCode *yearsOfExp*iq;
    }

}

