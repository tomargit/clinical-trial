package com.incedoinc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.incedoinc.dao.PostgreConnection;

public class SriptEngineMain {

	public static void main(String[] args) {

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		Connection con = PostgreConnection.getConnection();
	}
}
