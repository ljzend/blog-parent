package com.ljz.back.domain.model;

import com.ljz.back.common.enums.StatusCode;
import com.ljz.back.pojo.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p></p>
 *
 * @Author : ljz
 * @Date: 2022/12/22  13:04
 */

@Data
@ApiModel("登录用户信息")
@Accessors(chain = true)
@ToString
public class LoginUser implements UserDetails {

    private Long userId;

    private String token;

    private Long expireTime;

    private User user;

    private Set<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(permissions.toString());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(StatusCode.NORMAL.getCode());
    }
}
