package com.incedoinc;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class SriptEngineMain {

	public static void main(String[] args) {

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		try {
			engine.eval(Files.newBufferedReader(Paths.get("C:\\Users\\Administrator\\Desktop\\work\\clinical-trial\\script.js"), StandardCharsets.UTF_8));
			//engine.eval(script);
			Invocable inv = (Invocable) engine;

			Object obj = engine.get("obj");

			inv.invokeMethod(obj, "hello", "Script Method !!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
