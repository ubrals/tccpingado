package entities.values;

public class Content extends Product {
    private String title;
    private long size;
    private byte[] content = new byte[1047527424]; // 999 megabytes
    
    public Content(String title, long size, String type, String value, String subType) {
        super(type, value, subType);
        this.title = title;
        this.size = size;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) throws Exception {
        try{
            this.content = content;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
}
