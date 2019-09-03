package domain;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class Student implements HttpSessionActivationListener, Serializable
    {
        private String name;
        private String number;

        public Student() {
        }

        public Student(String name, String number) {
            super();
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        @Override
        public void sessionWillPassivate(HttpSessionEvent se) {
            System.out.println(se.toString() + "~~~钝化~~~");
        }

        @Override
        public void sessionDidActivate(HttpSessionEvent se) {
            System.out.println(se.toString() + "~~~活化~~~");
        }

    }
