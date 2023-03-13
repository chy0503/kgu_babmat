package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.dao.UserDao;
import kyonggi_girls.kgu_babmat.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserDao userDao;

    public List<User> getUsers() throws ExecutionException, InterruptedException {
        return userDao.getUsers();
    }

    public boolean isUser(String email) throws ExecutionException, InterruptedException {
        return userDao.isUser(email);
    }

    public User getUser(String email) throws ExecutionException, InterruptedException {
        return userDao.getUser(email);
    }

}
