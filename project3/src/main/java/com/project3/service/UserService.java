package com.project3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project3.dto.UserRequestDTO;
import com.project3.dto.UserResponseDTO;
import com.project3.mapper.UserMapper;
import com.project3.model.Role;
import com.project3.model.User;
import com.project3.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public List<UserResponseDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> result = new ArrayList<>();
        for (User u : users) {
            result.add(userMapper.toDto(u));
        }
        return result;
    }

    public UserResponseDTO findById(String id) {
        Optional<User> opUser = userRepository.findById(id);
        if (opUser.isPresent()) {
            return userMapper.toDto(opUser.get());
        }
        return null;
    }

    public List<UserResponseDTO> findByRole(Role role) {
        List<User> users = userRepository.findByRole(role);
        List<UserResponseDTO> result = new ArrayList<>();
        for (User u : users) {
            result.add(userMapper.toDto(u));
        }
        return result;
    }

    public UserResponseDTO findByUsername(String username) {
        Optional<User> opUser = userRepository.findByUsername(username);
        if (opUser.isPresent()) {
            return userMapper.toDto(opUser.get());
        }
        return null;
    }

    public UserResponseDTO create(UserRequestDTO request) {
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            return null; // email ja existeix → 409
        }
        User user = userMapper.toEntity(request);
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    public UserResponseDTO update(String id, UserRequestDTO request) {
        Optional<User> opUser = userRepository.findById(id);
        if (opUser.isEmpty()) {
            return null; // no existeix → 404
        }

        User existing = opUser.get();
        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setEmail(request.getEmail());
        existing.setUsername(request.getUsername());
        existing.setPassword(request.getPassword());
        existing.setRole(request.getRole());
        // dataCreated no es modifica

        if (request.getGrade() != null) {
            existing.getAcademicProfile().setGrade(request.getGrade());
            existing.getAcademicProfile().setCourse(request.getCourse());
            existing.getAcademicProfile().setObservations(request.getObservations());
        }

        User saved = userRepository.save(existing);
        return userMapper.toDto(saved);
    }

    public boolean delete(String id) {
        Optional<User> opUser = userRepository.findById(id);
        if (opUser.isEmpty()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}

