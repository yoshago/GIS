package test.java;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import main.java.Libraries.read;


public class testRead {

	@Test
	public void readFolderTest() {
		File f=new File("C:\\");
		assertEquals(0, read.readFolder(f.listFiles()).size());
	}
}
