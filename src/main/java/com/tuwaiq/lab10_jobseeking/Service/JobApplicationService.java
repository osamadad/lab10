package com.tuwaiq.lab10_jobseeking.Service;

import com.tuwaiq.lab10_jobseeking.Models.JobApplication;
import com.tuwaiq.lab10_jobseeking.Models.User;
import com.tuwaiq.lab10_jobseeking.Repository.JobApplicationRepository;
import com.tuwaiq.lab10_jobseeking.Repository.JobPostRepository;
import com.tuwaiq.lab10_jobseeking.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostService jobPostService;
    private final UserService userService;

    public List<?> getJobApplications() {
        return jobApplicationRepository.findAll();
    }

    public String applyForAJob(Integer userId, JobApplication jobApplication) {
        Integer applicationUserId = jobApplication.getUserId();
        if (userService.checkUserMatchApplication(userId, applicationUserId)) {
            User user = userService.checkUserExist(applicationUserId);
            if (user != null) {
                if (userService.checkUserRole(user, "JOB_SEEKER")) {
                    if (jobPostService.checkJobPostExist(jobApplication.getJobPostId())) {
                        jobApplicationRepository.save(jobApplication);
                        return "ok";
                    } else {
                        return "jobPost id error";
                    }
                } else {
                    return "user role error";
                }
            } else {
                return "User id error";
            }
        } else {
            return "Users matching error";
        }
    }

    public String withdrawJobApplication(Integer userId, Integer applicationId) {
        JobApplication jobApplication = jobApplicationRepository.getById(applicationId);
        if (jobApplication != null) {
            if (userService.checkUserMatchApplication(userId, jobApplication.getUserId())) {
                User user = userService.checkUserExist(jobApplication.getUserId());
                if (user != null) {
                    if (userService.checkUserRole(user, "JOB_SEEKER")) {
                        jobApplicationRepository.delete(jobApplication);
                    } else {
                        return "User role error";
                    }
                } else {
                    return "User id error";
                }
            } else {
                return "Users matching error";
            }
        }
        return "JobApplication error";

    }


}
