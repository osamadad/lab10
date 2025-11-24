package com.tuwaiq.lab10_jobseeking.Repository;

import com.tuwaiq.lab10_jobseeking.Models.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost,Integer> {
}
