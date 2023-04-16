package entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor

@Entity
@ToString
public class College {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private int collegeId;
	    
	 
	    private String collegeName;
	    
	   
	    private String collegeAddress;
	    
	    @OneToMany(cascade = CascadeType.ALL)
	    private Set<Student> students = new HashSet<>();

		public int getCollegeId() {
			return collegeId;
		}

		public void setCollegeId(int collegeId) {
			this.collegeId = collegeId;
		}

		public String getCollegeName() {
			return collegeName;
		}

		public void setCollegeName(String collegeName) {
			this.collegeName = collegeName;
		}

		public String getCollegeAddress() {
			return collegeAddress;
		}

		public void setCollegeAddress(String collegeAddress) {
			this.collegeAddress = collegeAddress;
		}

		public Set<Student> getStudents() {
			return students;
		}

		public void setStudents(Set<Student> students) {
			this.students = students;
		}


}
