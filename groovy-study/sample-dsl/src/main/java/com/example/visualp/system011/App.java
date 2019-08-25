package com.example.visualp.system011;

import com.example.visualp.system011.facade.StudyFacade;
import com.example.visualp.system011.facade.impl.StudyFacadeImpl;
import com.example.visualp.system011.resources.StudyResources;
import groovy.lang.Closure;
import groovy.lang.ExpandoMetaClass;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import java.io.File;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class App extends ResourceConfig {

  private boolean onBoot = false;

  public App() throws Exception {

    packages(this.getClass().getPackage().getName());

    // [groovy ファイルを Java から呼び出す]
    // 1. クラスローダに設定
    GroovyClassLoader classLoader = new GroovyClassLoader();

    classLoader.setResourceLoader(
        s -> getClass()
            .getClassLoader()
            .getResource(s)
    );

    // 2. クラスローダーから groovy ファイル読み込み
    Class proxyClass = classLoader.loadClass("actions/MyProxy.groovy");

    // 3. インスタンス生成して実行
    GroovyObject groovyObject = (GroovyObject) proxyClass.newInstance();
    groovyObject.invokeMethod("justDoIt", new Object[] {
        "Taro",
        buildClosure()
    });

    // [DSL を読み込んで実行する]
    // 1. DSL ファイルを読み込む
    Script dslScript = new GroovyShell().parse(new File(
        getClass()
            .getClassLoader()
            .getResource("dsl/MyDsl.groovy").toURI()
        )
    );

    // 2. DSL に対応する metaClass を設定する。
    ExpandoMetaClass emc = new ExpandoMetaClass(dslScript.getClass());
    emc.registerInstanceMethod("sampleAction", buildClosure());
    emc.initialize();

    // 3. metaClass で設定した内容を元に DSL を走らせる。
    //    ☆ クロージャの方法は別途勉強が必要
    dslScript.setMetaClass(emc);
    dslScript.run();

    register(new AbstractBinder() {
      @Override
      protected void configure() {

        // Facade
        bind(StudyFacadeImpl.class).to(StudyFacade.class);

        // Resources
        bind(StudyResources.class).to(StudyResources.class);
      }
    });
  }

  public Closure<?> buildClosure() {
    return new Closure(null) {
      public Object doCall(String name) {
        System.out.println("Hello" + name);
        return null;
      }
    };
  }
}
