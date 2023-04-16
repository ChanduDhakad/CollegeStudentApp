

import entities.College;
import entities.Student;
import exceptions.CollegeException;
import exceptions.StudentException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import utilities.EMUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.CollegeDao;
import dao.CollegeDaoImpl;
import dao.StudentDao;
import dao.StudentDaoImpl;
public class Runner {

    public static void main(String[] args) {
    
        EntityManager entityManager =EMUtil.provideEntityManager();

        try {
          
        	 CollegeDao collegeDao = new CollegeDaoImpl();
             StudentDao studentDao = new StudentDaoImpl();
             
             
             
             // create a new college
        	 College college = new College();
             college.setCollegeName("GECA ");
             college.setCollegeAddress("Ajmer ");
             
        	
             // 2 student details
           Student s1 = new Student();
             s1.setStudentName("Chandu");
             s1.setMobileNumber("5466876543");
             s1.setEmail("cpd@gmai.com");
             s1.setStatus(true);
             
             Student s2 = new Student();
             s2.setStudentName("Deepesh");
             s2.setMobileNumber("876543543");
             s2.setEmail("deepesh@gmai.com");
             s2.setStatus(true);
             
             
             // both student in collage add
             Set<Student> hs=new HashSet<>();
             hs.add(s1);
             hs.add(s2);
             
             
             // both student add clg
//             s1.setCollege(college);
             s2.setCollege(college);
             
             
             
             // register the college in the database
             college = collegeDao.registerCollege(college);
             
             System.out.print("Registor College Details is : "+college);
             
             
             // registor student
             Student st=studentDao.registerStudent(s1);
             System.out.print("Registor Student  Details is : "+st);
             
             
             
         //    collegeDAO method()==>2 
             
         College ClgById=  collegeDao.getCollegeFromCollegeId(1);
         System.out.print( "College Details of Id 1 is " +ClgById);
             
             
//       collegeDAO method()==>3
             
         List<College>list= collegeDao.getAllCollege();
         System.out.println("All College store in the database Details is :");
             
         for(College clg:list) {
        	 
        	 System.out.println("college details is : "+clg);
         }
             
             
             //collegeDAO method()==>4
         Student existStudent=collegeDao.registerStudentToCollege(1, 1);
         System.out.print(" Student  Details of Roll is "+existStudent);
             
             
             
         //collegeDAO method()==>5
         
         
         List<Student>nlist= collegeDao.getAllStudentsFromCollegeId(1);
         System.out.println(" College with 1 in  the database All Student Details is :");
             
         for(Student clg:nlist) {
        	 
        	 System.out.println("Student  details is : "+clg);
         }
             
             
       //collegeDAO method()==>6
         
         Student s=studentDao.getStudentFromStudentId(1);
         System.out.println("Student  details is : "+s);
             
             
             
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
           
        }
             
             
             
             
             
             
        	
    }
}
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        
