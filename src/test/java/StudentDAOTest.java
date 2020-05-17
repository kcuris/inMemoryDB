import hr.asseco.demo.database.Database;
import hr.asseco.demo.database.StudentDAO;
import hr.asseco.demo.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class StudentDAOTest {

    private Database db;
    private StudentDAO dao;

    @Before
    public void initialize(){

        // purge the CSV file
        try {
            FileWriter fw = new FileWriter("test.csv", false);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = Database.getInstance("test.csv");
        dao = new StudentDAO(db);
    }

    @After
    public void close(){
        dao.close();
    }

    @Test
    public void testDAO() throws InterruptedException {
        Student s1 = new Student("1111111111", "First", "Student", "1" );

        // if passes, database returns inserted record
        assertEquals(s1,dao.createStudent("1111111111", "First", "Student", "1"));

        s1.setOcjena(3);
        assertEquals(s1,dao.updateStudent("1111111111", "First", "Student", "3"));

        // cannot create student with same jmbag
        assertNull(dao.createStudent("1111111111", "First", "Student", "1"));

        //read test
        assertEquals(s1, dao.readStudent("1111111111"));
        assertNull (dao.readStudent("2222222222"));

        // filter by grade test
        assertTrue(dao.filterByGradeEQ(3).size() == 1);
        assertTrue(dao.filterByGradeLT(3).size() == 0);
        assertTrue(dao.filterByGradeGT(3).size() == 0);
        dao.createStudent("2222222222", "Second", "Student", "1");
        dao.createStudent("3333333333", "Third", "Student", "4");
        dao.createStudent("4444444444", "Fourth", "Student", "5");
        assertTrue(dao.filterByGradeLT(3).size() == 1);
        assertTrue(dao.filterByGradeGT(3).size() == 2);

        // filter by name test
        assertTrue(dao.filterByName("F").size() == 2);
        assertTrue(dao.filterByName("Fo").size() == 1);
        assertTrue(dao.filterByName("S").size() == 1);
        assertTrue(dao.filterByName("P").size() == 0);

        // delete test
        dao.delete("1111111111");
        assertNull (dao.readStudent("1111111111"));
        assertTrue(dao.filterByGradeEQ(3).size() == 0);

    }

}


