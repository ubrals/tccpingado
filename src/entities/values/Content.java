package entities.values;

import entities.Producer;
import entities.ISP;
import entities.Party;
import contracts.Econtract;
import entities.Customer;

public class Content extends Product {
	
	private long id;
	
	private String title;

	private long size;

	private byte content[];

//    private Producer producer;
    private Party producer;

//    private ISP isp;
    private Party isp;
	
	private String location;
	
	private String filename;

	/**
	 * 
	 * @param type String as type of media
	 * @param subType String as classification
	 * @param value String nevermind
	 * @param econtract {@link Econtract}
	 * @param title String as title of content
	 * @param size long as the size of the media
	 * @param content array of byte, in case the ctua content to be stored in memory
	 * @param producer Party
	 * @param isp Party
	 * @param location String as the location in the filesystem
	 * @param filename String
	 */
    public Content(String type, String subType, String value, Econtract econtract, String title, long size, byte content[],
            Party producer, Party isp, String location, String filename) {
        super(type, subType, value, econtract);
        this.title = title;
        this.size = size;
        this.content = content;
        this.producer = producer;
        this.isp = isp;
        this.setLocation(location);
        this.setFilename(filename);
    }

    /**
     * 
     * @return long as Content id
     */
    public long getId() {
		return id;
	}

    /**
     * 
     * @param id long as Content id
     */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return String as title of content
	 */
	public String getTitle() {
        return title;
    }

	/**
	 * 
	 * @param title String as title of content
	 */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return size long as the size of the media
     */
    public long getSize() {
        return size;
    }

    /**
     * 
     * @param size long as the size of the media
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * 
     * @return array of byte, in case the ctua content to be stored in memory
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * 
     * @param content array of byte, in case the ctua content to be stored in memory
     */
    public void setContent(byte content[]) {
        this.content = content;
    }

    /**
     * 
     * @return Party as producer
     */
    public Party getProducer() {
        return producer;
    }

    /**
     * 
     * @param producer {@link Producer}
     */
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    /**
     * 
     * @return Party as isp
     */
    public Party getISP() {
        return isp;
    }

    /**
     * 
     * @param iSP {@link ISP}
     */
    public void setISP(ISP iSP) {
        this.isp = iSP;
    }

    /**
     * 
     * @return String as the location in the filesystem
     */
	public String getLocation() {
		return location;
	}

	/**
	 * 
	 * @param location String as the location in the filesystem
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 
	 * @return filename String
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * 
	 * @param filename String
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
