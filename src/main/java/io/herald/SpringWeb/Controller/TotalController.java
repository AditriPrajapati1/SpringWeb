package io.herald.SpringWeb.Controller;

import io.herald.SpringWeb.Model.UserTable;
import io.herald.SpringWeb.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//Controller --> HTTP request handler like get, post, etc..
@Controller

public class TotalController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    //Autowired helps in dependency injection, provides all the read functions and APIS to a class/interface object
    //No new keyword is required
    private UserRepository uRepo;

    @GetMapping("/")
    public String firstPage() {
        return "index"; //returns index.html page

    }

    @GetMapping("/signup")
    public String signupGet() {

        return "signupPage.html";
    }

    @GetMapping("/login")
    public String loginGet() {
        return "loginPage";
    }

    @PostMapping("/loginPost")
    public String loginPost(HttpServletRequest request, Model m) {
        String username, password;

        username = request.getParameter("username");
        password = request.getParameter("password");

        //System.out.println(username);
        //System.out.println(password);

        //Static Login
//        if (username.equals("admin") && password.equals("admin")) {
//            return "homePage";
//        }



        //Repository Login

        String hashPassword= DigestUtils.md5DigestAsHex(password.getBytes());

//        if(uRepo.existsByUsernameAndPassword(username, hashPassword))

        try {
            UserTable user = uRepo.findByUsername(username);

            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                List<UserTable> userList = uRepo.findAll();
                m.addAttribute("userList", userList);

                HttpSession session = request.getSession();

                //Session revolves around the HTTP requests, we are trying to get a running session with the above code

                session.setAttribute("username", username);

                //After a successful signin, a username is provided a session acc to their username

                List<UserTable> totalUsers = uRepo.findAll();
                m.addAttribute("totalUsers", totalUsers);
                return "homePage";
            }
        }catch(Exception e)
        {
            m.addAttribute("message","Too many username!!!");
        }


        //Message called as --> Attribute of Model
        m.addAttribute("LoginError", "Username/Password Incorrect");
        return "loginPage";


    }

    @PostMapping("/signup")
    public String signupPost(HttpServletRequest request, Model m) {
        String username, password;
        username = request.getParameter("username");
        password = request.getParameter("password");
        String email = request.getParameter("email");

        //MD5 Hashing --> DigestUtils -->Crackable

//        String hashPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        String hashPassword = passwordEncoder.encode(password);

        UserTable ut = new UserTable();
        ut.setUsername(username);
        ut.setPassword(hashPassword);

        uRepo.save(ut);

        //Mail Sender

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Signed Up Successfully!!");
        message.setText("Welcome to the club: " + username + "!!!!1");
//        mailSender.send(message);

        m.addAttribute("signupSuccess", "Successfully Signed Up! Please login");

        return "loginPage";

    }
        @GetMapping("/home")
        public String homePage(Model m)
        {

            m.addAttribute("totalUsers",uRepo.findAll());
            return "homePage";

            //Model attribute is only for the upcoming page
            //Session attribute is for the whole session
        }


}




