package org.codehaus.commons.compiler.jdk;

import com.github.igorsuhorukov.codehaus.plexus.util.IOUtil;
import com.github.igorsuhorukov.smreed.dropship.MavenClassLoader;
import org.junit.Test;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

public class SimpleClassPathCompilerTest {

    @Test
    public void testClassloader() throws Exception {

        final String SCRIPT_NAME = "MyScript";
        List<URL> urlsCollection = MavenClassLoader.usingCentralRepo().getArtifactUrlsCollection("com.github.igor-suhorukov:phantomjs-runner:1.1", null);

        SimpleClassPathCompiler simpleCompiler = new SimpleClassPathCompiler(urlsCollection);
        simpleCompiler.setCompilerOptions("-8");
        simpleCompiler.setDebuggingInformation(true,true,true);

        String src = IOUtil.toString(getClass().getResourceAsStream(String.format("/%s.java", SCRIPT_NAME)));
        simpleCompiler.cook(SCRIPT_NAME+".java", src);

        Class<?> clazz   = simpleCompiler.getClassLoader().loadClass(SCRIPT_NAME);
        Method main = clazz.getMethod("main", String[].class);
        main.invoke(null, (Object) null);
    }

    public static void runIt(){
        System.out.println("DONE!");
    }
}
