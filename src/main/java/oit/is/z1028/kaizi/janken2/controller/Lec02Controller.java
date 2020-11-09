package oit.is.z1028.kaizi.janken2.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1028.kaizi.janken2.controller.model.Entry;
import oit.is.z1028.kaizi.janken2.controller.model.User;
import oit.is.z1028.kaizi.janken2.controller.model.UserMapper;
import oit.is.z1028.kaizi.janken2.controller.model.Match;
import oit.is.z1028.kaizi.janken2.controller.model.MatchMapper;

@Controller
public class Lec02Controller {

  @Autowired
  private Entry entry;
  @Autowired
  UserMapper usersMapper;
  @Autowired
  MatchMapper matchesMapper;

  /**
   * lec02というGETリクエストがあったら，lec02()を呼び出して，lec02.htmlを返すメソッド
   *
   * @return
   */
  @GetMapping("/lec02")
  public String lec02(Principal prin,ModelMap model) {
    String loginUser = prin.getName(); //ログインユーザー名
    ArrayList<User> names = usersMapper.selectAllByName();
    ArrayList<Match> users = matchesMapper.selectAllByUsers();
    this.entry.addUser(loginUser);
    model.addAttribute("login_user",loginUser);
    model.addAttribute("entry",this.entry);
    model.addAttribute("name", names);
    model.addAttribute("users", users);
    return "lec02.html";
  }
  @GetMapping("/hand={param}")
  @Transactional
  public String lec02_1(@PathVariable String param,ModelMap model,Principal prin){
    String hand = param;
    String aite = "Gu";
    String result = null;
    String loginUser = prin.getName();
    Match mm = new Match();
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
    mm.setUser_1(2);
    mm.setUser_2(1);
    mm.setUser_1_hand(aite);
    mm.setUser_2_hand(hand);
    try {
      matchesMapper.insertMatches(mm);
    } catch (RuntimeException e) {// 既に身長が登録されているユーザでさらに登録しようとすると実行時例外が発生するので，コンソールに出力してinsertをSkipする
      System.out.println("Exception:" + e.getMessage());
    }

    return "match.html";
  }
  @GetMapping("/match")
  @Transactional
  public String lec04_1(@RequestParam Integer id, ModelMap model, Principal prin){
    //String loginUser = prin.getName();
    ArrayList<User> names = usersMapper.selectAllByName();
    ArrayList<Match> users = matchesMapper.selectAllByUsers();
    model.addAttribute("name", names);
    model.addAttribute("users", users);
    if(id==1){
      model.addAttribute("id",id);
    }
    //model.addAttribute("loginUser", loginUser);
    return "match.html";
  }

}
