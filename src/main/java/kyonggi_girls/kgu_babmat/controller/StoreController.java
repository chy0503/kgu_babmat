package kyonggi_girls.kgu_babmat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dao.LikeDao;
import kyonggi_girls.kgu_babmat.dao.store.CafeteriaMenuDao;
import kyonggi_girls.kgu_babmat.dao.store.MenuDao;
import kyonggi_girls.kgu_babmat.dao.store.StoreDao;
import kyonggi_girls.kgu_babmat.dto.*;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Controller
public class StoreController {

    private final StoreDao storeDao;
    private final MenuDao menuDao;
    private final CafeteriaMenuDao cafeteriaMenuDao;
    private final LikeDao likeDao;

    public StoreController(StoreDao storeDao, MenuDao menuDao, CafeteriaMenuDao cafeteriaMenuDao, LikeDao likeDao) {
        this.storeDao = storeDao;
        this.menuDao = menuDao;
        this.cafeteriaMenuDao = cafeteriaMenuDao;
        this.likeDao = likeDao;
    }

    @GetMapping("store")
    public String store(@RequestParam(value = "selectStoreName", required = false) String selectStoreName, @RequestParam("storeName") String storeName, Model model, HttpServletRequest request) throws Exception {
        // session
        HttpSession session = request.getSession(false); // 세션 받기
        if (session == null) // 세션이 없으면 intro 화면으로 return
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId); // 세션에 있는 user 정보 받기
        model.addAttribute("user", user); // 화면에 전달하기 위해 model에 add

        // store
        if ((selectStoreName == null) || (selectStoreName == "")) { // 일반 식당일 경우
            List<Store> store = storeDao.getStore(storeName);
            model.addAttribute("store", store);
            List<Menu> menuList = menuDao.getMenu(null, storeName);
            model.addAttribute("menuList", menuList);
        } else { // 푸드코트 내의 식당일 경우
            List<Store> store = storeDao.getInnerStore(selectStoreName, storeName);
            model.addAttribute("store", store);
            List<Menu> menuList = menuDao.getMenu(selectStoreName, storeName);
            model.addAttribute("menuList", menuList);
        }
        List<CafeteriaMenu> cafeteriaMenuList = cafeteriaMenuDao.getCafeteriaMenu(storeName); // 학식 리스트 받기
        String today = cafeteriaMenuDao.getToday(); // 오늘 날짜 받기
        List likedList = likeDao.showLike_menu(storeName, user.getEmail()); // 좋아요 눌린 메뉴 리스트 받기

        Map<String, String> tags = new LinkedHashMap<>();
        tags.put("추천 해요", "추천 해요");
        tags.put("정말 맛있어요", "정말 맛있어요");
        tags.put("맛있어요", "맛있어요");
        tags.put("그냥 그래요", "그냥 그래요");
        tags.put("맛없어요", "맛없어요");
        tags.put("추천 안해요", "추천 안해요");

        model.addAttribute("tags", tags);
        model.addAttribute("cafeteriaMenuList", cafeteriaMenuList);
        model.addAttribute("today", today);
        model.addAttribute("storeName", storeName);
        model.addAttribute("selectStoreName", selectStoreName);
        model.addAttribute("likedList", likedList);
        return "store";
    }

    @PostMapping("store")
    public String store(@ModelAttribute Like like, RedirectAttributes redirect, HttpServletRequest request) throws Exception {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        likeDao.updateLike(user.getEmail(), like.getMenu(), like.getPrice(), like.getSelectStore(), like.getStore());
        redirect.addAttribute("selectStoreName", like.getSelectStore());
        redirect.addAttribute("storeName", like.getStore());
        return "redirect:/store";
    }

    @GetMapping("selectStore")
    public String selectStore(@RequestParam("storeName") String storeName, Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";

        List<Store> store = storeDao.getStore(storeName);
        model.addAttribute("store", store);
        model.addAttribute("storeName", storeName);
        return "selectStore";
    }
}