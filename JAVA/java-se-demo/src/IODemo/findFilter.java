package IODemo;

import java.io.File;
import java.io.FileFilter;

public class findFilter implements FileFilter {
	private String strPostfix = ".jar";

	public findFilter() {

	}

	public findFilter(String str) {
		strPostfix = str;
	}

	@Override
	public boolean accept(File file) {
		String strName = file.getName();
		return strName.endsWith(strPostfix);
	}

}
