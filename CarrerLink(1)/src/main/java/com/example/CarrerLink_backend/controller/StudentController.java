package com.example.CarrerLink_backend.controller;


import com.example.CarrerLink_backend.dto.request.ApplyJobRequestDTO;
import com.example.CarrerLink_backend.dto.request.StudentSaveRequestDTO;
import com.example.CarrerLink_backend.dto.request.StudentUpdateRequestDTO;
import com.example.CarrerLink_backend.dto.response.ApplyJobResponseDTO;
import com.example.CarrerLink_backend.dto.response.StudentgetResponseDTO;
import com.example.CarrerLink_backend.service.StudentService;
import com.example.CarrerLink_backend.utill.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @Operation(summary = "Save a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "student created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<StandardResponse> saveStudent(@RequestBody StudentSaveRequestDTO studentSaveRequestDTO){

        String message = studentService.saveStudent(studentSaveRequestDTO);

        return ResponseEntity.status(201)
                .body(new StandardResponse(true, "Company saved successfully", message));

    }

    @Operation(summary = "Update student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "student updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public ResponseEntity<StandardResponse> updateStudent(@RequestBody StudentUpdateRequestDTO studentUpdateRequestDTO){
        String message = studentService.updateStudent(studentUpdateRequestDTO);

        return ResponseEntity.ok(new StandardResponse(true, "Company updated successfully", message));
    }


    @Operation(summary = "Delete student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "student deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new StandardResponse(true, "Company deleted successfully", null));
    }

    @Operation(summary = "Apply for a job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Job applied successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/apply-job")
    public ResponseEntity<StandardResponse> applyJob(@RequestBody ApplyJobRequestDTO applyJobRequestDTO){
        String message = studentService.applyJob(applyJobRequestDTO);
        return ResponseEntity.ok(new StandardResponse(true, "Job applied successfully", message));
    }


    @Operation(summary = "Get all applicants for a job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched all applicants"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get-all-applicants-for-job")

    public ResponseEntity<StandardResponse> getAllApplicants(@RequestParam int jobId){
        List<StudentgetResponseDTO> students = studentService.getAllApplicants(jobId);
        return ResponseEntity.ok(new StandardResponse(true, "Applicants fetched successfully", students));
    }

    @Operation(summary = "Get all jobs applied by a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched all jobs"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get-job-by-student")

    public ResponseEntity<StandardResponse> getJobByStudent(@RequestParam int studentId){
        List<ApplyJobResponseDTO> students = studentService.getJobByStudent(studentId);
        return ResponseEntity.ok(new StandardResponse(true, "Jobs fetched successfully", students));
    }


    @Operation(summary = "Get student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched all applicants"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()

    public ResponseEntity<StandardResponse> getStudentById(@RequestParam int stId){
        StudentgetResponseDTO students = studentService.getStudentById(stId);
        return ResponseEntity.ok(new StandardResponse(true, "Applicants fetched successfully", students));
    }


}
