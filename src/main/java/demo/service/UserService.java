package demo.service;

import demo.domain.user.User;

public interface UserService {

    public User selectUser(Long id);

    public void addUser(User user);

}
