package hr.asseco.demo.query;

import hr.asseco.demo.database.StudentDAO;

public class UpdateQuery extends AbstractStudentQuery{

    public UpdateQuery(String[] arguments){
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
            System.out.println( "\nStudent updated: " + dao.updateStudent(arguments[1], arguments[2], arguments[3], arguments[4]));
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
