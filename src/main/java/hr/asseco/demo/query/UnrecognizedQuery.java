package hr.asseco.demo.query;

import hr.asseco.demo.database.StudentDAO;

public class UnrecognizedQuery extends AbstractStudentQuery{

    public UnrecognizedQuery(String[] arguments){
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
            System.out.println("Unrecognized command. Type 'man' for help.");
            System.out.print("\nEnter the command (man for help) ->");
        }
    }

    private boolean isCommandValid() {
        return true;
    }

}
