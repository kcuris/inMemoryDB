package hr.asseco.demo.query;

import hr.asseco.demo.database.StudentDAO;

public class CreateQuery extends AbstractStudentQuery{

    public CreateQuery(String[] arguments){
        this.arguments = arguments;
        dao = new StudentDAO();
    }
    @Override
    public void run() {
        executeQuery();
    }

    @Override
    public void executeQuery() {
        if (isCommandValid()){
            doDemoDelay();
            if (dao.readStudent(arguments[1]) != null) {
                System.out.println("\nStudent with this JMBAG allredy exists!");
                return;
            }
            System.out.println( "\nStudent added: " + dao.createStudent(arguments[1], arguments[2], arguments[3], arguments[4]));
            System.out.print("\nEnter the command (man for help) ->");
        }
    }

    private boolean isCommandValid() {
        if (arguments.length<5) {
            System.out.println("Missing arguments");
            System.out.print("\nEnter the command (man for help) ->");
            return false;
        }
        return true;
    }

}
