import java.util.ArrayList;

/** 블록생성자 관리자
 * @author 소영
 * 블록을 생성하는 블록생성자를 관리한다.
 * 블록생성자를 생성하고 각각 스레드인 블록생성자들은 마이닝을 한다.
 */

public class BlockGeneratorManager {
	public int index;
	public ArrayList<BlockGenerator> BG = new ArrayList<BlockGenerator>();
	
	public BlockGeneratorManager(){
		for(int i = 0; i<10; i++) addBG();
	}
	
	public void putRequest(Block previousBlock,String transaction){
		BlockGenerator temp;
		
		for(int i = 0; i<index; i++){
			temp = BG.get(i);
			temp.start();
			temp.putRequest(previousBlock, transaction);
		}
		
		threadJoin();
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
	
}
