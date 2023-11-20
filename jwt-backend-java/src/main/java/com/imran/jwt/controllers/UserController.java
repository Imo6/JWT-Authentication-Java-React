package com.imran.jwt.controllers;


import com.imran.jwt.entity.AuthRequest;
import com.imran.jwt.entity.UserInfo;
import com.imran.jwt.response.JwtResponse;
import com.imran.jwt.services.JwtService;
import com.imran.jwt.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to you in my website, Thankyou!!";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<JwtResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
      if (authentication.isAuthenticated()) {
        String token = jwtService.generateToken(authRequest.getUsername());
        String username = authRequest.getUsername();
        JwtResponse response = new JwtResponse(username, token);
        return ResponseEntity.ok(response);
      } else {
        throw new UsernameNotFoundException("Invalid user request!");
    }
  }

}

