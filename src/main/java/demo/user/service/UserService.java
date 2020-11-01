package demo.user.service;

import java.util.List;

import demo.aop.TestInnerMethodCallAop;
import demo.user.model.User;
import demo.user.repository.UserRepository;
import tw.framework.michaelcore.aop.AopHelper;
import tw.framework.michaelcore.aop.annotation.AopHere;
import tw.framework.michaelcore.data.annotation.Transactional;
import tw.framework.michaelcore.ioc.annotation.Autowired;
import tw.framework.michaelcore.ioc.annotation.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> queryAll() {
        return userRepository.queryAll();
    }

    @Transactional
    public void addUser(User user) {
    	AopHelper.executeInnerMethodWithAop(UserService.class).testInnerMethodCall();
        userRepository.addUser(user);
    }

    @AopHere(TestInnerMethodCallAop.class)
	public void testInnerMethodCall() {
		System.out.println("Inner Method()");
	}

}