import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;

public class Job {
    // Declare private member variables
    private String jobNumber;
    private String jobTitle;
    private String jobPosterName;
    private String jobPosterAddress;
    private String jobPostedDate;
    private String jobExperienceLevel; // Four job experience levels: Junior, Medium, Senior, Executive
    private String jobType; // Four job types: Full-time, Part-time, Internship, Volunteer
    private String[] jobRequiredSkills; // a list of skills; each skill is 1 word (e.g. C#) or a maximum of two words (e.g, Software Architecture)
    private double jobSalary;
    private String jobDescription;
    private String fileName = "jobs.txt";  // default file name

    // a setter for filename
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // constructor
    public Job(String number, String title, String posterName, String posterAddress, String date, String experienceLevel, String type, String[] skills, double salary, String description) {
        // Initialize member variables
        jobNumber = number;
        jobTitle = title;
        jobPosterName = posterName;
        jobPosterAddress = posterAddress;
        jobPostedDate = date;
        jobExperienceLevel = experienceLevel;
        jobType = type;
        jobRequiredSkills = skills;
        jobSalary = salary;
        jobDescription = description;
    }

    // Define a method to add job information to a TXT file
    public boolean addJob() {
        // Add the information of a new job to a TXT file
        // If the job information meets the defined conditions
        // the information should be added to the TXT file and the function should return true.
        // If the job information does not meet the conditions,
        // the information should not be added to the TXT file and the function should return false.

        if (isValidJob()) { // Check if job is valid
            // Try to write job details to a text file
            try {
                // Open the text file in append mode
                BufferedWriter writer = new BufferedWriter(new FileWriter("jobs.txt", true));

                // Write job details to the text file
                writer.write(toString());
                writer.newLine();

                // Close the text file
                writer.close();

                // Return true indicating job was added successfully
                return true;

            } catch (IOException e) {
                // Print stack trace in case of exception
                e.printStackTrace();
            }
        }
        // Return false indicating job wasn't added
        return false;
    }
    public boolean updateJob() {
        // Update the information of a given job in a TXT file
        // If the job's new information meets the defined conditions,
        // the job information should be updated in the TXT file and the function should return true.
        // If the job's new information does not meet the following conditions,
        // the job information should not be updated in the TXT file and the function should return false.

        if (isValidJob()) { // Check if job is valid before updating
            // Read existing lines from the text file
            try {
                List<String> lines = Files.readAllLines(Paths.get("jobs.txt"));
                List<String> updatedLines = new ArrayList<>();

                // Loop through each line
                for (String line : lines) {
                    if (line.startsWith(jobNumber)) {
                        // Replace old line with new job details
                        updatedLines.add(toString());
                    } else {
                        // Keep old line
                        updatedLines.add(line);
                    }
                }

                // Write updated lines to the file
                BufferedWriter writer = new BufferedWriter(new FileWriter("jobs.txt"));
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
                writer.close();
                return true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Checks to ensure all aspects of a job are valid
    private boolean isValidJob() {
        return isValidJobNumber() && isValidJobPostedDate() && isValidJobPosterAddress() && isValidJobSalary() && isValidJobType() && isValidJobRequiredSkills();
    }

    // Validate job number using regex
    private boolean isValidJobNumber() {
        return jobNumber.matches("^[1-5]{5}[A-Z]{3}\\W$");
    }

    // Validate posted date using SimpleDateFormat
    private boolean isValidJobPostedDate() {
        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(jobPostedDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Validate job poster address by checking number of comma separated parts
    private boolean isValidJobPosterAddress() {
        String[] parts = jobPosterAddress.split(", ");
        return parts.length == 3;
    }

    // Validate salary based on job experience level
    private boolean isValidJobSalary() {
        if (jobExperienceLevel.equals("Senior") || jobExperienceLevel.equals("Executive")) {
            return jobSalary >= 100000;
        } else if (jobExperienceLevel.equals("Junior")) {
            return jobSalary >= 40000 && jobSalary <= 70000;
        }
        return true;
    }

    // Validate job type based on job experience level
    private boolean isValidJobType() {
        if (jobType.equals("Part-time")) {
            return !jobExperienceLevel.equals("Senior") && !jobExperienceLevel.equals("Executive");
        }
        return true;
    }

    // Validate skills: number of skills and words per skill
    private boolean isValidJobRequiredSkills() {
        if (jobRequiredSkills.length >= 1 && jobRequiredSkills.length <= 3) {
            for (String skill : jobRequiredSkills) {
                if (skill.split(" ").length > 2) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // Override toString to provide custom string representation of Job
    @Override
    public String toString() {
        String skills = String.join(", ", jobRequiredSkills);
        return jobNumber + "," + jobTitle + "," + jobPosterName + "," + jobPosterAddress + "," + jobPostedDate + "," + jobExperienceLevel + "," + jobType + "," + skills + "," + jobSalary + "," + jobDescription;
    }
}