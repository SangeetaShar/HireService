package interview;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Vector;

public class Client {
    private String name;
    private String licenseNumber;
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
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o, true);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    @Override
    public String toString() {
        return name + licenseNumber;
    }
}
