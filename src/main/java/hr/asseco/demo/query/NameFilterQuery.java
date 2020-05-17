package hr.asseco.demo.query;

import hr.asseco.demo.database.StudentDAO;
import hr.asseco.demo.model.Student;
import hr.asseco.demo.util.Constants;

import java.util.Map;

public class NameFilterQuery extends AbstractStudentQuery{

    public NameFilterQuery(String[] arguments){
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

        }
    }

    private boolean isCommandValid() {
        if (arguments.length<2) {
            System.out.println("Missing arguments");
            System.out.print("\nEnter the command (man for help) ->");
            return false;
        }
        return true;
    }

}
