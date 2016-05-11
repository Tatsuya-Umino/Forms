package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import static play.data.Form.*;
import java.util.*;
import play.data.validation.Constraints.Required;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	Forms f = new Forms();
    	Form<Forms> form = form(Forms.class).fill(f);
    	return ok(index.render("フォームを入力してください。", form));
    }

    public static Result send() {
    	String radioSt = null;
    	String selectSt = null;
        Form<Forms> sampleForm = form(Forms.class).bindFromRequest();
        if (!sampleForm.hasErrors()) {
          Forms data = sampleForm.get();
          switch (data.radio){
          case 0:
        	  radioSt = "MS-Windows";
        	  break;
          case 1:
        	  radioSt = "Mac OS X";
        	  break;
          case 2:
        	  radioSt = "Linux";
        	  break;
          }
          switch (data.select){
          case 0:
        	  selectSt = "日本";
        	  break;
          case 1:
        	  selectSt = "アメリカ";
        	  break;
          case 2:
        	  selectSt = "イギリス";
        	  break;
          }
          String msg = "value ： " + "あなたの名前 = " + data.name + "," + " パスワード = " + data.pass + "," + " check = " + data.check + "," + " radio = "
          + radioSt + "," + " select = " + selectSt + "," + " area = " + data.area + "," + " date = " + data.date + "," + " num = " + data.num;
          return ok(index.render(msg, sampleForm));
          } else {
        	Forms f = new Forms();
        	Form<Forms> form = form(Forms.class).fill(f);
            return badRequest(index.render("ERROR", form(Forms.class)));
          }
    }


    public static class Forms{
    	@Required
        public String name;
        public String pass;
        public boolean check;
        public int radio;
        public int select;
        public String area;
        public Date date;
        public int num;

        public Forms(){
        	this.name = "default name";
        	this.check = true;
        	this.radio = 1;
        	this.select = 2;
        	num = 50;

        }
      }

}
