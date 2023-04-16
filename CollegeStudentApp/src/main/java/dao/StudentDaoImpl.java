package dao;

import java.util.List;

import entities.College;
import entities.Student;
import exceptions.CollegeException;
import exceptions.StudentException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utilities.EMUtil;

public class StudentDaoImpl implements  StudentDao{

	
		@Override
		public Student registerStudent(Student student) throws StudentException {
			// TODO Auto-generated method stub

			EntityManager em = EMUtil.provideEntityManager();
			try {
				em.getTransaction().begin();
				em.persist(student);
				em.getTransaction().commit();

			} catch (Exception e) {
				throw new StudentException("Error registering student");

			} finally { 
				em.close();
			}

			return student;
		}

		@Override
		public Student getStudentFromStudentId(int studentId) throws StudentException {
			// TODO Auto-generated method stub

			EntityManager em = EMUtil.provideEntityManager();
			try {

				Student student = em.find(Student.class, studentId);
				if (student == null) {
					throw new StudentException("Student not found with studentId: " + studentId);
				}
				return student;

			} catch (Exception e) {
				throw new StudentException("student not found with studentId: " + studentId);

			} finally {
				em.close();
			}
		}

		@Override
		public List<Student> getAllActiveStudents() throws StudentException {
			// TODO Auto-generated method stub

			EntityManager em = EMUtil.provideEntityManager();
			try {
				// Create a JPQL query to retrieve all active students
				TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.status = :status",
						Student.class);

				query.setParameter("status", true);
				List<Student> students = query.getResultList();
				return students;

			} catch (Exception e) {

				throw new StudentException("not able to find all active students");
			} finally {
				em.close();
			}
		}

		@Override
		public College getCollegeFromStudentId(int studentId) throws CollegeException, StudentException {
			// TODO Auto-generated method stub

			EntityManager em = EMUtil.provideEntityManager();

			try {
			
				Student student = getStudentFromStudentId(studentId);

				
				College college = student.getCollege();
				return college;

			} catch (Exception e) {

				throw new CollegeException("not able to find college for student with studentId: " + studentId);

			} finally {
				em.close();
			}
		}

	}


