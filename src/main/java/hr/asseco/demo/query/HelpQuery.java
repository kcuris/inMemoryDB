package hr.asseco.demo.query;

import hr.asseco.demo.database.StudentDAO;

public class HelpQuery extends AbstractStudentQuery{

    public HelpQuery(String[] arguments){
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
            System.out.println("\nSupported commands: ");
            System.out.println("	create <jmbag> <name> <surname> <grade1to5> ");
            System.out.println("	read <jmbag> ");
            System.out.println("	update <jmbag> <name> <surname> <grade1to5> ");
            System.out.println("	delete <jmbag> ");
            System.out.println("	filter-grade <grade1to5> <relation>    ");
            System.out.println("		Relation can be: \"l\", \"g\", \"e\" which stands for less, greater, equal ");
            System.out.println("	filter-name <beginningOfName> [case] ");
            System.out.println("		argument [case] is optional and can be \"-u\" or \"-l\" for upper or lower case ");
            System.out.println("	list  - lists all records in database ");
            System.out.println("	man   - shows this help text ");
            System.out.println("	exit  - exits the application and saves CSV file ");
            System.out.print("\nEnter the command (man for help) ->");
        }
    }

    private boolean isCommandValid() {
        return true;
    }

}
