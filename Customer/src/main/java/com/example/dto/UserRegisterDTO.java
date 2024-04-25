package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegisterDTO {
    @NotBlank(message = "Thông tin bắt buộc")
    @Size(min = 1, message = "Độ dài tối thiểu là 1")
    private String username;

    @NotBlank(message = "Thông tin bắt buộc")
    @Size(min = 8, message = "Độ dài tối thiểu là 8")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).*$", message = "Mật khẩu phải chứa ít nhất 1 chữ số và 1 ký tự đặc biệt")
    private String password;

    @NotBlank(message = "Thông tin bắt buộc")
    private String fullName;

    @NotBlank(message = "Thông tin bắt buộc")
    @Email(message = "Email không hợp lệ!")
    private String email;

    @NotBlank(message = "Address cannot be blank!")
    private String address;

    @NotBlank(message = "Phone Number cannot be blank!")
    private String phoneNumber;

    @NotBlank(message = "Avatar cannot be blank!")
    private String avatar = "default.jpg";
}
