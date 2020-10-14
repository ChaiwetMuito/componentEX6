/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author YUME
 */
public class StudentDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Student sd1 = new Student(61050165, "Chiwet", 4.00);
        Student sd2 = new Student(61050166, "Chanon", 2.00);
        StudentTable.insertStudent(sd1);
        StudentTable.insertStudent(sd2);
        Student sd ;
        sd = StudentTable.findStudentById(61050166);
        if (sd != null) {
           //sd.setName("Jack");
           sd.setGpa(3.50);
           StudentTable.updateStudent(sd);
        }
        StudentTable.removeStudent(sd1);

        
        
        
        List<Student> sdList = StudentTable.findAllStudent();
        printAllStudent(sdList);
    }
    
    public static void printAllStudent(List<Student> employeeList) {
        for(Student emp : employeeList) {
           System.out.print(emp.getId() + " ");
           System.out.print(emp.getName() + " ");
           System.out.println(emp.getGpa()+ " ");
       }
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabasePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
