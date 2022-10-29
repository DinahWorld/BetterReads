package xyz.dinahworld.betterreadsdataloader;

import xyz.dinahworld.betterreadsdataloader.author.Author;
import xyz.dinahworld.betterreadsdataloader.author.AuthorRepository;
import xyz.dinahworld.betterreadsdataloader.connection.DataStaxAstraProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.nio.file.Path;

@SpringBootApplication
public class BetterreadsDataLoaderApplication {

	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(BetterreadsDataLoaderApplication.class, args);
	}


	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}


	@PostConstruct
	public void start(){
		Author author = new Author();
		author.setId("id");
		author.setName("Name");
		author.setPersonalName("personalName");
		authorRepository.save(author);
	}


}