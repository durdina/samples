package com.amazon.msc.poc;

import java.io.File;

import org.apache.catalina.startup.Tomcat;

/**
 * Created by durdinam on 2017-02-23.
 */
public class Start {

    public static void main(String[] args) throws Exception {

        String webappDirLocation = "src/main/webapp/";
        String tomcatWorkDir = "target/";
        Tomcat tomcat = new Tomcat();
        int port = 8080;
        tomcat.setPort(port);

        System.out.println("configuring tomcat work dir: " +
                new File("./" + tomcatWorkDir).getAbsolutePath());
        tomcat.setBaseDir(tomcatWorkDir);
        System.out.println("configuring app with basedir: " +
                new File("./" + webappDirLocation).getAbsolutePath());
        tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());

        tomcat.start();
        System.out.println(String.format("Open the app at http://localhost:%d", port));
        tomcat.getServer().await();
    }
}
