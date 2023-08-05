import java.util.Scanner;

class Char {

	private char valor;
	private int prioridade;

	public char getValor() {
		return valor;
	}

	public Char() {
		valor = '0';
		prioridade = 0;
	}

	public Char(char caracter) {
		valor = caracter;
		setPrioridade(caracter);
	}

	public void setPrioridade(char caracter) {

		if ((caracter == '*') || (caracter == '/')) {
			prioridade = 3;
		}

		else if ((caracter == '+') || (caracter == '-')) {
			prioridade = 2;
		}

		else if (caracter == '(') {
			prioridade = 1;
		}
	}

	public int getPrioridade() {
		return prioridade;

	}

}

class Celula {

	private Char item;
	private Celula proximo;

	public Celula(Char novo) {
		item = novo;
		proximo = null;
	}

	public Celula() {

		item = new Char();
		proximo = null;
	}

	public Char getItem() {
		return item;
	}

	public void setItem(Char item) {
		this.item = item;
	}

	public Celula getProximo() {
		return proximo;
	}

	public void setProximo(Celula proximo) {
		this.proximo = proximo;
	}
}

class Pilha {

	private Celula fundo;
	private Celula topo;

	public Pilha() {

		Celula sentinela;

		sentinela = new Celula();
		fundo = sentinela;
		topo = sentinela;
	}

	public boolean pilhaVazia() {

		boolean resp;

		if (topo == fundo)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public void empilhar(Char novo) {

		Celula novaCelula;

		novaCelula = new Celula(novo);
		novaCelula.setProximo(topo);
		topo = novaCelula;
	}

	public Char desempilhar() throws Exception {

		Celula desempilhado;

		if (!pilhaVazia()) {
			desempilhado = topo;
			topo = topo.getProximo();
			desempilhado.setProximo(null);
			return (desempilhado.getItem());
		} else
			throw new Exception("Não foi possível desempilhar: a pilha está vazia!");
	}

	public Char consultarTopo() {

		if (!pilhaVazia()) {
			return (topo.getItem());
		} else
			return null;
	}
}

class ExpressaoMatematica {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String string = scan.nextLine();
		
		while (!string.equals("FIM")) {

			Pilha minhaPilha;
			Char novo;
			Char topo;
			Char desempilhado;
			char c;

			minhaPilha = new Pilha();
            string=string.replace(" ", "");
			for (int i = 0; i < string.length(); i++) {
				c = string.charAt(i);
				if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
					System.out.print(c + " ");
				}
				else {
				novo= new Char(c);
				try {
				if(c=='(') {
					minhaPilha.empilhar(novo);				
				}
				else 
				if (c==')')	{
				while (minhaPilha.consultarTopo()!=null && minhaPilha.consultarTopo().getValor()!='(') {
				System.out.print(minhaPilha.desempilhar().getValor() + " ");
				}
				minhaPilha.desempilhar();
				}
				else {
				while (minhaPilha.consultarTopo()!=null && minhaPilha.consultarTopo().getPrioridade()>=novo.getPrioridade()) {
				System.out.print(minhaPilha.desempilhar().getValor() + " ");	
				}
				minhaPilha.empilhar(novo);
				}
				}	
				catch(Exception e) {
				e.printStackTrace();	
				}
				}
					                
				}
			try {
			while (!minhaPilha.pilhaVazia()) {
			System.out.print(minhaPilha.desempilhar().getValor() + " ");		
			}
			System.out.println();
			}
			catch(Exception e) {
			e.printStackTrace();
			}
			string = scan.nextLine();
			}
		scan.close();
		
		}
	   System.out.print("Aqui é Galo");
	   System.out.print("Aqui é nois");
	}

