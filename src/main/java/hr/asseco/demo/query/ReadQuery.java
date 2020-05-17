package hr.asseco.demo.query;

import hr.asseco.demo.database.StudentDAO;

public class ReadQuery extends AbstractStudentQuery{

    public ReadQuery(String[] arguments){
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
            System.out.println("\nFetched student: " + dao.readStudent(arguments[1]));
            System.out.print("\nEnter the command (man for help) ->");
        }
    }

    private boolean isCommandValid() {
        if (arguments.length < 2) {
            System.out.println("Missing argument");
            System.out.print("\nEnter the command (man for help) ->");
            return false;
        }
        return true;
    }

}
