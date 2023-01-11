package kyonggi_girls.kgu_babmat.controller;

import kyonggi_girls.kgu_babmat.domain.CafeteriaMenu;
import kyonggi_girls.kgu_babmat.service.DormitoryMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DormitoryMenuController {
    private DormitoryMenuService dormitoryMenuService;

    public DormitoryMenuController(DormitoryMenuService dormitoryMenuService) {
        this.dormitoryMenuService = dormitoryMenuService;
    }

    @GetMapping("dormitoryMenu")
    public String dormitoryMenu(Model model) throws Exception {
        List<CafeteriaMenu> dormitoryMenuList = dormitoryMenuService.getMenuList();
        model.addAttribute("dormitoryMenu", dormitoryMenuList);
        return "dormitoryMenu";
    }
}
