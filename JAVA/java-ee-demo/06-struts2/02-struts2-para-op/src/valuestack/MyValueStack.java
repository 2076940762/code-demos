package valuestack;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import deal.para.User;

public class MyValueStack extends ActionSupport {

	public String execute() {

		// 获取ValueStack
		com.opensymphony.xwork2.util.ValueStack valueStack = ActionContext.getContext().getValueStack();

		valueStack.push(" 'hello ValueStack '");

		valueStack.set("msg", "hello");
		valueStack.set("k", "values");

		valueStack.push(new User("用户名", "密码", "18", "你猜"));
		return SUCCESS;
	}

	public String foreach() {
		// 获取值栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();

		ArrayList<User> list = new ArrayList<User>();
		list.add(new User("张三", "passwd1", "12", "male"));
		list.add(new User("张四", "passwd2", "13", "male"));
		list.add(new User("张五", "passwd3", "18", "male"));
		list.add(new User("张六", "passwd7", "22", "male"));
		list.add(new User("张八", "passwd1", "12", "male"));
		
		valueStack.push(list);
//		valueStack.set("list", list);
		return SUCCESS;
	}
}
