package hr.asseco.demo.query;

import hr.asseco.demo.database.StudentDAO;
import hr.asseco.demo.model.Student;

import java.util.Map;

public class GradeFilterQuery extends AbstractStudentQuery{

    public GradeFilterQuery(String[] arguments){
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
            String relation = arguments[2].toLowerCase();
            int grade;
            try{
                grade = Integer.parseInt(arguments[1]);
                if (grade < 1 || grade > 5){
                    System.out.println("Error: Grade must be 1-5.");
                    System.out.print("\nEnter the command (man for help) ->");
                    return;
                }
            } catch (Exception e){
                System.out.println("Error: Grade must be 1-5.");
                System.out.print("\nEnter the command (man for help) ->");
                return;
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
        }
    }

    private boolean isCommandValid() {
        if (arguments.length<3) {
            System.out.println("Missing arguments");
            System.out.print("\nEnter the command (man for help) ->");
            return false;
        }
        return true;
    }

}
