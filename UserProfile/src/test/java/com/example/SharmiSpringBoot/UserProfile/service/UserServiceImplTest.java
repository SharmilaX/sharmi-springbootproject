package com.example.SharmiSpringBoot.UserProfile.service;

import com.example.SharmiSpringBoot.UserProfile.dto.AddressDto;
import com.example.SharmiSpringBoot.UserProfile.dto.UserDto;
import com.example.SharmiSpringBoot.UserProfile.entity.Address;
import com.example.SharmiSpringBoot.UserProfile.entity.User;
import com.example.SharmiSpringBoot.UserProfile.exception.ResourceNotFoundException;
import com.example.SharmiSpringBoot.UserProfile.exception.UserAlreadyExistException;
import com.example.SharmiSpringBoot.UserProfile.repository.AddressRepository;
import com.example.SharmiSpringBoot.UserProfile.repository.UserRepository;
import com.example.SharmiSpringBoot.UserProfile.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;
    private Address address;
    private AddressDto addressDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUser_id(1L);
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPhoneNumber("9876543210");

        userDto = new UserDto();
        userDto.setName("John");
        userDto.setEmail("john@example.com");
        userDto.setPhoneNumber("9876543210");

        address = new Address();
        address.setAddress_id(1L);
        address.setUserId(1L);
        address.setAddress1("123 Main St");
        address.setCity("Chennai");
        address.setState("Tamil Nadu");
        address.setCountry("India");

        addressDto = new AddressDto(1L, "123 Main St", "Apt 4", "", "Chennai", "Tamil Nadu", "India");
    }

    // ─── createUser ───────────────────────────────────────────────

    @Test
    void createUser_Success() {
        when(userRepository.findByName("John")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertDoesNotThrow(() -> userService.createUser(userDto));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_ThrowsException_WhenUserAlreadyExists() {
        when(userRepository.findByName("John")).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistException.class, () -> userService.createUser(userDto));
        verify(userRepository, never()).save(any(User.class));
    }

    // ─── fetchUser ────────────────────────────────────────────────

    @Test
    void fetchUser_Success() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));

        UserDto result = userService.fetchUser("john@example.com");

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void fetchUser_ThrowsException_WhenNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.fetchUser("unknown@example.com"));
    }

    // ─── updateUser ───────────────────────────────────────────────

    @Test
    void updateUser_Success() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        boolean result = userService.updateUser(userDto);

        assertTrue(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser_ThrowsException_WhenUserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(userDto));
    }

    // ─── deleteUser ───────────────────────────────────────────────

    @Test
    void deleteUser_Success() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(anyLong());

        boolean result = userService.deleteUser("john@example.com");

        assertTrue(result);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_ThrowsException_WhenUserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser("unknown@example.com"));
    }

    // ─── createAddress ────────────────────────────────────────────

    @Test
    void createAddress_Success() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        assertDoesNotThrow(() -> userService.createAddress(addressDto, "john@example.com"));
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void createAddress_ThrowsException_WhenUserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> userService.createAddress(addressDto, "unknown@example.com"));
        verify(addressRepository, never()).save(any(Address.class));
    }

    // ─── updateAddress ────────────────────────────────────────────

    @Test
    void updateAddress_Success() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        when(addressRepository.findByUserId(1L)).thenReturn(Optional.of(address));
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        boolean result = userService.updateAddress(addressDto, "john@example.com");

        assertTrue(result);
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void updateAddress_ThrowsException_WhenAddressNotFound() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        when(addressRepository.findByUserId(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateAddress(addressDto, "john@example.com"));
    }

    // ─── fetchAddress ─────────────────────────────────────────────

    @Test
    void fetchAddress_Success() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        when(addressRepository.findByUserId(1L)).thenReturn(Optional.of(address));

        Address result = userService.fetchAddress("john@example.com");

        assertNotNull(result);
        assertEquals("Chennai", result.getCity());
    }

    @Test
    void fetchAddress_ThrowsException_WhenUserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> userService.fetchAddress("unknown@example.com"));
    }

    // ─── deleteAddress ────────────────────────────────────────────

    @Test
    void deleteAddress_Success() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        when(addressRepository.findByUserId(1L)).thenReturn(Optional.of(address));
        doNothing().when(addressRepository).deleteById(anyLong());

        boolean result = userService.deleteAddress("john@example.com");

        assertTrue(result);
        verify(addressRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteAddress_ThrowsException_WhenUserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> userService.deleteAddress("unknown@example.com"));
        verify(addressRepository, never()).deleteById(anyLong());
    }
}
