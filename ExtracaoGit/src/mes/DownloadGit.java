package mes;

import java.io.File;

import org.eclipse.jgit.api.Git;

public class DownloadGit {

	
	protected void specificDownload(File path, String url) throws Exception {
		Git.cloneRepository()
			.setDirectory(path)
			.setURI(url)
			.setCloneAllBranches(false)
			.call();
	}
	
}

