package resources;

public interface HandleWallet {
    
    long getBalance();
    
    void subtractBalance(long balance);
    
    void addBalance(long balance);
    
    void generateKey();
    
    String sendKey();

}
