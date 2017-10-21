package resources;

public class Wallet {
    
    private long balance;
    private String password;
    
    public Wallet(String password) {
        this.balance = 0l;
        this.password = password;
    }
    
    protected long getBalance(){
        return balance;
    }
    
    protected void subtractBalance(long balance) throws Exception{
        
    }
    
    protected void addBalance(long balance) throws Exception{
        
    }
    
    protected void setPassword() throws Exception{
        
    }
}
