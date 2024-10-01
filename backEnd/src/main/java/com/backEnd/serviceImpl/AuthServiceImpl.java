package com.backEnd.serviceImpl;


import com.backEnd.dto.AccessTokenDto;
import com.backEnd.dto.RegisterDto;
import com.backEnd.dto.UserDto;
import com.backEnd.entity.AppUser;
import com.backEnd.entity.Role;
import com.backEnd.entity.TokenInfo;
import com.backEnd.exception.DuplicateRecordException;
import com.backEnd.exception.RecordNotFoundException;
import com.backEnd.mapping.UserMapper;
import com.backEnd.repository.RoleRepository;
import com.backEnd.repository.UserRepository;
import com.backEnd.security.AppUserDetail;
import com.backEnd.security.JwtTokenUtils;
import com.backEnd.security.TokenInfoService;
import com.backEnd.service.AuthService;
import com.backEnd.utils.AppResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest httpRequest;
    private final TokenInfoService tokenInfoService;
    private final JwtTokenUtils jwtTokenUtils;
    private final RoleRepository roleRepository;

    @Override
    public AppResponse login(String username, String password) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated()){

            AppUserDetail userDetails = (AppUserDetail) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            TokenInfo tokenInfo = createLoginToken(username, userDetails.getId());
            Map<String, Object> userData = new HashMap<>();
            Optional<AppUser> appUser = userRepository.findById(userDetails.getId());

            if(appUser.isPresent()){
                userData.put("authUser", UserMapper.entityToDto(appUser.get()));
                userData.put("token", tokenInfo.getAccessToken());
                userData.put("refreshToken", tokenInfo.getRefreshToken());

                return AppResponse.builder()
                        .ok(true)
                        .message("user_logged_in_success")
                        .status(HttpStatus.OK)
                        .data(userData)
                        .build();
            }
        }
        return null;
    }

    @Override
    public UserDto register(RegisterDto registerDto) {

        Optional<AppUser> appUser =	userRepository.findByUsername(registerDto.getUsername());
        if (appUser.isPresent()) {
            throw new DuplicateRecordException("This User is already registered  : " + registerDto.getUsername());
        }
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        Optional<Role> role = roleRepository.findRoleByName(registerDto.getRole());
        if(!role.isPresent()){
            throw  new RecordNotFoundException("role_not_found");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(role.get());

        AppUser newUser = AppUser.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .password(registerDto.getPassword())
                .roles(roles)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .build();

        userRepository.save(newUser);

        return UserMapper.entityToDto(newUser);
    }

    @Override
    public TokenInfo createLoginToken(String userName, Long userId) {
        String userAgent = httpRequest.getHeader(HttpHeaders.USER_AGENT);
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String accessTokenId = UUID.randomUUID().toString();
        String accessToken = JwtTokenUtils.generateToken(userName, accessTokenId, false);

        String refreshTokenId = UUID.randomUUID().toString();
        String refreshToken = JwtTokenUtils.generateToken(userName, refreshTokenId, true);

        TokenInfo tokenInfo = TokenInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(new AppUser(userId))
                .userAgentText(userAgent)
                .localIpAddress(ip.getHostAddress())
                .remoteIpAddress(httpRequest.getRemoteAddr())
                .build();

        return tokenInfoService.save(tokenInfo);
    }

    @Override
    public AccessTokenDto refreshAccessToken(String refreshToken) {
        if (jwtTokenUtils.isTokenExpired(refreshToken)) {
            return null;
        }
        String userName = jwtTokenUtils.getUserNameFromToken(refreshToken);
        Optional<TokenInfo> refresh = tokenInfoService.findByRefreshToken(refreshToken);
        if (!refresh.isPresent()) {
            return null;
        }

        return new AccessTokenDto(JwtTokenUtils.generateToken(userName, UUID.randomUUID().toString(), false));
    }

    @Override
    public void logoutUser(String refreshToken) {
        log.info("begin logoutUser()");

        Optional<TokenInfo> tokenInfo = tokenInfoService.findByRefreshToken(refreshToken);
        if (tokenInfo.isPresent()) {

            log.info("User loged Out");

            tokenInfoService.deleteById(tokenInfo.get().getId());
        }
    }



}
