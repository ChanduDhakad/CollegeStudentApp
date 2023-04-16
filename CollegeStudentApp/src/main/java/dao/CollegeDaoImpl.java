package dao;

import entities.College;
import entities.Student;
import exceptions.CollegeException;
import exceptions.StudentException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import utilities.EMUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class CollegeDaoImpl  implements CollegeDao{

	
	@Override
	public College registerCollege(College college) throws CollegeException {
	College college2=null;
	
	boolean flag=false;

	EntityManager entityManager=EMUtil.provideEntityManager();

	entityManager.getTransaction().begin();

	     entityManager.persist(college);
	     flag=true;
	  college2=college;

	entityManager.getTransaction().commit();

	 entityManager.close();
	 
	  if(!flag||college2==null) throw new CollegeException("College s not registor ..");
	return college2;
	}

	@Override
	public College getCollegeFromCollegeId(int collegeId) throws CollegeException {
	 College college=null;

		EntityManager em=EMUtil.provideEntityManager();

		 College college2=em.find(College.class, collegeId);

		 if(college2!=null) {
			 college=college2;
		 }
		 else {

			 throw new CollegeException("No college is found with CollegeId : "+collegeId);
		 }
	 return college;
	}

	@Override
	public List<College> getAllCollege() throws CollegeException {
		List<College> colleges=new ArrayList<>();
		EntityManager em=EMUtil.provideEntityManager();

 
		
		         
		String nq="Select * From college";
		Query query=em.createNativeQuery(nq,College.class);
		 colleges=query.getResultList();
          if(colleges.isEmpty()) throw new CollegeException("No college is the DataBase ");
		return colleges;

	}

	@Override
	public Student registerStudentToCollege(int studentId, int collegeId) throws CollegeException, StudentException {
		Student student=null;
		EntityManager em=EMUtil.provideEntityManager();
		
		Student existStudent=em.find(Student.class,studentId);
		if(existStudent==null) throw new StudentException("Student not found");

		College college=em.find(College.class,collegeId);
		if(college==null) throw new CollegeException("Student not found");
		
		em.getTransaction().begin();
		
		existStudent.setCollege(college);
		college.getStudents().add(existStudent);
		    
		 em.persist(existStudent);
	   

	em.getTransaction().commit();

	 em.close();
		
	
		
	

		return student;
	}

	@Override
	public List<Student> getAllStudentsFromCollegeId(int collegeId) throws CollegeException, StudentException {
		EntityManager em=EMUtil.provideEntityManager();
		Set<Student> students=new  HashSet<>();
		
		  College clg=em.find(College.class, collegeId);
		  students= clg.getStudents();
		
		      if(students.isEmpty())throw new StudentException("Student not found");
		
		       List<Student> ans=new ArrayList<>();
		       
		
		return ans;
		
	}

}
