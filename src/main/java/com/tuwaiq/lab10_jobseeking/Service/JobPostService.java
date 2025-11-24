package com.tuwaiq.lab10_jobseeking.Service;

import com.tuwaiq.lab10_jobseeking.Models.JobPost;
import com.tuwaiq.lab10_jobseeking.Models.User;
import com.tuwaiq.lab10_jobseeking.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final UserService userService;

    public List<JobPost> getJobPosts(){
        return jobPostRepository.findAll();
    }

    public String addJobPost(Integer userId, JobPost jobPost){
        User user=userService.checkUserExist(userId);
        if (user!=null){
            if (userService.checkUserRole(user,"EMPLOYER")){
                jobPostRepository.save(jobPost);
                return "ok";
            }else {
                return "User role error";
            }
        }else {
            return "User id error";
        }
    }

    public String updateJobPost(Integer userId, Integer jobPostId, JobPost jobPost){
        User user=userService.checkUserExist(userId);
        if (user!=null){
            if (userService.checkUserRole(user,"EMPLOYER")){
                JobPost oldJobPost= jobPostRepository.getById(jobPostId);
                if (oldJobPost==null){
                    return "jobPost id error";
                }
                else {
                    oldJobPost.setTitle(jobPost.getTitle());
                    oldJobPost.setDescription(jobPost.getDescription());
                    oldJobPost.setLocation(jobPost.getLocation());
                    oldJobPost.setSalary(jobPost.getSalary());
                    jobPostRepository.save(oldJobPost);
                    return "ok";
                }
            }else {
                return "User role error";
            }
        }else {
            return "User id error";
        }
    }

    public String deleteJobPost(Integer userId, Integer id){
        User user=userService.checkUserExist(userId);
        if (user!=null){
            if (userService.checkUserRole(user,"EMPLOYER")){
                JobPost jobPost = jobPostRepository.getById(id);
                if (jobPost ==null){
                    return "jobPost id error";
                }
                else {
                    jobPostRepository.delete(jobPost);
                    return "ok";
                }
            }else {
                return "User role error";
            }
        }else {
            return "User id error";
        }
    }

    public Boolean checkJobPostExist(Integer jobPostId) {
        for (JobPost jobPost: jobPostRepository.findAll()){
            if (jobPost.getId().equals(jobPostId)){
                return true;
            }
        }
        return false;
    }
}
