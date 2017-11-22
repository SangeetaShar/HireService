package interview;

import java.sql.SQLException;

/**
 * Created by 43996861 on 22/11/2017.
 */
public class SimpleThread extends Thread {
    public HireService hireService;
    private String licenseNumber;
    private String regNumber;
    private double rate;
    private int days;

    public SimpleThread(String name, String licenseNumber, String regNumber, double rate, int days, DbServiceImpl db) {
        super(name);
        hireService = new HireService();

        hireService.setDb(db);
        this.licenseNumber = licenseNumber;
        this.regNumber = regNumber;
        this.rate= rate;
        this.days = days;

    }
    public void run() {
        try {
            long hire = hireService.hire(getName(), licenseNumber, "cd1", regNumber, "2017-11-22",days, rate);
            System.out.println(" thread: " + getName() + " long hire" + hire);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DONE! thread: " + getName());


    }
}

