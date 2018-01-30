import java.security.NoSuchAlgorithmException;

/** 블록 생성자
 * @author 소영
 * 블록생성자는 의뢰를 받으면 마이닝하여 블록을 생성한다.
 */

public class BlockGenerator extends Thread {
	
	public int index;
	public Block previousBlock;
	public String transaction;
	public Block newBlock;
	public boolean shouldRun = false;
	
	public BlockGenerator(int index){
		this.index = index;
	}
	
	public void run(){
		try {
			while(true){
				if(shouldRun){
					if(previousBlock != null){
						newBlock = new Block(previousBlock.getIndex()+1,transaction,previousBlock.getCurrentHash());
						if(newBlock.mineBlock(3)){
							System.out.println("Thread "+index+" mining success");
							//getBlock();
							break;
						}
					} else {	//genesisBlock
						newBlock = new Block(1,transaction,null);
						if(newBlock.mineBlock(3)){
							//getBlock();
							System.out.println("Thread "+index+" mining success");
							break;
						}
					}
				}
				else Thread.sleep(2000);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Thread "+index+" mining fail");
		}
	}
	
	public void putRequest(Block previousBlock,String transaction){
		this.previousBlock = previousBlock;
		this.transaction = transaction;
		this.shouldRun = true;
		
	}
	public Block getBlock(){
		return newBlock;
	}
}
