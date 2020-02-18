/***
 * Shado Logger class for debugging
 */

package ShadoLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger implements AutoCloseable {
	private int level;
	public static final int LOG_LEVEL_ERROR = 0;
	public static final int LOG_LEVEL_WARNNING = 1;
	public static final int LOG_LEVEL_INFO = 2;
	private boolean debugMode;

	// private List<String> buffer = new ArrayList<String>();
	private FileWriter buffer;

	public Logger(int _level, boolean overwriteFile) {
		if (_level < 0 || _level > 2)
			_level = 0;
		level = _level;
		debugMode = true;

		try {
			buffer = new FileWriter("log.txt", !overwriteFile);
		} catch (Exception e) {
			// Handle error
		}

	}

	public Logger(int _level) {
		this(_level, false);
	}

	public Logger() {
		this(LOG_LEVEL_ERROR, false);
	}

	// Turn debug mode on or off
	public void toggleDebugMode() {
		debugMode = !debugMode;
	}

	public void setDebugModeTo(boolean b) {
		debugMode = b;
	}

	// Getter
	public boolean isDebugMode() {
		return debugMode;
	}

	// Error
	public void error(Exception e) {
		if (level >= LOG_LEVEL_ERROR) {
			formatMessage(e, "ERROR");
		}
	}

	// Warnning
	public void warn(Exception e) {
		if (level >= LOG_LEVEL_WARNNING) {
			formatMessage(e, "WARNNING");
		}
	}

	// Info
	public void log(Exception e) {
		if (level >= LOG_LEVEL_INFO) {
			formatMessage(e, "INFO");
		}
	}

	// Other methodes
	@Override
	public void close() {
		try {
			buffer.close();
		} catch (Exception e) {
			System.out.println("Cannot close Buffer");
		}

	}

	public void clear() {
		try {
			new BufferedWriter(new FileWriter("log.txt", false)).close();
		} catch (Exception e) {
			this.error(e);
		}
	}

	private void formatMessage(Exception e, String label) {

		// if debug mode if off exit
		if (!debugMode)
			return;

		// Get Date and format it
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		// Get error line number
		StackTraceElement l = e.getStackTrace()[0];

		// Get message
		String msg = e.getMessage();

		try {
			// Format message and write it to file
			msg = String.format("[%s]:\t%s\t--> %s\t@%s::%s()@Line#%s\n", date.format(now), label, msg,
					l.getClassName(), l.getMethodName(), l.getLineNumber());
			buffer.append(msg);

		} catch (Exception err) {
			// If cannot write to file print error to console
			msg = String.format("[%s]:\t%s\t--> %s\t@%s::%s()@Line#%s\n", date.format(now), label, err.getMessage(),
					l.getClassName(), l.getMethodName(), l.getLineNumber());
			System.out.println(msg);
		}
	}

}
