package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.dto.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserService {
    List<User> getUsers() throws ExecutionException, InterruptedException;
    User getUserInfo(String email) throws ExecutionException, InterruptedException;
    void updateUser(String email, String username) throws ExecutionException, InterruptedException;

    void deleteUser(String email) throws ExecutionException, InterruptedException;
}
