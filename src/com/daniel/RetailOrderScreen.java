package com.daniel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.daniel.dao.OrderDao;
import com.daniel.dao.OrderDaoImpl;
import com.daniel.dao.ProductDao;
import com.daniel.dao.ProductDaoImpl;
import com.daniel.dao.StaffDao;
import com.daniel.dao.StaffDaoImpl;
import com.daniel.model.InvoiceSettings;
import com.daniel.model.Item;
import com.daniel.model.RetailOrder;
import com.daniel.model.RetailOrderLine;
import com.daniel.model.Staff;
import com.daniel.utilities.Utilities;


/**
 * This class designs and handles all of the operations that are performed on the Retail Order Tab of the main screen's tabbed pane. 
 * @author Robert Forde
 * @version 1.0
 */
public class RetailOrderScreen extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RetailOrder retailOrder;
	private RetailOrderLine retailOrderLine;
	private InvoiceSettings invoiceSettings;
	
	private JPanel panelCustOrderLine;
	private JPanel panelCustCurrentOrder;
	private JPanel panelCustPrintOrder;
	private JPanel panelCustDiscount;
	private JPanel panelCustGrossProfit;
	private JPanel panelCustRounding;
	private JPanel panelCustDetails;
	private JPanel panelCustNewOrder;
	private JPanel panelCustItemDescriptionChoice;
	private JPanel panelCustPaymentMethod;
	
	private JButton btnCustNewOrderLine;
	
	private JTable tblCustOrder;
	private JTable tblCustItemDescription;
	
	private JLabel lblCustTotalPrice;
	private JLabel lblCustTotalExVat;
	private JLabel lblCustGrossProfitValue;
	private JLabel lblCustGrossProfitPercent;
	
	private TableColumnModel columnModelOrder;
	
	private JRadioButton rdbtnCustCash;
	private JRadioButton rdbtnCustCreditCard;
	private JRadioButton rdbtnCustCheque;
	private JRadioButton rdbtnCustBankTransfer;
	
	private JTextField txtCustDiscount;
	private JTextField txtCustLineDisc;
	private JTextField txtCustLineRep;
	private JTextField txtCustLineCode;
	private JTextField txtCustLineDesc;
	private JTextField txtCustLinePrice;
	private JTextField txtCustLineQty;

	private float txtCustLineCost;
	private float custTradeLinePrice;
	private String oldOverallDiscount;

	private ProductDao productDao;
	private StaffDao staffDao;
	private OrderDao orderDao;

	
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public StaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}
	
	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public RetailOrder getRetailOrder() {
		return retailOrder;
	}

	public void setRetailOrder(RetailOrder retailOrder) {
		this.retailOrder = retailOrder;
	}

	public RetailOrderLine getRetailOrderLine() {
		return retailOrderLine;
	}

	public void setRetailOrderLine(RetailOrderLine retailOrderLine) {
		this.retailOrderLine = retailOrderLine;
	}

	public InvoiceSettings getInvoiceSettings() {
		return invoiceSettings;
	}

	public void setInvoiceSettings(InvoiceSettings invoiceSettings) {
		this.invoiceSettings = invoiceSettings;
	}

	public JTable getTblCustOrder() {
		return tblCustOrder;
	}

	public void setTblCustOrder(JTable tblCustOrder) {
		this.tblCustOrder = tblCustOrder;
	}

	public JPanel getPanelCustPrintOrder() {
		return panelCustPrintOrder;
	}

	public void setPanelCustPrintOrder(JPanel panelCustPrintOrder) {
		this.panelCustPrintOrder = panelCustPrintOrder;
	}

	public JPanel getPanelCustDiscount() {
		return panelCustDiscount;
	}

	public void setPanelCustDiscount(JPanel panelCustDiscount) {
		this.panelCustDiscount = panelCustDiscount;
	}

	public JPanel getPanelCustGrossProfit() {
		return panelCustGrossProfit;
	}

	public void setPanelCustGrossProfit(JPanel panelCustGrossProfit) {
		this.panelCustGrossProfit = panelCustGrossProfit;
	}

	public JPanel getPanelCustRounding() {
		return panelCustRounding;
	}

	public void setPanelCustRounding(JPanel panelCustRounding) {
		this.panelCustRounding = panelCustRounding;
	}

	public JPanel getPanelCustDetails() {
		return panelCustDetails;
	}

	public void setPanelCustDetails(JPanel panelCustDetails) {
		this.panelCustDetails = panelCustDetails;
	}

	public JPanel getPanelCustNewOrder() {
		return panelCustNewOrder;
	}

	public void setPanelCustNewOrder(JPanel panelCustNewOrder) {
		this.panelCustNewOrder = panelCustNewOrder;
	}

	public JPanel getPanelCustCurrentOrder() {
		return panelCustCurrentOrder;
	}

	public void setPanelCustCurrentOrder(JPanel panelCustCurrentOrder) {
		this.panelCustCurrentOrder = panelCustCurrentOrder;
	}

	public JPanel getPanelCustPaymentMethod() {
		return panelCustPaymentMethod;
	}

	public void setPanelCustPaymentMethod(JPanel panelCustPaymentMethod) {
		this.panelCustPaymentMethod = panelCustPaymentMethod;
	}

	public JLabel getLblCustTotalPrice() {
		return lblCustTotalPrice;
	}

	public void setLblCustTotalPrice(JLabel lblCustTotalPrice) {
		this.lblCustTotalPrice = lblCustTotalPrice;
	}

	public JLabel getLblCustTotalExVat() {
		return lblCustTotalExVat;
	}

	public void setLblCustTotalExVat(JLabel lblCustTotalExVat) {
		this.lblCustTotalExVat = lblCustTotalExVat;
	}

	public JLabel getLblCustGrossProfitValue() {
		return lblCustGrossProfitValue;
	}

	public void setLblCustGrossProfitValue(JLabel lblCustGrossProfitValue) {
		this.lblCustGrossProfitValue = lblCustGrossProfitValue;
	}

	public JLabel getLblCustGrossProfitPercent() {
		return lblCustGrossProfitPercent;
	}

	public void setLblCustGrossProfitPercent(JLabel lblCustGrossProfitPercent) {
		this.lblCustGrossProfitPercent = lblCustGrossProfitPercent;
	}

	public JTextField getTxtCustLineRep() {
		return txtCustLineRep;
	}

	public void setTxtCustLineRep(JTextField txtCustLineRep) {
		this.txtCustLineRep = txtCustLineRep;
	}

	/**
	 * This constructs the Retail Order Screen that is used for entering Retail Orders
	 * @param tabbedPane This is tab on the the main screen of the application and is passed in so we can add this screen to it 
	 * @param invoiceSettings This are the current print settings, vat rate etc. that is passed in for use by the retail order screen operations 
	 */
	public RetailOrderScreen(JTabbedPane tabbedPane, InvoiceSettings invoiceSettings){
		
		setProductDao(Dan.ctx.getBean("productDaoImpl", ProductDaoImpl.class));
		setStaffDao(Dan.ctx.getBean("staffDaoImpl", StaffDaoImpl.class));
		setOrderDao(Dan.ctx.getBean("orderDaoImpl", OrderDaoImpl.class));
		
		setRetailOrder(retailOrder);
		
		// Create the logo images for the application
		Image logo = new ImageIcon(this.getClass().getResource("/Logo199X198.jpg")).getImage();
		ImageIcon imageIcon = new ImageIcon(logo);

		// Create the up arrow ImageIcon
		Image upArrow = new ImageIcon(this.getClass().getResource("/Up.jpg")).getImage();
		ImageIcon imageIconUpArrow = new ImageIcon(upArrow);

		// Create the up arrow ImageIcon
		Image downArrow = new ImageIcon(this.getClass().getResource("/Down.jpg")).getImage();
		ImageIcon imageIconDownArrow = new ImageIcon(downArrow);

		setInvoiceSettings(invoiceSettings);
				
		setBackground(Color.DARK_GRAY);
		setBounds(0, 0, 1294, 624);
		tabbedPane.addTab("Retail Order", null, this, null);
		setLayout(null);
		
		panelCustNewOrder = new JPanel();
		panelCustNewOrder.setBackground(UIManager.getColor("List.dropLineColor"));
		panelCustNewOrder.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustNewOrder.setBounds(1046, 113, 199, 71);
		this.add(panelCustNewOrder);
		panelCustNewOrder.setLayout(null);
		panelCustNewOrder.setVisible(false);
		
		JButton btnCustNewOrder = new JButton("NEW ORDER");
		btnCustNewOrder.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), UIManager.getColor("Button.darkShadow")));
		btnCustNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				startNewOrder();
			}
		});
		btnCustNewOrder.setFont(new Font("Georgia", Font.BOLD, 16));
		btnCustNewOrder.setBounds(17, 17, 167, 38);
		panelCustNewOrder.add(btnCustNewOrder);
		
		panelCustOrderLine = new JPanel();
		panelCustOrderLine.setForeground(Color.BLACK);
		panelCustOrderLine.setBackground(UIManager.getColor("List.dropLineColor"));
		panelCustOrderLine.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustOrderLine.setBounds(28, 21, 1217, 71);
		this.add(panelCustOrderLine);
		panelCustOrderLine.setLayout(null);
		
		JLabel lblCustItemCodeText = new JLabel("Item Code");
		lblCustItemCodeText.setBounds(152, 9, 132, 18);
		lblCustItemCodeText.setForeground(Color.BLACK);
		lblCustItemCodeText.setFont(new Font("Georgia", Font.BOLD, 16));
		panelCustOrderLine.add(lblCustItemCodeText);
		
		txtCustLineCode = new JTextField();
		txtCustLineCode.setBounds(152, 31, 132, 28);
		txtCustLineCode.setEditable(false);
		txtCustLineCode.setFont(new Font("Georgia", Font.PLAIN, 16));
		txtCustLineCode.setColumns(10);
		txtCustLineCode.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustOrderLine.add(txtCustLineCode);
		
		JLabel lblCustItemDescText = new JLabel("Item Description");
		lblCustItemDescText.setBounds(318, 9, 175, 18);
		lblCustItemDescText.setForeground(Color.BLACK);
		lblCustItemDescText.setFont(new Font("Georgia", Font.BOLD, 16));
		panelCustOrderLine.add(lblCustItemDescText);
		
		txtCustLineDesc = new JTextField();
		txtCustLineDesc.setBounds(318, 31, 341, 28);
		txtCustLineDesc.addKeyListener (new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0){
				
				// Disable the normal tab key focus on the Item Description textbox
				txtCustLineDesc.setFocusTraversalKeysEnabled(false);
				
				// Uppercase the entered description
				txtCustLineDesc.setText(txtCustLineDesc.getText().toUpperCase());
				
				// Make ENTER button and the list of descriptions invisible
				panelCustItemDescriptionChoice.setVisible(false);
				btnCustNewOrderLine.setVisible(false);
				
				if (txtCustLineDesc.getText().equals("") == false) {  
					// Search in database for items that have this exact description in database and if found enable qty textfield and fill in code and price
					try {
							
						// Find item based on it's description
						Item foundItem = getProductDao().findItemByDesc(txtCustLineDesc.getText());

						if(foundItem.getItemCode() != null){
							txtCustLineCode.setText(foundItem.getItemCode());
							txtCustLinePrice.setText(Utilities.stringToDec(String.valueOf(foundItem.getRetailPrice())));
							txtCustLineCost = foundItem.getCostPrice();
							custTradeLinePrice = foundItem.getTradePrice();
							txtCustLineQty.setEnabled(true);
							txtCustLineDisc.setEnabled(true);
						} else {
							txtCustLineCode.setText("");
							txtCustLinePrice.setText("");
							txtCustLineQty.setText("");
							txtCustLineQty.setEnabled(false);
							txtCustLineDisc.setText("");
							txtCustLineDisc.setEnabled(false);
						
							// Exact match not found so bring up list of descriptions that contain this description and are not set as deleted
							List<Item> itemList = getProductDao().findPossibleItemMatches(txtCustLineDesc.getText().toUpperCase());
							
							if(itemList.size() != 0) {
								
								String columns[] = {"DESCRIPTION"};
								DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
								
								for(Item item : itemList){
									String tableDesc = item.getItemDescription();
									Object[] line = {tableDesc};
									tableModel.addRow(line);
								}
								
								tblCustItemDescription.setModel(tableModel);
								
								tblCustItemDescription.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
								TableColumnModel columnModel = tblCustItemDescription.getColumnModel();
								columnModel.getColumn(0).setPreferredWidth(340);
								panelCustItemDescriptionChoice.setVisible(true);
								
								// Set the tab key to the item description possible matches table
								if(arg0.getKeyCode() == KeyEvent.VK_TAB){
									tblCustItemDescription.requestFocus();
								} 
								
							} else {
								txtCustLineCode.setText("");
								txtCustLinePrice.setText("");
								txtCustLineQty.setText("");
								txtCustLineQty.setEnabled(false);
								txtCustLineDisc.setText("");
								txtCustLineDisc.setEnabled(false);
							}
								
						}
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
				}else {
					// Make ENTER button and the list of descriptions invisible
					panelCustItemDescriptionChoice.setVisible(false);
					btnCustNewOrderLine.setVisible(false);
				}
			}
		});
		txtCustLineDesc.setFont(new Font("Georgia", Font.PLAIN, 16));
		txtCustLineDesc.setColumns(50);
		txtCustLineDesc.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustOrderLine.add(txtCustLineDesc);
		
		JLabel lblCustItemPriceText = new JLabel("Item Price");
		lblCustItemPriceText.setBounds(678, 9, 104, 18);
		lblCustItemPriceText.setForeground(Color.BLACK);
		lblCustItemPriceText.setFont(new Font("Georgia", Font.BOLD, 16));
		panelCustOrderLine.add(lblCustItemPriceText);
		
		txtCustLinePrice = new JTextField();
		txtCustLinePrice.setBounds(678, 31, 132, 28);
		txtCustLinePrice.setEditable(false);
		txtCustLinePrice.setFont(new Font("Georgia", Font.PLAIN, 16));
		txtCustLinePrice.setColumns(10);
		txtCustLinePrice.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustOrderLine.add(txtCustLinePrice);
		
		JLabel lblCustItemQtyText = new JLabel("Quantity");
		lblCustItemQtyText.setBounds(828, 9, 75, 18);
		lblCustItemQtyText.setForeground(Color.BLACK);
		lblCustItemQtyText.setFont(new Font("Georgia", Font.BOLD, 16));
		panelCustOrderLine.add(lblCustItemQtyText);
		
		txtCustLineQty = new JTextField();
		txtCustLineQty.setBounds(828, 31, 57, 28);
		txtCustLineQty.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				panelCustItemDescriptionChoice.setVisible(false);
				btnCustNewOrderLine.setVisible(true);
			}
		});
		txtCustLineQty.setEnabled(false);
		txtCustLineQty.setFont(new Font("Georgia", Font.PLAIN, 16));
		txtCustLineQty.setColumns(10);
		txtCustLineQty.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustOrderLine.add(txtCustLineQty);
		
		btnCustNewOrderLine = new JButton("ENTER");
		btnCustNewOrderLine.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), UIManager.getColor("Button.darkShadow")));
		btnCustNewOrderLine.setBounds(1025, 17, 167, 38);
		btnCustNewOrderLine.addActionListener (new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// First check that the Rep is valid
				try {
					
					Staff checkStaff = getStaffDao().staffExists(txtCustLineRep.getText());
					
					// If this repCode is okay
					if(checkStaff.getRepCode() != null) {

						txtCustLineRep.setEditable(false);
						txtCustLineRep.setEnabled(false);
						// Check if Order exists and if not create one
						if(getRetailOrder() == null){
							setRetailOrder(new RetailOrder());
							getRetailOrder().setOrderDate(new Date());
							getRetailOrder().setSaleType("Retail");
							getRetailOrder().setRepNo(txtCustLineRep.getText());
						}

						try {

							// Check if an item is entered
							if(!txtCustLineDesc.getText().isEmpty()) {
								// Check that the Quantity entered is an Integer and > 0.
								if(Integer.parseInt(txtCustLineQty.getText()) > 0) {
									
									float retailLinePrice = Float.parseFloat(txtCustLinePrice.getText());
									String strDiscountLinePrice = txtCustLineDisc.getText().equals("")?"0.00":txtCustLineDisc.getText();
									float discountLinePrice = retailLinePrice * Float.parseFloat(strDiscountLinePrice) / 100;
									
									if(retailLinePrice - discountLinePrice >= (custTradeLinePrice)+0.01) {
															
										// Create a RetailOrderLine and call the addRetailOrderLine() method to calculate it's values and save it to the arraylist  
										retailOrderLine = new RetailOrderLine();
	
										retailOrderLine.addRetailOrderLine(txtCustLineCode, txtCustLineDesc, txtCustLineQty, txtCustLinePrice, txtCustLineDisc, txtCustDiscount, 
																			lblCustGrossProfitValue, lblCustGrossProfitPercent, getRetailOrder(), custTradeLinePrice,
																			txtCustLineCost, getInvoiceSettings().getReceiptVatRate());
										
										// Clear the order textfield boxes and disable the quantity+discount textfields and make the ENTER button invisible
										txtCustLineCode.setText("");
										txtCustLineDesc.setText("");
										txtCustLinePrice.setText("");
										txtCustLineQty.setText("");
										txtCustLineQty.setEnabled(false);
										txtCustLineDisc.setText("");
										txtCustLineDisc.setEnabled(false);
										btnCustNewOrderLine.setVisible(false);
											
										// Make the Cust: CurrentOrder, PrintOrder, PaymentMethod, DiscountPanel, GrossProfit, Rounding, CustDetails and NewOrder panels visible
										panelCustCurrentOrder.setVisible(true);
										panelCustPrintOrder.setVisible(true);
										panelCustPaymentMethod.setVisible(true);
										panelCustDiscount.setVisible(false);
										panelCustGrossProfit.setVisible(true);
										panelCustRounding.setVisible(true);
										panelCustDetails.setVisible(true);
										panelCustNewOrder.setVisible(true);
											
										// Load the current order lines onto the screen
										refreshCurrentOrderTable();
																			
										
										// Set the focus back to the item description
										txtCustLineDesc.requestFocus();
									
									}else {
										JOptionPane.showMessageDialog(null, "Discount too large !");
									}
								} else {
									JOptionPane.showMessageDialog(null, "You must enter a Quantity greater than 0 !");
								}
							}else {
								JOptionPane.showMessageDialog(null, "You must enter an Item !");	
							}
						}catch(Exception ex){
							JOptionPane.showMessageDialog(null, "You must enter a number for the Quantity and the Discount % !");
						}
					}else{
						JOptionPane.showMessageDialog(null, "That Rep Code doesn't exist, please try again !");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnCustNewOrderLine.setFont(new Font("Georgia", Font.BOLD, 16));
		btnCustNewOrderLine.setVisible(false);
		panelCustOrderLine.add(btnCustNewOrderLine);

		JLabel lblCustItemDiscText = new JLabel("Discount %");
		lblCustItemDiscText.setForeground(Color.BLACK);
		lblCustItemDiscText.setFont(new Font("Georgia", Font.BOLD, 16));
		lblCustItemDiscText.setBounds(913, 9, 104, 18);
		panelCustOrderLine.add(lblCustItemDiscText);
		
		txtCustLineDisc = new JTextField();
		txtCustLineDisc.setFont(new Font("Georgia", Font.PLAIN, 16));
		txtCustLineDisc.setEnabled(false);
		txtCustLineDisc.setColumns(10);
		txtCustLineDisc.setBounds(913, 31, 57, 28);
		txtCustLineDisc.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustOrderLine.add(txtCustLineDisc);
		
		JLabel lblCustRepCodeText = new JLabel("Rep Code");
		lblCustRepCodeText.setForeground(Color.BLACK);
		lblCustRepCodeText.setFont(new Font("Georgia", Font.BOLD, 16));
		lblCustRepCodeText.setBounds(34, 9, 96, 18);
		panelCustOrderLine.add(lblCustRepCodeText);
		
		txtCustLineRep = new JTextField();
		txtCustLineRep.setFont(new Font("Georgia", Font.PLAIN, 16));
		txtCustLineRep.setBounds(34, 31, 86, 28);
		txtCustLineRep.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustOrderLine.add(txtCustLineRep);
		txtCustLineRep.setColumns(10);
		
		txtCustLineDisc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				panelCustItemDescriptionChoice.setVisible(false);
				btnCustNewOrderLine.setVisible(true);
			}
		});
		
		panelCustItemDescriptionChoice = new JPanel();
		panelCustItemDescriptionChoice.setBounds(350, 91, 338, 398);
		this.add(panelCustItemDescriptionChoice);
		panelCustItemDescriptionChoice.setLayout(null);
		
		JScrollPane scrlPanetblCustItemDescription = new JScrollPane();
		scrlPanetblCustItemDescription.setBounds(0, 0, 337, 397);
		panelCustItemDescriptionChoice.add(scrlPanetblCustItemDescription);
		
		// When creating the JTable for the description dropdown I over-ride the isCellEditable method to make the table un-editable
		tblCustItemDescription = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column)
		    {
		        return false;
		    }
		};
		tblCustItemDescription.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlPanetblCustItemDescription.setViewportView(tblCustItemDescription);
		
		tblCustItemDescription.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// If enter pressed call a method to fill the orderline fields with this item, hide the panel with this table, set focus to qty and make ENTER visible 
				if(arg0.getKeyCode() == 10) {
					ordLineItemSelected();					
				}
			}
		});
		tblCustItemDescription.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
								
				// Call a method to fill the orderline fields with this item, hide the panel with this table, set focus to qty and make the enter button visible
				ordLineItemSelected();
			}
		});
		tblCustItemDescription.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCustItemDescription.setCellSelectionEnabled(true);
		tblCustItemDescription.setColumnSelectionAllowed(false);
		
		// Initially make the description dropdown invisible
		panelCustItemDescriptionChoice.setVisible(false);
		
		panelCustCurrentOrder = new JPanel();
		panelCustCurrentOrder.setBackground(UIManager.getColor("List.dropLineColor"));
		panelCustCurrentOrder.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustCurrentOrder.setBounds(28, 138, 800, 447);
		this.add(panelCustCurrentOrder);
		panelCustCurrentOrder.setLayout(null);
		panelCustCurrentOrder.setVisible(false);
		
		JLabel lblCustCurrentTransaction = new JLabel("RETAIL ORDER");
		lblCustCurrentTransaction.setFont(new Font("Georgia", Font.BOLD, 18));
		lblCustCurrentTransaction.setBounds(10, 11, 179, 31);
		panelCustCurrentOrder.add(lblCustCurrentTransaction);
		
		JLabel lblCustTotalPriceText = new JLabel("TOTAL INC-VAT");
		lblCustTotalPriceText.setFont(new Font("Georgia", Font.BOLD, 18));
		lblCustTotalPriceText.setBounds(634, 11, 156, 31);
		panelCustCurrentOrder.add(lblCustTotalPriceText);
		
		lblCustTotalPrice = new JLabel("0.00", SwingConstants.RIGHT);
		lblCustTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCustTotalPrice.setBounds(634, 37, 156, 31);
		panelCustCurrentOrder.add(lblCustTotalPrice);
		
		JScrollPane scrlCustOrder = new JScrollPane();
		scrlCustOrder.setBounds(10, 68, 780, 368);
		panelCustCurrentOrder.add(scrlCustOrder);
		
		tblCustOrder = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public TableCellRenderer getCellRenderer( int row, int col ) { 
				TableCellRenderer renderer = super.getCellRenderer(row,col);
				if ( col == dataModel.getColumnCount() - 1  || col == dataModel.getColumnCount() - 2 || col == dataModel.getColumnCount() - 3 || 
					 col == dataModel.getColumnCount() - 4) 
					((JLabel) renderer).setHorizontalAlignment( SwingConstants.RIGHT );
				
				// Left justify the other columns
				else 
					((JLabel) renderer).setHorizontalAlignment( SwingConstants.LEFT );
				
				return renderer; 
			}

			// Override method so that table is not editable
			@Override
		    public boolean isCellEditable(int row, int column)
		    {
		        return false;
		    }
		};
		tblCustOrder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCustOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
							
				if(retailOrder.getOrderList().size() > 1) {
					// We use the table's getSelectedRow() method to find the row that the User selected. 
					int row = tblCustOrder.getSelectedRow();
						
					// We use this row to find the element in the arraylist that the user selected
					getRetailOrder().getOrderList().remove(row);
						
					// Recalculate everything for the Order
					getRetailOrder().recalculateAfterDelete(getInvoiceSettings().getReceiptVatRate(), oldOverallDiscount, lblCustGrossProfitValue, lblCustGrossProfitPercent);
						
					// Load the current order lines onto the screen
					refreshCurrentOrderTable();
				
				} else{

					startNewOrder();
				}
										
			}
		});
		tblCustOrder.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tblCustOrder.setShowGrid(false);
		tblCustOrder.setShowHorizontalLines(false);
		tblCustOrder.setShowVerticalLines(false);
		tblCustOrder.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		scrlCustOrder.setViewportView(tblCustOrder);
		
		JLabel lblCustTotalExVatText = new JLabel("TOTAL EX-VAT");
		lblCustTotalExVatText.setFont(new Font("Georgia", Font.BOLD, 18));
		lblCustTotalExVatText.setBounds(297, 11, 148, 31);
		panelCustCurrentOrder.add(lblCustTotalExVatText);
		
		lblCustTotalExVat = new JLabel("0.00", SwingConstants.RIGHT);
		lblCustTotalExVat.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCustTotalExVat.setBounds(297, 37, 144, 31);
		panelCustCurrentOrder.add(lblCustTotalExVat);
		
		panelCustPrintOrder = new JPanel();
		panelCustPrintOrder.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustPrintOrder.setBackground(UIManager.getColor("List.dropLineColor"));
		panelCustPrintOrder.setBounds(1046, 204, 199, 71);
		panelCustPrintOrder.setVisible(false);
		this.add(panelCustPrintOrder);
		panelCustPrintOrder.setLayout(null);
		
		JButton btnCustPrintOrder = new JButton("PRINT ORDER");
		btnCustPrintOrder.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), UIManager.getColor("Button.darkShadow")));
		btnCustPrintOrder.setBounds(17, 17, 167, 38);
		panelCustPrintOrder.add(btnCustPrintOrder);
		btnCustPrintOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRetailOrder().setOrderLinesToPrint(0);
				// Set the PayType as per the user's selection
				String paytype = "";
				if(rdbtnCustCash.isSelected())
					paytype = "Cash";
				else if(rdbtnCustCreditCard.isSelected())
					paytype = "Credit Card";
				else if(rdbtnCustCheque.isSelected())
					paytype = "Cheque";
				else
					paytype = "Bank Transfer";
				
				getRetailOrder().setPayType(paytype);
				
				try {
					getRetailOrder().printOrder(getInvoiceSettings(), null, null, null);
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnCustPrintOrder.setFont(new Font("Georgia", Font.BOLD, 16));
		
		JLabel lblCustLogo = new JLabel();
		// Put Logo image on order tab by adding an image to the Logo Label
		lblCustLogo.setIcon(imageIcon);
		
		lblCustLogo.setBounds(1046, 385, 199, 200);
		this.add(lblCustLogo);
		
		panelCustPaymentMethod = new JPanel();
		panelCustPaymentMethod.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustPaymentMethod.setBackground(UIManager.getColor("List.dropLineColor"));
		panelCustPaymentMethod.setBounds(855, 113, 163, 118);
		this.add(panelCustPaymentMethod);
		panelCustPaymentMethod.setLayout(null);
		panelCustPaymentMethod.setVisible(false);
				
		rdbtnCustCash = new JRadioButton("Cash");
		rdbtnCustCash.setSelected(true);
		rdbtnCustCash.setBackground(UIManager.getColor("List.dropLineColor"));
		rdbtnCustCash.setFont(new Font("Georgia", Font.BOLD, 16));
		rdbtnCustCash.setBounds(25, 7, 109, 23);
		panelCustPaymentMethod.add(rdbtnCustCash);
		
		rdbtnCustCreditCard = new JRadioButton("Credit Card");
		rdbtnCustCreditCard.setSelected(false);
		rdbtnCustCreditCard.setBackground(UIManager.getColor("List.dropLineColor"));
		rdbtnCustCreditCard.setFont(new Font("Georgia", Font.BOLD, 16));
		rdbtnCustCreditCard.setBounds(25, 33, 132, 23);
		panelCustPaymentMethod.add(rdbtnCustCreditCard);
		
		rdbtnCustCheque = new JRadioButton("Cheque");
		rdbtnCustCheque.setSelected(false);
		rdbtnCustCheque.setBackground(UIManager.getColor("List.dropLineColor"));
		rdbtnCustCheque.setFont(new Font("Georgia", Font.BOLD, 16));
		rdbtnCustCheque.setBounds(25, 59, 109, 23);
		panelCustPaymentMethod.add(rdbtnCustCheque);
		
		rdbtnCustBankTransfer = new JRadioButton("Transfer");
		rdbtnCustBankTransfer.setSelected(false);
		rdbtnCustBankTransfer.setFont(new Font("Georgia", Font.BOLD, 16));
		rdbtnCustBankTransfer.setBackground(UIManager.getColor("List.dropLineColor"));
		rdbtnCustBankTransfer.setBounds(25, 85, 109, 23);
		panelCustPaymentMethod.add(rdbtnCustBankTransfer);
		
		// Group the radio buttons.
		ButtonGroup retailPayTypegroup = new ButtonGroup();
		retailPayTypegroup.add(rdbtnCustCash);
		retailPayTypegroup.add(rdbtnCustCreditCard);
		retailPayTypegroup.add(rdbtnCustCheque);
		retailPayTypegroup.add(rdbtnCustBankTransfer);
		
		panelCustDiscount = new JPanel();
		panelCustDiscount.setBorder(new BevelBorder(BevelBorder.RAISED, null, UIManager.getColor("Button.shadow"), UIManager.getColor("Button.foreground"), UIManager.getColor("Button.darkShadow")));
		panelCustDiscount.setBackground(UIManager.getColor("List.dropLineColor"));
		panelCustDiscount.setBounds(880, 226, 114, 91);
		this.add(panelCustDiscount);
		panelCustDiscount.setLayout(null);
		panelCustDiscount.setVisible(false);
		
		txtCustDiscount = new JTextField();
		txtCustDiscount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {

					getRetailOrder().recalculateDiscount(txtCustDiscount, getInvoiceSettings().getReceiptVatRate(), oldOverallDiscount, lblCustGrossProfitValue, lblCustGrossProfitPercent);
	
					refreshCurrentOrderTable();	
					
				}catch(NumberFormatException ex) {
					txtCustDiscount.setText("0.00");
					getRetailOrder().recalculateDiscount(txtCustDiscount, getInvoiceSettings().getReceiptVatRate(), oldOverallDiscount, lblCustGrossProfitValue, lblCustGrossProfitPercent);
					JOptionPane.showMessageDialog(null, "You must enter a number for the Overall Discount!");
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Exception: " + ex);
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				oldOverallDiscount = txtCustDiscount.getText();
			}
		});

		txtCustDiscount.setFont(new Font("Georgia", Font.PLAIN, 16));
		txtCustDiscount.setBounds(33, 47, 44, 28);
		panelCustDiscount.add(txtCustDiscount);
		txtCustDiscount.setColumns(10);
		
		JLabel lblCustDiscountPercentage = new JLabel("Discount %");
		lblCustDiscountPercentage.setFont(new Font("Georgia", Font.BOLD, 16));
		lblCustDiscountPercentage.setBounds(10, 11, 104, 25);
		panelCustDiscount.add(lblCustDiscountPercentage);
		
		panelCustGrossProfit = new JPanel();
		panelCustGrossProfit.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustGrossProfit.setBackground(UIManager.getColor("List.dropLineColor"));
		panelCustGrossProfit.setBounds(855, 443, 163, 142);
		this.add(panelCustGrossProfit);
		panelCustGrossProfit.setLayout(null);
		panelCustGrossProfit.setVisible(false);
		
		lblCustGrossProfitValue = new JLabel("\u20AC 0.00", SwingConstants.RIGHT);
		lblCustGrossProfitValue.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCustGrossProfitValue.setForeground(Color.BLACK);
		lblCustGrossProfitValue.setBounds(25, 58, 102, 24);
		panelCustGrossProfit.add(lblCustGrossProfitValue);
		
		JLabel lblCustGrossProfitText = new JLabel("Gross Profit");
		lblCustGrossProfitText.setForeground(Color.BLACK);
		lblCustGrossProfitText.setFont(new Font("Georgia", Font.BOLD, 18));
		lblCustGrossProfitText.setBounds(27, 23, 111, 24);
		panelCustGrossProfit.add(lblCustGrossProfitText);
		
		lblCustGrossProfitPercent = new JLabel("0.00 %", SwingConstants.RIGHT);
		lblCustGrossProfitPercent.setForeground(Color.BLACK);
		lblCustGrossProfitPercent.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCustGrossProfitPercent.setBounds(25, 92, 102, 24);
		panelCustGrossProfit.add(lblCustGrossProfitPercent);
		
		panelCustRounding = new JPanel();
		panelCustRounding.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustRounding.setBackground(UIManager.getColor("List.dropLineColor"));
		// Put Logo image on order tab by adding an image to the Logo Label
		lblCustLogo.setIcon(imageIcon);
		panelCustRounding.setBounds(855, 297, 164, 81);
		this.add(panelCustRounding);
		panelCustRounding.setLayout(null);
		panelCustRounding.setVisible(false);
		
		JLabel lblCustRoundText = new JLabel("Round");
		lblCustRoundText.setForeground(Color.BLACK);
		lblCustRoundText.setFont(new Font("Georgia", Font.BOLD, 16));
		lblCustRoundText.setBounds(55, 11, 55, 17);
		panelCustRounding.add(lblCustRoundText);
				
		JLabel lblCustRoundUp = new JLabel("Round Up");
		lblCustRoundUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
						
				String beforeRound = getRetailOrder().getTotalPreRounding();
				int theDot = beforeRound.indexOf(".");
				String afterRound = Utilities.stringToDec(String.valueOf(Float.parseFloat(beforeRound.substring(0, theDot)) + 1));
				getRetailOrder().setTotalPostRounding(afterRound);
				float rounding = Utilities.floatToNumDec(Float.parseFloat(afterRound) - Float.parseFloat(beforeRound),2);
				getRetailOrder().setRounding(String.valueOf(rounding));

				// Save the rounding and post Rounding values if the invoice has been printed
				if(getRetailOrder().getReceiptNo() != 0) {
					try {

						getOrderDao().updateRoundingHeader("orderheader", rounding, retailOrder.getTotalPostRounding(), retailOrder.getReceiptNo());
	
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);					
					}
				}
						
				// Update the screen
				lblCustTotalPrice.setText("\u20AC " + getRetailOrder().getTotalPostRounding());
			}
		});
		
		lblCustRoundUp.setBounds(36, 39, 35, 31);
		panelCustRounding.add(lblCustRoundUp);
		lblCustRoundUp.setIcon(imageIconUpArrow);
				
		JLabel lblCustRoundDown = new JLabel("Round Down");
		lblCustRoundDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
						
				String beforeRound = getRetailOrder().getTotalPreRounding();
				int theDot = beforeRound.indexOf(".");
				String afterRound = Utilities.stringToDec(beforeRound.substring(0, theDot));
				getRetailOrder().setTotalPostRounding(afterRound);
				float rounding = Utilities.floatToNumDec(Float.parseFloat(afterRound) - Float.parseFloat(beforeRound),2);
				getRetailOrder().setRounding(String.valueOf(rounding));

				// Save the rounding and post Rounding values if the invoice has been printed
				if(getRetailOrder().getReceiptNo() != 0) {
					try {

						getOrderDao().updateRoundingHeader("orderheader", rounding, retailOrder.getTotalPostRounding(), retailOrder.getReceiptNo());
	
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);					
					}
				}
						
				// Update the screen
				lblCustTotalPrice.setText("\u20AC " + getRetailOrder().getTotalPostRounding());
			}
		});
				
		lblCustRoundDown.setBounds(92, 39, 35, 31);
		panelCustRounding.add(lblCustRoundDown);
		lblCustRoundDown.setIcon(imageIconDownArrow);
		
		tblCustItemDescription.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCustItemDescription.setCellSelectionEnabled(true);
		tblCustItemDescription.setColumnSelectionAllowed(true);
		
		panelCustDetails = new JPanel();
		panelCustDetails.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelCustDetails.setBackground(UIManager.getColor("List.dropLineColor"));
		panelCustDetails.setBounds(1046, 295, 199, 71);
		this.add(panelCustDetails);
		panelCustDetails.setLayout(null);
		panelCustDetails.setVisible(false);
		
		JButton btnCustDetails = new JButton("CUST DETAILS");
		btnCustDetails.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), UIManager.getColor("Button.darkShadow")));
		btnCustDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CustomerDetails(getRetailOrder()); 
			}
		});
		btnCustDetails.setFont(new Font("Georgia", Font.BOLD, 16));
		btnCustDetails.setBounds(17, 17, 167, 38);
		panelCustDetails.add(btnCustDetails);
		
		// Initially make the description dropdown invisible
		panelCustItemDescriptionChoice.setVisible(false);

	}

	
	/**
	 * This method takes the item selected from the item dropdown and fills textfields with it's values (code, description,price), it also 
	 * retrieves the item's trade price and cost price and enables the quantity and discount fields.  
	 */
	public void ordLineItemSelected() {
		try {
		
			// We use the table's getSelectedRow() method to find the row that the User selected. 
			int row = tblCustItemDescription.getSelectedRow();
	
			// Then we can get the description of the selected row by getting the model of the table and then using the getValueAt() method we can get the specific
			// row and column that we require.
			// This returns an Object so we get the toString of it.
			String descString = tblCustItemDescription.getModel().getValueAt(row, 0).toString();
			
			Item selectedItem = getProductDao().findItemByDesc(descString);
			
			if(selectedItem.getItemCode() != null){
				// Put selected item's code, description and price into the oderline entry fields and Enable the quantity+discount fields and make the enter button visible. 
				txtCustLineCode.setText(selectedItem.getItemCode());
				txtCustLineDesc.setText(selectedItem.getItemDescription());
				txtCustLinePrice.setText(Utilities.stringToDec( String.valueOf(selectedItem.getRetailPrice()) ));
				custTradeLinePrice = selectedItem.getTradePrice();
				txtCustLineCost = selectedItem.getCostPrice();
				txtCustLineQty.setEnabled(true);
				txtCustLineDisc.setEnabled(true);
			}
			
			txtCustLineQty.requestFocus();
			panelCustItemDescriptionChoice.setVisible(false);
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	
	/**
	 * This method loads the current retail order lines into the current order table that the user sees on the Retail Order Screen.
	 */
	public void refreshCurrentOrderTable(){
		
		String columns[] = {"ITEM", "DESCRIPTION", "QTY", "PRICE", "DISC", "TOTAL"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
		
		for(RetailOrderLine o : retailOrder.getOrderList()){
			String tableCode= o.getItemCode();
			String tableDesc = o.getItemDescription();
			String tableQty = o.getOrderQty();
			String tableItemPrice = o.getItemPrice();
			String tableItemDiscount = o.getDiscountPercent() + " %";
			String tableOrderPrice = o.getValueExVat();
			Object[] line = {tableCode, tableDesc, tableQty, tableItemPrice, tableItemDiscount, tableOrderPrice};
			tableModel.addRow(line);
		}
		tblCustOrder.setModel(tableModel);
		lblCustTotalPrice.setText("\u20AC " + String.valueOf(retailOrder.getTotalPreRounding()));
		lblCustTotalExVat.setText("\u20AC " + retailOrder.getTotalExVat());
	
	
		// Set the column widths for the table
		tblCustOrder.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		columnModelOrder = tblCustOrder.getColumnModel();
		columnModelOrder.getColumn(0).setPreferredWidth(90);
		columnModelOrder.getColumn(1).setPreferredWidth(409);
		columnModelOrder.getColumn(2).setPreferredWidth(40);
		columnModelOrder.getColumn(3).setPreferredWidth(70);	
		columnModelOrder.getColumn(4).setPreferredWidth(70);
		columnModelOrder.getColumn(5).setPreferredWidth(83);
	}

	
	/**
	 * This method removes the current Retail Order, clears all of the screen's textfields and hides panels so as to start a new Retail Order 
	 */
	public void startNewOrder() {
		// Make the current Current Order panel, Print Order panel, CustomerPaymentMethod panel, DiscountPanel visible and the CustomerGrossProfit panel invisible
		panelCustCurrentOrder.setVisible(false);
		panelCustPrintOrder.setVisible(false);
		panelCustPaymentMethod.setVisible(false);
		panelCustDiscount.setVisible(false);
		panelCustGrossProfit.setVisible(false);
		
		// Remove the current order from the order object
		setRetailOrder(null);
		
		// Blank the current order line entry fields
		txtCustLineCode.setText(null);
		txtCustLineDesc.setText(null);
		txtCustLinePrice.setText(null);
		txtCustLineQty.setText(null);
		txtCustLineDisc.setText(null);
		
		// Disable quantity+discount fields and make ENTER button invisible - because they may be accessible when NEW ORDER button pressed 
		txtCustLineQty.setEnabled(false);
		txtCustLineDisc.setEnabled(false);
		btnCustNewOrderLine.setVisible(false);
		
		// Make the Current Order, Print Order, CustomerPaymentMethod, DiscountPanelvisible, CustomerGrossProfit, Rounding, CustDetails and NewOrder panels 
		// invisible
		panelCustCurrentOrder.setVisible(false);
		panelCustPrintOrder.setVisible(false);
		panelCustPaymentMethod.setVisible(false);
		panelCustDiscount.setVisible(false);
		panelCustGrossProfit.setVisible(false);
		panelCustRounding.setVisible(false);
		panelCustDetails.setVisible(false);
		panelCustNewOrder.setVisible(false);
		
		// Blank the Overall Discount field and set the Payment Type back to Cash
		txtCustDiscount.setText("");
		rdbtnCustCash.setSelected(true);
		
		// Blank the Rep Code field, enable it and make it editable
		txtCustLineRep.setEditable(true);
		txtCustLineRep.setEnabled(true);
		txtCustLineRep.setText("");
	}

}