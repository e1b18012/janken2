package oit.is.z1028.kaizi.janken2.controller.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT id, namefrom users where id = #{id}")
  User selectById(int id);

  @Select("SELECT * from users")
  ArrayList<User> selectAllByName();

  /**
   * DBのカラム名とjavaクラスのフィールド名が同じ場合はそのまま代入してくれる（大文字小文字の違いは無視される）
   * カラム名とフィールド名が異なる場合の対応も可能だが，いきなり複雑になるので，selectで指定するテーブル中のカラム名とクラスのフィールド名は同一になるよう設計することが望ましい
   *
   * @return
   */
  /**@Select("select CHAMBER.USER,CHAMBER.NUMBER,USERINFO.HEIGHT from CHAMBER JOIN USERINFO ON CHAMBER.USER=USERINFO.USER;")
  ArrayList<ChamberUser> selectAllChamberUser();**/

  /**
   * #{user}などはinsertの引数にあるChamberクラスのフィールドを表しています 引数に直接String userなどと書いてもいけるはず
   * 下記のOptionsを指定すると，insert実行時にAuto incrementされたIDの情報を取得できるようになる useGeneratedKeys
   * = true -> Keyは自動生成されることを表す keyColumn : keyになるテーブルのカラム名 keyProperty :
   * keyになるJavaクラスのフィールド名
   *
   * @param users
   */
  @Insert("INSERT INTO users (name) VALUES (#{name});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUsers(User users);

  @Insert("INSERT INTO matches (user_1,user_2,user_1_hand,user_2_hand) VALUES (#{user_1},#{user_2},#{user_1_hand},#{user_2_hand});")
  void insertMatches(User matches);

}
