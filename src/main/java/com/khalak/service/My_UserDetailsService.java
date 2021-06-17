package com.khalak.service;

import com.khalak.model.User;
import com.khalak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class My_UserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        My_UserDetails userDetails;
        if(user != null){
            userDetails = new My_UserDetails();
            userDetails.setUser(user);
        }else{
            throw new UsernameNotFoundException("User not exist!");
        }
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not present"));
        return userDetails;
    }

    public void createUser(UserDetails user){
        userRepository.save((User) user);
    }

}