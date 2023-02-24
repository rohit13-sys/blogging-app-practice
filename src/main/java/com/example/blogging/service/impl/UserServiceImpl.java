package com.example.blogging.service.impl;

import com.example.blogging.entity.Users;
import com.example.blogging.exceptions.UserAlreadyExists;
import com.example.blogging.exceptions.UserNotFound;
import com.example.blogging.payloads.UserDto;
import com.example.blogging.repository.UserReposiory;
import com.example.blogging.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserReposiory userReposiory;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto){
        try{
            UserDto savedUser=getUserByUserName(userDto.getEmail());
            if(savedUser!=null){
                throw new UserAlreadyExists("User Already Exists!!");
            }else {
                throw new UserNotFound("User Not Found!!!");
            }
        }catch (UserNotFound e){
            Users user=dtoToUser(userDto);
            user=userReposiory.save(user);
            return userToDto(user);
        }


    }

    @Transactional
    @Override
    public UserDto updateUser(int id,UserDto userDto) {
       Users user=userReposiory.findById(id).get();
       user.setName(userDto.getName());
       user.setAbout(user.getAbout());
       userReposiory.save(user);
       return userToDto(user);
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        Users user=userReposiory.findByEmail(userName).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        if(user!=null){

            return userToDto(user);

        }else {
            throw new UserNotFound("User Not Found!!!");
        }

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<Users> users=userReposiory.findAll();
        List<UserDto> userDtoList=users.stream().map(u->userToDto(u)).collect(Collectors.toList());
        return userDtoList;
    }

    @Transactional
    @Override
    public void deleteUserByUserName(int id) {
        Users user=userReposiory.findById(id).get();
        userReposiory.delete(user);
    }

    @Override
    public int getUserIdByUserName(String userName) {
        Users user=userReposiory.findByEmail(userName).orElseThrow(()->new UsernameNotFoundException("USer Not Found"));
        if (user != null) {
                return user.getId();
        }else {
            throw new UserNotFound("User Not Found!!! ");
        }

    }

    UserDto userToDto(Users user){
      UserDto userDto=modelMapper.map(user, UserDto.class);
      return userDto;
    }

    Users dtoToUser(UserDto userDto){
        Users user=modelMapper.map(userDto, Users.class);
        return user;
    }
}
