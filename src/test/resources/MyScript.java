import com.github.igorsuhorukov.phantomjs.PhantomJsDowloader;
import com.github.igorsuhorukov.smreed.dropship.MavenClassLoader;
import org.codehaus.commons.compiler.jdk.SimpleClassPathCompilerTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyScript{

    public static void main(String[] args)  throws Exception{

        class Wrapper{
            private String value;

            public Wrapper(String value) {
                this.value = value;
            }

            public String getValue() {
                return value;
            }
        }

        SimpleClassPathCompilerTest.runIt();

        List<String> res = Arrays.asList(new Wrapper("do"), new Wrapper("something"), new Wrapper("wrong")).stream().
                                            map(Wrapper::getValue).collect(Collectors.toList());
        System.out.println(String.join(" ",res));

        System.out.println("Classes from project classpath. For example "+MavenClassLoader.class.getName());

        System.out.println(PhantomJsDowloader.getPhantomJsPath());
    }
}