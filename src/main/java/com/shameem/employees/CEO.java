package com.shameem.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO extends Employee implements Flyer{
    private int averageStock = 0;
    private Flyer flyer =new Pilot(1000,true);

    private final String ceoRegex = "\\w+=(?<avgStockPrice>\\w+)";
    private final Pattern ceoPat =Pattern.compile((ceoRegex));

    public CEO(String personText) {
        super(personText);
            Matcher ceoMat = ceoPat.matcher(peopleMat.group("details"));
            if (ceoMat.matches()) {
                this.averageStock = Integer.parseInt(ceoMat.group("avgStockPrice"));

        }
    }
    public int getSalary(){
        return 5000  * averageStock ;
    }

    public void fly() {
        flyer.fly();
    }

    public int getHoursFlown() {
        return flyer.getHoursFlown();
    }

    public void setHoursFlown(int hoursFlown) {
        flyer.setHoursFlown(hoursFlown);
    }

    public boolean isIfr() {
        return flyer.isIfr();
    }

    public void setIfr(boolean ifr) {
        flyer.setIfr(ifr);
    }
}
