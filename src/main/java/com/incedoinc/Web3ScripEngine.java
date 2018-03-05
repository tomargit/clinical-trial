package com.incedoinc;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Web3ScripEngine {

	public static void main(String[] args) {

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		try {
			engine.eval(Files.newBufferedReader(
					Paths.get("C:\\Users\\Administrator\\Desktop\\work\\clinical-trial\\web3.js"),
					StandardCharsets.UTF_8));
			engine.eval(Files.newBufferedReader(
					Paths.get("C:\\Users\\Administrator\\Desktop\\work\\clinical-trial\\web3-custom.js"),
					StandardCharsets.UTF_8));
			Invocable inv = (Invocable) engine;
			System.out.println(engine);
			
			Object web3 = engine.get("web3");

			System.out.println(web3);
			
			//inv.invokeMethod(obj, "hello", "Script Method !!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
