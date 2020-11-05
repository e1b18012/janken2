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
  @GetMapping("/hand={param}")
  public String lec02_1(@PathVariable String param,ModelMap model){
    String hand = param;
    String aite = "Gu";
    String result = null;
    model.addAttribute("hand",hand);
    model.addAttribute("aite",aite);
    if(hand.equals("Pa")){
      result="You Win!";
      model.addAttribute("result",result);
    }else if(hand.equals("Tyoki")){
      result="You Lose...";
      model.addAttribute("result",result);
    }else{
      result="Even";
      model.addAttribute("result",result);
    }
    return "lec02.html";
  }
}
