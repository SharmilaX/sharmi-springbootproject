package com.example.SharmiSpringBoot.UserProfile.repository;

import com.example.SharmiSpringBoot.UserProfile.entity.Address;
import com.example.SharmiSpringBoot.UserProfile.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    private User savedUser;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPhoneNumber("9876543210");
        savedUser = userRepository.save(user);
    }

    // ─── UserRepository ───────────────────────────────────────────

    @Test
    void findByEmail_ReturnsUser_WhenExists() {
        Optional<User> result = userRepository.findByEmail("john@example.com");

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getName());
    }

    @Test
    void findByEmail_ReturnsEmpty_WhenNotExists() {
        Optional<User> result = userRepository.findByEmail("unknown@example.com");

        assertFalse(result.isPresent());
    }

    @Test
    void findByName_ReturnsUser_WhenExists() {
        Optional<User> result = userRepository.findByName("John");

        assertTrue(result.isPresent());
        assertEquals("john@example.com", result.get().getEmail());
    }

    @Test
    void findByName_ReturnsEmpty_WhenNotExists() {
        Optional<User> result = userRepository.findByName("Unknown");

        assertFalse(result.isPresent());
    }

    // ─── AddressRepository ────────────────────────────────────────

    @Test
    void findByUserId_ReturnsAddress_WhenExists() {
        Address address = new Address();
        address.setUserId(savedUser.getUser_id());
        address.setAddress1("123 Main St");
        address.setCity("Chennai");
        address.setState("Tamil Nadu");
        address.setCountry("India");
        addressRepository.save(address);

        Optional<Address> result = addressRepository.findByUserId(savedUser.getUser_id());

        assertTrue(result.isPresent());
        assertEquals("Chennai", result.get().getCity());
    }

    @Test
    void findByUserId_ReturnsEmpty_WhenNotExists() {
        Optional<Address> result = addressRepository.findByUserId(999L);

        assertFalse(result.isPresent());
    }
}
