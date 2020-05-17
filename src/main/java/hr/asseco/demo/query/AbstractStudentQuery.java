package hr.asseco.demo.query;

import hr.asseco.demo.database.StudentDAO;
import hr.asseco.demo.util.Constants;

public abstract class AbstractStudentQuery extends Thread implements StudentQuery{

    protected String[] arguments;
    protected StudentDAO dao;

    protected void doDemoDelay(){
        //        added for demonstration of multithreading
        System.out.println("\nRunning thread" +Thread.currentThread().getId());
        System.out.println("" + Constants.DEMO_DELAY_TIME_MS/1000 +" seconds delay...");
        System.out.print("\nEnter the command (man for help) ->");
        try {
            Thread.currentThread().sleep(Constants.DEMO_DELAY_TIME_MS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
