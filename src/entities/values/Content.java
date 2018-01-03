package entities.values;

import entities.Producer;
import entities.ISP;
import contracts.Econtract;
import entities.Customer;

public class Content extends Product {

	private String title;

	private long size;

	private byte content[];

	private Producer producer;

	private ISP iSP;
	
	private String location;
	
	private String filename;

    public Content(String type, String subType, String value, Econtract econtract, String title, long size, byte content[],
            Producer producer, ISP iSP, String location, String filename) {
        super(type, subType, value, econtract);
        this.title = title;
        this.size = size;
        this.content = content;
        this.producer = producer;
        this.iSP = iSP;
        this.setLocation(location);
        this.setFilename(filename);
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

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public ISP getISP() {
        return iSP;
    }

    public void setISP(ISP iSP) {
        this.iSP = iSP;
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
