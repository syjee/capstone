import java.util.ArrayList;

/** ��ϻ����� ������
 * @author �ҿ�
 * ����� �����ϴ� ��ϻ����ڸ� �����Ѵ�.
 * ��ϻ����ڸ� �����ϰ� ���� �������� ��ϻ����ڵ��� ���̴��� �Ѵ�.
 */

public class BlockGeneratorManager {
	public int index;
	public ArrayList<BlockGenerator> BG = new ArrayList<BlockGenerator>();
	
	public BlockGeneratorManager(){
		for(int i = 0; i<10; i++) addBG();
	}
	
	public void putRequest(Block previousBlock,String transaction){
		BlockGenerator temp = new BlockGenerator(-1);
		
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
		BlockGenerator temp = new BlockGenerator(-1);
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
