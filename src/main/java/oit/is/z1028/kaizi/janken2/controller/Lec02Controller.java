package oit.is.z1028.kaizi.janken2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Lec02Controller {

  /**
   * lec02というGETリクエストがあったら，lec02()を呼び出して，lec02.htmlを返すメソッド
   *
   * @return
   */
  @GetMapping("/lec02")
  public String lec02(@RequestParam String name,ModelMap model) {
    model.addAttribute("name",name);
    return "lec02.html";
  }

}
