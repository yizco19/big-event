package cm.zy.controller;

import cm.zy.pojo.Result;
import cm.zy.pojo.User;
import cm.zy.service.UserService;
import cm.zy.utils.JwtUtil;
import cm.zy.utils.Md5Util;
import cm.zy.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$" ) String username ,@Pattern(regexp = "^\\S{5,16}$" )  String password){
        // buscar usuario
        User u = userService.findByUsername(username);
        /*if(username!= null && username.length()>=5 && username.length()<=16 &&
                password!= null && password.length()>=5 && password.length()<=16) {*/
            if (u == null) {
                // usuario no existe
                // registrar usuario
                userService.register(username, password);
                return Result.success();
            } else {
                // usuario existe
                return Result.error("Usuario ya existe");
            }
       /* }else {
            return Result.error("El nombre de usuario y la contraseña deben tener entre 5 y 16 caracteres");
        }*/

    }
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$" ) String username ,@Pattern(regexp = "^\\S{5,16}$" )  String password){
        // buscar usuario
        User loginUser = userService.findByUsername(username);
        if(loginUser==null){
            return Result.error("User error");
        }
        // codificar password
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);

            return Result.success(token);
        }else {
            return Result.error("Password error");
        }
    }

    @RequestMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization" ) String token*/){
        // buscar usuario por username
       /* Map<String, Object> claims = JwtUtil.parseToken(token);
        String username =(String) claims.get("username");*/
        Map<String, Object> maps = ThreadLocalUtil.get();
        String username =(String) maps.get("username");
        User u = userService.findByUsername(username);

        return Result.success(u);

    }
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("updateAvatar")
    public Result updateAvatar(@RequestParam String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();

    }

    @PatchMapping("updatePwd")
    public  Result updatePwd(@RequestBody Map<String,String> params) {
        // 1.
        String oldPassword = params.get("old_pwd");
        String newPassword = params.get("new_pwd");
        String rePassword = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPassword) || !StringUtils.hasLength(newPassword) || !StringUtils.hasLength(rePassword)) {
            return Result.error("contraseña vacía");
        }
        // comprobar contraseña old si es correcta
        // usa userService para buscar la contraseña del usuario y comparar la contraseña
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUsername(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPassword))) {
            return Result.error("contraseña antigente");
        }
        if (!newPassword.equals(rePassword)) {
            return Result.error("nuevas contraseñas  no coinciden");
        }
        // 2. actualizar contraseña
        userService.updatePwd(newPassword);
        return Result.success();

    }

}
