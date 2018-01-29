package entities;

public abstract class Party {

    private long id;

    private String name;

    /**
     * 
     * @param id long as identification within the systems
     * @param name String
     */
    public Party(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 
     * @return id long
     */
    public long getId() {
        return id;
    }

    /**
     * 
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 
     * @return name String
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

}
