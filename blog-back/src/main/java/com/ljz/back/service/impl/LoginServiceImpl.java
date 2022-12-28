package com.ljz.back.service.impl;

import com.ljz.back.common.Result;
import com.ljz.back.common.constant.RedisConstant;
import com.ljz.back.domain.dto.LoginUserDTO;
import com.ljz.back.domain.model.LoginUser;
import com.ljz.back.domain.vo.LoginUserInfoVo;
import com.ljz.back.domain.vo.RouterVo;
import com.ljz.back.pojo.Menu;
import com.ljz.back.service.LoginService;
import com.ljz.back.service.MenuService;
import com.ljz.back.utils.MenuTree;
import com.ljz.back.utils.SecurityUtils;
import com.ljz.back.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @Author : ljz
 * @Date: 2022/12/22  11:03
 */

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private MenuService menuService;

    /**
     * <p> 用户登录 </p>
     *
     * @param loginUserDTO 登录用户信息
     * @return Result
     */
    @Override
    public Result login(LoginUserDTO loginUserDTO) {
        // 调用 Security 的登录方法
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginUserDTO.getUsername());
        if (ObjectUtils.isEmpty(userDetails) || !passwordEncoder.matches(loginUserDTO.getPassword(), userDetails.getPassword())) {
            return Result.error().message("用户名或密码错误");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 生成token
        String token = jwtUtils.generateToken(userDetails);
        // token 放入redis
        redisTemplate.opsForValue().set(RedisConstant.LOGIN_TOKEN + token, token,
                jwtUtils.getExpiration() / 1000, TimeUnit.SECONDS);

        long expireTime = Jwts.parser()
                .setSigningKey(jwtUtils.getSecret()) // 设置解析秘钥
                .parseClaimsJws(token.replace("jwt_", ""))
                .getBody()
                .getExpiration().getTime();

        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("expireTime", String.valueOf(expireTime));

        return Result.ok().setData(map);
    }

    /**
     * <p> 获取登录用户信息 </p>
     *
     * @return Result
     */
    @Override
    public Result getLoginUserInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        System.out.println(loginUser);
        LoginUserInfoVo loginUserInfoVo = new LoginUserInfoVo().setId(loginUser.getUserId())
                .setName(loginUser.getUsername())
                .setAvatar(loginUser.getUser().getAvatar())
                .setIntroduction(null)
                .setRoles(loginUser.getPermissions());
        return Result.ok().setData(loginUserInfoVo);
    }

    /**
     * <p> 用户退出登录 </p>
     *
     * @param request  请求对象
     * @param response 响应对象
     * @return Result
     */
    @Override
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter("token");
        if (ObjectUtils.isEmpty(token)) {
            token = request.getHeader("token");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            //清空用户信息
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            // 清除 Redis 中存储的 token
            redisTemplate.delete(RedisConstant.LOGIN_TOKEN + token);
            return Result.ok().message("用户退出成功");
        }
        return Result.error().message("用户退出失败");
    }

    /**
     * <p> 获取登录用户的路由信息 </p>
     * @return Result
     */
    @Override
    public Result getRouters() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        List<Menu> menus = menuService.selectMenuTreeByUserId(userId);
        List<RouterVo> routerTree = MenuTree.createRouterTree(menus);
        return Result.ok().setData(routerTree);
    }
}
