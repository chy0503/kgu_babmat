package kyonggi_girls.kgu_babmat.service;

import kyonggi_girls.kgu_babmat.dto.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserService {
    List<User> getUsers() throws ExecutionException, InterruptedException;

}
