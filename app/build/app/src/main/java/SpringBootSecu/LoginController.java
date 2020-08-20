package SpringBootSecu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
public class LoginController {
	@Autowired
	LoginRepository repository;

	public LoginController(LoginRepository repository) {
		this.repository = repository;
	}

	public Optional<Login> select(String name) {
		System.out.println("repository:" + repository);
		Optional<Login> l = repository.findByName(name);
		return l;
	}
}
