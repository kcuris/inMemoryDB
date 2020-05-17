package hr.asseco.demo.query;

public class QueryFactory {

    public AbstractStudentQuery getStudentQuery(String command)
    {
        // normalize command
        command = command.trim();
        while (command.indexOf("  ") >= 0){
            command = command.replace("  ", " ");
        }

        String[] arguments = command.split(" ");
        String cmd = arguments[0];

        switch(cmd) {
            case "create":{
                return new CreateQuery(arguments);
            }
            case "read":{
                return new ReadQuery(arguments);
            }
            case "update":{
                return new UpdateQuery(arguments);
            }
            case "delete":{
                return new DeleteQuery(arguments);
            }
            case "filter-name":{
                return new NameFilterQuery(arguments);
            }
            case "filter-grade":{
                return new GradeFilterQuery(arguments);
            }
            case "exit":{
                return new CloseQuery(arguments);
            }
            case "list":{
                return new ListAllQuery(arguments);
            }
            case "man":{
                return new HelpQuery(arguments);
            }
            default:
                return new UnrecognizedQuery(arguments);
        }
    }
}
