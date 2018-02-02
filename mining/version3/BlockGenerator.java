import java.security.NoSuchAlgorithmException;

/** ��� ������
 * @author �ҿ�
 * ��ϻ����ڴ� �Ƿڸ� ������ ���̴��Ͽ� ����� �����Ѵ�.
 */

public class BlockGenerator extends Thread {
	
	public int index;
	public int height;
	public Block previousBlock;
	public String transaction;
	public Block newBlock;
	public boolean shouldRun = false;
	public boolean isMined = false;
	
	public BlockGenerator(int index){
		this.index = index;
		
	}
	
	public void run(){
		try {
			while(true){
				if(shouldRun){
					if(previousBlock != null){
						newBlock = new Block(height,transaction,previousBlock.getCurrentHash());
						if(newBlock.mineBlock(2)){
							isMined = true;
							System.out.println("Thread "+index+" mining success");
							break;
						}
					} else {	//genesisBlock
						newBlock = new Block(height,transaction,null);
						if(newBlock.mineBlock(2)){
							isMined = true;
							
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
	
	/*
	public void threadStop(){
		stop = false;
		shouldRun = false;
		System.out.println("Thread "+index+" stopped...");
	}
	*/
	
	public boolean isMined(){
		return isMined;
	}
	
	public void putRequest(int height, Block previousBlock,String transaction){
		this.height = height;
		this.previousBlock = previousBlock;
		this.transaction = transaction;
		this.shouldRun = true;
		
		System.out.println("Thread "+index+" for block "+height+ " created...");
		
	}
	
	public Block getBlock(){
		return newBlock;
	}
}
