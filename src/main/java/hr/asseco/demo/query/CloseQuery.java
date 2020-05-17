package hr.asseco.demo.query;

import hr.asseco.demo.database.StudentDAO;

public class CloseQuery extends AbstractStudentQuery{

    public CloseQuery(String[] arguments){
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
            dao.close();
        }
    }

    private boolean isCommandValid() {
        return true;
    }

}
