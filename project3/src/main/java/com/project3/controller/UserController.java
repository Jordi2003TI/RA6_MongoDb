package com.project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project3.dto.ErrorDTO;
import com.project3.dto.UserRequestDTO;
import com.project3.dto.UserResponseDTO;
import com.project3.model.Role;
import com.project3.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<UserResponseDTO> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        UserResponseDTO dto = userService.findById(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO("No s'ha trobat cap usuari amb la id: " + id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponseDTO>> getByRole(@PathVariable Role role) {
        List<UserResponseDTO> users = userService.findByRole(role);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        UserResponseDTO dto = userService.findByUsername(username);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO("No s'ha trobat cap usuari amb el username: " + username));
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserRequestDTO request) {
        UserResponseDTO dto = userService.create(request);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorDTO("Ja existeix un usuari amb aquest email"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UserRequestDTO request) {
        UserResponseDTO dto = userService.update(id, request);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO("No s'ha trobat cap usuari amb la id: " + id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        boolean deleted = userService.delete(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO("No s'ha pogut eliminar. No existeix cap usuari amb la id: " + id));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
