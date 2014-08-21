


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadFpML file= new ReadFpML("File:///D:/Users/Franky/Desktop/Internet_Technology/Swap.TradeCreated.xml");
		
		Calculation.AddInterval(file.getTradeDate(), file.getInterval());
		System.out.println("Amount: $"+file.getAmount()+". Fixed interest rate: "+file.getRate());
		System.out.println("Monthly fixed cash flow: $"+file.getAmount()*file.getRate());
	//	System.out.println(file.getTradeDate().toString());

	}

}
