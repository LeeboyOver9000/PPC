package conta;

public class Cliente 
{
	private Banco banco;
	private int tipo;
	
	public Cliente(Banco banco, int tipo) 
	{
		this.banco = banco;
		this.tipo = tipo;
	}

	public Banco getBanco() { return banco; }
	public void setBanco(Banco banco) { this.banco = banco; }
	public int getTipo() { return tipo; }
	public void setTipo(int tipo) { this.tipo = tipo; }
}
