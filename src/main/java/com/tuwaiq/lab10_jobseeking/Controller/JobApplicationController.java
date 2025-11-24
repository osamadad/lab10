package com.tuwaiq.lab10_jobseeking.Controller;

import Api.ApiResponse;
import com.tuwaiq.lab10_jobseeking.Models.JobApplication;
import com.tuwaiq.lab10_jobseeking.Service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-application")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity<?> getJobApplication() {
        List<JobApplication> jobApplications = jobApplicationService.getJobApplications();
        if (jobApplications.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("There are no job application to show"));
        } else {
            return ResponseEntity.status(200).body(jobApplications);
        }
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyForJob(Integer userId, JobApplication jobApplication, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        } else {
            String value = jobApplicationService.applyForAJob(userId, jobApplication);
            return switch (value) {
                case "ok" ->
                        ResponseEntity.status(200).body(new ApiResponse("You have applied to the job successfully"));
                case "jobPost id error" ->
                        ResponseEntity.status(400).body(new ApiResponse("There were no job posts with this id found"));
                case "user role error" ->
                        ResponseEntity.status(400).body(new ApiResponse("You don't have the qualification to apply to the job, you must be a JOB_SEEKER"));
                case "User id error" ->
                        ResponseEntity.status(400).body(new ApiResponse("There were no user with this id found"));
                case "Users matching error" ->
                        ResponseEntity.status(400).body(new ApiResponse("You can't apply to a job with a id that is not yours"));
                default -> ResponseEntity.status(400).body(new ApiResponse("General error"));
            };
        }
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<?> WithdrawJobApplication(Integer userId, Integer applicationId){
        String value = jobApplicationService.withdrawJobApplication(userId,applicationId);
        switch (value) {
            case "ok":
                return ResponseEntity.status(200).body(new ApiResponse("You have withdrawn your application to the job successfully"));
            case "user role error":
                return ResponseEntity.status(400).body(new ApiResponse("You don't have the qualification to withdraw from the job, you must be a JOB_SEEKER"));
            case "User id error":
                return ResponseEntity.status(400).body(new ApiResponse("There were no user with this id found"));
            case "Users matching error":
                return ResponseEntity.status(400).body(new ApiResponse("You can't withdraw from a job application with a id that is not yours"));
            case "JobApplication error":
                return ResponseEntity.status(400).body(new ApiResponse("There were no applications with this ip"));
            default:
                return ResponseEntity.status(400).body(new ApiResponse("General error"));
        }
    }


}
