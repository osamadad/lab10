package com.tuwaiq.lab10_jobseeking.Models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Sorry, the user name can't be empty, please try again")
    @Size(min = 4, max = 20,message = "Sorry, the user name can't be less than 4 or longer than 20 characters long, please try again")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Sorry, the user name can only contain alphabet, please try again")
    @Column(columnDefinition = "varchar(20)")
    private String name;
    @Email(message = "Sorry, the user E-mail must follow a valid email format, please try again")
    @Column(columnDefinition = "varchar(50) unique")
    private String email;
    @NotEmpty(message = "Sorry, the user password can't be empty, please try again")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$", message = "Sorry, the user password must contain at least 1 small letter, 1 capital letter, 1 number, and 8 characters, please try again")
    @Column(columnDefinition = "varchar(20) ")
    private String password;
    @NotNull(message = "Sorry, the user age can't be empty, please try again")
    @Min(value = 21, message = "Sorry, the user age can't be less than 21 years old, please try again")
    @Positive(message = "Sorry, the user age must be a positive number, please try again")
    @Column(columnDefinition = "int ")
    private Integer age;
    @NotEmpty(message = "Sorry, the user role can't be empty, please try again")
    @Pattern(regexp = "JOB_SEEKER|EMPLOYER", message = "Sorry, role must either be 'JOB_SEEKER' or 'EMPLOYER'")
    @Column(columnDefinition = "varchar(11) ")
    private String role;
}
