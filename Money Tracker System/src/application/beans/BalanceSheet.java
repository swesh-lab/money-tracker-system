package application.beans;
import java.sql.Date;
import java.sql.Time;

public class BalanceSheet {

	private Date transactionDate;
	private Time transactionTime;
	private float expenseAmount;
	private int expenseID;
	private String expenseNote;
	private float depositAmount;
	private int depositID;
	private float balanceAmount;

	private String defaultValue = "-";
	
	public BalanceSheet() {
		
	}

	public BalanceSheet(Date transactionDate, Time transactionTime, float expenseAmount, int expenseID, String expenseNote, float depositAmount, int depositID, float balanceAmount) {
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.expenseAmount = expenseAmount;
		this.expenseID = expenseID;
		this.expenseNote = expenseNote;
		this.depositAmount = depositAmount;
		this.depositID = depositID;
		this.balanceAmount = balanceAmount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Time getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Time transactionTime) {
		this.transactionTime = transactionTime;
	}

	public float getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(float expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public int getExpenseID() {
		return expenseID;
	}

	public void setExpenseID(int expenseID) {
		this.expenseID = expenseID;
	}

	public String getExpenseNote() {
		return expenseNote;
	}

	public void setExpenseNote(String expenseNote) {
		this.expenseNote = expenseNote;
	}

	public float getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(float depositAmount) {
		this.depositAmount = depositAmount;
	}

	public int getDepositID() {
		return depositID;
	}

	public void setDepositID(int depositID) {
		this.depositID = depositID;
	}

	public float getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(float balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	
}
