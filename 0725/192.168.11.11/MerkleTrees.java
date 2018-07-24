import java.awt.List;
import java.security.MessageDigest;
import java.util.ArrayList;

public class MerkleTrees {

  // transaction List
  ArrayList<Transaction> txList;
  // Merkle Root
  String root;
  
  /**
   * constructor
   * @param txList transaction List
   */
  public MerkleTrees(ArrayList<Transaction> txList) {
    this.txList = txList;
    root = "";
  }
   
  /**
   * execute merkle_tree and set root.
   */
  public void merkle_tree() {
    
	  ArrayList<String> tempTxList = new ArrayList<String>();
    
    for (int i = 0; i < this.txList.size(); i++) {
      tempTxList.add(this.txList.get(i).getTransaction());
    }
    
    ArrayList<String> newTxList = getNewTxList(tempTxList);
    while (newTxList.size() != 1) {
      newTxList = getNewTxList(newTxList);
    }
    
    this.root = newTxList.get(0);
    System.out.println("[MerkleRoot]MerkleRoot : "+root);
  }
  
  /**
   * return Node Hash List.
   * @param tempTxList
   * @return
   */
  private ArrayList<String> getNewTxList(ArrayList<String> tempTxList) {
    
	  ArrayList<String> newTxList = new ArrayList<String>();
    int index = 0;
    while (index < tempTxList.size()) {
      // left
      String left = tempTxList.get(index);
      index++;

      // right
      String right = "";
      if (index != tempTxList.size()) {
        right = tempTxList.get(index);
      }

      // sha2 hex value
      String sha2HexValue = getSHA2HexValue(left + right);
      newTxList.add(sha2HexValue);
      index++;
      
    }
    
    return newTxList;
  }
  
  /**
   * Return hex string
   * @param str
   * @return
   */
  public String getSHA2HexValue(String str) {
        byte[] cipher_byte;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            cipher_byte = md.digest();
            StringBuilder sb = new StringBuilder(2 * cipher_byte.length);
            for(byte b: cipher_byte) {
              sb.append(String.format("%02x", b&0xff) );
            }
            return sb.toString();
        } catch (Exception e) {
                e.printStackTrace();
        }
        
        return "";
  }
  
  /**
   * Get Root
   * @return
   */
  public String getRoot() {
    return this.root;
  }
    
}