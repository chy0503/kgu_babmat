package kyonggi_girls.kgu_babmat.controller.reviews;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dto.*;
import kyonggi_girls.kgu_babmat.service.ReviewService;
import kyonggi_girls.kgu_babmat.service.StoreService;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WriteReviewController {
    private final ReviewService reviewService;
    private final StoreService storeService;

    public WriteReviewController(ReviewService reviewService, StoreService storeService) {
        this.reviewService = reviewService;
        this.storeService = storeService;
    }

    @GetMapping("writeReview")
    public String reviewPage(@RequestParam(value = "storeName", required = false) String storeName, @RequestParam(value = "selectStoreName", required = false) String selectStoreName, Model model, HttpServletRequest request) throws Exception {
//
        // session
        HttpSession session = request.getSession(false);
        if (session == null) return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        if ((selectStoreName == null) || (selectStoreName == "")) {
            List<Store> store = storeService.getStore(storeName);
            model.addAttribute("store", store);
            List<Menu> menuList = storeService.getMenu(null, storeName);
            model.addAttribute("menuList", menuList);
        } else {
            List<Store> store = storeService.getInnerStore(selectStoreName, storeName);
            model.addAttribute("store", store);
            List<Menu> menuList = storeService.getMenu(selectStoreName, storeName);
            model.addAttribute("menuList", menuList);
        }
        model.addAttribute("storeReview", new StoreReview());
        model.addAttribute("selectStoreName", selectStoreName);
        model.addAttribute("storeName", storeName);

        Map<String, String> tags = new LinkedHashMap<>();
        tags.put("추천 해요", "추천 해요");
        tags.put("정말 맛있어요", "정말 맛있어요");
        tags.put("맛있어요", "맛있어요");
        tags.put("그냥 그래요", "그냥 그래요");
        tags.put("맛없어요", "맛없어요");
        tags.put("추천 안해요", "추천 안해요");
        model.addAttribute("tags", tags);

        return "reviews/writeReview";
    }

    @PostMapping("/reviewCreate")
    public String writeReview(@ModelAttribute StoreReview storeReview, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null) return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        reviewService.createReview(user.getEmail(), storeReview.getStoreName(), storeReview.getSelectStore(), storeReview.getMenu(), storeReview.getReviewScore(), storeReview.getTags(), storeReview.getReview(), storeReview.getWriteTime());
        System.out.println(storeReview.getTags());
        System.out.println(storeReview.getSelectStore());
        System.out.println(storeReview.getWriteTime());

        return "redirect:/main";
    }

}
