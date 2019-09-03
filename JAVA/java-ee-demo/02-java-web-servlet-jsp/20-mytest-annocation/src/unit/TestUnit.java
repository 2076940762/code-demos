package unit;

import java.lang.reflect.Method;

import annocation.MyTest;
import testing.Test;

public class TestUnit
    {
        public static void main(String[] args) {
//            获取class对象
            Class clazz = Test.class;

//            获取方法
            Method[] methods = clazz.getMethods();

            for (Method m : methods) {
                if (m.isAnnotationPresent(MyTest.class)) {//是否有指定的注解
                    try {
                        m.invoke(clazz.newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
