import java.util.Scanner;

public class Main {
	private static Scanner scanner;

	public static void main(String[] agrs) {
		ScanDirFiles scanDirFiles = new ScanDirFiles();
		System.out.println("Enter Your Classes Path:");
		scanner = new Scanner(System.in);
		String dirPath = scanner.nextLine();
		System.out.println("Your cpps are:");
		scanDirFiles.scanCocosDirFiles(dirPath);
	}
}
