package com.monitoringanak.controller;

import com.monitoringanak.model.User;
import com.monitoringanak.dto.ApiResponse;
import com.monitoringanak.dto.RegisterRequest;
import com.monitoringanak.service.UserService;
import com.monitoringanak.security.RoleValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

        private final UserService userService;
        private final RoleValidator roleValidator;

        /**
         * Get all users by role ID
         * Access: Admin and Guru only
         */
        @GetMapping("/role/{idRole}")
        public ResponseEntity<?> getUsersByRole(@PathVariable Integer idRole, HttpServletRequest request) {
                try {
                        String userRole = (String) request.getAttribute("userRole");

                        // Only Admin and Guru can list users by role
                        if (!roleValidator.hasFullAccess(userRole)) {
                                return ResponseEntity.status(403).body(
                                                ApiResponse.builder()
                                                                .success(false)
                                                                .message("Unauthorized: Only Admin and Guru can access this")
                                                                .code(403)
                                                                .build());
                        }

                        List<User> users = userService.getUsersByRole(idRole);
                        return ResponseEntity.ok(
                                        ApiResponse.builder()
                                                        .success(true)
                                                        .message("Users retrieved successfully")
                                                        .data(users)
                                                        .code(200)
                                                        .build());
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body(
                                        ApiResponse.builder()
                                                        .success(false)
                                                        .message(e.getMessage())
                                                        .code(400)
                                                        .build());
                }
        }

        /**
         * Get user by ID
         * Access: Admin/Guru or the user themselves
         */
        @GetMapping("/{id}")
        public ResponseEntity<?> getUserById(@PathVariable Integer id, HttpServletRequest request) {
                try {
                        Integer userId = (Integer) request.getAttribute("userId");
                        String userRole = (String) request.getAttribute("userRole");

                        // Validate access: Must be Admin/Guru or the user themselves
                        if (!roleValidator.hasFullAccess(userRole) && !userId.equals(id)) {
                                return ResponseEntity.status(403).body(
                                                ApiResponse.builder()
                                                                .success(false)
                                                                .message("Unauthorized access to user profile")
                                                                .code(403)
                                                                .build());
                        }

                        User user = userService.getUserById(id)
                                        .orElseThrow(() -> new RuntimeException("User not found"));

                        return ResponseEntity.ok(
                                        ApiResponse.builder()
                                                        .success(true)
                                                        .message("User details retrieved")
                                                        .data(user)
                                                        .code(200)
                                                        .build());
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body(
                                        ApiResponse.builder()
                                                        .success(false)
                                                        .message(e.getMessage())
                                                        .code(400)
                                                        .build());
                }
        }

        /**
         * Update user
         * Access: Admin only
         */
        @PutMapping("/{id}")
        public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody RegisterRequest request,
                        HttpServletRequest httpRequest) {
                try {
                        String userRole = (String) httpRequest.getAttribute("userRole");

                        // Only Admin can update users
                        if (!roleValidator.isAdmin(userRole)) {
                                return ResponseEntity.status(403).body(
                                                ApiResponse.builder()
                                                                .success(false)
                                                                .message("Unauthorized: Only Admin can update users")
                                                                .code(403)
                                                                .build());
                        }

                        User updatedUser = userService.updateUser(
                                        id,
                                        request.getUsername(),
                                        request.getPassword(),
                                        request.getNama(),
                                        request.getEmail(),
                                        request.getIdRole());

                        return ResponseEntity.ok(
                                        ApiResponse.builder()
                                                        .success(true)
                                                        .message("User updated successfully")
                                                        .data(updatedUser)
                                                        .code(200)
                                                        .build());
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body(
                                        ApiResponse.builder()
                                                        .success(false)
                                                        .message(e.getMessage())
                                                        .code(400)
                                                        .build());
                }
        }

        /**
         * Delete user
         * Access: Admin only
         */
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteUser(@PathVariable Integer id, HttpServletRequest request) {
                try {
                        String userRole = (String) request.getAttribute("userRole");

                        // Only Admin can delete users
                        if (!roleValidator.isAdmin(userRole)) {
                                return ResponseEntity.status(403).body(
                                                ApiResponse.builder()
                                                                .success(false)
                                                                .message("Unauthorized: Only Admin can delete users")
                                                                .code(403)
                                                                .build());
                        }

                        userService.deleteUser(id);
                        return ResponseEntity.ok(
                                        ApiResponse.builder()
                                                        .success(true)
                                                        .message("User deleted successfully")
                                                        .code(200)
                                                        .build());
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body(
                                        ApiResponse.builder()
                                                        .success(false)
                                                        .message(e.getMessage())
                                                        .code(400)
                                                        .build());
                }
        }
}
