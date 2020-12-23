package king.controller;

import king.entity.Admin;
import king.entity.User;
import king.service.AdminService;
import king.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import result.*;
import result.ResultFactory;

@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @PostMapping("/api/register")
    public Result register(@RequestBody User user) {
        int status = userService.register(user);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("用户名和密码不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("用户已存在");
        }
        return ResultFactory.buildFailResult("未知错误");
    }
    @PostMapping("/api/login")
    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getName();
        username = HtmlUtils.htmlEscape(username);
        User user=userService.findByUsername(username);
        String password =requestUser.getPassword();
        password= userService.encrypt("123",password);
        if(user==null||!password.equals(user.getPassword())){
            return ResultFactory.buildFailResult("账号或密码错误");
        }
        return ResultFactory.buildSuccessResult("登录成功");
    }
    @GetMapping("/api/logout")
    public Result logout() {
        return ResultFactory.buildSuccessResult("成功登出");
    }

    @PostMapping("/api/adminregister")
    public Result adminregister(@RequestBody Admin admin) {
        int status = adminService.registeradmin(admin);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("用户名和密码不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("用户已存在");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @PostMapping("/api/adminlogin")
    public Result adminlogin(@RequestBody Admin requestAdmin) {
        String username = requestAdmin.getName();
        username = HtmlUtils.htmlEscape(username);
        Admin admin=adminService.findByName(username);
        String password =requestAdmin.getPassword();
        password= userService.encrypt("123",password);
        if(admin==null||!password.equals(admin.getPassword())){
            return ResultFactory.buildFailResult("账号或密码错误");
        }
        return ResultFactory.buildSuccessResult("登录成功");
    }

}
