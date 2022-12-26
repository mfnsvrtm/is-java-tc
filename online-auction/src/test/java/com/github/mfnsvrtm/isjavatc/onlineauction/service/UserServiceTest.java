package com.github.mfnsvrtm.isjavatc.onlineauction.service;

import com.github.mfnsvrtm.isjavatc.onlineauction.TestData;
import com.github.mfnsvrtm.isjavatc.onlineauction.dao.UserDao;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.wip.*;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Group;
import com.github.mfnsvrtm.isjavatc.onlineauction.entity.User;
import com.github.mfnsvrtm.isjavatc.onlineauction.mapper.*;
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

import java.util.List;
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
    @Spy
    private final BidMapper bidMapper = Mappers.getMapper(BidMapper.class);
    @Spy
    private final LotMapper lotMapper = Mappers.getMapper(LotMapper.class);

    @BeforeEach
    void setUpMappers() {
        // https://stackoverflow.com/a/66327399
        ReflectionTestUtils.setField(userMapper, "addressMapper", addressMapper);
        ReflectionTestUtils.setField(userMapper, "groupMapper", groupMapper);
        ReflectionTestUtils.setField(userMapper, "bidMapper", bidMapper);
        ReflectionTestUtils.setField(bidMapper, "lotMapper", lotMapper);
        ReflectionTestUtils.setField(bidMapper, "userMapper", userMapper);
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
                null,
                expectedEntity.getFirstName(),
                expectedEntity.getLastName(),
                expectedEntity.getEmail(),
                expectedEntity.getPhoneNumber(),
                new AddressDto(expectedEntity.getAddress().getId(), expectedEntity.getAddress().getAddress()),
                expectedEntity.getGroups().stream().map(group -> new GroupDto(group.getId(), group.getName())).toList(),
                expectedEntity.getItems().stream().map(item -> new ItemDto(
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getCondition(),
                        new CategoryDto(item.getCategory().getId(), item.getCategory().getName()),
                        new UserDto(expectedEntity.getId(), expectedEntity.getUsername(), null, null, null, null, null, null, null, null, null),
                        new LotDto(item.getLot().getId(), null, null, null, null, null, null, null))).toList(),
                expectedEntity.getBids().stream().map(bid -> new BidDto(
                        bid.getId(),
                        bid.getAmount(),
                        bid.getTime(),
                        bid.getRetracted(),
                        new LotDto(bid.getLot().getId(), null, null, null, null, null, null, null),
                        new UserDto(expectedEntity.getId(), expectedEntity.getUsername(), null, null, null, null, null, null, null, null, null)
                )).toList()
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

        AddressDto userAddress = AddressDto.builder().address("Address").build();
        UserDto newUser = UserDto
                .builder()
                .username("user4")
                .password("password")
                .firstName("Us")
                .lastName("Er")
                .email("user4@whatever.idk")
                .phoneNumber("89415317045")
                .address(userAddress)
                .build();

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
