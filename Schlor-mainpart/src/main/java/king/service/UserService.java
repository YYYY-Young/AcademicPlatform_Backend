package king.service;

import king.dao.UserDao;
import king.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    //加密
    public String encrypt(String sourceString,String password){
        char[] p=password.toCharArray();
        int n=p.length;
        char [] c=sourceString.toCharArray();
        int m=c.length;
        for (int k = 0; k < m; k++) {
            int mima = c[k] + p[k / n];
            c[k] = (char) mima;
        }
        return new String(c);
    }
    //解密
    public String decrypt(String sourceString, String password) {
        char[] p = password.toCharArray();
        int n = p.length;
        char[] c = sourceString.toCharArray();
        int m = c.length;
        for (int k = 0; k < m; k++) {
            int mima = c[k] - p[k / n];
            c[k] = (char) mima;
        }
        return new String(c);
    }
    public User findByUsername(String username) {
        return userDao.findByName(username);
    }
    public boolean isExist(String username) {
        User user = userDao.findByName(username);
        return null != user;
    }
    public int register(User user) {
        String username = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();

        username = HtmlUtils.htmlEscape(username);
        user.setName(username);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);
        password = HtmlUtils.htmlEscape(password);

        if (username.equals("") || password.equals("")) {
            return 0;
        }

        boolean exist = isExist(username);

        if (exist) {
            return 2;
        }
        password=encrypt("123",password);
        user.setPassword(password);
        userDao.save(user);
        return 1;
    }

    public User resetPassword(User user){
        User userInDB = userDao.findByName(user.getName());
        String old=userInDB.getPassword();
        String password=user.getPassword();
        password=encrypt("123",password);
        if(old.equals(password)){
            return null;
        }
        userInDB.setPassword(password);
        return userDao.save(userInDB);
    }
    public User editUser(User user) {
        User userInDB = userDao.findByName(user.getName());
        userInDB.setName(user.getName());
        userInDB.setBirth(user.getBirth());
        userInDB.setEmail(user.getEmail());
        User re=userDao.save(userInDB);
        return re;
    }
    public void deleteById(int id){
        userDao.deleteById(id);
    }
}
