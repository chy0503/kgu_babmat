package kyonggi_girls.kgu_babmat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SelectStoreController {
    @GetMapping("selectStore")
    public String selectStore() {
        return "selectStore";
    }
}