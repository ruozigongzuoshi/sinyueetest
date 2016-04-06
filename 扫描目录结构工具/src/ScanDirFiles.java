import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ScanDirFiles {
	
	public void scanDirFiles(String dirPath) {
		File dir = new File(dirPath);

		File[] dirs = dir.listFiles();

		if (dirs == null) {
			return;
		}
		
		for (int i = 0; i < dirs.length; i++) {
			if (dirs[i].isDirectory()) {
				scanDirFiles(dirPath + "/" + dirs[i].getName());
			} else {
				System.out.println(dirPath + "/" + dirs[i].getName());
			}
		}

	}

	public void scanCocosDirFiles(String dirPath) {
				
		File dir = new File(dirPath);
		
		File[] dirs = dir.listFiles();

		if (dirs == null) {
			return;
		}
		for (int i = 0; i < dirs.length; i++) {
			if (dirs[i].isDirectory()) {
				scanCocosDirFiles(dirPath + "/" + dirs[i].getName());
			} else if (dirs[i].getName().contains(".cpp") == true) {
				// 将字符串转成字节数组
				int temp = dirPath.indexOf("Classes");
				//String linePrint = dirPath.replace(dirPath, "\t\t\t\t   ../../Classes/");
				String linePrint = "\t\t\t\t   ../../" + dirPath.substring(temp)+ "/" + dirs[i].getName() + " \\";
				
				System.out.println(linePrint);
			}
			
		}
		
	}
}
