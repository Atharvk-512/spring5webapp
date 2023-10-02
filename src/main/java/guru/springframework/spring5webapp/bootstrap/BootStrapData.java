package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorrepository;
    private final BookRepository bookrepository;
    private final PublisherRepository publisherRepository;


    public BootStrapData(AuthorRepository authorrepository, BookRepository bookrepository, PublisherRepository publisherRepository) {
        this.authorrepository = authorrepository;
        this.bookrepository = bookrepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in BootSTrap");
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);
        System.out.println("Publisher count = " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorrepository.save(eric);
        bookrepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("rod","johnson");
        Book noEJB = new Book("J2EE", "52421635");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorrepository.save(rod);
        bookrepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookrepository.count());
        System.out.println("Publisher number of books = " + publisher.getBooks().size());
    }
}
