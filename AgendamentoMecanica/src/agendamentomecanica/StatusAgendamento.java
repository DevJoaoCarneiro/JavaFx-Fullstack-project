/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamentomecanica;

/**
 *
 * @author João Victor
 */
public enum StatusAgendamento {
       ABERTO("Aberto"),
       EM_ANDAMENTO("Em andamento"),
       FINALIZADO("Finalizado");
       
       private final String nomeExibicao;
       
       StatusAgendamento(String nomeExibicao){
           this.nomeExibicao = nomeExibicao;
       }
       
       @Override
       public String toString(){
           return nomeExibicao;
       }
}
