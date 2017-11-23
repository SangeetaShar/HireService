package interview;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.DateTime;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class Car {
    private String make;
    private String reg;
    private int category;
    private volatile boolean hired;
    private Date hireEnd;
    private int age;
    private volatile long hireNumber;

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
    public void hire(DbService dbService, String cd, Date startDate, int days, long hireNumber) throws SQLException {
        this.hired = true;
        hireEnd = new DateTime(startDate.getTime()).plusDays(days).toDate();
        this.hireNumber = hireNumber;
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

    public String getMake() {
        return make;
    }

    public String getReg() {
        return reg;
    }

    public int getCategory() {
        return category;
    }

    public boolean isHired() {
        return hired;
    }

    public Date getHireEnd() {
        return hireEnd;
    }

    public long getHireNumber() {
        return hireNumber;
    }

    @Override
    public boolean equals(Object o) {
       return EqualsBuilder.reflectionEquals(this, o, true);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", reg='" + reg + '\'' +
                ", category=" + category +
                ", hired=" + hired +
                ", hireEnd=" + hireEnd +
                ", age=" + age +
                ", hireNumber=" + hireNumber +
                '}';
    }
}
