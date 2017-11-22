package interview;

import org.joda.time.DateTime;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class Car {
    public String make;
    public String reg;
    public int category;
    public boolean hired;
    public Date hireEnd;
    private int age;
    private long hireNumber;

    public Car(String reg) {
        this.reg = reg;
    }

    public Car(String reg, String make, int category) {
        this.make = make;
        this.reg = reg;
        this.category = category;
    }

    public int getAge() {
        return 2015 - Integer.parseInt("20" +reg.substring(3, 2));
    }
    public void hire(DbService dbService, String cd, HireRecord record) throws SQLException {
        hireEnd = new DateTime(record.getStartDate().getTime()).plusDays(record.getDays()).toDate();
        hireNumber = record.getHireno();
        Calendar.getInstance();
        dbService.saveToDatabase(this, cd);
    }
    public void release(DbService dbService, String cd) throws SQLException {
        hireNumber = 0;
        hireEnd = null;
        dbService.saveToDatabase(this, cd);
    }
    //disabled
    public Car(String make, String reg, int category, boolean hired,Date hireEnd) {
// this.make = make;
// this.reg = reg;
// this.category = category;
// this.hired = hired;
// this.hireEnd = hireEnd;
        throw new RuntimeException("disabled full constructor");
    }
}
