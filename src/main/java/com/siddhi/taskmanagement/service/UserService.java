package com.siddhi.taskmanagement.service;

import com.siddhi.taskmanagement.dto.UserDto;
import com.siddhi.taskmanagement.exception.ResourceNotFoundException;
import com.siddhi.taskmanagement.model.User;
import com.siddhi.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto createUser(UserDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already in use: " + dto.getEmail());
        }

        User user = new User();
        copyFromDtoToEntity(dto, user);

        // Hash password
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setJoiningDate(LocalDate.now());
        user.setLeavingDate(null);

        User saved = userRepository.save(user);

        return mapToDto(saved);
    }

    public UserDto updateUser(Long id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getMobile() != null) user.setMobile(dto.getMobile());
        if (dto.getAddress() != null) user.setAddress(dto.getAddress());
        if (dto.getAge() != null) user.setAge(dto.getAge());
        if (dto.getSalary() != null) user.setSalary(dto.getSalary());
        if (dto.getLeavingDate() !=null) user.setLeavingDate(dto.getLeavingDate());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        User updated = userRepository.save(user);
        return mapToDto(updated);
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapToDto(user);
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return mapToDto(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Helper methods (same as before)
    private void copyFromDtoToEntity(UserDto dto, User user) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setMobile(dto.getMobile());
        user.setAddress(dto.getAddress());
        user.setAge(dto.getAge());
        user.setSalary(dto.getSalary());
    }

    private UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        // NEVER set password here!
        dto.setRole(user.getRole());
        dto.setMobile(user.getMobile());
        dto.setAddress(user.getAddress());
        dto.setAge(user.getAge());
        dto.setSalary(user.getSalary());
        dto.setJoiningDate(user.getJoiningDate());
        dto.setLeavingDate(user.getLeavingDate());
        return dto;
    }
}