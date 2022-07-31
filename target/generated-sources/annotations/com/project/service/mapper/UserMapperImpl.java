package com.project.service.mapper;

import com.project.dto.user.UserDto;
import com.project.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-12T16:51:52+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromDto(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setUsername( userDto.getUsername() );
        user.setPassword( userDto.getPassword() );
        user.setEmail( userDto.getEmail() );

        return user;
    }

    @Override
    public UserDto toDto(User byUsername) {
        if ( byUsername == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( byUsername.getId() );
        userDto.setUsername( byUsername.getUsername() );
        userDto.setPassword( byUsername.getPassword() );
        userDto.setEmail( byUsername.getEmail() );

        return userDto;
    }

    @Override
    public List<UserDto> toDtos(List<User> all) {
        if ( all == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( all.size() );
        for ( User user : all ) {
            list.add( toDto( user ) );
        }

        return list;
    }
}
