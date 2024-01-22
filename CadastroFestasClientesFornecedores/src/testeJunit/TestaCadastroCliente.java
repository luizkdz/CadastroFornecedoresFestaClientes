package testeJunit;



import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class TestaCadastroCliente {

@Test
public void testCadastrarCliente() {
// Executar o método cadastrarCliente()

Cliente cliente1 = Main.cadastrarCliente(8921l,"João","PucMinas","3199999999",LocalDate.parse("01/10/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
assertEquals(8921l, cliente1.getCodigo(), 0.01);
}
@Test
public void testCadastrarClienteExistente() {

	Cliente cliente1 = Main.cadastrarCliente(456, "Jane Doe", "456 Oak St", "555-5678", LocalDate.parse("01/10/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

	
	String input = "456\nJohn Smith\n789 Elm St\n555-9876\n02/02/1985";
	InputStream inputStream = new ByteArrayInputStream(input.getBytes());
	System.setIn(inputStream);

	
	Main.cadastrarCliente(456, "", "", "", LocalDate.now()); 

	
	Cliente cliente2 = Main.buscarCliente(456);

	
	assertEquals(cliente1, cliente2);
}
}
