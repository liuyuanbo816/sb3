package org.zmz.sb3.security.browser.userdetails;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    public static final Logger LOG = LoggerFactory.getLogger(MyUserDetailsServiceImpl.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Set<MyUserDetails> getUserDetails() {
        ClassPathResource classPathResource = new ClassPathResource("user.json");
        try {
            InputStream in = classPathResource.getInputStream();
            return objectMapper.readValue(in, new TypeReference<>() {});
        } catch (IOException e) {
            LOG.error("getUserDetails 异常 (读取文件路径不正确或者文件格式非法)");
            throw new RuntimeException(e);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.info("用户名: {}", username);
        Set<MyUserDetails> userDetails = getUserDetails();
        MyUserDetails myUserDetails = userDetails.stream()
                .filter(u -> username.equals(u.getUsername()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(username + " 对应数据不存在"));
        Set<SimpleGrantedAuthority> authorities = Arrays.stream(myUserDetails.getAuthorities().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return User.withUsername(username)
                .password(passwordEncoder.encode(myUserDetails.getPassword()))
                .authorities(authorities)
                .build();
    }
}
