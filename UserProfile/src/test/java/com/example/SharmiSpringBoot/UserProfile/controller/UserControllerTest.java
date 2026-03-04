package com.example.SharmiSpringBoot.UserProfile.controller;

import com.example.SharmiSpringBoot.UserProfile.constants.UserConstants;
import com.example.SharmiSpringBoot.UserProfile.dto.AddressDto;
import com.example.SharmiSpringBoot.UserProfile.dto.UserDto;
import com.example.SharmiSpringBoot.UserProfile.entity.Address;
import com.example.SharmiSpringBoot.UserProfile.exception.GlobalExceptionHandler;
import com.example.SharmiSpringBoot.UserProfile.exception.ResourceNotFoundException;
import com.example.SharmiSpringBoot.UserProfile.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IUserService iUserService;

    @InjectMocks
    private UserController userController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UserDto userDto;
    private AddressDto addressDto;
    private Address address;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        userDto = new UserDto();
        userDto.setName("John");
        userDto.setEmail("john@example.com");
        userDto.setPhoneNumber("9876543210");

        addressDto = new AddressDto(1L, "123 Main St", "Apt 4", "", "Chennai", "Tamil Nadu", "India");

        address = new Address();
        address.setAddress_id(1L);
        address.setUserId(1L);
        address.setAddress1("123 Main St");
        address.setCity("Chennai");
        address.setState("Tamil Nadu");
        address.setCountry("India");
    }

    // ─── createUser ───────────────────────────────────────────────

    @Test
    void createUser_Returns201() throws Exception {
        doNothing().when(iUserService).createUser(any(UserDto.class));

        mockMvc.perform(post("/api/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_201))
                .andExpect(jsonPath("$.statusMsg").value(UserConstants.MESSAGE_201));
    }

    // ─── fetchUser ────────────────────────────────────────────────

    @Test
    void fetchUser_Returns200() throws Exception {
        when(iUserService.fetchUser("john@example.com")).thenReturn(userDto);

        mockMvc.perform(get("/api/fetchUser")
                        .param("email", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    // ─── updateUser ───────────────────────────────────────────────

    @Test
    void updateUser_Returns200_WhenSuccess() throws Exception {
        when(iUserService.updateUser(any(UserDto.class))).thenReturn(true);

        mockMvc.perform(put("/api/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200))
                .andExpect(jsonPath("$.statusMsg").value(UserConstants.MESSAGE_200));
    }

    @Test
    void updateUser_Returns417_WhenFails() throws Exception {
        when(iUserService.updateUser(any(UserDto.class))).thenReturn(false);

        mockMvc.perform(put("/api/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().is(417))
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_417));
    }

    // ─── deleteUser ───────────────────────────────────────────────

    @Test
    void deleteUser_Returns200_WhenSuccess() throws Exception {
        when(iUserService.deleteUser("john@example.com")).thenReturn(true);

        mockMvc.perform(delete("/api/deleteUser")
                        .param("email", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200));
    }

    @Test
    void deleteUser_Returns417_WhenFails() throws Exception {
        when(iUserService.deleteUser(anyString())).thenReturn(false);

        mockMvc.perform(delete("/api/deleteUser")
                        .param("email", "unknown@example.com"))
                .andExpect(status().is(417))
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_417));
    }

    // ─── createAddress ────────────────────────────────────────────

    @Test
    void createAddress_Returns201() throws Exception {
        doNothing().when(iUserService).createAddress(any(AddressDto.class), anyString());

        mockMvc.perform(post("/api/createAddress")
                        .param("email", "john@example.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.ADDRESS_201))
                .andExpect(jsonPath("$.statusMsg").value(UserConstants.ADDRESS_MSG_201));
    }

    // ─── fetchAddress ─────────────────────────────────────────────

    @Test
    void fetchAddress_Returns200() throws Exception {
        when(iUserService.fetchAddress("john@example.com")).thenReturn(address);

        mockMvc.perform(get("/api/fetchAddress")
                        .param("email", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Chennai"));
    }

    @Test
    void fetchAddress_Returns404_WhenUserNotFound() throws Exception {
        when(iUserService.fetchAddress(anyString()))
                .thenThrow(new ResourceNotFoundException("User not found with email: unknown@example.com"));

        mockMvc.perform(get("/api/fetchAddress")
                        .param("email", "unknown@example.com"))
                .andExpect(status().isNotFound());
    }

    // ─── negative paths ───────────────────────────────────────────

    @Test
    void fetchUser_Returns404_WhenNotFound() throws Exception {
        when(iUserService.fetchUser(anyString()))
                .thenThrow(new ResourceNotFoundException("The User is unavailable"));

        mockMvc.perform(get("/api/fetchUser")
                        .param("email", "unknown@example.com"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createAddress_Returns404_WhenUserNotFound() throws Exception {
        doThrow(new ResourceNotFoundException("User not found with email: unknown@example.com"))
                .when(iUserService).createAddress(any(AddressDto.class), anyString());

        mockMvc.perform(post("/api/createAddress")
                        .param("email", "unknown@example.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressDto)))
                .andExpect(status().isNotFound());
    }
}
