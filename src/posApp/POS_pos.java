package posApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class POS_pos extends JPanel implements ActionListener{
	
	//ItemDAO ��ü ����(dao) �� �ε�
	
	private JButton btnDB; //��ǰ �ҷ�����
	private JLabel lblItem; 
	
	private JComboBox cmbBox;
	private JLabel lblStock;
	private JTextField txtStock; //"�����Է¹ڽ�"
	private JLabel lblTotal;
	private JTextField txtTotal;
	private JButton btnAdd;
	private JButton btnPay;
	private JButton btnCancel;
	private JTable jTableItem;
	
	DefaultComboBoxModel<String> dcbm;
	DefaultTableModel model; 
	
	//�Ѱ��� ������ ������ ������� ����(total)
	int total;
	
	public POS_pos() {
		
		//�ڵ� ��ġ ���̾ƿ� ��Ȱ��ȭ
		setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel() {
			public boolean inCellEditable(int i, int c) {
				return false;
			}
		};
		
		model.addColumn("�̸�");
		model.addColumn("����");
		model.addColumn("����");
		model.addColumn("�Ѱ���");
		
		//�� ������Ʈ ��ü ���� �� ȭ�� ��ġ/ũ�� ����
		jTableItem = new JTable(model);
		JScrollPane jscroll = new JScrollPane(jTableItem);
		jscroll.setBounds(300, 20, 210, 200);
		add(jscroll);
		
		cmbBox = new JComboBox();
		cmbBox.setBounds(70, 90, 200, 30);
		add(cmbBox);
		
		btnDB = new JButton("��ǰ �ҷ�����");
		btnDB.setBounds(20, 20, 140, 40);
		
		lblItem = new JLabel("��ǰ");
		lblItem.setBounds(20, 90, 100, 30);

		lblStock = new JLabel("����");
		lblStock.setBounds(20, 140, 100, 30);

		txtStock = new JTextField();
		txtStock.setBounds(70, 140, 200, 30);
		
		lblTotal = new JLabel("�Ѱ���");
		lblTotal.setBounds(20, 250, 100, 40);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(70, 250, 200, 40);
		txtTotal.setEditable(false);
		
		btnAdd = new JButton("�߰�");
		btnAdd.setBounds(170, 190, 100, 40);
		
		btnPay = new JButton("����");
		btnPay.setBounds(300, 250, 100, 40);
		
		btnCancel = new JButton("���");
		btnCancel.setBounds(410, 250, 100, 40);
		
		//JPanel�� �߰�
		add(btnDB);
		add(lblItem);
		add(txtStock);
		add(lblStock);
		add(cmbBox);
		add(lblTotal);
		add(txtTotal);
		add(btnAdd);
		add(btnPay);
		add(btnCancel);
		add(jTableItem);
		
		//�̺�Ʈ ó���� ���� ������ ���
		btnDB.addActionListener(this);
		txtStock.addActionListener(this);
		cmbBox.addActionListener(this);
		txtTotal.addActionListener(this);
		btnAdd.addActionListener(this);
		btnPay.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//�̺�Ʈ ��ü�κ��� �ؽ�Ʈ ��������
		String s = e.getActionCommand();
		
		//��ǰ��, ���, ���� ������ �������� ���� �� �ʱ�ȭ
		String name, stock, price = "";
		
		//[��ǰ �ҷ�����] ��ư Ŭ�� ��
		if(s == "��ǰ �ҷ�����") { 
			// comboBox�� ��� ������ ��� ����(removeAllItems());
			cmbBox.removeAllItems();
			//DB�κ��� ��ǰ�� ��ü �˻� �� Vector�� ����
			dcbm = (DefaultComboBoxModel<String>) cmbBox.getModel();

			try {
				Vector<Item> itemlist = ItemDAO.getInstance().getAllItem();
				for (Item item : itemlist) {
					String item_name = item.getItem_name();
					Vector<String> in = new Vector<String>();
					in.add(item_name);
					dcbm.addElement(in.get(0));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Vector<String> vct = new Vector<String>();
			//Vector�� ������ ��ǰ���� comboBox�� �߰�
		}		
		else if (s == "�߰�") {
			model = (DefaultTableModel) jTableItem.getModel();
			// comboBox���� ������ ��ǰ��� �ؽ�Ʈ�ʵ忡 �Է��� ���� ����
			String totalprice;
			name = cmbBox.getSelectedItem().toString();
			stock = txtStock.getText();
			// DB�κ��� ����ڰ� ������ ��ǰ���� �ܰ� �ҷ�����
			try {
				price = ItemDAO.getInstance().getPrice(name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// ����ڰ� ������ ��ǰ�� ���Ű���(�ܰ�*����)�� ���� �Ѿ� �����ϱ�
			totalprice = String.valueOf(Integer.parseInt(stock) * Integer.parseInt(price));
			total += Integer.parseInt(totalprice);
			txtTotal.setText(total + "");
			// ��ǰ��, ���ż���, ���Ű���, �����Ѿ��� Vector�� ����
			Vector<String> in1 = new Vector<String>();
			in1.add(name);
			in1.add(stock);
			in1.add(price);
			in1.add(totalprice);
			model.addRow(in1);
			// Vector ��ü�� tableModel�� �߰�

		} // [����] ��ư Ŭ�� ��
		else if (s == "����") {
			// "�����Ͻðڽ��ϱ�?"��� ���̾�α� â ���(JOptionPane.showConfirmDialog())
			int gun2 = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?");
			if (gun2 == 0) {
				int gun3 = Integer.parseInt(JOptionPane.showInputDialog(null, "�ѱݾ���" + total + "�Դϴ�"));
				if (gun3 >= total) {
					JOptionPane.showMessageDialog(null, "�����Ͻ� �ݾ���" + gun3 + "�̰�\n" + "��ǰ�� �հ��" + total + "�̸�,\n"
							+ "�Ž�������" + (gun3 - total) + "�Դϴ�");
					stockUpdate(model);
				} else {
					JOptionPane.showMessageDialog(null, "�ݾ��� �����ϴ�");
				}
				clean();
			}
		} // [���] ��ư Ŭ�� ��
		else {
			int gun2 = JOptionPane.showConfirmDialog(null, "�ֹ��� ����Ͻðڽ��ϱ�");
			if (gun2 == 0) {
				clean(); // ��� ������Ʈ�� ������ �ʱ�ȭ
			}
			// "�ֹ��� ����Ͻðڽ��ϱ�?" Dialog â ���
		}
	}
	// JTable, ������ �Ѱ����� JTextField �� ������ �ʱ�ȭ
	public void clean() {
		int rows = model.getRowCount();
		for (int i = rows - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		total = 0;
		txtStock.setText("");
		txtTotal.setText("0��");
	}

	// JTable�� ��µ� ��� �������� ��ǰ��, ���, ������ �̿��Ͽ� DB ������ ������Ʈ
	public void stockUpdate(DefaultTableModel model) {
		int inputMoney;

		String product_stock[] = new String[model.getRowCount()];

		// "YES"�� ������ "�ѱݾ��� ~�Դϴ�"�� ����� �� ����ڷκ��� ���� �Է¹ޱ�(JOptionPane.showInputDialog())

		int count = model.getRowCount();

		for (int i = 0; i < count; i++) {
			try {
				product_stock[i] = ItemDAO.getInstance().getStock(model.getValueAt(i, 0).toString());
				ItemDAO.getInstance().updateStock(product_stock[i], model.getValueAt(i, 1).toString(),model.getValueAt(i, 0).toString());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
