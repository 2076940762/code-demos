package testing;

import annocation.MyTest;

public class Test
    {
        @MyTest
        public void f1() {
            System.out.println("f1 执行了~~~");
        }
        
        public void f2() {
            System.out.println("f2 执行了~~~");
            
        }
        
        @MyTest
        public void f3() {
            System.out.println("f3 执行~~~");
        }

    }
