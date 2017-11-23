package interview;

import java.sql.SQLException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by 43996861 on 23/11/2017.
 */
public class HireRunnable extends Thread {

    private final HireService hireService;
    private final CyclicBarrier gate;
    private final String clientName;
    private final String licenseNumber;
    private final String carReg;
    private long hire;

    public HireRunnable(final String threadName, final HireService hireService, final CyclicBarrier gate, final String clientName,
                        final String licenseNumber, final String carReg) {
        super(threadName);
        this.hireService=hireService;
        this.gate = gate;
        this.clientName = clientName;
        this.licenseNumber= licenseNumber;
        this.carReg = carReg;
    }

    @Override
    public void run() {
        try {
            gate.await();
            hire = hireService.hire(clientName, licenseNumber, "cd1", carReg, "2017-11-22",4, 30.0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getHire() {
        return hire;
    }
}
