package com.tuwaiq.lab10_jobseeking.Models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Sorry, the job application user id can't be empty, please try again")
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @NotNull(message = "Sorry, the job application post id can't be empty, please try again")
    @Column(columnDefinition = "int not null")
    private Integer jobPostId;
}
