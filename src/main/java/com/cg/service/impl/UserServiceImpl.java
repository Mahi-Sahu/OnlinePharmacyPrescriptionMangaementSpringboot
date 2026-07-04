package com.cg.service.impl;

import com.cg.dto.request.UserRequestDto;
import com.cg.dto.response.UserResponseDto;
import com.cg.entity.User;
import com.cg.repository.UserRepository;
import com.cg.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users=userRepository.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users.stream().map(u->modelMapper.map(u,UserResponseDto.class)).toList();
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user=userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        return modelMapper.map(user,UserResponseDto.class);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user=new User();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setDob(userRequestDto.getDob());
        user.setGender(userRequestDto.getGender());
        user.setEmergencyContactName(userRequestDto.getEmergencyContactName());
        user.setEmergencyContactPhone(userRequestDto.getEmergencyContactPhone());
        user.setIsActive(1);
        user.setPhone(userRequestDto.getPhone());
        user.setRole(userRequestDto.getRole());
        return  modelMapper.map(userRepository.saveAndFlush(user),UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        if(userRequestDto.getFirstName()!=null){
            user.setFirstName(userRequestDto.getFirstName());
        }
        if(userRequestDto.getLastName()!=null){
            user.setLastName(userRequestDto.getLastName());
        }
        if(userRequestDto.getDob()!=null){
            user.setDob(userRequestDto.getDob());
        }
        if(userRequestDto.getEmergencyContactName()!=null){
            user.setEmergencyContactName(userRequestDto.getEmergencyContactName());
        }
        if(userRequestDto.getEmergencyContactPhone()!=null){
            user.setEmergencyContactPhone(userRequestDto.getEmergencyContactPhone());
        }
        if(userRequestDto.getPhone()!=null) {
            user.setPhone(userRequestDto.getPhone());
        }
        if(userRequestDto.getIsActive()!=null){
            user.setIsActive(userRequestDto.getIsActive());
        }
        user.setUpdatedAt(LocalDateTime.now());
        return  modelMapper.map(userRepository.saveAndFlush(user),UserResponseDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
