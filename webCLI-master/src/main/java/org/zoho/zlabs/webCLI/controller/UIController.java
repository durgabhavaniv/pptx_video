package org.zoho.zlabs.webCLI.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.zoho.zlabs.webCLI.model.User;
import org.zoho.zlabs.webCLI.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Log
@Controller
public class UIController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public void welcome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        if(request.getSession().getAttribute("username") == null){
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }else{
            response.sendRedirect(request.getContextPath()+"/");
        }
    }

    @PostMapping("/loginSubmit")
    void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> user = userService.getUserById(username);
        if(user.isPresent() && user.get().getPassword().equals(password)){
            //Invalidating session if any
            log.info("User:"+username+" logged in successfully");
            request.getSession().invalidate();
            HttpSession newSession = request.getSession(true);
            newSession.setMaxInactiveInterval(300);
            newSession.setAttribute("username", username);
            newSession.setAttribute("role", user.get().getRole());
            response.sendRedirect(request.getContextPath()+"/");
        }else{
            response.sendRedirect(request.getContextPath()+"/login");
        }
    }

    @GetMapping("/")
    void getHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
    }

    @GetMapping("/cards")
    void getCards(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        if(request.getSession().getAttribute("username") == null){
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }else{
            switch(request.getParameter("page")){
                case "terminal" : request.getRequestDispatcher("/WEB-INF/jsp/terminal_card.jsp").forward(request, response);
                break;
                case "pptximage" : request.getRequestDispatcher("/WEB-INF/jsp/pptx_video_card.jsp").forward(request, response);
                break;
                case "ffmpegfilter" : request.getRequestDispatcher("/WEB-INF/jsp/ffmpeg_video_card.jsp").forward(request, response);
                break;
                case "filter1" : request.getRequestDispatcher("/WEB-INF/jsp/filter1_video_card.jsp").forward(request, response);
                break;
                case "filter2" : request.getRequestDispatcher("/WEB-INF/jsp/filter2_video_card.jsp").forward(request, response);
                break;
                case "filter3" : request.getRequestDispatcher("/WEB-INF/jsp/filter3_video_card.jsp").forward(request, response);
                break;
                case "filter4" : request.getRequestDispatcher("/WEB-INF/jsp/filter4_video_card.jsp").forward(request, response);
                break;
                case "filter5" : request.getRequestDispatcher("/WEB-INF/jsp/filter5_video_card.jsp").forward(request, response);
                break;
                case "filter6" : request.getRequestDispatcher("/WEB-INF/jsp/filter6_video_card.jsp").forward(request, response);
                break;
                case "filter7" : request.getRequestDispatcher("/WEB-INF/jsp/filter7_video_card.jsp").forward(request, response);
                break;
                case "filter8" : request.getRequestDispatcher("/WEB-INF/jsp/filter8_video_card.jsp").forward(request, response);
                break;
                default : request.getRequestDispatcher("/WEB-INF/jsp/terminal_card.jsp").forward(request, response);
            }
        }
    }

    @GetMapping("/logout")
    void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        request.getSession().invalidate();
        log.info("User:"+username+" logged out successfully");
        response.sendRedirect(request.getContextPath()+"/login");
    }

    @GetMapping("/admin")
    String usersPage(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        if(request.getSession().getAttribute("role") != null && Integer.parseInt(request.getSession().getAttribute("role").toString()) == 1){
            List<User> userList = userService.getAllUsers();
            model.addAttribute("userList", userList);
            return "Users_admin";
        }else{
            return "redirect:/login";
        }
    }

    @GetMapping("/edit_user")
    String editUser(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        String username = request.getParameter("username");
        Optional<User> user = Optional.empty();
        if(username != null)
            user = userService.getUserById(username);
        if(user.isPresent()){
            model.addAttribute("user",user.get());
            return "User_update";
        }else {
            User user1 = new User("","",0);
            model.addAttribute("user",user1);
            return "User_update";
        }
    }

    @PostMapping("/add_user")
    String addUser(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int role = Integer.parseInt(request.getParameter("role"));
        log.info("user: "+username+" added to database with role: "+(role==1?"Admin":"user"));
        User user = new User(username,password,role);
        userService.saveUser(user);
        log.info("user: "+username+" added/updated");
        return "redirect:/admin";
    }

    @GetMapping("/delete_user")
    String deleteUser(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        String username = request.getParameter("username");
        Optional<User> user = Optional.empty();
        if(username != null){
            userService.deleteUserbyId(username);
            log.info("user: "+username+" deleted");
        }
        return "redirect:/admin";
    }
}
