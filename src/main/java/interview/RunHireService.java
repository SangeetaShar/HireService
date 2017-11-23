package interview;

import java.sql.SQLException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by 43996861 on 22/11/2017.
 */
public class RunHireService {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        DbServiceImpl db = new DbServiceImpl();

        final CyclicBarrier gate = new CyclicBarrier(5);
        HireService hireService  = new HireService();
        hireService.setDb(db);

        Thread t1 = new Thread(){
            public void run(){
                try {
                    gate.await();
                    String hire = hireService.hire("test1", "l1", "cd1", "reg1", "2017-11-22",3, 30.0);
                    System.out.println(" thread: " + getName() + " Message:: " + hire);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //do stuff
            }};
        Thread t2 = new Thread(){
            public void run(){
                try {
                    gate.await();
                    String hire = hireService.hire("test2", "l2", "cd1", "reg1", "2017-11-22",2, 30.0);
                    System.out.println(" thread: " + getName() + " Message:: " + hire);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //do stuff
            }};
        Thread t3 = new Thread(){
            public void run(){
                try {
                    gate.await();
                    String hire = hireService.hire("test3", "l3", "cd1", "reg1", "2017-11-22",1, 30.0);
                    System.out.println(" thread: " + getName() + " Message:: " + hire);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //do stuff
            }};
        Thread t4 = new Thread(){
            public void run(){
                try {
                    gate.await();
                    String hire = hireService.hire("test4", "l4", "cd1", "reg1", "2017-11-22",4, 30.0);
                    System.out.println(" thread: " + getName() + " Message:: "  + hire);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //do stuff
            }};

        t1.start();
        t2.start();
        t3.start();
        t4.start();

// At this point, t1 and t2 are blocking on the gate.
// Since we gave "3" as the argument, gate is not opened yet.
// Now if we block on the gate from the main thread, it will open
// and all threads will start to do stuff!

        gate.await();
        System.out.println("all threads started");

        //new SimpleThread("test1","l1","reg1", 30.0, 2, db).start();
        //new SimpleThread("test2", "l1", "reg1", 30.0, 3, db).start();

    }
}
