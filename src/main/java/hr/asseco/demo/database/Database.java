package hr.asseco.demo.database;

import hr.asseco.demo.model.Student;

import java.io.*;
import java.util.HashMap;

public class Database {

    private static Database instance;
    private static String path;
    private static long rowCnt = 0;
    private static HashMap<String, Student> students;

    // Initialize HashMap and load Students from file to HashMap
    private Database(String dbFile){
        initialize(dbFile);
    }

    public static synchronized Database getInstance(String dbFile){
        if(instance == null){
            instance = new Database(dbFile);
        }
        return instance;
    }
    public static synchronized Database getInitialisedInstance(){
        return instance;
    }

    private void initialize(String dbFile) {
        path = dbFile;
        students = new HashMap<String, Student>();
        System.out.println("Loading data from file " + path);
        BufferedReader br = null;
        String record = "";
        try {
            br = new BufferedReader(new FileReader(path));
            while ((record = br.readLine()) != null) {
                rowCnt ++;
                Student s = Student.parseCSVrecord(record);
                if (s !=null){
                    students.put(s.getJmbag(), s);
                }
            }
            dump();
        } catch (FileNotFoundException e) {
            System.out.println("Specified CSV file was not found. Creating a new empty database");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void listAll(){
        System.out.println("\n--- database content: ------------");
        students.forEach((k,v)->System.out.println(v.toString()));
        System.out.println("----------------------------------");
        System.out.println("Database contains " + students.size() + " data records.");
    }

    public void dump(){
        System.out.println("\n--- database content: ------------");
        students.forEach((k,v)->System.out.print(v.toRecord()));
        System.out.println("----------------------------------");
        System.out.println("File contains "+ rowCnt + " rows. Loaded " + students.size() + " data records.");
    }

    public static HashMap<String, Student> getStudents() {
        return students;
    }

    public void close(){
        StringBuilder sb = new StringBuilder();
        students.forEach((k,v)-> sb.append(v.toRecord()));
        try {
            FileWriter fw = new FileWriter(path, false);
            fw.write(sb.toString());
            fw.flush();
            fw.close();
            System.out.println("Data saved to file: "+ path );
        } catch (IOException ignored) {
            System.out.println("Error: Data save failed!");
        }
    }

    public Student get(String jmbag){
        return students.get(jmbag);
    }

    public Student put(Student s){
        synchronized(students){
            return students.put(s.getJmbag(), s);
        }
    }



}
