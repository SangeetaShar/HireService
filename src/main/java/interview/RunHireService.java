package interview;

import java.sql.SQLException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by 43996861 on 22/11/2017.
 */
public class RunHireService {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException, SQLException {
        long hire =0;
        DbServiceImpl db = new DbServiceImpl();
        HireService hireService  = new HireService();
        hireService.setDb(db);
        hireService.setCd("cd");

        final CyclicBarrier gate = new CyclicBarrier(5);
        HireRunnable hr1 = new HireRunnable("HR1", hireService, gate,"test1","l1","reg1");
        HireRunnable hr2 = new HireRunnable("HR2", hireService, gate,"test1","l1","reg1");
        HireRunnable hr3 = new HireRunnable("HR3", hireService, gate,"test1","l1","reg1");
        HireRunnable hr4 = new HireRunnable("HR4", hireService, gate,"test1","l1","reg1");
        hr1.start();
        hr2.start();
        hr3.start();
        hr4.start();

        gate.await();
        System.out.println("all threads started");

        Thread.sleep(1000);
        if(hr1.getHire()>0){
            hire = hr1.getHire();
        }
        if(hr2.getHire()>0){
            hire = hr2.getHire();
        }
        if(hr3.getHire()>0){
            hire = hr3.getHire();
        }
        if(hr4.getHire()>0){
            hire = hr4.getHire();
        }
        System.out.println("Car with Registration rg1 is hired with Hire number " + hire);
        hireService.hire("test1", "l1", "reg1", "2017-11-22", 4, 30.0);

        /*System.out.println("Car with Registration rg1 is now marked as Returned and ready for Hire");
        hireService.markReturned("cd1", hire);*/

        HireReturnedRunnable hrr1 = new HireReturnedRunnable("HRR1", hireService, gate,hire);
        hr2 = new HireRunnable("HR2", hireService, gate,"test1","l1","reg1");
        hr3 = new HireRunnable("HR3", hireService, gate,"test1","l1","reg1");
        hr4 = new HireRunnable("HR4", hireService, gate,"test1","l1","reg1");
        hrr1.start();
        hr2.start();
        hr3.start();
        hr4.start();
        gate.await();
    }

}
