package com.daniel;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.daniel.dao.OrderDao;
import com.daniel.dao.OrderDaoImpl;
import com.daniel.dao.ProductDao;
import com.daniel.dao.ProductDaoImpl;
import com.daniel.dao.StaffDao;
import com.daniel.dao.StaffDaoImpl;
import com.daniel.model.InvoiceSettings;
import com.daniel.model.Item;
import com.daniel.model.Staff;
import com.daniel.model.TradeOrder;
import com.daniel.model.TradeOrderLine;
import com.daniel.utilities.Utilities;

import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * This class designs the Trade Quotation screen that allows the User to enter in a Trade Quotation. It also handles all of the functionality 
 * available to the User on this screen. 
 * @author Robert Forde
 *
 */
public class TradeQuotation extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TradeOrder tradeQuote;
	private TradeOrderLine tradeQuoteLine;
	private InvoiceSettings invoiceSettings;
	
	private JTextField txtTradeQuoteLineRep;
	private JTextField txtTradeQuoteLineCode;
	private JTextField txtTradeQuoteLineDesc;
	private JTextField txtTradeQuoteLinePrice;
	private JTextField txtTradeQuoteLineQty;
	private JTable tblTradeQuoteItemDescription;
	private JTable tblTradeQuoteOrder;
	private JLabel lblTradeQuoteTotalPrice;
	private JLabel lblTradeQuoteTotalExVat;
	private JPanel panelTradeQuoteItemDescriptionChoice;
	private JButton btnTradeQuoteNewOrderLine;
	private JLabel lblTradeQuoteGrossProfitValue;
	private JLabel lblTradeQuoteGrossProfitPercent;
	private JPanel panelTradeQuoteCurrentQuote;
	private JPanel panelTradeQuoteRounding;
	private JPanel panelTradeQuoteGrossProfit;
	private JPanel panelTradeQuoteNewQuote;
	private JPanel panelTradeQuotePrintQuote;
	private JPanel panelTradeQuoteCustDetails;

	private float txtTradeQuoteLineCost;
	
	private TableColumnModel columnModelOrder;

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

	public InvoiceSettings getInvoiceSettings() {
		return invoiceSettings;
	}

	public void setInvoiceSettings(InvoiceSettings invoiceSettings) {
		this.invoiceSettings = invoiceSettings;
	}

	
	/**
	 * Constructs the Trade Quotation Screen
	 * @param invoiceSettings invoiceSettings The current InvoiceSettings for Vat, Invoice Printing e.t.c
	 */
	public TradeQuotation(InvoiceSettings invoiceSettings ){

		setProductDao(Dan.ctx.getBean("productDaoImpl", ProductDaoImpl.class));
		setStaffDao(Dan.ctx.getBean("staffDaoImpl", StaffDaoImpl.class));
		setOrderDao(Dan.ctx.getBean("orderDaoImpl", OrderDaoImpl.class));
		
		getContentPane().setBackground(Color.DARK_GRAY);
		
		setInvoiceSettings(invoiceSettings);
		
		setBounds(30, 50, 1310, 629);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Trade Quotation");
		setVisible(true);

		// Create the logo images for the application
		Image logo = new ImageIcon(this.getClass().getResource("/Logo199X198.jpg")).getImage();
		ImageIcon imageIcon = new ImageIcon(logo);

		// Create the up arrow ImageIcon
		Image upArrow = new ImageIcon(this.getClass().getResource("/Up.jpg")).getImage();
		ImageIcon imageIconUpArrow = new ImageIcon(upArrow);

		// Create the down arrow ImageIcon
		Image downArrow = new ImageIcon(this.getClass().getResource("/Down.jpg")).getImage();
		ImageIcon imageIconDownArrow = new ImageIcon(downArrow);
		
		JPanel panelTradeQuoteLine = new JPanel();
		panelTradeQuoteLine.setBackground(UIManager.getColor("List.dropLineColor"));
		panelTradeQuoteLine.setBounds(28, 21, 1217, 71);
		panelTradeQuoteLine.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), UIManager.getColor("Button.darkShadow")));
		getContentPane().add(panelTradeQuoteLine);
		panelTradeQuoteLine.setLayout(null);
		
		JLabel lblTradeQuoteRepCodeText = new JLabel("Rep Code");
		lblTradeQuoteRepCodeText.setFont(new Font("Georgia", Font.BOLD, 16));
		lblTradeQuoteRepCodeText.setBounds(34, 9, 86, 18);
		panelTradeQuoteLine.add(lblTradeQuoteRepCodeText);
		
		JLabel lblTradeQuoteItemDescriptionText = new JLabel("Item Description");
		lblTradeQuoteItemDescriptionText.setFont(new Font("Georgia", Font.BOLD, 16));
		lblTradeQuoteItemDescriptionText.setBounds(318, 9, 202, 18);
		panelTradeQuoteLine.add(lblTradeQuoteItemDescriptionText);
		
		JLabel lblTradeQuoteItemCodeText = new JLabel("Item Code");
		lblTradeQuoteItemCodeText.setFont(new Font("Georgia", Font.BOLD, 16));
		lblTradeQuoteItemCodeText.setBounds(152, 9, 132, 18);
		panelTradeQuoteLine.add(lblTradeQuoteItemCodeText);
		
		JLabel lblTradeQuoteItemPriceText = new JLabel("Item Price");
		lblTradeQuoteItemPriceText.setFont(new Font("Georgia", Font.BOLD, 16));
		lblTradeQuoteItemPriceText.setBounds(678, 9, 105, 18);
		panelTradeQuoteLine.add(lblTradeQuoteItemPriceText);
		
		JLabel lblTradeQuoteItemQuantityText = new JLabel("Quantity");
		lblTradeQuoteItemQuantityText.setFont(new Font("Georgia", Font.BOLD, 16));
		lblTradeQuoteItemQuantityText.setBounds(828, 9, 75, 18);
		panelTradeQuoteLine.add(lblTradeQuoteItemQuantityText);
		
		txtTradeQuoteLineRep = new JTextField();
		txtTradeQuoteLineRep.setFont(new Font("Georgia", Font.PLAIN, 16));
		txtTradeQuoteLineRep.setBounds(34, 31, 86, 28);
		txtTradeQuoteLineRep.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelTradeQuoteLine.add(txtTradeQuoteLineRep);
		txtTradeQuoteLineRep.setColumns(10);
		
		txtTradeQuoteLineCode = new JTextField();
		txtTradeQuoteLineCode.setFont(new Font("Georgia", Font.PLAIN, 16));
		txtTradeQuoteLineCode.setEditable(false);
		txtTradeQuoteLineCode.setColumns(10);
		txtTradeQuoteLineCode.setBounds(152, 31, 132, 28);
		txtTradeQuoteLineCode.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelTradeQuoteLine.add(txtTradeQuoteLineCode);
		
		txtTradeQuoteLineDesc = new JTextField();
		txtTradeQuoteLineDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				// Disable the normal tab key focus on the Item Description textbox
				txtTradeQuoteLineDesc.setFocusTraversalKeysEnabled(false);
				
				// Uppercase the entered description
				txtTradeQuoteLineDesc.setText(txtTradeQuoteLineDesc.getText().toUpperCase());
				
				// Make ENTER button and the list of descriptions invisible
				panelTradeQuoteItemDescriptionChoice.setVisible(false);
				btnTradeQuoteNewOrderLine.setVisible(false);
				
				if (txtTradeQuoteLineDesc.getText().equals("") == false) {  
					// Search in database for items that have this exact description in database and if found enable qty textfield and fill in code and price
					try {

						// Find item based on it's description
						Item foundItem = getProductDao().findItemByDesc(txtTradeQuoteLineDesc.getText());

						if(foundItem.getItemCode() != null){
							txtTradeQuoteLineCode.setText(foundItem.getItemCode());
							txtTradeQuoteLinePrice.setText(Utilities.stringToDec(String.valueOf(foundItem.getTradePrice())));
							txtTradeQuoteLineCost = foundItem.getCostPrice();
							txtTradeQuoteLineQty.setEnabled(true);
						} else {
							txtTradeQuoteLineCode.setText("");
							txtTradeQuoteLinePrice.setText("");
							txtTradeQuoteLineQty.setText("");
							txtTradeQuoteLineQty.setEnabled(false);
							
							// Exact match not found so bring up list of descriptions that contain this description and are not set as deleted
							List<Item> itemList = getProductDao().findPossibleItemMatches(txtTradeQuoteLineDesc.getText().toUpperCase());
							
							if(itemList.size() != 0) {
								
								String columns[] = {"DESCRIPTION"};
								DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
								
								for(Item item : itemList){
									String tableDesc = item.getItemDescription();
									Object[] line = {tableDesc};
									tableModel.addRow(line);
								}
								
								tblTradeQuoteItemDescription.setModel(tableModel);
								tblTradeQuoteItemDescription.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
								TableColumnModel columnModel = tblTradeQuoteItemDescription.getColumnModel();
								columnModel.getColumn(0).setPreferredWidth(340);
								panelTradeQuoteItemDescriptionChoice.setVisible(true);
								
								// Set the tab key to the item description possible matches table
								if(arg0.getKeyCode() == KeyEvent.VK_TAB){
									tblTradeQuoteItemDescription.requestFocus();
								} 
								
							} else {
								txtTradeQuoteLineCode.setText("");
								txtTradeQuoteLinePrice.setText("");
								txtTradeQuoteLineQty.setText("");
								txtTradeQuoteLineQty.setEnabled(false);
							}
							
						}
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
				}else {
					// Make ENTER button and the list of descriptions invisible
					panelTradeQuoteItemDescriptionChoice.setVisible(false);
					btnTradeQuoteNewOrderLine.setVisible(false);
				}
			}
		});
		txtTradeQuoteLineDesc.setFont(new Font("Georgia", Font.PLAIN, 16));
		txtTradeQuoteLineDesc.setColumns(10);
		txtTradeQuoteLineDesc.setBounds(318, 31, 341, 28);
		txtTradeQuoteLineDesc.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelTradeQuoteLine.add(txtTradeQuoteLineDesc);
		
		txtTradeQuoteLinePrice = new JTextField();
		txtTradeQuoteLinePrice.setFont(new Font("Georgia", Font.PLAIN, 16));
		txtTradeQuoteLinePrice.setEditable(false);
		txtTradeQuoteLinePrice.setColumns(10);
		txtTradeQuoteLinePrice.setBounds(678, 31, 132, 28);
		txtTradeQuoteLinePrice.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelTradeQuoteLine.add(txtTradeQuoteLinePrice);
		
		txtTradeQuoteLineQty = new JTextField();
		txtTradeQuoteLineQty.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				panelTradeQuoteItemDescriptionChoice.setVisible(false);
				btnTradeQuoteNewOrderLine.setVisible(true);
			}
			
		});
		txtTradeQuoteLineQty.setFont(new Font("Georgia", Font.PLAIN, 16));
		txtTradeQuoteLineQty.setEnabled(false);
		txtTradeQuoteLineQty.setColumns(10);
		txtTradeQuoteLineQty.setBounds(828, 31, 57, 28);
		txtTradeQuoteLineQty.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		panelTradeQuoteLine.add(txtTradeQuoteLineQty);
		
		panelTradeQuoteItemDescriptionChoice = new JPanel();
		panelTradeQuoteItemDescriptionChoice.setBounds(350, 91, 338, 398);
		getContentPane().add(panelTradeQuoteItemDescriptionChoice);
		panelTradeQuoteItemDescriptionChoice.setLayout(null);
		
		JScrollPane scrlTradeQuoteItemDescription = new JScrollPane();
		scrlTradeQuoteItemDescription.setBounds(0, 0, 338, 398);
		panelTradeQuoteItemDescriptionChoice.add(scrlTradeQuoteItemDescription);
		
		tblTradeQuoteItemDescription = new JTable();
		tblTradeQuoteItemDescription.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// If enter pressed call a method to fill the orderline fields with this item, hide the panel with this table, set focus to qty and make ENTER visible 
				if(arg0.getKeyCode() == 10) {
					ordLineTradeItemSelected();					
				}
			}
		});
		tblTradeQuoteItemDescription.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ordLineTradeItemSelected();
			}
		});
		tblTradeQuoteItemDescription.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblTradeQuoteItemDescription.setCellSelectionEnabled(true);
		tblTradeQuoteItemDescription.setColumnSelectionAllowed(false);
		scrlTradeQuoteItemDescription.setViewportView(tblTradeQuoteItemDescription);
		panelTradeQuoteItemDescriptionChoice.setVisible(false);
		
		btnTradeQuoteNewOrderLine = new JButton("ENTER");
		btnTradeQuoteNewOrderLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// First check that the Rep is valid
				try {

					Staff checkStaff = getStaffDao().staffExists(txtTradeQuoteLineRep.getText());
					
					// If this repCode is okay
					if(checkStaff.getRepCode() != null) {
						txtTradeQuoteLineRep.setEditable(false);
						txtTradeQuoteLineRep.setEnabled(false);
						// Check if Quote exists and if not create one
						if(tradeQuote== null){
							tradeQuote= new TradeOrder();
							tradeQuote.setOrderDate(new Date());
							tradeQuote.setSaleType("TradeQuote");
							tradeQuote.setRepNo(txtTradeQuoteLineRep.getText());
						}

						try {

							// Check if an item is entered
							if(!txtTradeQuoteLineDesc.getText().isEmpty()) {
								// Check that the Quantity entered is an Integer and > 0.
								if(Integer.parseInt(txtTradeQuoteLineQty.getText()) > 0) {
															
									// Create a TradeQuoteLine and call the addTradeOrderLine() method to calculate it's values and save it to the arraylist  
									tradeQuoteLine = new TradeOrderLine();

									tradeQuoteLine.addTradeOrderLine(txtTradeQuoteLineCode, txtTradeQuoteLineDesc, txtTradeQuoteLineQty, txtTradeQuoteLinePrice, 
											lblTradeQuoteGrossProfitValue, lblTradeQuoteGrossProfitPercent, tradeQuote, txtTradeQuoteLineCost, getInvoiceSettings());
									
									// Clear the order textfield boxes and disable the quantity textfield and make the ENTER button invisible
									txtTradeQuoteLineCode.setText("");
									txtTradeQuoteLineDesc.setText("");
									txtTradeQuoteLinePrice.setText("");
									txtTradeQuoteLineQty.setText("");
									txtTradeQuoteLineQty.setEnabled(false);
									btnTradeQuoteNewOrderLine.setVisible(false);
										
									// Make the Trade: Current Quote, Print Quote, GrossProfit, Rounding, TradeDetails and New Quote panels visible
									panelTradeQuoteCurrentQuote.setVisible(true);
									panelTradeQuotePrintQuote.setVisible(true);
									panelTradeQuoteGrossProfit.setVisible(true);
									panelTradeQuoteRounding.setVisible(true);
									panelTradeQuoteCustDetails.setVisible(true);
									panelTradeQuoteNewQuote.setVisible(true);
										
									refreshCurrentOrderTable();
									
									// Set the focus back to the item description
									txtTradeQuoteLineDesc.requestFocus();
								} else {
									JOptionPane.showMessageDialog(null, "You must enter a Quantity greater than 0 !");
								}
							}else {
								JOptionPane.showMessageDialog(null, "You must enter an Item !");	
							}
						}catch(Exception ex){
							JOptionPane.showMessageDialog(null, "You must enter a number for the Quantity !" + ex);
						}
					}else{
						JOptionPane.showMessageDialog(null, "That Rep Code doesn't exist, please try again !");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnTradeQuoteNewOrderLine.setFont(new Font("Georgia", Font.BOLD, 16));
		btnTradeQuoteNewOrderLine.setBounds(964, 17, 167, 38);
		btnTradeQuoteNewOrderLine.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), UIManager.getColor("Button.darkShadow")));
		panelTradeQuoteLine.add(btnTradeQuoteNewOrderLine);
		
		panelTradeQuoteCurrentQuote = new JPanel();
		panelTradeQuoteCurrentQuote.setBackground(UIManager.getColor("List.dropLineColor"));
		panelTradeQuoteCurrentQuote.setBounds(28, 138, 800, 447);
		panelTradeQuoteCurrentQuote.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		getContentPane().add(panelTradeQuoteCurrentQuote);
		panelTradeQuoteCurrentQuote.setLayout(null);
		panelTradeQuoteCurrentQuote.setVisible(false);
		
		JLabel lblTradeQuoteCurrentTransaction = new JLabel("TRADE QUOTATION");
		lblTradeQuoteCurrentTransaction.setFont(new Font("Georgia", Font.BOLD, 18));
		lblTradeQuoteCurrentTransaction.setBounds(10, 11, 221, 31);
		panelTradeQuoteCurrentQuote.add(lblTradeQuoteCurrentTransaction);
		
		JLabel lblTradeQuoteTotalExVatText = new JLabel("TOTAL EX-VAT");
		lblTradeQuoteTotalExVatText.setFont(new Font("Georgia", Font.BOLD, 18));
		lblTradeQuoteTotalExVatText.setBounds(297, 11, 148, 31);
		panelTradeQuoteCurrentQuote.add(lblTradeQuoteTotalExVatText);
		
		JLabel lblTradeQuoteTotalPriceText = new JLabel("TOTAL INC-VAT");
		lblTradeQuoteTotalPriceText.setFont(new Font("Georgia", Font.BOLD, 18));
		lblTradeQuoteTotalPriceText.setBounds(634, 11, 156, 31);
		panelTradeQuoteCurrentQuote.add(lblTradeQuoteTotalPriceText);
		
		lblTradeQuoteTotalPrice = new JLabel("0.00", SwingConstants.RIGHT);
		lblTradeQuoteTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTradeQuoteTotalPrice.setBounds(634, 37, 156, 31);
		panelTradeQuoteCurrentQuote.add(lblTradeQuoteTotalPrice);
		
		lblTradeQuoteTotalExVat = new JLabel("0.00", SwingConstants.RIGHT);
		lblTradeQuoteTotalExVat.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTradeQuoteTotalExVat.setBounds(297, 37, 144, 31);
		panelTradeQuoteCurrentQuote.add(lblTradeQuoteTotalExVat);
		
		JScrollPane scrlTradeQuoteOrder = new JScrollPane();
		scrlTradeQuoteOrder.setBounds(10, 68, 780, 368);
		panelTradeQuoteCurrentQuote.add(scrlTradeQuoteOrder);
		
		tblTradeQuoteOrder = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public TableCellRenderer getCellRenderer( int row, int col ) { 
				TableCellRenderer renderer = super.getCellRenderer(row,col);
				if ( col == dataModel.getColumnCount() - 1  || col == dataModel.getColumnCount() - 2 || col == dataModel.getColumnCount() - 3) 
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
		tblTradeQuoteOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				if(tradeQuote.getOrderList().size() > 1) {
					// We use the table's getSelectedRow() method to find the row that the User selected. 
					int row = tblTradeQuoteOrder.getSelectedRow();
					
					// We use this row to find the element in the arraylist that the user selected
					tradeQuote.getOrderList().remove(row);
					
					// Recalculate everything for the Order
					tradeQuote.recalculateAfterDelete(getInvoiceSettings().getReceiptVatRate(), lblTradeQuoteGrossProfitValue, lblTradeQuoteGrossProfitPercent);
					
					// Load the current order lines onto the screen
					refreshCurrentOrderTable();
					
				} else{
					startNewQuote();
				}
			}
		});
		tblTradeQuoteOrder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblTradeQuoteOrder.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tblTradeQuoteOrder.setShowGrid(false);
		tblTradeQuoteOrder.setShowHorizontalLines(false);
		tblTradeQuoteOrder.setShowVerticalLines(false);
		tblTradeQuoteOrder.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		scrlTradeQuoteOrder.setViewportView(tblTradeQuoteOrder);
		
		panelTradeQuoteRounding = new JPanel();
		panelTradeQuoteRounding.setBackground(UIManager.getColor("List.dropLineColor"));
		panelTradeQuoteRounding.setBounds(855, 173, 164, 81);
		panelTradeQuoteRounding.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), UIManager.getColor("Button.darkShadow")));
		getContentPane().add(panelTradeQuoteRounding);
		panelTradeQuoteRounding.setLayout(null);
		panelTradeQuoteRounding.setVisible(false);
		
		JLabel lblTradeQuoteRoundText = new JLabel("Round");
		lblTradeQuoteRoundText.setFont(new Font("Georgia", Font.BOLD, 16));
		lblTradeQuoteRoundText.setBounds(55, 11, 55, 17);
		panelTradeQuoteRounding.add(lblTradeQuoteRoundText);
		
		JLabel lblTradeQuoteRoundUp = new JLabel("Round Up");
		lblTradeQuoteRoundUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String beforeRound = tradeQuote.getTotalPreRounding();
				int theDot = beforeRound.indexOf(".");
				String afterRound = Utilities.stringToDec(String.valueOf(Float.parseFloat(beforeRound.substring(0, theDot)) + 1));
				tradeQuote.setTotalPostRounding(afterRound);
				float rounding = Utilities.floatToNumDec(Float.parseFloat(afterRound) - Float.parseFloat(beforeRound),2);
				tradeQuote.setRounding(String.valueOf(rounding));

				// Save the rounding and post Rounding values if the invoice has been printed
				if(tradeQuote.getReceiptNo() != 0) {
					try {

						getOrderDao().updateRoundingHeader("quoteheader", rounding, tradeQuote.getTotalPostRounding(), tradeQuote.getReceiptNo());
	
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);					
					}
				}
						
				// Update the screen
				lblTradeQuoteTotalPrice.setText("\u20AC " + tradeQuote.getTotalPostRounding());

			}
		});
		lblTradeQuoteRoundUp.setBounds(36, 39, 35, 31);
		panelTradeQuoteRounding.add(lblTradeQuoteRoundUp);
		lblTradeQuoteRoundUp.setIcon(imageIconUpArrow);
		
		JLabel lblTradeQuoteRoundDown = new JLabel("Round Down");
		lblTradeQuoteRoundDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String beforeRound = tradeQuote.getTotalPreRounding();
				int theDot = beforeRound.indexOf(".");
				String afterRound = Utilities.stringToDec(beforeRound.substring(0, theDot));
				tradeQuote.setTotalPostRounding(afterRound);
				float rounding = Utilities.floatToNumDec(Float.parseFloat(afterRound) - Float.parseFloat(beforeRound),2);
				tradeQuote.setRounding(String.valueOf(rounding));

				// Save the rounding and post Rounding values if the invoice has been printed
				if(tradeQuote.getReceiptNo() != 0) {
					try {

						getOrderDao().updateRoundingHeader("quoteheader", rounding, tradeQuote.getTotalPostRounding(), tradeQuote.getReceiptNo());
	
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);					
					}
				}
						
				// Update the screen
				lblTradeQuoteTotalPrice.setText("\u20AC " + tradeQuote.getTotalPostRounding());
			}
		});
		lblTradeQuoteRoundDown.setBounds(92, 39, 35, 31);
		panelTradeQuoteRounding.add(lblTradeQuoteRoundDown);
		lblTradeQuoteRoundDown.setIcon(imageIconDownArrow);
		
		panelTradeQuoteGrossProfit = new JPanel();
		panelTradeQuoteGrossProfit.setBackground(UIManager.getColor("List.dropLineColor"));
		panelTradeQuoteGrossProfit.setBounds(855, 347, 163, 142);
		panelTradeQuoteGrossProfit.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), UIManager.getColor("Button.darkShadow")));
		getContentPane().add(panelTradeQuoteGrossProfit);
		panelTradeQuoteGrossProfit.setLayout(null);
		panelTradeQuoteGrossProfit.setVisible(false);
		
		JLabel lblTradeQuoteGrossProfitText = new JLabel("Gross Profit");
		lblTradeQuoteGrossProfitText.setFont(new Font("Georgia", Font.BOLD, 18));
		lblTradeQuoteGrossProfitText.setBounds(27, 23, 111, 24);
		panelTradeQuoteGrossProfit.add(lblTradeQuoteGrossProfitText);
		
		lblTradeQuoteGrossProfitValue = new JLabel("\u20AC 0.00", SwingConstants.RIGHT);
		lblTradeQuoteGrossProfitValue.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTradeQuoteGrossProfitValue.setBounds(25, 58, 102, 24);
		panelTradeQuoteGrossProfit.add(lblTradeQuoteGrossProfitValue);
		
		lblTradeQuoteGrossProfitPercent = new JLabel("0.00 %", SwingConstants.RIGHT);
		lblTradeQuoteGrossProfitPercent.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTradeQuoteGrossProfitPercent.setBounds(25, 92, 102, 24);
		panelTradeQuoteGrossProfit.add(lblTradeQuoteGrossProfitPercent);
		
		panelTradeQuoteNewQuote = new JPanel();
		panelTradeQuoteNewQuote.setBackground(UIManager.getColor("List.dropLineColor"));
		panelTradeQuoteNewQuote.setBounds(1046, 113, 199, 71);
		panelTradeQuoteNewQuote.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		getContentPane().add(panelTradeQuoteNewQuote);
		panelTradeQuoteNewQuote.setLayout(null);
		panelTradeQuoteNewQuote.setVisible(false);
		
		JButton btnTradeQuoteNewQuote = new JButton("NEW QUOTE");
		btnTradeQuoteNewQuote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				startNewQuote();
			}
		});
		btnTradeQuoteNewQuote.setFont(new Font("Georgia", Font.BOLD, 16));
		btnTradeQuoteNewQuote.setBounds(17, 17, 167, 38);
		btnTradeQuoteNewQuote.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), UIManager.getColor("Button.darkShadow")));
		panelTradeQuoteNewQuote.add(btnTradeQuoteNewQuote);
		
		panelTradeQuotePrintQuote = new JPanel();
		panelTradeQuotePrintQuote.setBackground(UIManager.getColor("List.dropLineColor"));
		panelTradeQuotePrintQuote.setBounds(1046, 204, 199, 71);
		panelTradeQuotePrintQuote.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		getContentPane().add(panelTradeQuotePrintQuote);
		panelTradeQuotePrintQuote.setLayout(null);
		panelTradeQuotePrintQuote.setVisible(false);
		
		JButton btnTradeQuotePrintQuote = new JButton("PRINT QUOTE");
		btnTradeQuotePrintQuote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				tradeQuote.setOrderLinesToPrint(0);
								
				try {
					tradeQuote.printOrder(getInvoiceSettings(), null, null, null);
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnTradeQuotePrintQuote.setFont(new Font("Georgia", Font.BOLD, 16));
		btnTradeQuotePrintQuote.setBounds(17, 17, 167, 38);
		btnTradeQuotePrintQuote.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), UIManager.getColor("Button.darkShadow")));
		panelTradeQuotePrintQuote.add(btnTradeQuotePrintQuote);
		
		panelTradeQuoteCustDetails = new JPanel();
		panelTradeQuoteCustDetails.setBackground(UIManager.getColor("List.dropLineColor"));
		panelTradeQuoteCustDetails.setBounds(1046, 295, 199, 71);
		panelTradeQuoteCustDetails.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(0, 0, 0), new Color(105, 105, 105)));
		getContentPane().add(panelTradeQuoteCustDetails);
		panelTradeQuoteCustDetails.setLayout(null);
		panelTradeQuoteCustDetails.setVisible(false);
		
		JButton btnTradeQuoteCustDetails = new JButton("CUST DETAILS");
		btnTradeQuoteCustDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CustomerDetails(tradeQuote);
			}
		});
		btnTradeQuoteCustDetails.setFont(new Font("Georgia", Font.BOLD, 16));
		btnTradeQuoteCustDetails.setBounds(17, 17, 167, 38);
		btnTradeQuoteCustDetails.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(160, 160, 160), new Color(0, 0, 0), UIManager.getColor("Button.darkShadow")));
		panelTradeQuoteCustDetails.add(btnTradeQuoteCustDetails);
		
		JLabel lblTradeQuoteLogo = new JLabel("New label");
		lblTradeQuoteLogo.setBounds(1046, 385, 199, 200);
		// Put Logo image on order tab by adding an image to the Logo Label
		lblTradeQuoteLogo.setIcon(imageIcon);
		getContentPane().add(lblTradeQuoteLogo);
	
	}
	
	
	/**
	 * This method takes the item selected from the item dropdown and fills textfields with it's values (code, description,price), it also 
	 * retrieves the item's cost price and enables the quantity field.
	 */
	public void ordLineTradeItemSelected() {
		try {
		
			// We use the table's getSelectedRow() method to find the row that the User selected. 
			int row = tblTradeQuoteItemDescription.getSelectedRow();
	
			// Then we can get the description of the selected row by getting the model of the table and then using the getValueAt() method we can get the specific
			// row and column that we require.
			// This returns an Object so we get the toString of it.
			String descString = tblTradeQuoteItemDescription.getModel().getValueAt(row, 0).toString();
			
			Item selectedItem = getProductDao().findItemByDesc(descString);
			
			if(selectedItem.getItemCode() != null){
				// Put selected item's code, description and price into the oderline entry fields and enable the quantity field and make the enter button visible. 
				txtTradeQuoteLineCode.setText(selectedItem.getItemCode());
				txtTradeQuoteLineDesc.setText(selectedItem.getItemDescription());
				txtTradeQuoteLinePrice.setText(Utilities.stringToDec( String.valueOf(selectedItem.getTradePrice()) ));
				txtTradeQuoteLineCost = selectedItem.getCostPrice();
				txtTradeQuoteLineQty.setEnabled(true);
			}
			
			txtTradeQuoteLineQty.requestFocus();
			panelTradeQuoteItemDescriptionChoice.setVisible(false);
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	

	/**
	 * This method loads the current trade quote lines into the current order table that the user sees on the Trade Quotation Screen.
	 */
	public void refreshCurrentOrderTable(){
			
		String columns[] = {"ITEM", "DESCRIPTION", "QTY", "PRICE", "TOTAL"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
		
		for(TradeOrderLine o : tradeQuote.getOrderList()){
			String tableCode= o.getItemCode();
			String tableDesc = o.getItemDescription();
			String tableQty = o.getOrderQty();
			String tableItemPrice = o.getItemPrice();
			String tableOrderPrice = o.getValueExVat();
			Object[] line = {tableCode, tableDesc, tableQty, tableItemPrice, tableOrderPrice};
			tableModel.addRow(line);
		}
		tblTradeQuoteOrder.setModel(tableModel);
		lblTradeQuoteTotalPrice.setText("\u20AC " + String.valueOf(tradeQuote.getTotalPreRounding()));
		lblTradeQuoteTotalExVat.setText("\u20AC " + tradeQuote.getTotalExVat());
	
	
		// Set the column widths for the table
		tblTradeQuoteOrder.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		columnModelOrder = tblTradeQuoteOrder.getColumnModel();
		columnModelOrder.getColumn(0).setPreferredWidth(90);
		columnModelOrder.getColumn(1).setPreferredWidth(479);
		columnModelOrder.getColumn(2).setPreferredWidth(40);
		columnModelOrder.getColumn(3).setPreferredWidth(70);	
		columnModelOrder.getColumn(4).setPreferredWidth(83);
	}
		
	
	/**
	 * This method removes the current Trade Quote, clears all of the screen's textfields and hides panels so as to start a new Trade Quote
	 */
	public void startNewQuote(){
		
		// Make the current Current Order panel, Print Order panel and the GrossProfit panel invisible
		panelTradeQuoteCurrentQuote.setVisible(false);
		panelTradeQuotePrintQuote.setVisible(false);
		panelTradeQuoteGrossProfit.setVisible(false);
		
		// Remove the current quote from the order object
		tradeQuote= null;
		
		// Blank the current order line entry fields
		txtTradeQuoteLineCode.setText(null);
		txtTradeQuoteLineDesc.setText(null);
		txtTradeQuoteLinePrice.setText(null);
		txtTradeQuoteLineQty.setText(null);
		
		// Disable the quantity field and make ENTER button invisible - because they may be accessible when NEW ORDER button pressed 
		txtTradeQuoteLineQty.setEnabled(false);
		btnTradeQuoteNewOrderLine.setVisible(false);
		
		// Make the Current Order, Print Order, GrossProfit, Rounding, CustDetails and NewOrder panels invisible
		panelTradeQuoteCurrentQuote.setVisible(false);
		panelTradeQuotePrintQuote.setVisible(false);
		panelTradeQuoteGrossProfit.setVisible(false);
		panelTradeQuoteRounding.setVisible(false);
		panelTradeQuoteCustDetails.setVisible(false);
		panelTradeQuoteNewQuote.setVisible(false);
		
		// Blank the Rep Code field, enable it and make it editable
		txtTradeQuoteLineRep.setEditable(true);
		txtTradeQuoteLineRep.setEnabled(true);
		txtTradeQuoteLineRep.setText("");
	}
	
}