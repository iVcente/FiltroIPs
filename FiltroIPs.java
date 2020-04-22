import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class FiltroIPs{
    private static List<Intervalo> listaIPsOriginal = new ArrayList<Intervalo>();
    private static Timestamp inicioExecucao;
    private static Timestamp fimExecucao;
    private static int tamanhoListaOriginal;
    private static int tamanhoListaFiltrada;

    public static void main(String[] args){
        System.out.println("\n=> Iniciando leitura do arquivo: \n");
        System.out.println("\t \t   ...");
        leArquivo(listaIPsOriginal);
        System.out.println("\n=> Leitura do arquivo de arquivo finalizada. \n");
        System.out.println("\n=> Iniciando ordenacao da lista: \n");
        System.out.println("\t \t   ...");
        Collections.sort(listaIPsOriginal);
        System.out.println("\n=> Ordenacao da lista finalizada. \n");
        System.out.println("\n--> Lista de IPs filtrada com sucesso <--\n");
        System.out.println(filtraIPs(listaIPsOriginal));
        System.out.print("\n-> Tamanho da lista original: ");
        System.out.println(tamanhoListaOriginal);
        System.out.print("\n-> Tamanho da lista filtrada: ");
        System.out.println(tamanhoListaFiltrada);
        System.out.println("\n-> Inicio da execucao: " + inicioExecucao);
        System.out.println("-> Fim da execucao:    " + fimExecucao + "\n");
    }

    public static List<Intervalo> filtraIPs(List<Intervalo> listaIPsOriginal){
        List<Intervalo> listaIPsFiltrada = new ArrayList<Intervalo>();
        
        Intervalo intervaloAtual;
        Intervalo proximoIntervalo;
        Intervalo aux = listaIPsOriginal.get(0);

        for (int i = 0; i < listaIPsOriginal.size() - 1; i++){
            
            intervaloAtual = aux;
            proximoIntervalo = listaIPsOriginal.get(i+1);
            
            if(intervaloAtual.getMax() < proximoIntervalo.getMin()){ // Caso nenhum limite do próximoIntervalo esteja no intervaloAtual
                listaIPsFiltrada.add(aux);
                aux = proximoIntervalo;
            }
            else if (intervaloAtual.getMax() > proximoIntervalo.getMax()){ // Caso o próximoIntervalo esteja totalmente contido dentro do intervaloAtual
                    aux = intervaloAtual;
            }
            else{
                // else if (intervaloAtual.getMax() <= proximoIntervalo.getMax()) // // Caso apenas o limite mínimo do próximoIntervalo esteja contido dentro do intervaloAtual
                aux.setMin(intervaloAtual.getMin());
                aux.setMax(proximoIntervalo.getMax());
            }   
            
            if (i == listaIPsOriginal.size() - 2) // Antes de finalizar o laco adiciona o ultimo intervalo a lista
                listaIPsFiltrada.add(aux);
        }
        fimExecucao = new Timestamp(System.currentTimeMillis());
        tamanhoListaFiltrada = listaIPsFiltrada.size();

        return listaIPsFiltrada;
    }

    public static void leArquivo(List<Intervalo> listaIPsOriginal) {

        inicioExecucao = new Timestamp(System.currentTimeMillis());

        try (BufferedReader br = new BufferedReader(new FileReader("Teste.txt"))){

            String leitura;

            while ((leitura = br.readLine()) != null) {
                String[] valores = leitura.split("-");            
                Intervalo inter = new Intervalo(Integer.parseInt(valores[0]), Integer.parseInt(valores[1]));                       
                listaIPsOriginal.add(inter);
            }
            
            tamanhoListaOriginal = listaIPsOriginal.size();

            br.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}