package br.com.javalessons.service;

import br.com.javalessons.domain.User;
import br.com.javalessons.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "register_user")
    public void listenUser(String userJson) {
        log.info("Received new user: {}", userJson);
        this.registerUser(userJson);
    }

    @Cacheable(value = "users", key = "#firstName + '-' + #lastName")
    public User getUserByFirstNameAndLastName(String firstName, String lastName) {
        return this.userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public void registerUser(String userJson) {
        try {
            User user = this.objectMapper.readValue(userJson, User.class);

            User existingUser = this.getUserByFirstNameAndLastName(user.getFirstName(), user.getLastName());
            if (existingUser != null) {
                log.info("User already registered: {} {}", user.getFirstName(), user.getLastName());
                return;
            }

            user = this.userRepository.save(user);
            log.info("User registered | id: {}", user.getUserId());
        } catch (JsonProcessingException e) {
            log.error("Error to register user: {}", userJson, e);
        }
    }

}