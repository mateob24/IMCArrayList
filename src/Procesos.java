import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Procesos {
	ArrayList<String> nombres;
	ArrayList<String> conclusiones;
	ArrayList<Integer> telefonos;
	ArrayList<Double> alturas;
	ArrayList<Double> pesos;
	ArrayList<Double> imc;
	
	public Procesos() {
		 nombres=new ArrayList<String>();
		 conclusiones=new ArrayList<String>();
		 telefonos=new ArrayList<Integer>();
		 alturas=new ArrayList<Double>();
		 pesos=new ArrayList<Double>();
		 imc=new ArrayList<Double>();
	}
	
	public void inicia() {
		String menu="ENTRADA A MENÚ IMC\n";
		menu+="1. Registrar datos\n";
		menu+="2. Imprimir lista de datos\n";
		menu+="3. Consulta individual\n";
		menu+="4. Eliminar persona\n";
		menu+="5. Actualizar\n";
		menu+="6. Vaciar lista\n";
		menu+="7. Salir\n";
		menu+="Ingrese una opción:\n";
		
		int opc=0;
		do {
			opc=(int)validarNumero(menu);
			
			validarMenu(opc);
		} while (opc!=7);
	}
	
//------------------------------------------------------------------------------------------------
	
	public void validarMenu(int opc) {
		switch (opc) {
		case 1:
			ingresarDatos();
			calcularImc();//SE DEBE LLAMAR ESTE MÉTODO PARA QUE
			break;
			
		case 2:
			if(validarArreglo()) {
				imprimirDatos();
			}
			break;

		case 3:
			if(validarArreglo()) {
				buscarPersona();
			}
			break;
			
		case 4:
			if(validarArreglo()) {
				eliminarPersona();
			}
			break;
			
		case 5:
			if(validarArreglo()) {
				actualizarPersona();
			}
			break;

			
		case 6:
			if(validarArreglo()) {
				vaciarLista();
			}
			break;
			
		case 7: JOptionPane.showMessageDialog(null,"Cerrando",
				"Cierre del sistema",JOptionPane.CLOSED_OPTION);
			break;
		default:System.out.println("Ingrese una opción válida");
			break;
		}
	}
	
	public boolean validarArreglo() {
		System.out.println(nombres);
		if (nombres!=null) {
			return true;
		}else {
			System.out.println("Debe primero ingresar datos");
			return false;
		}
	}
	
	public double validarNumero(String mensaje) {
		double dato;
		do {
			dato=Double.parseDouble(JOptionPane.
					showInputDialog(mensaje));
			if (dato<0) {
				System.out.println("Ingrese un valor que NO sea negativo");
			}
			
		} while (dato<0);
		
		return dato;
	}
	
//------------------------------------------------------------------------------------------------
	
	public void ingresarDatos() {
		int numPerso=(int) validarNumero("Ingrese el número de personas a calcular IMC");
		String name="";
		int tel=0;
		double alt=0;
		double pso=0;
		
		for (int i = 0; i < numPerso; i++) {
			name=JOptionPane.showInputDialog("Ingrese el nombre de la persona "+(i+1));
			nombres.add(name);
			tel=(int) validarNumero("Ingrese el número de teléfono de "+name);
			telefonos.add(tel);
			alt=validarNumero("Ingrese la altura en metros de "+name);
			alturas.add(alt);
			pso=validarNumero("Ingrese el peso en kilogramos de "+name);
			pesos.add(pso);
		}
		
	}
	
//------------------------------------------------------------------------------------------------

	//public ArrayList<Double> calcularImc() --> para devolver el ArrayList
	public void calcularImc() {
		double indiceMC=0;
		String result="";
		for (int i = 0; i < alturas.size(); i++) {
			indiceMC=pesos.get(i)/(alturas.get(i)*alturas.get(i));
			imc.add(indiceMC);
			
			if (imc.get(i)<18) {
				result=("Anorexia");
			}else if (imc.get(i)>=18 && imc.get(i)<20) {
				result=("Delgadez");
			}else if (imc.get(i)>=20 && imc.get(i)<27) {
				result=("Normalidad");
			}else if (imc.get(i)>=27 && imc.get(i)<30) {
				result=("Obesidad 1");
			}else if (imc.get(i)>=30 && imc.get(i)<35) {
				result=("Obesidad 2");
			}else if (imc.get(i)>=35 && imc.get(i)<40) {
				result=("Obesidad 3");
			}else if (imc.get(i)>=40) {
				result=("Obesidad Morbida");
			}
			
			conclusiones.add(result);
		}
	}
	
//------------------------------------------------------------------------------------------------

	public void imprimirDatos() {
		System.out.println("\nLISTA DE PERSONAS");	
//		System.out.println(nombres);
//		System.out.println(telefonos);
//		System.out.println(imc);
		for (int i = 0; i < nombres.size(); i++) {
			System.out.println("Nombre: "+nombres.get(i)+" -> Telefono: "+telefonos.get(i)+" -> IMC: "+imc.get(i)+" -> CONCLUSIÓN: "+conclusiones.get(i));
		}
	}
	
//------------------------------------------------------------------------------------------------
	
	public void buscarPersona() {
		System.out.println("\nBÚSQUEDA POR PERSONA");
		int cont=0;
		String persoBuscar=JOptionPane.showInputDialog("Ingrese el nombre de la persona a buscar");
		
		for (int i = 0; i < nombres.size(); i++) {
			//USAR Y MODIFICAR LA CONDICION AL 'CONTAINS'
			if (nombres.get(i).equalsIgnoreCase(persoBuscar)) {
				System.out.println("Con el nombre ingresado se encontró a la persona "+nombres.get(i)+
						" con el número de telefono "+telefonos.get(i)+" en la posición "+i);
				cont++;
			}
		}
		
		if(cont>0) {
			System.out.println("Se encontró el nombre "+persoBuscar+" en la lista un total de "+cont+" veces.");
		}else {
			System.out.println("No se ha registrado una persona con el nombre "+persoBuscar+" en la lista.");
		}
	}
	
//------------------------------------------------------------------------------------------------

	public void eliminarPersona() {
		System.out.println("\nELIMINAR PERSONA");
		String persoEliminar=JOptionPane.showInputDialog("Ingrese el nombre de la persona que desea eliminar");

		for (int i = 0; i < nombres.size(); i++) {
			if (nombres.get(i).equalsIgnoreCase(persoEliminar)) { 	
				nombres.remove(i);
				JOptionPane.showMessageDialog(null, "Se eliminó la persona " +persoEliminar);
			}
		}
	}
	
//------------------------------------------------------------------------------------------------

	public void vaciarLista() {
		System.out.println("\nVACIAR LISTA");
		nombres.clear();
		telefonos.clear();
		alturas.clear();
		pesos.clear();
		conclusiones.clear();
		JOptionPane.showMessageDialog(null, "LA LISTA FUE VACIADA");
	}
	
//------------------------------------------------------------------------------------------------
	
	public void actualizarPersona() {
		System.out.println("\nACTUALIZAR PERSONA");
		String persoActualizar=JOptionPane.showInputDialog("Ingrese el nombre de la persona que desea actualizar");

		for (int i = 0; i < nombres.size(); i++) {
			if (nombres.get(i).equalsIgnoreCase(persoActualizar)) { 	
				
				nombres.set(i, "Pedro");
				
				nombres.remove(i);
				telefonos.remove(i);
				alturas.remove(i);
				pesos.remove(i);
				imc.clear();
				conclusiones.clear();
				
				imprimirListasNueva();
				
				String name=JOptionPane.showInputDialog("Ingrese el nombre de la nueva persona de la lista");
				nombres.add(i, name);
				int tel=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el teléfono de "+name));
				telefonos.add(i, tel);
				double alt=Double.parseDouble(JOptionPane.showInputDialog("Ingrese la altura en metros de "+name));
				alturas.add(i, alt);
				double pso=Double.parseDouble(JOptionPane.showInputDialog("Ingrese el peso en kilogramos de "+name));
				pesos.add(i, pso);
				calcularImc();
				JOptionPane.showMessageDialog(null, "Se actualizó la persona " +persoActualizar+" por "+name);
				System.out.println();
				
				imprimirListasNueva();
			}
		}
	

}
	private void imprimirListasNueva() {
		System.out.println(nombres);
		System.out.println(telefonos);
		System.out.println(alturas);
		System.out.println(pesos);
		System.out.println(imc);
		System.out.println(conclusiones);
	}
}
