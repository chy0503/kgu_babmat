package kyonggi_girls.kgu_babmat.controller.reviews;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kyonggi_girls.kgu_babmat.dao.ReviewDao;
import kyonggi_girls.kgu_babmat.dto.StoreReview;
import kyonggi_girls.kgu_babmat.dto.User;
import kyonggi_girls.kgu_babmat.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
public class EditReviewController {
    private final ReviewDao reviewDao;

    public EditReviewController(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @GetMapping("/edit")
    public String myReview(@ModelAttribute StoreReview storeReview, @RequestParam(required = false) String writeTime,
                           Model model, HttpServletRequest request) throws Exception {


        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";
        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);
        StoreReview storeReviews = reviewDao.getReview(user.getEmail(), writeTime);
        model.addAttribute("reviewList", storeReviews);
        model.addAttribute("writeTime", writeTime);

        Map<String, String> tags = new LinkedHashMap<>();
        tags.put("추천 해요", "추천 해요");
        tags.put("정말 맛있어요", "정말 맛있어요");
        tags.put("맛있어요", "맛있어요");
        tags.put("그냥 그래요", "그냥 그래요");
        tags.put("맛없어요", "맛없어요");
        tags.put("추천 안해요", "추천 안해요");
        model.addAttribute("tags", tags);

        return "reviews/editReview";
    }

    @PostMapping("/modify")
    public String reviewCollect(@ModelAttribute StoreReview storeReview, HttpServletRequest request) throws Exception {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";

        User user = (User) session.getAttribute(SessionConst.sessionId);
        reviewDao.updates(user.getEmail(), storeReview.getReviewScore(), storeReview.getTags(), storeReview.getReview(), storeReview.getWriteTime());
        System.out.println("리뷰 모아보기 : " + reviewDao.showReview_all(user.getEmail()));

        return "redirect:/myPage";
    }

    @GetMapping("/delete")
    public String deleteReview(@RequestParam(required = false) String writeTime,
                               Model model, HttpServletRequest request) throws Exception {
        // session
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/";

        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        reviewDao.modifyAndDeleteReview(user.getEmail(), writeTime);
        List<StoreReview> storeReviews = reviewDao.showReview_all(user.getEmail());
        model.addAttribute("reviewList", storeReviews);
        System.out.println("리뷰 모아보기 : " + reviewDao.showReview_all(user.getEmail()));
        return "redirect:/myPage";
    }
}




