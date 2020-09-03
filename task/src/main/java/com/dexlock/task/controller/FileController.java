package com.dexlock.task.controller;

import com.dexlock.task.message.ResponseFile;
import com.dexlock.task.message.ResponseMessage;
import com.dexlock.task.models.FileDB;
import com.dexlock.task.models.Task;
import com.dexlock.task.models.User;
import com.dexlock.task.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/auth")

public class FileController {
    @Autowired
    private UserService service;


}
