package hr.asseco.demo.query;

import hr.asseco.demo.database.StudentDAO;

public class DeleteQuery extends AbstractStudentQuery{

    public DeleteQuery(String[] arguments){
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
            System.out.print("\nStudent"+arguments[1]+" deleted. \nEnter the command (man for help) ->");
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
