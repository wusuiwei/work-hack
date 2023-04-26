package com.wusuiwei.java.spring6.principle.bean;

import com.wusuiwei.java.spring6.principle.annotation.Bean;
import com.wusuiwei.java.spring6.principle.annotation.Di;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationApplicationContext implements ApplicationContext {
    //创建一个map对象，放bean对象
    private Map<Class, Object> beanFactory = new HashMap<>();
    private static String rootPath;


    //返回对象
    @Override
    public Object getBean(Class clazz) {

        return beanFactory.get(clazz);
    }

    //设置包扫描规则
    //当钱包及其子包，哪个类有@Bean注解，把这个类通过反射实例化
    //创建有参数的构造，
    public AnnotationApplicationContext(String basePackages) {

        try {
            //1. 把包名的.替换成\
            String packagePath = basePackages.replaceAll("\\.", "\\\\");
            //2. 获取包的绝对路径
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), "utf-8");

                //获取包前面的路径，字符串截取

                rootPath = filePath.substring(0, filePath.length() - packagePath.length());
                //包扫描
                loadBean(new File(filePath));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(beanFactory);

        loadDi();
    }

    private void loadDi() {
        //实例化的对象都在beanFactory的map集合中
        //1. 遍历beanFactory的map
        Set<Map.Entry<Class, Object>> entries = beanFactory.entrySet();
        for (Map.Entry<Class, Object> entry : entries) {
            Object obj = entry.getValue();

            Class<?> aClass = obj.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                Di di = field.getAnnotation(Di.class);
                if (di != null) {
                    field.setAccessible(true);
                    try {
                        field.set(obj, beanFactory.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        //2. 获取map集合中每个对象及属性
        //3. 判断属性是否存在@Di注解
        //4 存在则将对象注入
    }

    //包扫描
    private void loadBean(File file) throws Exception {
        //1. 判断是否未文件夹
        if (file.isDirectory()) {
            //2. 获取文件夹所有内容
            File[] subFiles = file.listFiles();
            //3. 判断文件夹里面为空，直接返回
            if (subFiles == null || subFiles.length == 0) {
                return;
            }
            //4. 文件夹不为空，遍历文件夹中的所有内容
            for (File f : subFiles) {
                //4.1  遍历每个field对象，继续判断还是文件夹，则递归遍历
                if (f.isDirectory()) {
                    loadBean(f);
                } else {
                    //4.2  判断是文件，则获取包路径+类名称部分， 判断文件是否为.class类型，把路径中的\替换成.，去掉.class
                    String pathWithClass = f.getAbsolutePath().substring(rootPath.length() - 1);
                    if (pathWithClass.endsWith(".class")) {

                        String allName = pathWithClass.replaceAll("\\\\", ".").replace(".class", "");

                        //4.3 如果类上有@Bean的注解，如果有则实例化，并放入beanFactory属性中。
                        //获取class对象
                        Class<?> aClass = Class.forName(allName);
                        //判断是否为接口
                        if (!aClass.isInterface()) {
                            Bean bean = aClass.getAnnotation(Bean.class);
                            if (bean != null) {
                                //实例化
                                Object instance = aClass.getConstructor().newInstance();
                                //4.3.1 如果类有接口，则将接口的class作为key，实例对象的class作为value
                                if (aClass.getInterfaces().length > 0) {
                                    beanFactory.put(aClass.getInterfaces()[0], instance);
                                } else {
                                    //4.3.2 如果类没有接口，则将接口自己的class当作key，实例对象作为value
                                    beanFactory.put(aClass, instance);
                                }
                            }
                        }

                    }

                }
            }

        }
    }
}
