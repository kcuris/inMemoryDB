package hr.asseco.demo.query;

import hr.asseco.demo.database.StudentDAO;

public class ListAllQuery extends AbstractStudentQuery{

    public ListAllQuery(String[] arguments){
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
            dao.listAll();
            System.out.print("\nEnter the command (man for help) ->");
        }
    }

    private boolean isCommandValid() {
        return true;
    }

}
