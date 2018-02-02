import java.util.ArrayList;

/** ��ϻ����� ������
 * @author �ҿ�
 * ����� �����ϴ� ��ϻ����ڸ� �����Ѵ�.
 * ��ϻ����ڸ� �����ϰ� ���� �������� ��ϻ����ڵ��� ���̴��� �Ѵ�.
 */

public class BlockGeneratorManager extends Thread{
	public int index;
	public ArrayList<BlockGenerator> BG = new ArrayList<BlockGenerator>();
	public Block newBlock;
	public boolean shouldRun = true;
	
	public BlockGeneratorManager(){
		for(int i = 0; i<10; i++) addBG();
	}
	
	public void run(){}
	
	public void check(){
		BlockGenerator temp;
		int i = 0;
		while(shouldRun){
			for(i = 0; i<index; i++){
				temp = BG.get(i);
					if(temp.isMined()){		
						newBlock = temp.getBlock();
						threadStop();
						
						System.out.println("A block is mined...");
						shouldRun = false;
						break;
					}
			}
		}
	}
	public Block getBlock(){
		return newBlock;
	}
	
	public boolean getShoudRun(){
		return shouldRun;
	}
	public void putRequest(int height, Block previousBlock,String transaction){
		BlockGenerator temp;
		
		for(int i = 0; i<index; i++){
			temp = BG.get(i);
			temp.start();
			temp.putRequest(height,previousBlock, transaction);
		}
		
		check();
	}
	
	public void addBG(){
		BG.add(new BlockGenerator(index+1)); index++;
	}
	
	public void threadJoin(){
		BlockGenerator temp;
		int i = 0;
		try {
			for(i = 0; i<index; i++){
				temp = BG.get(i);
				temp.join();
			}
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("thread " + i+1 +" join error...");
		}
	}
	
	public void threadStop(){
		BlockGenerator temp;
		int i = 0;
		try {
			for(i = 0; i<index; i++){
				temp = BG.get(i);
				//temp.threadStop();
				temp.stop();
				System.out.println("thread " + (i+1) +" stopped...");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("thread " + i+1 +" stop error...");
		}
	}
	
}
