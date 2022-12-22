package com.github.mfnsvrtm.isjavatc.onlineauction.service;

import com.github.mfnsvrtm.isjavatc.onlineauction.TestData;
import com.github.mfnsvrtm.isjavatc.onlineauction.dao.UserDao;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.UserDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.creation.UserCreationDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Group;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.User;
import com.github.mfnsvrtm.isjavatc.onlineauction.mapper.AddressMapper;
import com.github.mfnsvrtm.isjavatc.onlineauction.mapper.GroupMapper;
import com.github.mfnsvrtm.isjavatc.onlineauction.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserDao userDao;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Spy
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Spy
    private final AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
    @Spy
    private final GroupMapper groupMapper = Mappers.getMapper(GroupMapper.class);

    @BeforeEach
    void setUp() {
        // https://stackoverflow.com/a/66327399
        ReflectionTestUtils.setField(userMapper, "addressMapper", addressMapper);
        ReflectionTestUtils.setField(userMapper, "groupMapper", groupMapper);
    }

    @Test
    void getAllUsers() {
        when(userDao.findAll()).thenReturn(TestData.USERS);
        assertIterableEquals(TestData.USERS.stream().map(userMapper::toDto).toList(), userService.getAllUsers());
    }

    @Test
    void getUserById() {
        when(userDao.findById(anyInt())).thenAnswer(i -> Optional.of(TestData.USERS.get(i.getArgument(0))));

        User expectedEntity = TestData.USERS.get(2);
        UserDto expected = new UserDto(
                expectedEntity.getId(),
                expectedEntity.getUsername(),
                expectedEntity.getFirstName(),
                expectedEntity.getLastName(),
                expectedEntity.getEmail(),
                expectedEntity.getPhoneNumber(),
                expectedEntity.getAddress().getAddress(),
                expectedEntity.getGroups().stream().map(Group::getName).toList()
        );

        assertEquals(expected, userService.getUserById(2));
    }

    @Test
    void getUserById_ThrowsForNonExistentId() {
        when(userDao.findById(anyInt())).thenReturn(Optional.empty());
        assertThrowsExactly(NoSuchElementException.class, () -> userService.getUserById(4));
    }

    @Test
    void createUser() {
        when(userDao.save(any(User.class))).thenAnswer(i -> {
            User saved = i.getArgument(0);
            saved.setId(4);
            return saved;
        });
        when(passwordEncoder.encode(anyString())).thenAnswer(i -> "{encoded}" + i.getArgument(0));

        UserCreationDto newUser = new UserCreationDto(
                "user4",
                "password",
                "Us",
                "Er",
                "user4@whatever.idk",
                "89415317045",
                "Address"
        );

        UserDto actual = userService.createUser(newUser);
        assertEquals(newUser.getUsername(), actual.getUsername());

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userDao).save(captor.capture());

        assertEquals("{encoded}" + newUser.getPassword(), captor.getValue().getPassword());
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById(5);
        verify(userDao).deleteById(5);
    }

}
