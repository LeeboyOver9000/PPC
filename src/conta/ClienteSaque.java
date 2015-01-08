package conta;

public class ClienteSaque extends Cliente implements Runnable
{
	private int valor;
	private int position;
	private boolean execute;
	
	public ClienteSaque(Banco banco, int tipo) 
	{
		super(banco, tipo);
		banco.entrarNaFila(this);
		position = banco.getFilaTamanho();
	}
	
	@Override
	public void run() 
	{
		execute = true;
		valor = getTipo() * 100;
		
		while( execute ) {
			getBanco().sacar(valor);
		}
		
		getBanco().sairDaFila();
	}
	
	public void stopExecuting() {
		execute = false;
	}
	
	public int getValor() { return valor; }
	public int getPosition() { return position; }
	public void setPosition(int position) { this.position = position; }
}
