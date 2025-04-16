package it.aulab.xjava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.aulab.xjava.dtos.UserDto;
import it.aulab.xjava.models.Role;
import it.aulab.xjava.models.User;
import it.aulab.xjava.repositories.RoleRepository;
import it.aulab.xjava.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String DEFAULT_ROLE = "ROLE_USER";

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(UserDto userDto, RedirectAttributes redirectAttributes,
                         HttpServletRequest request, HttpServletResponse response) {

        User user = new User();
        user.setUsername(userDto.getFirstname() + " " + userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // 查找 ROLE_USER 角色
        Optional<Role> optionalRole = Optional.ofNullable(roleRepository.findByName(DEFAULT_ROLE));
        if (!optionalRole.isPresent()) {
            throw new RuntimeException("Role " + DEFAULT_ROLE + " not found");
        }
        user.setRoles(List.of(optionalRole.get()));

        userRepository.save(user);

        authenticateUserAndSetSession(user, userDto, request);
    }

    private void authenticateUserAndSetSession(User user, UserDto userDto, HttpServletRequest request) {
        try {
            CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        } catch (AuthenticationException e) {
            // Log error here
            e.printStackTrace();
            throw new RuntimeException("Authentication failed for user: " + user.getEmail(), e);
        }
    }
}
