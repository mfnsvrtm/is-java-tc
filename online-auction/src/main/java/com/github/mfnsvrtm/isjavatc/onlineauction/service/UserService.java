package com.github.mfnsvrtm.isjavatc.onlineauction.service;

import com.github.mfnsvrtm.isjavatc.onlineauction.dao.UserDao;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip.UserDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.User;
import com.github.mfnsvrtm.isjavatc.onlineauction.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUsers() {
        return userDao.findAll().stream().map(userMapper::toDto).toList();
    }

    public UserDto getUserById(int id) {
        return userMapper.toDtoFull(userDao.findById(id).get());
    }

    public UserDto createUser(UserDto userDto) {
        User mapped = userMapper.toEntity(userDto);
        mapped.setPassword(passwordEncoder.encode(mapped.getPassword()));
        User saved = userDao.save(mapped);
        return userMapper.toDto(saved);
    }

    public void deleteUserById(int id) {
        userDao.deleteById(id);
    }

}
