package kyonggi_girls.kgu_babmat.controller;

import kyonggi_girls.kgu_babmat.domain.CafeteriaMenu;
import kyonggi_girls.kgu_babmat.service.CafeteriaMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CafeteriaMenuController {
    private CafeteriaMenuService cafeteriaMenuService;

    public CafeteriaMenuController(CafeteriaMenuService dormitoryMenuService) {
        this.cafeteriaMenuService = dormitoryMenuService;
    }

    @GetMapping("dormitoryMenu")
    public String dormitoryMenu(Model model) throws Exception {
        List<CafeteriaMenu> dormMenuList = cafeteriaMenuService.getDormMenuList();
        model.addAttribute("dormMenu", dormMenuList);
        return "dormitoryMenu";
    }

    @GetMapping("gamcoMenu")
    public String gamcoMenu(Model model) throws Exception {
        List<CafeteriaMenu> gamcoMenuList = cafeteriaMenuService.getGamcoMenuList();
        model.addAttribute("gamcoMenu", gamcoMenuList);
        return "gamcoMenu";
    }


}
