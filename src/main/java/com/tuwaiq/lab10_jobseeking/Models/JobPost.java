package com.tuwaiq.lab10_jobseeking.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Sorry, the job post title can't be empty, please try again")
    @Size(min = 4, max = 20, message = "Sorry, the job post title can't be less than 4 or longer than 20 characters long, please try again")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;
    @NotEmpty(message = "Sorry, the job post description can't be empty, please try again")
    @Column(columnDefinition = "varchar(50) not null")
    private String description;
    @NotEmpty(message = "Sorry, the job post location can't be empty, please try again")
    @Column(columnDefinition = "varchar(35) not null")
    private String location;
    @NotNull(message = "Sorry, the job post salary can't be empty, please try again")
    @Positive(message = "Sorry, the job post salary can't be a negative number, please try again")
    @Column(columnDefinition = "int not null")
    private Integer salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd@HH-mm-ss")
    private LocalDateTime postingDate=LocalDateTime.now();
}
