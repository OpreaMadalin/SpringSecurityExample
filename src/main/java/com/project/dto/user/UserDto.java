package com.project.dto.user;

import com.project.dto.GenericDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto extends GenericDto {

  @NotBlank
  @Size(max = 50)
  private String username;

  @NotBlank
  @Size(max = 50)
  private String password;

  @Email
  @NotBlank
  @Size(max = 50)
  private String email;
}
