package hr.asseco.demo;

import hr.asseco.demo.database.Database;
import hr.asseco.demo.database.StudentDAO;
import hr.asseco.demo.query.AbstractStudentQuery;
import hr.asseco.demo.query.QueryFactory;
import hr.asseco.demo.query.StudentQuery;

import java.util.Scanner;

public class Demo {

    public Demo(){}

    public static Database db;
    public static StudentDAO dao;
    private static final QueryFactory queryFactory = new  QueryFactory();

    public static void main(String[] args) {
            if (args.length != 1){
                System.out.println("Usage: java -jar Demo <full/path/to/CSV/file.csv>");
                return;
            }
            db = Database.getInstance(args[0]);
            dao = new StudentDAO(db);
            Demo demo = new Demo();
            demo.start();
        }

        private void start(){
            String command = "";
            Scanner in = new Scanner(System.in);
            while (!command.equals("exit")){
                System.out.print("\nEnter the command (man for help) ->");
                command = in.nextLine();
                AbstractStudentQuery studentQuery = queryFactory.getStudentQuery(command);
                studentQuery.start();

//                This call is deprecated ->
//                Command c = new Command(dao, command);
//                c.start();
        }

    }
}
