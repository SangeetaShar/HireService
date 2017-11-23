package interview;

import java.sql.SQLException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by 43996861 on 23/11/2017.
 */
public class HireReturnedRunnable extends Thread {

    private final HireService hireService;
    private final CyclicBarrier gate;
    private final long hireNumber;
    private long hire;

    public HireReturnedRunnable(final String threadName, final HireService hireService, final CyclicBarrier gate, final long hireNumber) {
        super(threadName);
        this.hireService=hireService;
        this.gate = gate;
        this.hireNumber = hireNumber;
    }

    @Override
    public void run() {
        try {
            gate.await();
            hireService.markReturned(hireNumber);
            System.out.println("Thread " + getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public long getHire() {
        return hire;
    }
}
