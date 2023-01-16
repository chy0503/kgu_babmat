package kyonggi_girls.kgu_babmat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoreController {
    @GetMapping("store")
    public String store(@RequestParam("storeName") String storeName, Model model) {
        model.addAttribute("storeName", storeName);
        return "store";
    }
}
