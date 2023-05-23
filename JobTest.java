import org.junit.jupiter.api.*;
import java.io.*;

// Class to test the Job class and its methods
class JobTest {
    // Before each test, create a text file with a single job in it
    @BeforeEach
    void setUp() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("jobs.txt"));
        writer.write("12345MMM_,Job Title,Company Name,Melbourne, Victoria, Australia,2023-05-05,Senior,Full-time,[Software Architecture, C#, SQL],120000,Job Description");
        writer.newLine();
        writer.close();
    }

    // Test valid cases for addJob method
    @Test
    void testAddJob_Valid() {
        // Different skills arrays for different jobs
        String[] validSkills1 = {"Java", "Spring"};
        String[] validSkills2 = {"Python"};
        String[] validSkills3 = {"C#", "ASP.NET", "MVC"};

        Job job1 = new Job("12345AAA@", "Java Developer", "ABC Corp", "Melbourne, Vic, Australia", "2023-06-01", "Senior", "Full-time", validSkills1, 120000, "Job Description");
        Job job2 = new Job("23451BBB#", "Python Developer", "XYZ Inc", "Sydney, NSW, Australia", "2023-07-01", "Junior", "Part-time", validSkills2, 60000, "Job Description");
        Job job3 = new Job("34512CCC%", "C# Developer", "PQR Limited", "Canberra, ACT, Australia", "2023-08-01", "Medium", "Internship", validSkills3, 70000, "Job Description");

        Assertions.assertTrue(job1.addJob());
        Assertions.assertTrue(job2.addJob());
        Assertions.assertTrue(job3.addJob());
    }

    // Test invalid cases for addJob method
    @Test
    void testAddJob_Invalid() {
        // Different skills arrays for different jobs
        String[] invalidSkills1 = {"Java", "Spring", "Hibernate", "React"};
        String[] invalidSkills2 = {"Python and Machine Learning"};
        String[] invalidSkills3 = {"C#", "ASP.NET MVC"};

        Job job1 = new Job("12345AAA_", "Java Developer", "ABC Corp", "Melbourne, Vic", "2023-06-01", "Senior", "Part-time", invalidSkills1, 120000, "Job Description");
        Job job2 = new Job("23451BBB_", "Python Developer", "XYZ Inc", "Sydney, NSW", "2023-07-35", "Junior", "Part-time", invalidSkills2, 80000, "Job Description");
        Job job3 = new Job("34512CCC_", "C# Developer", "PQR Limited", "Canberra, Australia", "2023-02-29", "Medium", "Internship", invalidSkills3, 30000, "Job Description");

        Assertions.assertFalse(job1.addJob());
        Assertions.assertFalse(job2.addJob());
        Assertions.assertFalse(job3.addJob());
    }

    // Test valid cases for updateJob method
    @Test
    void testUpdateJob_Valid() {
        // Different skills arrays for different jobs
        String[] validSkills1 = {"Java", "Spring"};
        String[] validSkills2 = {"Python"};
        String[] validSkills3 = {"C#", "ASP.NET", "MVC"};

        Job job1 = new Job("12345AAA@", "Java Developer", "ABC Corp", "Melbourne, Vic, Australia", "2023-06-01", "Senior", "Full-time", validSkills1, 144000, "Updated Job Description");
        Job job2 = new Job("23451BBB#", "Python Developer", "XYZ Inc", "Sydney, NSW, Australia", "2023-07-01", "Junior", "Part-time", validSkills2, 66000, "Updated Job Description");
        Job job3 = new Job("34512CCC%", "C# Developer", "PQR Limited", "Canberra, ACT, Australia", "2023-08-01", "Medium", "Internship", validSkills3, 98000, "Updated Job Description");

        Assertions.assertTrue(job1.updateJob());
        Assertions.assertTrue(job2.updateJob());
        Assertions.assertTrue(job3.updateJob());
    }

    // Test invalid cases for updateJob method
    @Test
    void testUpdateJob_Invalid() {
        String[] invalidSkills1 = {"Java", "Spring", "Hibernate", "React"};
        String[] invalidSkills2 = {"Python and Machine Learning"};
        String[] invalidSkills3 = {"C#", "ASP.NET MVC"};

        Job job1 = new Job("12345AAA_", "Java Developer", "ABC Corp", "Melbourne, Vic", "2023-06-01", "Senior", "Part-time", invalidSkills1, 150000, "Updated Job Description");
        Job job2 = new Job("23451BBB_", "Python Developer", "XYZ Inc", "Sydney, NSW", "2023-07-35", "Junior", "Part-time", invalidSkills2, 80000, "Updated Job Description");
        Job job3 = new Job("34512CCC_", "C# Developer", "PQR Limited", "Canberra, Australia", "2023-02-29", "Medium", "Internship", invalidSkills3, 110000, "Updated Job Description");

        Assertions.assertFalse(job1.updateJob());
        Assertions.assertFalse(job2.updateJob());
        Assertions.assertFalse(job3.updateJob());
    }

    // Test case for salary increase scenario in updateJob method
    @Test
    void testUpdateJob_SalaryIncrease() {
        String[] skills = {"Java", "Spring"};
        Job job1 = new Job("12345AAA@", "Java Developer", "ABC Corp", "Melbourne, Vic, Australia", "2023-06-01", "Senior", "Full-time", skills, 140000, "Updated Job Description");
        Job job2 = new Job("23451BBB#", "Python Developer", "XYZ Inc", "Sydney, NSW, Australia", "2023-07-01", "Junior", "Part-time", skills, 66000, "Updated Job Description");

        Assertions.assertTrue(job1.updateJob());
        Assertions.assertTrue(job2.updateJob());
    }

    // Test case for invalid salary increase scenario in updateJob method
    @Test
    void testUpdateJob_SalaryIncreaseInvalid() {
        String[] skills = {"Python"};
        Job job1 = new Job("23451BBB_", "Python Developer", "XYZ Inc", "Melbourne, Vic, Australia", "2023-07-01", "Junior", "Part-time", skills, 77000, "Updated Job Description");
        Job job2 = new Job("34512CCC_", "C# Developer", "PQR Limited", "Sydney, NSW, Australia", "2023-08-01", "Medium", "Internship", skills, 81000, "Updated Job Description");

        Assertions.assertFalse(job1.updateJob());
        Assertions.assertFalse(job2.updateJob());
    }
}
