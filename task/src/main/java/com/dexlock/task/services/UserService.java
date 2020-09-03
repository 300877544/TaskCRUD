package com.dexlock.task.services;


import com.dexlock.task.models.FileDB;
import com.dexlock.task.models.Task;
import com.dexlock.task.models.User;
import com.dexlock.task.repository.FileDBRepository;
import com.dexlock.task.repository.TaskRepository;
import com.dexlock.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FileDBRepository fileDBRepository;

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    public Task saveTask(Task task)
    {
        return taskRepository.save(task);
    }

    public  Task updateTask(Task task)
    {
        Task existingTask = taskRepository.findById(task.getTaskid()).orElse(null);
        existingTask.setTitle(task.getTitle());
        existingTask.setAcceptanceCriteria(task.getAcceptanceCriteria());
        existingTask.setDescription(task.getDescription());
        existingTask.setDuedate(task.getDuedate());
        existingTask.setStartdate(task.getStartdate());
        existingTask.setOriginalestimate(task.getOriginalestimate());
        existingTask.setReporter(task.getReporter());
        existingTask.setStatus(task.getStatus());
        existingTask.setType(task.getType());
        return  taskRepository.save(existingTask);
    }

    public FileDB store(MultipartFile file) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        return fileDBRepository.save(fileDB);

    }

    public FileDB getFile(String id)
    {
        return fileDBRepository.findById(id).get();
    }
    public Stream<FileDB> getAllFiles(){return fileDBRepository.findAll().stream();}
}
