import java.security.NoSuchAlgorithmException;
import java.util.*;

public class main {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		List<Block>BlockChain = new ArrayList<Block>();
		
		Block genesis = new Block(1,"A",null);
		if(genesis.mineBlock(2)) BlockChain.add(genesis);
		
		Block second_block = new Block(2, "B", genesis.getCurrentHash());
		if(second_block.mineBlock(2)) BlockChain.add(second_block);
		
		for(int i = 0; i< BlockChain.size(); i++){
			BlockChain.get(i).showBlock();
		}
		
	}

}
