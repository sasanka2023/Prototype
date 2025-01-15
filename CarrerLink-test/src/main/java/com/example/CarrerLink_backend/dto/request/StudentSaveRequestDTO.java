package com.example.CarrerLink_backend.dto.request;

import com.example.CarrerLink_backend.dto.AcedemicResultsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentSaveRequestDTO {
    private int StudentId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String userName;
    private List<AcedemicResultsDTO> acedemicResults;
    private String university;
    private String department;
    private String degree;
}
