package hello;

public class User
    {

        public String getName() {
            return userName;
        }

        public void setName(String userName) {
            this.userName = userName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        private String userName;
        private int id;
        private int age;
    }
