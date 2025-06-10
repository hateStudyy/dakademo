package in.yumi.dakademo.aidemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestLingma {
    public static void main(String[] args) throws Exception {
        // 获取类对象
        Class<?> clazz = Class.forName("in.yumi.dakademo.ExerciseRecord");

        // 获取构造函数并创建实例
        Constructor<?> constructor = clazz.getConstructor();
        Object instance = constructor.newInstance();

        // 获取方法并调用
        Method method = clazz.getMethod("setId", Long.class);
        method.invoke(instance, 1L);

        System.out.println("Reflection demo completed.");
    }
}