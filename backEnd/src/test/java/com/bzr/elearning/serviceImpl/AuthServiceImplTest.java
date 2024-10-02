package com.bzr.elearning.serviceImpl;

import com.bzr.elearning.entity.AppUser;
import com.bzr.elearning.entity.TokenInfo;
import com.bzr.elearning.security.AppUserDetail;
import com.bzr.elearning.utils.AppResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplTest {

    @Test
    void testLogin_Success() {
        // Mock des paramètres
        String username = "testUser";
        String password = "password";
        AppUserDetail userDetails = new AppUserDetail(1L, username, "password");

        Authentication authentication = mock(Authentication.class);
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        AppUser appUser = AppUser.builder().id(1L).username(username).build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(appUser));

        TokenInfo tokenInfo = TokenInfo.builder().accessToken("accessToken").refreshToken("refreshToken").build();
        when(tokenInfoService.save(any(TokenInfo.class))).thenReturn(tokenInfo);

        // Appel de la méthode à tester
        AppResponse response = authServiceImpl.login(username, password);

        // Vérification des résultats
        assertNotNull(response);
        assertTrue(response.isOk());
        assertEquals("user_logged_in_success", response.getMessage());
    }


}