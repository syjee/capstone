import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class test {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		Transaction t = new Transaction();
		Block b = new Block(t.getTransaction());
		
		b.mineBlock(2);

	}

}
