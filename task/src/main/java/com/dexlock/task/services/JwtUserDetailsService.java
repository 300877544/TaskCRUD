package com.dexlock.task.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class JwtUserDetailsService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return new User("admin", "$2a$10$sbSo71iMp8dNfcjtLBzCj.J5R8.vfQIsLkvq6Mq/EwVFdEU7MpKpS",
                    new ArrayList<>());
        }
        else if ("user".equals(username)) {
            return new User("user", "$2a$10$ZpSf1bsJEyz.waJzdyHREeoGSGWFRI95RJ6FSGV0IhITgwp8WpMTu",
                    new ArrayList<>());
        }
        else if ("super-admin".equals(username)) {
            return new User("super-admin", "$2a$10$f4WI3CPJysR1ZSELgWCVWOPEeHJPRkcMIno2g1mZJoVKq08XP1dqO",
                    new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
