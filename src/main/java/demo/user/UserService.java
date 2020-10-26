package demo.user;

import java.util.List;

import tw.framework.michaelcore.data.annotation.Transactional;
import tw.framework.michaelcore.ioc.annotation.Autowired;
import tw.framework.michaelcore.ioc.annotation.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> queryAll() {
        return userRepository.queryAll();
    }

    @Transactional
    public void addUser(User user) {
        userRepository.addUser(user);
    }

}
