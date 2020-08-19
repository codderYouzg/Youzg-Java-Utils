package edu.youzg.util;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 包扫描 小工具
 * @author Youzg
 */
public abstract class PackageScanner {

    public PackageScanner() {
    }

    /* 处理类 */
    public abstract void dealClass(Class<?> klass);

    /* 通过所传File类参数以及相应的包名称来处理包 */
    private void dealPackage(File cur, String packageName) {
        File[] files = cur.listFiles();
        for (File file : files) {    //遍历这个问渐渐中的所有包含的子文件/子文件夹
            if (file.isFile()) {    //如果是文件
                String fileName = file.getName();
                if (!fileName.endsWith(".class")) {    //如果是.class文件
                    continue;
                }
                fileName = fileName.replace(".class", "");
                String className = packageName + "." + fileName;
                try {
                    Class<?> klass = Class.forName(className);
                    dealClass(klass);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {    //如果是文件夹
                dealPackage(file, packageName + "." + file.getName());
            }
        }
    }

    /* 通过URL来处理相应的Jar包 */
    private void dealJar(URL url) {
        try {
            JarURLConnection connection = (JarURLConnection) url.openConnection();
            JarFile jarFile = connection.getJarFile();
            Enumeration<JarEntry> entryList = jarFile.entries();
            while (entryList.hasMoreElements()) {
                JarEntry jarEntry = entryList.nextElement();
                if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {    //如果是文件夹，或者不是.class文件，运行下一轮循环
                    continue;
                }
                String className = jarEntry.getName();    //组装我们执行反射机制所需的类名
                className = className.replace(".class", "");
                className = className.replace('/', '.');
                try {
                    Class<?> klass = Class.forName(className);
                    dealClass(klass);    //处理通过反射机制所获得的类
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 通过所给的包名称，去扫描这个包中的所有信息 */
    public void scanPackage(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String pathName = packageName.replace('.', '/');
        try {
            Enumeration<URL> urls = classLoader.getResources(pathName);
            while (urls.hasMoreElements()) {    //如果包含很多文件（即：不是文件夹就是Jar包）
                URL url = urls.nextElement();
                if (url.getProtocol().equals("jar")) {    //如果是Jar包
                    dealJar(url);
                } else {    //如果是文件夹
                    try {
                        File curFile = new File(url.toURI());
                        dealPackage(curFile, packageName);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
