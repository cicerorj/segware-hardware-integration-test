package com.example.tcpclient.configs;

import com.example.tcpclient.utils.WriteToFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

public class TCPSendMessage implements Runnable {

    private boolean socketIsExits = false;
    private OutputStream outputStream;
    private Socket socketClient;
    private int remotePort = 5005;
    private String remoteAddress = "localhost";
    private static byte[] buffer;
    private Object object2;

    public Object getObject(Object object){
        this.object2 =  object;
        return object2;
    }

    public static byte[] testMessageSend( byte[] b ){
        buffer = b;
        return buffer;
    }

    public TCPSendMessage(Object obj) {
        this.object2 = obj;
    }

    public TCPSendMessage() {
    }

    public void CSendMessage() throws IOException {
    }
    public String convertToSaveFile(Object obj){
        Set<Object> setObject = new LinkedHashSet<>();
        setObject.add(obj);
        String getFrameValue = setObject.toString();
        return getFrameValue;
    }
    public void run() {
        String str = new String();
        checkSocket();
        int size = 0;
        int aux = 0;
        while ( true) {

        if (socketIsExits) {
            try {
                if(aux == 0){
                menu();
                buffer = new byte[2048];
                size = System.in.read(buffer);
                str = new String(buffer, 0, size);

                }
//                buffer = new byte[2048];
                System.in.read("".getBytes());
                if(aux == 1){
                menu2();
                buffer = new byte[2048];
                size = System.in.read(buffer);
                str = new String(buffer, 0, size);

                }

                System.in.read("".getBytes());
                if(aux > 1){
                    menu3();
                }
                outputStream.flush();
                aux++;

            } catch (Exception e) {
                System.out.println ("Client Socket does not exist.");
                checkSocket();
            }
            } else {
                checkSocket();
            }

        }
    }

    public void menu(){
        System.out.println("***************************************************************************");
        System.out.println("*---------------------------------ATENÇÃO---------------------------------*");
        System.out.println("*--------------------------INICIANDO A APLICAÇÃO--------------------------*");
        System.out.println("*--LER AS INSTRUÇÕES E SEGUI-LAS PARA MELHOR FUNCIONAMENTO DA APLICAÇÃO---*");
        System.out.println("*---------------PRESSIONE ENTER ATÉ APARECER O PRÓXIMO MENU---------------*");
        System.out.println("*                                                                         *");
        System.out.println("***************************************************************************");

    }
    public void menu2(){
        System.out.println();
        System.out.println();
        System.out.println("***************************************************************************");
        System.out.println("*--------------------------SIGA AS INTRUÇÕES -----------------------------*");
        System.out.println("***************************************************************************");
        System.out.println("* PARA O FLUXO FUNCIONAR CORRETAMENTA, SIGA OS PASSOS CORRETAMENTE        *");
        System.out.println("*                                                                         *");
        System.out.println("*-------------------------------------------------------------------------*");
        System.out.println("* 1 - INSERIR OS VALORES NO FORMATO JSON. ESTA VERSÃO DO PROGRAMA POR     *");
        System.out.println("* ENQUANTO ACEITA APENAS ENTRADAS EM FORMATO JSON                         *");
        System.out.println("*                                                                         *");
        System.out.println("* 2 - AO INSERIR OS VALORES E NÃO OBTER A RESPOSTA DESEJADA, POR FAVOR    *");
        System.out.println("* TENTAR NOVAMENTE COM OS MESMOS DADOS OU OUTROS DADOS.                   *");
        System.out.println("*                                                                         *");
        System.out.println("* 3 - QUALQUER ENTRADA FORA DO PADRÃO SOLICITADO NÃO FUNCIONARÁ           *");
        System.out.println("*                                                                         *");
        System.out.println("* 4 - NÃO FOI NOTADA DIFERENÇA ENTRE INICIAR O CLIENTE OU SERVER PRIMEIRO *");
        System.out.println("*                                                                         *");
        System.out.println("*---------------PRESSIONE ENTER ATÉ APARECER O PRÓXIMO MENU---------------*");
        System.out.println("*                                                                         *");
        System.out.println("***************************************************************************");

    }
    public void menu3() throws IOException {
        String str = new String();
        int size = 0;
        int aux = 0;
        System.out.println();
        System.out.println("***************************************************************************");
        System.out.println("*--------FAVOR INSERIR OS DADOS E FICAR ATENTO ÀS INSTRUÇÕES 1 e 2--------*");
        System.out.println("*                                                                         *");
        System.out.println("***************************************************************************");
        System.out.print("* INSERIR DADOS: ");
        System.out.println();
        buffer = new byte[2048];
        size = System.in.read(buffer);
        str = new String(buffer, 0, size);
        WriteToFile w = new WriteToFile();
       var concated = str.concat('"'+"date" + '"' +":"+ " " + '"'+ horaEntradaDados() +'"');
        w.write(convertToSaveFile(concated));
        outputStream.write(str.getBytes());
    }

    private void checkSocket() {
        try {
            socketClient = new Socket(remoteAddress, remotePort);
            outputStream = socketClient.getOutputStream();
            socketIsExits = true;
        } catch (Exception e) {
            socketIsExits = false;
        }
    }

    public String horaEntradaDados(){
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTimeOf = ZonedDateTime.ofInstant(instant , ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");

        return zonedDateTimeOf.format(formatter);
    }

}
