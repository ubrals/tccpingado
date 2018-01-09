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

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

    public void setContent(byte content[]) {
        this.content = content;
    }

    public Party getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Party getISP() {
        return isp;
    }

    public void setISP(ISP iSP) {
        this.isp = iSP;
    }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
