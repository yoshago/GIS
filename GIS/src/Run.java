import java.util.Scanner;

/**
 * @author ישי
 *
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("please enter path for intput");
		Scanner userInput=new Scanner(System.in);
		String inputPath=userInput.nextLine();
		Folder f=new Folder(inputPath);
		processCsv processCsv=new processCsv(f.getOutputPath());
	}

}
