import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


public class ReadFpML {
	
	private Date tradeDate;
	private Interval interval;
	private int amount;
	private double rate;
	
	public ReadFpML(String url) {
		InputStream in=null;
	//	ArrayList <String> time=new ArrayList <String> ();
		try {
			
			URL xml = new URL(url);
			in=xml.openStream();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser;
		try {
			parser = factory.createXMLStreamReader(in);
			String multiplier=null;
			String period;
			
			while (true) {
			    int event = parser.next();
			    if (event == XMLStreamConstants.END_DOCUMENT) {
			       parser.close();
			       break;
			    }
			    if (event == XMLStreamConstants.START_ELEMENT) {
			    	if (parser.getLocalName()=="tradeDate"){
			    		event = parser.next();
			    		tradeDate=getTime(parser.getText());
			    	}
			    	else if(parser.getLocalName()=="periodMultiplier"){
			    		event = parser.next();
			    		multiplier=parser.getText();
			    	}
			    	else if(parser.getLocalName()=="period"){
			    		event = parser.next();
			    		period=parser.getText();
			    		interval=new Interval(Integer.parseInt(multiplier),period);
			    	}
			    	else if(parser.getLocalName()=="amount"){
			    		event = parser.next();
			    		amount=Integer.parseInt(parser.getText());
			    		
			    	}
			    	else if(parser.getLocalName()=="price"){
			    		event = parser.next();
			    		rate=Double.parseDouble(parser.getText());
			    		
			    	}

			    }

			}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Date getTradeDate(){
		return tradeDate;
	}
	public Interval getInterval(){
		return interval;
	}
	public int getAmount(){
		return amount;
	}
	public double getRate(){
		return rate;
	}
	
	
	public Date getTime(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date time = null;
		try {
			time = formatter.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return time;
	}

}
