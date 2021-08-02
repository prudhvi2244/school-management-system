package com.sms.in.service;

import com.sms.in.entity.UserRegistration;
import com.sms.in.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements IUserRegistrationService {
    @Autowired
    private UserRegistrationRepository urepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserRegistration saveUser(UserRegistration user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        return urepo.save(user);
    }
}
