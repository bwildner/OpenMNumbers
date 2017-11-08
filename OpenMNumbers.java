import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class OpenMNumbers {

	public static void main(String[] args) throws IOException {
	//String pfadmnumbers ="\\\\n0204\\dateien"; //Hier liegen die MNumber Verzeichnisse
	String pfadmnumbers ="\\\\Heller.biz\\hnt\\Steuerungstechnik\\Projects-Machines\\M-Numbers"; //Hier liegen die MNumber Verzeichnisse
// \\Heller.biz\hnt\Steuerungstechnik\Projects-Machines\M-Numbers\
	//Eingabe
	Eingabe eingabe = new Eingabe();
	String nummer = eingabe.eingabe();

	//Verarbeitung
	String teilnummer = nummer.substring(0, 2); //nur die vorderen 2 Chars für den Zwischenpfad nehmen
	String pfad=pfadmnumbers+"\\M"+teilnummer+"xxx"; // ZwischenPfad zusammenbauen (M52xxx)
	System.out.println(pfad);

	//Suchen nach dem Endpfad
	Suchen suchen = new Suchen();
	String endpfad = suchen.verz(pfad, nummer);
	System.out.println("Pfad für Ausgabe: "+endpfad);


	//Ausgabe
	Ausgabe aus = new Ausgabe();
	aus.ausgabe(endpfad);
	}
}



class Suchen{

	String fullpath = null;
	String rueckgabe = null;
	String verz(String pfad, String MNummer){
	File temp= new File(pfad); // Pfad für Suche zusammenbauen

	File[] tempInhalt = temp.listFiles(); //Files im Pfad auslesen
//	System.out.println("Inhalt: "+ temp.getAbsolutePath());
	if (tempInhalt==null)
		System.exit(0);



	for(int i=0;i<tempInhalt.length;i++){
		//System.out.println( tempInhalt[i].toString());
		if (tempInhalt[i].isDirectory()){
			//System.out.println("ist Verzeichniss");
			String verzname=tempInhalt[i].toString();
			if (verzname.contains(MNummer)){
				System.out.print("hier ist das gewünschte Vezeichniss: ");
				String fullpath = tempInhalt[i].getPath();
				System.out.println(fullpath);
				rueckgabe = fullpath;

			}
		}
	}

	return rueckgabe;
	}
}




class Ausgabe{
	void ausgabe(String pfad) throws IOException{

		String pfad2= 	"explorer.exe \"" + pfad+"\"";
		Runtime.getRuntime().exec(pfad2);
	}
}

class Eingabe {
	//Hier nur Eingabe , Rückgabewert ist ein String "45123"
	String eingabe(){
		String eingabe1 = null;
		int fehler;
		do{
			try{
		//	  addWindowListener(new WindowClosingAdapter());
			  eingabe1 = JOptionPane.showInputDialog("Bitte MNummer ohne M eingeben: ");
			  if (eingabe1 == null){
				  System.exit(0);
			  }

			  int eing1 = new Integer(eingabe1).intValue();
			  fehler=0;
		  }
		  catch(NumberFormatException e1) {
			  String ausgabe = "Nur Zahlen bitte!!";
			  JOptionPane.showMessageDialog(null, ausgabe , "Antwort", JOptionPane.INFORMATION_MESSAGE);
			  fehler=1;
		  }

	  }		  while (fehler==1);


	return eingabe1;
	}
}
