package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.dao.UserDao;
import kyonggi_girls.kgu_babmat.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public List<User> getUsers() throws ExecutionException, InterruptedException {
        return userDao.getUsers();
    }
    @Override
    public User getUserInfo(String email) throws ExecutionException, InterruptedException{
        return userDao.getUserInfo(email);
    }
    @Override
    public void updateUser(String email, String username) throws ExecutionException, InterruptedException {
        userDao.updateUser(email, username);
    }
}
