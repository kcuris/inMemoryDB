package hr.asseco.demo.database;

import hr.asseco.demo.model.Student;

import java.util.Map;
import java.util.stream.Collectors;

public class StudentDAO {
    private final Database db;

    public StudentDAO(Database db){
        this.db = db;
    }
    public StudentDAO(){
        this.db = Database.getInitialisedInstance();
    }

    public Student createStudent(String jmbag, String ime, String prezime, String ocjena){
        if (db.get(jmbag) == null){
        Student newStudent = new Student(jmbag, ime, prezime, ocjena);
            db.put( newStudent);
        return newStudent;
        }else{
            System.out.println("Can't create, student with JMBAG " +jmbag+" exists.");
            return null;
        }
    }

    public Student readStudent(String jmbag){
        return db.getStudents().get(jmbag);
    }

    public Student updateStudent(String jmbag, String ime, String prezime, String ocjena){
        if (db.get(jmbag) != null){
            Student newStudent = new Student(jmbag, ime, prezime, ocjena);
                db.put(newStudent);
            return newStudent;
        }else{
            System.out.println("Can't update, student with JMBAG " +jmbag+" doesn't exist.");
            return null;
        }
    }

    public void delete(String jmbag){
        synchronized (db) {
            db.getStudents().remove(jmbag);
        }
    }

    public Map<String, Student> filterByName(String sample){
            Map<String, Student> result =  db.getStudents().entrySet()
                .stream()
                .filter(map -> (map.getValue().getIme().toLowerCase().startsWith(sample.toLowerCase())))
                .collect(Collectors.toMap(map -> map.getKey(), map ->map.getValue()));
            return result;
    }

    public Map<String, Student> filterByGradeEQ(int sample){
        Map<String, Student> result =  db.getStudents().entrySet()
                .stream()
                .filter(map -> (map.getValue().getOcjena() == sample))
                .collect(Collectors.toMap(map -> map.getKey(), map ->map.getValue()));
        return result;
    }

    public Map<String, Student> filterByGradeLT(int sample){
        Map<String, Student> result =  db.getStudents().entrySet()
                .stream()
                .filter(map -> (map.getValue().getOcjena() < sample))
                .collect(Collectors.toMap(map -> map.getKey(), map ->map.getValue()));
        return result;
    }

    public Map<String, Student> filterByGradeGT(int sample){
        Map<String, Student> result =  db.getStudents().entrySet()
                .stream()
                .filter(map -> (map.getValue().getOcjena() > sample))
                .collect(Collectors.toMap(map -> map.getKey(), map ->map.getValue()));
        return result;
    }

    public void close(){
        db.close();
    }

    public void listAll() {
        db.listAll();
    }
}
