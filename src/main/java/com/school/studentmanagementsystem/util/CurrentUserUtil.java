package com.school.studentmanagementsystem.util;

import com.school.studentmanagementsystem.model.domain.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserUtil {
    public static User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Object principal = context.getAuthentication().getPrincipal();
        System.out.println(principal);
        if (principal instanceof UserDetails) {
            System.out.println((UserDetails) context.getAuthentication().getPrincipal());
            return (User) context.getAuthentication().getPrincipal();
        }
        else {
            return null;
        }
    }
}
