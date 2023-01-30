package kyonggi_girls.kgu_babmat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.CafeteriaMenu;
import kyonggi_girls.kgu_babmat.dto.Menu;
import kyonggi_girls.kgu_babmat.dto.Store;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.service.LoginService;
import kyonggi_girls.kgu_babmat.service.StoreService;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class StoreController {
    private final StoreService storeService;
    private final LoginService loginService;

    public StoreController(StoreService storeService, LoginService loginService) {
        this.storeService = storeService;
        this.loginService = loginService;
    }

    @GetMapping("store")
    public String store(@RequestParam(value = "selectStoreName", required = false) String selectStoreName, @RequestParam("storeName") String storeName, Model model, HttpServletRequest request) throws Exception {
        // session
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        // store
        if (selectStoreName == null) { // 일반 식당일 경우
            List<Store> store = storeService.getStore(storeName);
            model.addAttribute("store", store);
            List<Menu> menuList = storeService.getMenu(null, storeName);
            model.addAttribute("menuList", menuList);
        } else { // 푸드코트 내의 식당일 경우
            List<Store> store = storeService.getInnerStore(selectStoreName, storeName);
            model.addAttribute("store", store);
            List<Menu> menuList = storeService.getMenu(selectStoreName, storeName);
            model.addAttribute("menuList", menuList);
        }
        List<CafeteriaMenu> cafeteriaMenuList = storeService.getCafeteriaMenu(storeName);
        model.addAttribute("cafeteriaMenuList", cafeteriaMenuList);

        // 학식 불러올 때 - 오늘 날짜
        String today = storeService.getToday();
        model.addAttribute("today", today);

        // 식당 이름
        model.addAttribute("storeName", storeName);

        return "store";
    }

    @GetMapping("selectStore")
    public String selectStore(@RequestParam("storeName") String storeName, Model model) throws ExecutionException, InterruptedException {
        List<Store> store = storeService.getStore(storeName);
        model.addAttribute("store", store);
        model.addAttribute("storeName", storeName);
        return "selectStore";
    }
}