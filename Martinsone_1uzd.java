import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class Martinsone_1uzd {
	
	//Iegūst Desktopa adresi 
	static File darbvirsma = FileSystemView.getFileSystemView().getHomeDirectory();
	static String atrasanasVieta = darbvirsma.getAbsolutePath();
	
	//Metodes, kas veic MB konvertāciju uz attiecīgajām vienībām.
	static void MBtoBits(int MB) {
	long longMB = MB;
	long bits = longMB*8388608;
	String teksts = ""+MB+" MB = "+bits+" b";
	rezultats(teksts);	
}

	static void MBtoBytes(int MB) {
	long longMB = MB;
	long Bytes = longMB*1048576;
	String teksts = ""+MB+" MB = "+Bytes+" B";
	rezultats(teksts);	
	}
	
	static void MBtoKb(int MB) {
	long longMB = MB;
	long kb = longMB*1024;
	String teksts = ""+MB+" MB = "+kb+" kb";
	rezultats(teksts);
	}
	
	static void MBtoGB(int MB) {
	double dMB = MB;
	double gb = dMB/1024;
	String teksts = ""+MB+" MB = "+gb+" GB";
	rezultats(teksts);
	}
	
	static void MBtoTB(int MB) {
	double dMB = MB;
	double tb = dMB/1048576;
	String teksts = ""+MB+" MB = "+tb+" TB";
	rezultats(teksts);
	}
	
	static void rezultats(String teksts) {
	//rezultāta izvade uz ekrāna
	JOptionPane.showMessageDialog(null, teksts, "REZULTĀTS",
				 JOptionPane.INFORMATION_MESSAGE);
	
	//rezultāta saglabāšana failā uz Desktopa, ar iespēju ievadīt nosaukumu.
	//ievadot jau esošu faila nosaukumu, tā teksts tiek papildināts.
	String fNosaukums = JOptionPane.showInputDialog("Ievadi faila nosaukumu,\nkur saglabāt rezultātu");
	try {
	FileWriter fw = new FileWriter(new File(atrasanasVieta, fNosaukums+".txt"), true);
	PrintWriter pw = new PrintWriter(fw);
	pw.println(teksts);
	pw.close();
	JOptionPane.showMessageDialog(null, "Teksts saglabāts uz darbvirsmas datnē \n"+fNosaukums, "Paziņojums",
			JOptionPane.INFORMATION_MESSAGE);
	
	//Izvēle, vai lietotājam aplūkot saglabātā faila saturu.
	String [] opcijas = {"Jā", "Nē"};
	String izvele;
	String adrese = atrasanasVieta+"\\"+fNosaukums+".txt";
		
	izvele = (String)JOptionPane.showInputDialog(null, "Vai vēlies apskatīt failā "+fNosaukums+" saglabātās vērtības?",
				"Darbības izvēle",
				JOptionPane.QUESTION_MESSAGE, null, opcijas, opcijas[0]);
	switch(izvele) {
	case "Jā":
		nolasit(adrese);
		break;
	case "Nē":
		break;
	default:
		break;
	}
	}catch(Exception e) {
		JOptionPane.showMessageDialog(null, "Kļūda ierakstot failā!",
				"Kļūda", JOptionPane.ERROR_MESSAGE);
	}
	}
	//Metode nolasa saglabātā faila saturu.
	static void nolasit(String adrese) {
		String nolasitais="";
		try {
			FileReader fr = new FileReader(
					adrese);
			BufferedReader br = new BufferedReader(fr);
			String teksts;
			while ((teksts = br.readLine()) !=null) {
				nolasitais += teksts+"\n";
			}
			br.close();
			JOptionPane.showMessageDialog(null, nolasitais, "Saturs",
					JOptionPane.INFORMATION_MESSAGE);
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Kļūda nolasot failu!",
					"Kļūda", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public static void main(String[] args) {
		int MB=0;
		String ievadeMB = "";
		String mervieniba = "";
		
				do {
				
				ievadeMB =(String)JOptionPane.showInputDialog(null, 
					"Ievadi SSD diska ietilpību MB!\nLai beigtu darbu, ievadi ^^", "Minimums 1GB(1024 MB)");
				if(ievadeMB==null || ievadeMB.equalsIgnoreCase("^^"))  
					break;
				else
					try {
						MB = Integer.parseInt(ievadeMB);
						}catch(NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Kļūda ievadē!", "Kļūda", 
									JOptionPane.WARNING_MESSAGE);
						}
				
				if(MB>=1024) 
				mervieniba =(String)JOptionPane.showInputDialog(null, 
					"Uz kādu mērvienību konvertēt?\n"
					+ "|  bits  |  baits  |  kb  |  gb  |  tb  |\n(Lai beigtu darbu, ievadi ^^)");
				if(mervieniba==null || mervieniba.equalsIgnoreCase("^^"))
					break;
				mervieniba = mervieniba.toLowerCase();
				
				switch(mervieniba) {
				case "bits":
					MBtoBits(MB);
					break;
				case "baits":
					MBtoBytes(MB);
					break;
				case "kb":
					MBtoKb(MB);
					break;
				case "gb":
					MBtoGB(MB);
					break;
				case "tb":
					MBtoTB(MB);
					break;
				default:
						JOptionPane.showMessageDialog(null, "Kļūda ievadē!", "Kļūda", 
								JOptionPane.WARNING_MESSAGE);
						
				}
				}while(!ievadeMB.equalsIgnoreCase("^^") && !mervieniba.equalsIgnoreCase("^^"));	
		}
	}

			
	
