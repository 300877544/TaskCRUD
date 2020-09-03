package com.dexlock.task.controller;

import com.dexlock.task.config.JwtTokenUtil;
import com.dexlock.task.message.ResponseFile;
import com.dexlock.task.message.ResponseMessage;
import com.dexlock.task.models.FileDB;
import com.dexlock.task.models.Task;
import com.dexlock.task.models.User;
import com.dexlock.task.payload.response.JwtResponse;
import com.dexlock.task.payload.response.JwtResponses;
import com.dexlock.task.services.JwtUserDetailsService;
import com.dexlock.task.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api/auth")
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService service;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception{
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final  String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponses(token));
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }
        catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    @PostMapping("/user")
    public User addUser(@RequestBody User user)
    {
        return service.saveUser(user);
    }

    @PostMapping("/task")
    public Task addTask(@RequestBody Task task)
    {
        return service.saveTask(task);
    }

    @PutMapping("/task")
    public Task updateTask(@RequestBody Task task){ return service.updateTask(task);}


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file)
    {
        String message="";
        try {
            service.store(file);
            message = "Uploaded file successfully: " + file.getOriginalFilename();
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }
        catch (Exception e)
        {
            message = "Could not update file " + file.getOriginalFilename() ;
            return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles(){
        List<ResponseFile> files =  service.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files")
                    .path(dbFile.getId())
                    .toUriString();
            return  new ResponseFile(dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id){
        FileDB fileDB = service.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, " attachment; filename= " + fileDB.getName() + "/")
                .body(fileDB.getData());
    }

}
