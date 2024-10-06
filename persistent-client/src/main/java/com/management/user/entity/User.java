package com.management.user.entity;

import com.management.user.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "EMP_ID")
    private String id;
    @Column(name = "EMP_FIRST_NAME")
    private String firstName;
    @Column(name = "EMP_LAST_NAME")
    private String lastName;
    @Column(name = "EMP_EMAIL")
    private String email;
    @Column(name = "EMP_PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "EMP_HIRE_DATE")
    private LocalDate hireDate;
    @Column(name = "EMP_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
    @Column(name = "EMP_ROLE")
    private String employeeRole;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMP_ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    private Address address;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMP_TEAM_ID",referencedColumnName = "TEAM_ID")
    private Team team;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeaveRequest> leaves = new ArrayList<>();

    public User(String id, String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate, LocalDate dateOfBirth){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.dateOfBirth = dateOfBirth;
    }

    public static UserDto mapEntityToDto(User employee){
        return new UserDto(employee.id, employee.firstName, employee.lastName, employee.email, employee.employeeRole, employee.phoneNumber, employee.hireDate, employee.dateOfBirth);
    }
    public static User mapDtoToEntity(UserDto employeeDto){
        return new User(employeeDto.id, employeeDto.first_name, employeeDto.last_name, employeeDto.email, employeeDto.phone_number, employeeDto.hire_date, employeeDto.date_of_birth);
    }
}
