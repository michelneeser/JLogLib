/*
 This file is part of JLogLib.

 JLogLib is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 JLogLib is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with JLogLib.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.miness.jloglib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class contains various utility methods for JLogLib.<br />
 * It is a singleton, hence there's only one instance at runtime (held by this class itself).
 * 
 * @author Michel Neeser
 * @version 1.0
 */
public final class JLLUtils {
	
	// Single instance of this class
	private static JLLUtils instance;
	// A reference to the single JLLogger instance
	private JLLogger logger;
	// A reference to the single JLLSettings instance
	private JLLSettings settings;
	
	// This class can't be instantiated from outside
	private JLLUtils() {}
		
	/* Returns the single instance of this class.
	 * Default access, should be only callable from within JLogLib. */
	protected static synchronized JLLUtils getInstance() {
		if (instance == null) {
			instance = new JLLUtils();
			instance.logger = JLLogger.getInstance();
			instance.settings = JLLSettings.getInstance();
		}
		return instance;
	}
	
	/**
	 * Saves the current state of all settings.<br />
	 * This saved state will be kept up until the application shuts down.<br />
	 * If you call this method again, the last saved state will be overwritten.<br />
	 * To recover the saved state, call loadSettings().
	 */
	public void saveSettings() {
		settings.createInstanceCopy();
	}
	
	/**
	 * Loads the saved settings state.<br />
	 * <b>Attention</b>: By calling this method, the current settings will be completely overwritten!<br />
	 * If saveSettings() isn't called before calling this, nothing will happen.
	 */
	public void loadSettings() {
		JLLSettings instanceCopy = settings.getInstanceCopy();
		if (instanceCopy != null) {
			settings.enableLogging(instanceCopy.isLoggingEnabled());
			settings.setLogLevel(instanceCopy.getLogLevel());
			settings.allowSubLogLevels(instanceCopy.getAllowSubLogLevels());
			settings.setLogTarget(instanceCopy.getLogTarget());
			settings.setLogFile(instanceCopy.getLogFile() != null ? instanceCopy.getLogFile().toString() : null);
			settings.printDate(instanceCopy.getPrintDate());
			settings.setDateFormat(instanceCopy.getDateFormat());
			settings.printTime(instanceCopy.getPrintTime());
			settings.setTimeFormat(instanceCopy.getTimeFormat());
			settings.setDelimiter(instanceCopy.getDelimiter());
			settings.setApplicationName(instanceCopy.getApplicationName());
			settings.printApplicationName(instanceCopy.getPrintApplicationName());
		}
	}
	
	/**
	 * Writes the current settings into the specified file.<br />
	 * The data format being used is JSON, so it's very easy to share/recover the settings after writing them.
	 * @param path the path to the file to write into.
	 */
	public void saveSettings(String path) {
		try {
			JsonObject objOuter = new JsonObject();
			JsonObject objInner = new JsonObject();
			objInner.addProperty("loggingEnabled", settings.isLoggingEnabled());
			objInner.addProperty("logLevel", settings.getLogLevel().toString());
			objInner.addProperty("allowSubLogLevels", settings.getAllowSubLogLevels());
			objInner.addProperty("logTarget", settings.getLogTarget().toString());
			objInner.addProperty("logFile", settings.getLogFile() != null ? settings.getLogFile().toString() : "");
			objInner.addProperty("printDate", settings.getPrintDate());
			objInner.addProperty("dateFormat", settings.getDateFormat().toString());
			objInner.addProperty("printTime", settings.getPrintTime());
			objInner.addProperty("timeFormat", settings.getTimeFormat().toString());
			objInner.addProperty("delimiter", settings.getDelimiter());
			objInner.addProperty("applicationName", settings.getApplicationName());
			objInner.addProperty("printApplicationName", settings.getPrintApplicationName());
			objOuter.add("jll_settings", objInner);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(objOuter);
			new PrintWriter(new File(path)).append(json).flush();
		}
		catch (FileNotFoundException e) {
			printInternalError("Possible Reasons:\n" +
							   "   - The specified settings file exists but is a directory rather than a regular file.\n" +
							   "   - The specified settings file file does not exist but cannot be created.\n" +
							   "   - The specified settings file cannot be opened for any other reason.");
		}
	}
	
	/**
	 * Resets the settings to the initial state.
	 * <b>Attention</b>: By calling this method, the current settings will be completely overwritten!<br />
	 */
	public void resetSettings() {
		settings.reset();
	}
	
	/**
	 * Loads the specified settings from a settings file (JSON data).<br />
	 * <b>Attention</b>: By calling this method, the current settings will be completely overwritten!<br />
	 * @param path the path to the file to load settings from.
	 */
	public void loadSettings(String path) {
		try {
			Scanner scanner = new Scanner(new File(path)).useDelimiter("\\Z");
			String settingsString = scanner.next();
			JsonObject settingsObjOuter = new JsonParser().parse(settingsString).getAsJsonObject();
			JsonObject settingsObjInner = settingsObjOuter.get("jll_settings").getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> settingsSet = settingsObjInner.entrySet();
			for (Map.Entry<String, JsonElement> e : settingsSet) {
				String key = e.getKey();
				JsonElement value = e.getValue();
				if (key.equals("loggingEnabled")) {
					settings.enableLogging(value.getAsBoolean());
				}
				else if (key.equals("logLevel")) {
					settings.setLogLevel(Enum.valueOf(LogLevel.class, value.getAsString()));
				}
				else if (key.equals("allowSubLogLevels")){
					settings.allowSubLogLevels(value.getAsBoolean());
				}
				else if (key.equals("logTarget")) {
					settings.setLogTarget(Enum.valueOf(LogTarget.class, value.getAsString()));
				}
				else if (key.equals("logFile")) {
					settings.setLogFile(value.getAsString());
				}
				else if (key.equals("printDate")) {
					settings.printDate(value.getAsBoolean());
				}
				else if (key.equals("dateFormat")) {
					settings.setDateFormat(Enum.valueOf(LogDateFormat.class, value.getAsString()));
				}
				else if (key.equals("printTime")) {
					settings.printTime(value.getAsBoolean());
				}
				else if (key.equals("timeFormat")) {
					settings.setTimeFormat(Enum.valueOf(LogDateFormat.class, value.getAsString()));
				}
				else if (key.equals("delimiter")) {
					settings.setDelimiter(value.getAsString());
				}
				else if (key.equals("applicationName")) {
					settings.setApplicationName(value.getAsString());
				}
				else if (key.equals("printApplicationName")) {
					settings.printApplicationName(value.getAsBoolean());
				}
			}
		}
		catch (FileNotFoundException e) {
			printInternalError("Reason: The specified settings file cannot be found.");
		}
	}
	
	// Checks if the given log level is included in the currently set log level
	protected boolean checkLogLevel(LogLevel level) {
		LogLevel currentLevel = settings.getLogLevel();
		boolean check = false;
		if (currentLevel == LogLevel.LOW && level == LogLevel.LOW) {
			check = true;
		}
		else if (currentLevel == LogLevel.MEDIUM) {
			if (settings.getAllowSubLogLevels() && level == LogLevel.LOW) {
				check = true;
			}
			else if (level == LogLevel.MEDIUM) {
				check = true;
			}
		}
		else if (currentLevel == LogLevel.HIGH) {
			if (settings.getAllowSubLogLevels() || level == LogLevel.HIGH) {
				check = true;
			}
		}
		return check;
	}
	
	// Creates the appropriate DateFormat instance for the defined settings
	protected DateFormat createDateFormat() {
		DateFormat df = null;
		if (settings.getPrintDate() && settings.getPrintTime()) {
			df = DateFormat.getDateTimeInstance(convertLogDateFormat(settings.getDateFormat()),
												convertLogDateFormat(settings.getTimeFormat()));
		}
		else if (settings.getPrintDate()) {
			df = DateFormat.getDateInstance(convertLogDateFormat(settings.getDateFormat()));
		}
		else if (settings.getPrintTime()) {
			df = DateFormat.getTimeInstance(convertLogDateFormat(settings.getTimeFormat()));
		}
		return df;
	}
	
	// Converts a constant of LogDateFormat into a constant of DateFormat
	protected int convertLogDateFormat(LogDateFormat format) {
		int dateFormat = 0;
		switch (format) {
			case SHORT:
				dateFormat = DateFormat.SHORT;
			break;
			case MEDIUM:
				dateFormat = DateFormat.MEDIUM;
			break;
			case LONG:
				dateFormat = DateFormat.LONG;
			break;
		}
		return dateFormat;
	}
	
	/* Writes an internal error to the console.
	 * Like all other log messages, error messages are printed over the logger object. */
	protected void printInternalError(String message) {
		saveSettings();
		settings.setLogTarget(LogTarget.CONSOLE);
		settings.setApplicationName("JLogLib Error Reporter");
		logger.log("See below:\n\n" +
				   "   ===== AN INTERNAL ERROR OCCURED =====\n\n" +
				   "   " + message + "\n\n" +
				   "   ===== END OF INTERNAL ERROR MESSAGE =====\n");
		loadSettings();
	}
	
}