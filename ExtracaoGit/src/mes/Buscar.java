package mes;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.eclipse.jgit.api.Git;

public class Buscar {

	public static void main(String[] args) throws Exception 
	{

			DownloadGit downloadgit = new DownloadGit();

			String urlGit="https://github.com/traex/ExpandableLayout.git";
			File localPath = File.createTempFile("GitRepository", "", new File("C:/GitHub"));

			localPath.delete();
			
			System.out.println("Cloning from " + urlGit + " to " + localPath);
			downloadgit.specificDownload(localPath, urlGit);	
	}
}