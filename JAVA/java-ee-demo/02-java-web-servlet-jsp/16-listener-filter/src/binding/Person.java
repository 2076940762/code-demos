package binding;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Person implements HttpSessionBindingListener
    {
        private String username;
        private int age;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public void valueBound(HttpSessionBindingEvent event) {
            System.out.println(event.getName() + ":" + event.getValue() + " 绑定");
        }

        @Override
        public void valueUnbound(HttpSessionBindingEvent event) {
            System.out.println(event.getName() + ":" + event.getValue() + " 解绑");
        }

        @Override
        public String toString() {
            return "Person [username=" + username + ", age=" + age + "]";
        }

        public Person(String username, int age) {
            super();
            this.username = username;
            this.age = age;
        }

        public  Person() {
            
        }
    }
