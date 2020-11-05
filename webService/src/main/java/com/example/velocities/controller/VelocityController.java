package com.example.velocities.controller;

import com.example.velocities.entity.Velocity;
import com.example.velocities.repository.VelocityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VelocityController {

    @Autowired
    VelocityRepository velocityRepository;

 // Get All Notes
    @CrossOrigin(origins = "*") //CORS (Cross-origin resource sharing) for all sites
    @GetMapping("/velocities")
    public List<Velocity> getAllVelocities(HttpServletResponse response) {
    	response.setHeader("Cache-Control", "no-cache");
        return velocityRepository.findAll();
    }
}