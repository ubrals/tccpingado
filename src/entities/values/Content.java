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

    public Content(String type, String value, Econtract econtract, String title, long size, byte content[],
            Producer producer, ISP iSP) {
        super(type, value, econtract);
        this.title = title;
        this.size = size;
        this.content = content;
        this.producer = producer;
        this.iSP = iSP;
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

    public ISP getiSP() {
        return iSP;
    }

    public void setiSP(ISP iSP) {
        this.iSP = iSP;
    }

}
