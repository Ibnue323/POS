package posApp;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		ItemDAO dao = ItemDAO.getInstance();
		
		Vector<Item> list = new Vector<Item>();
		
		/*
		
		try {
			list = dao.getAllItem();
			
			Iterator it = list.iterator();
			
			System.out.println("*** ��ü ��ǰ ��� ��� ***");
			while(it.hasNext()) {
				Item item = (Item)it.next();
				System.out.println(item.getId()+", "+item.getItem_name()+", "+item.getItem_stock()+", "+item.getItem_price());
			}
			
			System.out.println("*** ��ǰ�� ��� ***");
			Vector<String> itemlist = new Vector<String>();
			
			itemlist = dao.getItem();
			
			//��ȸ�ϸ鼭 Vector�� �� ��� ���
			it = itemlist.iterator();
			while(it.hasNext()) {
				String item = (String) it.next();
				System.out.println(item);
			}
			
			System.out.println("*** ��ǰ�� ���� ��� ***");
			Scanner sc = new Scanner(System.in);
			System.out.println("��ǰ�� �Է� : ");
			String input = sc.next();
			String price = dao.getPrice(input);
			System.out.println("��ǰ ���� : "+price);
			
			System.out.println("*** ��ǰ�� ��� ��� ***");
			String stock = dao.getStock(input);
			System.out.println("��ǰ ��� : "+stock);
			
			System.out.println("*** ��ǰ�� ��� ������Ʈ ***");
			String total = dao.getStock(input);
			System.out.println("�Ǹ� ���� : ");
			String count = sc.next();
			dao.updateStock(total, count, input);
			
			list = dao.getAllItem();			
			it = list.iterator();
			
			System.out.println("*** ��ü ��ǰ ��� ��� ***");
			while(it.hasNext()) {
				Item item = (Item)it.next();
				System.out.println(item.getId()+", "+item.getItem_name()+", "+item.getItem_stock()+", "+item.getItem_price());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

		Scanner sc = new Scanner(System.in);
		
		Item itemNew = new Item();
		System.out.print("��ǰ�� : ");
		String name = sc.next();
		System.out.print("�԰� : ");
		int stock = sc.nextInt();
		System.out.print("�ܰ� : ");
		int price = sc.nextInt();
		itemNew.setItem_name(name);
		itemNew.setItem_stock(stock);
		itemNew.setItem_price(price);
		boolean result = dao.insertItem(itemNew);
		
		list = dao.getAllItem();			
		Iterator it = list.iterator();
		
		System.out.println("*** ��ü ��ǰ ��� ��� ***");
		while(it.hasNext()) {
			Item item = (Item)it.next();
			System.out.println(item.getId()+", "+item.getItem_name()+", "+item.getItem_stock()+", "+item.getItem_price());
		}
		
	}

}
