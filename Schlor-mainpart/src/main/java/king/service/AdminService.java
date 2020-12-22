package king.service;

import king.dao.AdminDao;
import king.dao.UserDao;
import king.entity.Admin;
import king.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@Service
public class AdminService {
    @Autowired
    AdminDao adminDao;
    @Autowired
    UserService userService;
    public int registeradmin(Admin admin){
        String username = admin.getName();
        String password = admin.getPassword();

        username = HtmlUtils.htmlEscape(username);
        admin.setName(username);
        password = HtmlUtils.htmlEscape(password);

        Admin a = adminDao.findByName(username);
        if (username.equals("") || password.equals("")) {
            return 0;
        }

        if (a!=null) {
            return 2;
        }
        password=userService.encrypt("123",password);
        admin.setPassword(password);
        adminDao.save(admin);
        return 1;
    }

    public Admin findByName(String name){
        return adminDao.findByName(name);
    }
}
