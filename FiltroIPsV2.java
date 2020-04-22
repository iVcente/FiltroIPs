import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

// Codigo da Clara

public class FiltroIPsV2{
    private static ArrayList<Intervalo> listaIPsOriginal = new ArrayList<Intervalo>();
    private static Timestamp inicioExecucao;
    private static Timestamp fimExecucao;

    public static void main(String[] args){
        System.out.println("\n--> Lista de IPs original <--");
        System.out.println("\n=> Iniciando leitura do arquivo: \n");
        leArquivo(listaIPsOriginal);
        System.out.println("\n=> Leitura do arquivo de arquivo finalizada. \n");
        Collections.sort(listaIPsOriginal);
        System.out.println("\n--> Lista ordenada <-- \n");
        System.out.println(listaIPsOriginal);
        System.out.println("\n-> Inicio da execucao: " + inicioExecucao);
        System.out.println("-> Fim da execucao:    " + fimExecucao);
        System.out.println("\n--> Lista de IPs filtrados <--\n");
        System.out.println(filtraIPs(listaIPsOriginal));
        System.out.print("\nTamanho da lista: ");
        System.out.println(filtraIPs(listaIPsOriginal).size());
    }

    public static List<Intervalo> filtraIPs(ArrayList<Intervalo> listaIPsOriginal){
        List <Intervalo> ipsFiltrados = new ArrayList<Intervalo>();
        Intervalo proximoIntervalo = listaIPsOriginal.get(0);

        for (int i = 1; i < listaIPsOriginal.size(); i++){
            Intervalo intervaloAtual = listaIPsOriginal.get(i);

            if (intervaloAtual.getMin() <= proximoIntervalo.getMax()){
                if (intervaloAtual.getMax() > proximoIntervalo.getMax())
                    proximoIntervalo.setMax(intervaloAtual.getMax());
            }
            else{
                ipsFiltrados.add(proximoIntervalo);
                proximoIntervalo = intervaloAtual;
            }

            if (i == listaIPsOriginal.size() - 1)
                ipsFiltrados.add(proximoIntervalo);
        }

        return ipsFiltrados;
    }

    public static void leArquivo(List<Intervalo> listaIPsOriginal) {

        inicioExecucao = new Timestamp(System.currentTimeMillis());

        try (BufferedReader br = new BufferedReader(new FileReader("testeJB.txt"))){
            String leitura;

            while ((leitura = br.readLine()) != null) {
                String[] valores = leitura.split("-");            
                Intervalo inter = new Intervalo(Integer.parseInt(valores[0]), Integer.parseInt(valores[1]));                       
                listaIPsOriginal.add(inter);
            }

            System.out.println(listaIPsOriginal.toString());
            System.out.println(listaIPsOriginal.get(0));
            br.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        fimExecucao = new Timestamp(System.currentTimeMillis());
        System.out.println(listaIPsOriginal.size());
        
    }
}