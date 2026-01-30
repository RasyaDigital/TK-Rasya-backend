package com.monitoringanak.service;

import com.monitoringanak.model.User;
import com.monitoringanak.repository.UserRepository;
import com.monitoringanak.repository.RoleRepository;
import com.monitoringanak.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
@AllArgsConstructor
@SuppressWarnings("null")
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Login user - cek username & password
     */
    public Optional<User> login(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Register user (admin only)
     */
    public User register(String username, String password, String nama, String email, Integer idRole) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        Role role = roleRepository.findById(idRole)
                .orElseThrow(() -> new RuntimeException("Role not found!"));

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nama(nama)
                .email(email)
                .role(role)
                .build();

        return userRepository.save(user);
    }

    /**
     * Get user by ID
     */
    public Optional<User> getUserById(Integer idUser) {
        return userRepository.findById(idUser);
    }

    /**
     * Get user by username
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Get all users by role
     */
    public List<User> getUsersByRole(Integer idRole) {
        return userRepository.findByRole_IdRole(idRole);
    }

    /**
     * Get all teachers (role = guru)
     */
    public List<User> getAllGuru() {
        // Assuming idRole 2 for guru
        return userRepository.findByRole_IdRole(2);
    }

    /**
     * Verify password
     */
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * Update user
     */
    /**
     * Update user (comprehensive)
     */
    public User updateUser(Integer idUser, String username, String password, String nama, String email,
            Integer idRole) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // Update Username (check uniqueness)
        if (username != null && !username.isEmpty() && !username.equals(user.getUsername())) {
            if (userRepository.findByUsername(username).isPresent()) {
                throw new RuntimeException("Username already exists!");
            }
            user.setUsername(username);
        }

        // Update Password (if provided)
        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }

        if (nama != null && !nama.isEmpty()) {
            user.setNama(nama);
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }

        // Update Role (if provided)
        if (idRole != null) {
            Role role = roleRepository.findById(idRole)
                    .orElseThrow(() -> new RuntimeException("Role not found!"));
            user.setRole(role);
        }

        return userRepository.save(user);
    }

    /**
     * Delete user
     */
    public void deleteUser(Integer idUser) {
        userRepository.deleteById(idUser);
    }
}
