package conta;

import java.util.Iterator;
import java.util.Queue;

public class Banco 
{
	private int saldo;
	private Queue<ClienteSaque> fila;
	
	public Banco(Queue<ClienteSaque> fila) {
		this.fila = fila;
	}	
	
	public synchronized void entrarNaFila(ClienteSaque cliente) { fila.add(cliente); }
	
	public synchronized void sairDaFila()
	{
		fila.remove();
		Iterator<ClienteSaque> it = fila.iterator();
		
		while( it.hasNext() )
		{
			ClienteSaque temp = it.next();
			int newPositon = temp.getPosition();
			temp.setPosition(newPositon-1);
		}
	}
	
	public synchronized void sacar(int valor) 
	{	
		if( !fila.isEmpty() )
		{					
			ClienteSaque cliente = fila.peek();
			
			try 
			{	
				if( saldo >= valor )
				{																		
					saldo -= valor;
					Thread.sleep(2000);
					System.out.println("Saque R$ " + valor);
					System.out.println("Saldo atual do banco: " + getSaldo());
					cliente.stopExecuting();
					notifyAll();
				}
				else {
					wait();
				}	
			}
			catch (InterruptedException e) {
				System.err.println( e.getMessage() );
			}
		}
	}
	
	public synchronized void depositar(int valor) throws InterruptedException
	{
		System.out.println("Deposito R$ " + valor);
		saldo += valor;
		Thread.sleep(2000);
		System.out.println("Saldo atual do banco: " + getSaldo());
		notifyAll();
	}
	
	public int getSaldo() { return saldo; }	
	public int tamanhoDaFila() { return fila.size(); }
	public int getFilaTamanho() { return fila.size(); }
}
