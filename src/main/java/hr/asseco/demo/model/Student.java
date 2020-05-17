package hr.asseco.demo.model;

import hr.asseco.demo.util.Constants;

import java.util.Objects;
import java.util.regex.Pattern;

public class Student {
    private static final Pattern pattern = Pattern.compile(Constants.JMBAG_PATTERN);

// fields:
//  jbbag is unique and is used as a primary key
    private String jmbag;
    private String ime;
    private String prezime;
    private int ocjena;
    
    public Student(){};

    public Student(String jmbag, String ime, String prezime, String ocjena) throws IllegalArgumentException{
            setJmbag(jmbag);
            setIme(ime);
            setPrezime(prezime);
            setOcjena(ocjena);
    }

    public static Student parseCSVrecord (String csvRecord) throws IllegalArgumentException{
        Student student = new Student();
        try{
            String[] studentRecord = csvRecord.split(Constants.SEPARATOR);
            if (studentRecord.length > 4) throw new IndexOutOfBoundsException();
            student.setJmbag(studentRecord[0]);
            student.setIme(studentRecord[1]);
            student.setPrezime(studentRecord[2]);
            student.setOcjena(studentRecord[3]);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage() + "\n Record \"" + csvRecord + "\" was not parsed." );
            return null;
        } catch (IndexOutOfBoundsException e){
            System.out.println("\n Record \"" + csvRecord + "\" has wrong structure and was not parsed." );
            return null;
        }
        return student;
    }

    public String toRecord() {
        return jmbag + ";" +
                ime + ";" +
                prezime+ ";" +
                ocjena + "\n";
    }

    @Override
    public String toString() {
        return "{" +
                "jmbag='" + jmbag + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", ocjena=" + ocjena +
                '}';
    }

    public void setJmbag(String jmbag) throws IllegalArgumentException {
        if (pattern.matcher(jmbag).matches()){
            this.jmbag = jmbag;
        }else{
            throw new IllegalArgumentException("JMBAG has wrong format.");
        }
    }

    public void setIme(String ime)  throws IllegalArgumentException {
        if ( ime == null || ime.indexOf(Constants.SEPARATOR) >= 0 ) {
            throw new IllegalArgumentException("Student's ime i s null or contains illegal character! ");
        }
        this.ime = ime;
    }

    public void setPrezime(String prezime) throws IllegalArgumentException {
        if ( prezime == null || prezime.indexOf(Constants.SEPARATOR) >= 0 ) {
            throw new IllegalArgumentException("Student's prezime i s null or contains illegal character! ");
        }
        this.prezime = prezime;
    }

    public void setOcjena(String ocjena) throws IllegalArgumentException {
        setOcjena(Integer.parseInt(ocjena));
    }

    public void setOcjena(int ocjena) throws IllegalArgumentException {
        if (ocjena < 1 || ocjena > 5) {
            throw new IllegalArgumentException("Student's grade is out of scope! ");
        }
        this.ocjena = ocjena;
    }
    
    public String getJmbag() {
        return jmbag;
    }
    public String getIme() {
        return ime;
    }    
    public String getPrezime() {
        return prezime;
    }
    public int getOcjena() {
        return ocjena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getOcjena() == student.getOcjena() &&
                Objects.equals(getJmbag(), student.getJmbag()) &&
                Objects.equals(getIme(), student.getIme()) &&
                Objects.equals(getPrezime(), student.getPrezime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJmbag(), getIme(), getPrezime(), getOcjena());
    }
}
