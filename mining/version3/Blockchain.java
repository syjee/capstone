import java.util.ArrayList;

public class Blockchain {
	
	public ArrayList<Block>Blockchain = new ArrayList<Block>();
	public int height;
	
	public Blockchain(){
		height = Blockchain.size();
	}
	
	public void addBlock(Block newBlock){
		Blockchain.add(newBlock);
		height++;
		System.out.println("newBlock added to a Blockchain...");
	}

	public void showBlock(){
		for(int i = 0; i<Blockchain.size(); i++){
			Blockchain.get(i).showBlock();
		}
	}
	
	public int getHeight(){
		return height;
	}
	
	public Block getBlock(){
		return Blockchain.get(height-1);
	}
	
	
}
