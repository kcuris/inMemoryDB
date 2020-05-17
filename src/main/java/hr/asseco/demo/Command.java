package hr.asseco.demo;

import hr.asseco.demo.database.StudentDAO;
import hr.asseco.demo.model.Student;

import java.util.Map;

// bad example - not readable so implementation is refactored using factory pattern
@Deprecated
public class Command extends Thread implements Runnable{
    private StudentDAO dao;
    private String cmd;

    @Override
    public void run() {
//        added for demonstration of multithreading
        System.out.println("\nRunning thread" +Thread.currentThread().getId());
        System.out.println("5 seconds delay...");
        System.out.print("\nEnter the command (man for help) ->");
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        processCommand(cmd);
    }

    public Command( StudentDAO dao, String cmd) {
        this.dao = dao;
        this.cmd = cmd;
    }

    public synchronized void processCommand (String command){
        // normalize command
        command = command.trim();
        while (command.indexOf("  ") >= 0){
            command = command.replace("  ", " ");
        }

        String[] arguments = command.split(" ");
        String cmd = arguments[0];

        switch(cmd) {
            case "read":{
                if (arguments.length<2) {
                    System.out.println("Missing argument");
                    System.out.print("\nEnter the command (man for help) ->");
                    break;
                }
                System.out.println("\nFetched student: " + dao.readStudent(arguments[1]));
                System.out.print("\nEnter the command (man for help) ->");
                break;
            }
            case "create":{
                if (arguments.length<5) {
                    System.out.println("Missing arguments");
                    System.out.print("\nEnter the command (man for help) ->");
                    break;
                }
                if (dao.readStudent(arguments[1]) != null) {
                    System.out.println("\nStudent with this JMBAG allredy exists!");
                    break;
                }
                System.out.println( "\nStudent added: " + dao.createStudent(arguments[1], arguments[2], arguments[3], arguments[4]));
                System.out.print("\nEnter the command (man for help) ->");
                break;
            }
            case "update":{
                if (arguments.length<5) {
                    System.out.println("Missing arguments");
                    System.out.print("\nEnter the command (man for help) ->");
                    break;
                }
                System.out.println( "\nStudent updated: " + dao.updateStudent(arguments[1], arguments[2], arguments[3], arguments[4]));
                System.out.print("\nEnter the command (man for help) ->");
                break;
            }
            case "delete":{
                if (arguments.length<2) {
                    System.out.println("Missing argument");
                    System.out.print("\nEnter the command (man for help) ->");
                    break;
                }
                    dao.delete(arguments[1]);
                System.out.print("\nStudent"+arguments[1]+" deleted. \nEnter the command (man for help) ->");
                break;
            }
            case "filter-name":{
                if (arguments.length<2) {
                    System.out.println("Missing arguments");
                    System.out.print("\nEnter the command (man for help) ->");
                    break;
                }
                Map<String, Student> filtered = dao.filterByName(arguments[1]);
                if (arguments.length > 2){
                    switch(arguments[2].toLowerCase()){
                        case "-l":{
                            filtered.forEach((k,v)->System.out.println(v.getIme().toLowerCase() + " " + v.getPrezime().toLowerCase()));
                            break;
                        }
                        case "-u":{
                            filtered.forEach((k,v)->System.out.println(v.getIme().toUpperCase() + " " + v.getPrezime().toUpperCase()));
                            break;
                        }
                        default:{
                            System.out.println("\nError: case argument can be only -l or -u.");
                        }
                    }
                }
                else{
                    filtered.forEach((k,v)->System.out.println(v.getIme() + " " + v.getPrezime()));
                }
                System.out.print("\nEnter the command (man for help) ->");
                break;
            }
            case "filter-grade":{
                if (arguments.length<3) {
                    System.out.println("Missing arguments");
                    System.out.print("\nEnter the command (man for help) ->");
                    break;
                }
                String relation = arguments[2].toLowerCase();
                int grade;
                try{
                    grade = Integer.parseInt(arguments[1]);
                    if (grade < 1 || grade > 5){
                        System.out.println("Error: Grade must be 1-5.");
                        System.out.print("\nEnter the command (man for help) ->");
                        break;
                    }
                } catch (Exception e){
                    System.out.println("Error: Grade must be 1-5.");
                    System.out.print("\nEnter the command (man for help) ->");
                    break;
                }
                Map<String, Student> filtered = null;
                switch (relation){
                    case "l":{
                        filtered = dao.filterByGradeLT(grade);
                        break;
                    }
                    case "g":{
                        filtered = dao.filterByGradeGT(grade);
                        break;
                    }
                    case "e":{
                        filtered = dao.filterByGradeEQ(grade);
                        break;
                    }
                    default : {
                        System.out.println("Error: Relation must be one of \"l g e\" ");
                    }
                }
                if (filtered != null){
                    filtered.forEach((k,v)->System.out.println(v.toString()));
                }else{
                    System.out.println("No records found by criteria.");
                }
                System.out.print("\nEnter the command (man for help) ->");
                break;
            }
            case "exit":{
                dao.close();
                break;
            }
            case "list":{
                dao.listAll();
                System.out.print("\nEnter the command (man for help) ->");
                break;
            }
            case "man":{
                man();
                System.out.print("\nEnter the command (man for help) ->");
                break;
            }
            default:
                System.out.println("Unrecognized command. Type 'man' for help.");
                System.out.print("\nEnter the command (man for help) ->");
        }
    }

    public static  void man(){
        System.out.println("\nSupported commands: ");
        System.out.println("	create <jmbag> <name> <surname> <grade1to5> ");
        System.out.println("	read <jmbag> ");
        System.out.println("	update <jmbag> <name> <surname> <grade1to5> ");
        System.out.println("	delete <jmbag> ");
        System.out.println("	filter-grade <grade1to5> <relation>    ");
        System.out.println("		Relation can be: \"l\", \"g\", \"e\" which stands for less, greater, equal ");
        System.out.println("	filter-name <beginningOfName> [case] ");
        System.out.println("		argument [case] is optional and can be \"-u\" or \"-l\" for upper or lower case ");
        System.out.println("	list  - lists all records in database ");
        System.out.println("	man   - shows this help text ");
        System.out.println("	exit  - exits the application and saves CSV file ");
    }
}
