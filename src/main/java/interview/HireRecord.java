package interview;

import java.util.Date;

public class HireRecord {
    private Car car;
    private String client;
    private Date startDate;
    private int days;
    private double rate;
    private int state;
    private long hireno;

    public Car getCar() {
        return car;
    }
    public String getClient() {
        return client;
    }
    public Date getStartDate() {
        return startDate;
    }
    public int getDays() {
        return days;
    }
    public int getState() {
        return state;
    }
    public long getHireno() {
        return hireno;
    }
    public double getRate() {
        if(car instanceof Bike) return rate;  //no discount for bikes
        return this.rate = car.getAge() > 3 ? rate * 0.9 : rate;  //discount for older cars
    }

    public HireRecord(Car car, String client, Date startDate, int days, double rate, long hireno) {
        this.car = car;
        this.client = client;
        this.startDate = startDate;
        this.days = days;
        this.rate = rate;
        this.state = state;
        this.hireno = hireno;
    }
}
