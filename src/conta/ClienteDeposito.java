package conta;

public class ClienteDeposito extends Cliente implements Runnable 
{	
	public ClienteDeposito(Banco banco, int tipo) {
		super(banco, tipo);
	}
	
	@Override
	public void run() 
	{
		try 
		{
			int valor = getTipo() * 100;
			getBanco().depositar(valor);
		} 
		catch (InterruptedException e) {
			System.err.println( e.getMessage() );
		}
	}
}
