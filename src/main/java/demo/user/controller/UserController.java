package demo.user.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.gson.Gson;

import demo.user.model.User;
import demo.user.service.UserForOrmService;
import demo.user.service.UserService;
import demo.user.service.UserServiceAsync;
import tw.framework.michaelcore.ioc.annotation.Autowired;
import tw.framework.michaelcore.ioc.annotation.components.Controller;
import tw.framework.michaelcore.mvc.Model;
import tw.framework.michaelcore.mvc.annotation.Get;
import tw.framework.michaelcore.mvc.annotation.Post;
import tw.framework.michaelcore.mvc.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private Gson gson;

    @Autowired
    private UserService userService;

    @Autowired
    private UserForOrmService userForOrmService;

    @Autowired
    private UserServiceAsync userServiceAsync;

    @Get("/")
    public Model home() {
        return new Model("index.html");
    }

    @Get("/users")
    public Model queryAll() throws Exception {
        Model model = new Model("data.html");
        model.add("data", gson.toJson(userService.queryAll()));
        return model;
    }

    @Get("/users-orm")
    public Model queryAllOrm() throws Exception {
        Model model = new Model("data.html");
        model.add("data", gson.toJson(userForOrmService.queryAll()));
        return model;
    }

    @Get("/users/async")
    public Model queryAllAsync() throws Exception {
        System.out.println("before queryAsync()");
        CompletableFuture<List<User>> usersFuture = userServiceAsync.queryAllAsync();
        System.out.println("after queryAsync()");

        Model model = new Model("data.html");
        System.out.println("before get()");
        model.add("data", gson.toJson(usersFuture.get()));
        System.out.println("after get()");

        return model;
    }

    @Get("/user/add")
    public Model addUserByGet(@RequestParam("name") String name, @RequestParam("age") int age) {
        userService.addUser(new User(name, age));
        Model model = new Model("success.html");
        model.add("name", name);
        model.add("age", age);
        return model;
    }

    @Post("/user/add")
    public Model addUserByPost(@RequestParam("name") String name, @RequestParam("age") int age) {
        userService.addUser(new User(name, age));
        Model model = new Model("success.html");
        model.add("name", name);
        model.add("age", age);
        return model;
    }

}
