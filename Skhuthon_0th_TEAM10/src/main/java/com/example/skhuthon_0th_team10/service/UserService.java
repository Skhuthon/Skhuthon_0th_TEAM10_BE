package com.example.skhuthon_0th_team10.service;

import com.example.skhuthon_0th_team10.domain.User;
import com.example.skhuthon_0th_team10.dto.UserInfo;
import com.example.skhuthon_0th_team10.dto.UserResDto;
import com.example.skhuthon_0th_team10.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResDto getUserInfo(Principal principal) {
        Long id = Long.parseLong(principal.getName());
        User user = userRepository.findById(id)
                .orElseThrow(()
                        -> new EntityNotFoundException("user", new Exception("user를 찾을 수 없습니다.")));

        return  UserResDto.of(user);
    }
}
