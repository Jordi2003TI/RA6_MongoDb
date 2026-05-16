package com.project3.mapper;

import org.springframework.stereotype.Component;

import com.project3.dto.AcademicProfileDTO;
import com.project3.dto.UserRequestDTO;
import com.project3.dto.UserResponseDTO;
import com.project3.model.AcademicProfile;
import com.project3.model.User;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public UserResponseDTO toDto(User user) {
        if (user == null) return null;

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        dto.setDataCreated(user.getDataCreated());

        if (user.getAcademicProfile() != null) {
            AcademicProfileDTO profileDTO = new AcademicProfileDTO();
            profileDTO.setGrade(user.getAcademicProfile().getGrade());
            profileDTO.setCourse(user.getAcademicProfile().getCourse());
            profileDTO.setObservations(user.getAcademicProfile().getObservations());
            profileDTO.setStatus(user.getAcademicProfile().getStatus());
            dto.setAcademicProfile(profileDTO);
        }

        return dto;
    }

    public User toEntity(UserRequestDTO request) {
        if (request == null) return null;

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setDataCreated(LocalDateTime.now());

        if (request.getGrade() != null) {
            AcademicProfile profile = new AcademicProfile();
            profile.setGrade(request.getGrade());
            profile.setCourse(request.getCourse());
            profile.setObservations(request.getObservations());
            profile.setStatus("ACTIVE");
            user.setAcademicProfile(profile);
        }

        return user;
    }
}