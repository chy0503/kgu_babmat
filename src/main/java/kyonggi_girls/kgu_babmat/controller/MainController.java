package kyonggi_girls.kgu_babmat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dao.MenuLankingDao;
import kyonggi_girls.kgu_babmat.dao.UserDao;
import kyonggi_girls.kgu_babmat.dao.store.StoreDao;
import kyonggi_girls.kgu_babmat.dto.Menu;
import kyonggi_girls.kgu_babmat.dto.Store;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class MainController {
    private final UserDao userDao;
    private final StoreDao storeDao;
    private final MenuLankingDao menuLankingDao;

    public MainController(UserDao userDao, StoreDao storeDao, MenuLankingDao menuLankingDao) {
        this.userDao = userDao;
        this.storeDao = storeDao;
        this.menuLankingDao = menuLankingDao;
    }


    @GetMapping("main")
    public String main(HttpServletRequest request, Model model) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";

        User userTmp = (User) session.getAttribute(SessionConst.sessionId);
        User user = userDao.getUser(userTmp.getEmail());
        model.addAttribute("user", user);

        // store list
        List<Store> storeList = storeDao.getStores();
        model.addAttribute("storeList", storeList);

        List<Menu> menuLanking = menuLankingDao.showMenuLanking();
        model.addAttribute("menuLanking", menuLanking);
        return "main";
    }
}
