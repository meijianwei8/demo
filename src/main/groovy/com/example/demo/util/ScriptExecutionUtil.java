package com.example.demo.util;


import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Collections;
import java.util.Map;


public class ScriptExecutionUtil {

    private ScriptExecutionUtil() throws InstantiationException {
        throw new InstantiationException("this util class should not be instance");
    }

    private static final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

    private static ScriptEngine getScriptEngineByName(String scriptName) {
        return scriptEngineManager.getEngineByName(scriptName);
    }

    public static void setGlobalBindings(Map<String,?> globalParams) {
        Bindings managerBindings = scriptEngineManager.getBindings();
        managerBindings.putAll(globalParams);
    }

    /**
     * @param scriptName script language name(脚本语言名称）
     * @param scriptContent specific script content(执行的脚本内容）
     * @param parameters parameters which needed in content(脚本执行所需要的参数）
     * @param resultClass return class type,the default name used in script should be "result"
     *                    (返回结果类型，在脚本中默认名称应使用"result"）
     * @param <T> return result generics(返回值的泛型类型）
     * @return script execution result(脚本执行结果）
     * @throws ScriptException script execution exception
     * @throws IllegalAccessException do not have access when creating object whit default constructor
     * @throws InstantiationException creating new Instance exception
     */
    public static <T> T executeScript(String scriptName, String scriptContent, Map<String, ?> parameters, Class<T> resultClass)
            throws ScriptException, IllegalAccessException, InstantiationException {
        ScriptEngine scriptEngine = getScriptEngineByName(scriptName);
        Bindings bindings = scriptEngine.createBindings();
        if (parameters == null) {
            parameters = Collections.emptyMap();
        }
        bindings.putAll(parameters);
        if (resultClass != null) {
            T result = resultClass.newInstance();
            bindings.put("result", result);
            return resultClass.cast(scriptEngine.eval(scriptContent, bindings));
        }
        scriptEngine.eval(scriptContent, bindings);
        return null;
    }

    public static void executeScript(String scriptName, String scriptContent)
            throws IllegalAccessException, ScriptException, InstantiationException {
        executeScript(scriptName, scriptContent, null, null);
    }

    public static void executeScript(String scriptName, String scriptContent, Map<String, ?> parameters)
            throws IllegalAccessException, ScriptException, InstantiationException {
        executeScript(scriptName, scriptContent, parameters, null);
    }

    public static <T> T executeScript(String scriptName, String scriptContent, Class<T> resultClass)
            throws IllegalAccessException, ScriptException, InstantiationException {
        return executeScript(scriptName, scriptContent, null, resultClass);
    }

}
