package mes;


import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apiminer.entities.api.GitHubRepository;
import org.apiminer.util.git.SearchRepository;




public class ListarRepositorio {
	
	private static final String CLONE_URL_MODEL = new String("https://github.com/%s/%s.git");
	private static final String GIT_URL_MODEL = new String("git://github.com/%s/%s.git");
	private static final String HTML_URL_MODEL = new String("https://github.com/%s/%s");
	private static final String SSH_URL_MODEL = new String("git@github.com:%s/%s");
	private static final String SVN_URL_MODEL = new String("https://svn.github.com/%s/%s");

	public static final String JAVA_LANGUAGE = "Java";

	private String username;
	private String password;

	/**
	 *  Create a new object to search as anonymous user
	 */
	public ListarRepositorio() {
		super();
	}

	/**
	 *  Create a new object to search logged
	 *  
	 *  @param username github username
	 *  @param password github password
	 */
	public ListarRepositorio(String username, String password) {
		this();
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Search for repositories with the keyword.
	 * 
	 * @param keyword 
	 * @param startPage
	 * @param endPage
	 * @return
	 * @throws IOException
	 */
	public void searchRepositories(final String keyword, int startPage, int endPage){
		List<SearchRepository> searchRepositories = new LinkedList<SearchRepository>();
		List<GitHubRepository> repositories = new LinkedList<GitHubRepository>();
		
		int initPage = startPage;
		do {
			searchRepositories = repositoryService.searchRepositories(keyword, JAVA_LANGUAGE, initPage++);

			for (SearchRepository searchRepository : searchRepositories) {
				 GitHubRepository repository = new GitHubRepository();
				
				repository.setCloneUrl(String.format(CLONE_URL_MODEL, searchRepository.getOwner(), searchRepository.getName()));
				
				try {
					repository.setCommits(commitService.getCommits(searchRepository).size());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				repository.setCreatedAt(searchRepository.getCreatedAt());
				repository.setDescription(searchRepository.getDescription());
				repository.setFork(searchRepository.isFork());
				repository.setForks(searchRepository.getForks());
				repository.setGitUrl(String.format(GIT_URL_MODEL, searchRepository.getOwner(), searchRepository.getName()));
				repository.setHasDownloads(searchRepository.isHasDownloads());
				repository.setHasIssues(searchRepository.isHasIssues());
				repository.setHasWiki(searchRepository.isHasWiki());
				repository.setHomepage(searchRepository.getHomepage());
				repository.setHtmlUrl(String.format(HTML_URL_MODEL, searchRepository.getOwner(), searchRepository.getName()));
				repository.setLanguage(searchRepository.getLanguage());
				repository.setMasterBranch("master");
				repository.setMirrorUrl(null);
				repository.setName(searchRepository.getName());
				repository.setOpenIssues(searchRepository.getOpenIssues());
				repository.setOwner(searchRepository.getOwner());
				repository.setPrivate(searchRepository.isPrivate());
				repository.setPushedAt(searchRepository.getPushedAt());
				repository.setSize(searchRepository.getSize());
				repository.setSshUrl(String.format(SSH_URL_MODEL, searchRepository.getOwner(), searchRepository.getName()));
				repository.setSvnUrl(String.format(SVN_URL_MODEL, searchRepository.getOwner(), searchRepository.getName()));
				repository.setUpdatedAt(null);
				repository.setUrl(searchRepository.getUrl());
				repository.setUrlAddress(searchRepository.getUrl());
				repository.setWatchers(searchRepository.getWatchers());

				repository.setRepositoryType(RepositoryType.GIT);
				
				repositories.add(repository);
			}

		} while (searchRepositories.size() > 0 && initPage <= endPage);	
		return repositories;
	}
}

