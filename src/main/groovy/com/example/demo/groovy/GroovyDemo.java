package com.example.demo.groovy;

import com.example.demo.pojo.Gooo;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.springframework.scripting.groovy.GroovyScriptFactory;


import javax.script.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GroovyDemo {
    public static void main(String[] args) {
//        URL scriptResources = GroovyDemo.class.getClassLoader().getResource("script");
//        GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine(new URL[]{scriptResources});
//        try {
//            Map<String,Object> map = new HashMap<>();
//            map.put("args",new String[]{"Haha ","hehe"});
//            map.put("x",new ArrayList<>());
//            map.put("y",2);
//            Binding binding = new Binding(map);
//            Object helloGroovy = groovyScriptEngine.run("script/HelloGroovy.groovy", binding);
//            System.out.println(helloGroovy);
//        } catch (ResourceException | ScriptException e ) {
//            e.printStackTrace();
//        }
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine groovyEngine = scriptEngineManager.getEngineByName("groovy");
        ScriptEngine javascriptEngine = scriptEngineManager.getEngineByName("javascript");
        Bindings bindings = groovyEngine.createBindings();
        Gooo gooo1 = new Gooo("123","345");
        Gooo gooo2 = new Gooo("abc","def");
        bindings.put("a",gooo1);
        bindings.put("b",gooo2);
        try {
//            Object eval = groovyEngine.eval("def getTime(date){return date.getTime();}; getTime(date)",bindings);
            Object eval1 = javascriptEngine.eval("function test(gooo1,gooo2){" +
                    " return gooo1.getA() + gooo2.getB() + new Date()" +
                    "}; test(a,b)", bindings);
            System.out.println(eval1);
//            Invocable invocableEngine = (Invocable) groovyEngine;
//            Object getTime = invocableEngine.invokeFunction("getTime");
//            System.out.println(getTime);
        } catch (javax.script.ScriptException e) {
            e.printStackTrace();
        }
//        catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
    }

    public Object groovyShell(){
        Binding binding = new Binding();
        GroovyShell groovyShell = new GroovyShell();
        groovyShell.evaluate("");
        Script parse = groovyShell.parse("");
        Object evaluate = parse.evaluate("");
        return evaluate;
    }

    public Object groovyScript(){
        URL scriptResources = GroovyDemo.class.getClassLoader().getResource("script");
        GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine(new URL[]{scriptResources});
        try {
            groovyScriptEngine.run("HelloGroovy","");
        } catch (ResourceException | ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void script(){
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine groovyEngine = scriptEngineManager.getEngineByName("groovy");
    }

}
