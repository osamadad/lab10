package com.tuwaiq.lab10_jobseeking.Controller;

import Api.ApiResponse;
import com.tuwaiq.lab10_jobseeking.Models.JobPost;
import com.tuwaiq.lab10_jobseeking.Service.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-post")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity<?> getJobPosts() {
        List<JobPost> jobPosts = jobPostService.getJobPosts();
        if (jobPosts.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("There are no job posts to show"));
        } else {
            return ResponseEntity.status(200).body(jobPosts);
        }
    }

    @PutMapping("/add")
    public ResponseEntity<?> addJobPost(Integer userId, JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        } else {
            String value = jobPostService.addJobPost(userId, jobPost);
            return switch (value) {
                case "ok" -> ResponseEntity.status(200).body(new ApiResponse("The job have been posted successfully"));
                case "User role error" ->
                        ResponseEntity.status(400).body(new ApiResponse("You don't have the qualification to post a job, you must be an EMPLOYER"));
                case "User id error" ->
                        ResponseEntity.status(400).body(new ApiResponse("There were no user with this id found"));
                default -> ResponseEntity.status(400).body(new ApiResponse("General error"));
            };
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateJobPost(Integer userId, Integer jobPostId, JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        } else {
            String value = jobPostService.updateJobPost(userId, jobPostId, jobPost);
            return switch (value) {
                case "ok" ->
                        ResponseEntity.status(200).body(new ApiResponse("The job post have been updated successfully"));
                case "User role error" ->
                        ResponseEntity.status(400).body(new ApiResponse("You don't have the qualification to post a job, you must be an EMPLOYER"));
                case "User id error" ->
                        ResponseEntity.status(400).body(new ApiResponse("There were no user with this id found"));
                case "jobPost id error" ->
                        ResponseEntity.status(400).body(new ApiResponse("There were no job post with this id found"));
                default -> ResponseEntity.status(400).body(new ApiResponse("General error"));
            };
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteJobPost(Integer userId, Integer jobPostId){
        String value = jobPostService.deleteJobPost(userId,jobPostId);
        return switch (value) {
            case "ok" ->
                    ResponseEntity.status(200).body(new ApiResponse("The job post have been deleted successfully"));
            case "User role error" ->
                    ResponseEntity.status(400).body(new ApiResponse("You don't have the qualification to delete a job, you must be an EMPLOYER"));
            case "User id error" ->
                    ResponseEntity.status(400).body(new ApiResponse("There were no user with this id found"));
            case "jobPost id error" ->
                    ResponseEntity.status(400).body(new ApiResponse("There were no job post with this id found"));
            default -> ResponseEntity.status(400).body(new ApiResponse("General error"));
        };
    }


}
