package ru.otus.spring.autoreport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.autoreport.models.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private long id;
    private String login;
    private String name;
    private String roles;
    private String password;
    private String newPassword;
    private String checkPassword;

    public UserDto(User user){
        this.id= user.getId();
        this.login = user.getLogin();
        this.name = user.getName();
        this.password = user.getPassword();
        this.roles = user.getRoles();

        this.checkPassword = "";
        this.newPassword = "";

    }


    public static UserDto toDto(User user){
        return new UserDto(user);
    }

    public static User toEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setRoles(userDto.getRoles());

        if(!userDto.getNewPassword().equals("")){
            user.setPassword(userDto.getNewPassword());
        }
        return user;
    }


}
