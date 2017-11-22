package interview;

import java.util.Vector;

public class Client {
    private String name;
    String licenseNumber;
    private String cd;
    private Vector records;
    public Client(String name, String licenseNumber, String cd) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.cd = cd;
    }

    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getCd() {
        return cd;
    }

    public Vector getRecords() {
        return records;
    }

    public void addHirerecrd(HireRecord r) {
        if(records == null) {
            records = new Vector();
        }
        records.add(r);
    }

    @Override
    public String toString() {
        return name + licenseNumber;
    }
}
