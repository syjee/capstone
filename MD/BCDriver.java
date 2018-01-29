import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 소영
 * 출처 http://guruble.com/java-코드로-이해하는-블록체인blockchain/
 */
public class BCDriver {

	List<Block> blockchain = new ArrayList<Block>();
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub		
			String[] transactions = {"Soyoung sent 1k Bitcoins to Somi.","Somi sent 10k Bitcoins to Soyoung."};
			Block genesisBlock = new Block(new BlockHeader(null,transactions),transactions);
			System.out.println("1 Block Hash : "+ genesisBlock.getBlockHash());
			
			String[] second_transactions = {"Somi sent 10k Bitcoins to Soyoung."};
			Block secondBlock = new Block(new BlockHeader(genesisBlock.getBlockHash().getBytes(),second_transactions),second_transactions);
			System.out.println("2 Block Hash : "+ secondBlock.getBlockHash());
			
			String[] third_transactions = {"Somi sent 10k Bitcoins to Soyoung."};
			Block thirdBlock = new Block(new BlockHeader(secondBlock.getBlockHash().getBytes(),third_transactions),third_transactions);
			System.out.println("3 Block Hash : "+ thirdBlock.getBlockHash());
			
	}

}
