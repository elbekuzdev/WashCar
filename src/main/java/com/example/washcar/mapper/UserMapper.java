package com.example.washcar.mapper;

import com.example.washcar.dto.RoleDto;
import com.example.washcar.dto.UserDto;
import com.example.washcar.entity.Role;
import com.example.washcar.entity.Users;

public class UserMapper {
    public static Users toEntity(UserDto userDto){
        if (userDto != null){
            return new Users(userDto.getId(), userDto.getName(), userDto.getLogin(), userDto.getPassword(), Role.valueOf(userDto.getRoleDto().toString()));
        }
        return null;
    }

    public static UserDto toDto(Users user){
        if (user != null){
            return new UserDto(user.getId(), user.getName(), user.getLogin(), user.getPassword(), RoleDto.valueOf(user.getRole().name()));
        }
        return null;
    }
}
