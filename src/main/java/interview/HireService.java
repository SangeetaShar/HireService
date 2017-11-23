package interview;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.StampedLock;


/*
 * Main hire service class
 */
public class HireService {
    private DbService db;
    private String cd;

    //called by rest servlet to make booking
    public long hire(String name, String licenseNumber, String reg, String start, int days, double rate) throws SQLException {
       // boolean hired = false;
        Date startDate = Date.valueOf(start);
        long hireNumber = System.nanoTime();//use as unique id
        Car car = getCarDetails(reg, cd);
        synchronized (car) {
            if (car == null || car.isHired()) {
                System.out.println(name + " Sorry your request for car registration number " + car.getReg() + " can not be complete as it is already hired");
                return -1;
            }
            car.hire(db, cd, startDate, days, hireNumber);
        }
        HireRecord hire = new HireRecord(car, name, startDate, days, rate, hireNumber); //HireRecord - Immutable
        Client client = getClient(name, licenseNumber, cd);
        client.addHirerecrd(hire);
        System.out.println(client + " Congratulation you have hired car with registration " + car.getReg() + " and hire number:: " + car.getHireNumber());
        return hireNumber;

    }

    public void markReturned(long hireno)  {
        try {
            //Car car = (Car) db.loadFromDb(cd, "select * from crs where hrrnm = " + hireno, Car.class);
            Car car = (Car) db.loadFromDb(cd, ""+hireno, Car.class);
            car.release(db, cd);
            db.delete(hireno+"", Car.class, cd);
            System.out.println("car with Hireno "+hireno+ " marked as returned " + car);
        } catch (SQLException e) {
//nothing we can do
        }
    }
    public Car getCarDetails(String rg, String cd)  {
        try {
            //(Car) db.loadFromDb(cd, "select * from crs where rg = " + rg,Car.class);
            Car car = (Car) db.loadFromDb(cd, rg, Car.class);
            return car;
        } catch (SQLException e) {
            return new Car(rg);
        }
    }
    //dont use
    private DbService getDb() {
        return db;
    }

    public void setDb(DbService db) {
        this.db = db;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public Client getClient(String name, String licenseNumber, String cd)  {
        Client client;
        try {
            //client = (Client) db.loadFromDb(cd, "select * from clients where clientId = " + name,Client.class);
            client = (Client) db.loadFromDb(cd, name,Client.class);
        } catch (Exception e1) {
            throw new RuntimeException();
        }
        if(client == null) {
            client = new Client(name,licenseNumber,cd);
            try {
                db.saveToDatabase(client, cd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }
}
