package com.example.CarrerLink_backend.controller;

import com.example.CarrerLink_backend.dto.request.CVUpdateRequestDTO;
import com.example.CarrerLink_backend.dto.response.CVgetResponseDTO;
import com.example.CarrerLink_backend.service.CVService;
import com.example.CarrerLink_backend.utill.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cv")
@AllArgsConstructor
public class CVController {

    private final CVService cvService;

    @Operation(summary = "Update student's CV")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated CV"),
            @ApiResponse(responseCode = "404", description = "CV not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping()
    public ResponseEntity<StandardResponse> updateCV(@RequestParam int studentId, @RequestBody CVUpdateRequestDTO cvUpdateRequestDTO) {
        String updatedCV = cvService.updateCV(studentId, cvUpdateRequestDTO);
        return ResponseEntity.ok(new StandardResponse(true, "CV updated successfully", updatedCV));
    }


    @Operation(summary = "Get student's Cv")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched CV"),
            @ApiResponse(responseCode = "404", description = "CV not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    public ResponseEntity<StandardResponse> getCV(@RequestParam int studentId){
        CVgetResponseDTO cv = cvService.getCV(studentId);
        return ResponseEntity.ok(new StandardResponse(true,"CV fetched successfully",cv));
    }
}
