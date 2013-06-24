package Operacoes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.smi.*;
import org.snmp4j.mp.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.util.*;

public class Conexao {

    public Conexao() {
    }

    public String metodoGet(String ip, String oidASerBuscado, String comunidade) {
        String menssagem = "";
        try {
            Snmp snmp4j = new Snmp(new DefaultUdpTransportMapping());
            snmp4j.listen();
            //Address add = new UdpAddress("192.168.25.29"+"/"+"161");
            Address ipAlvo = new UdpAddress(ip + "/" + "161");
            CommunityTarget target = new CommunityTarget();
            target.setAddress(ipAlvo);
            target.setTimeout(500);

            target.setRetries(3);
            target.setCommunity(new OctetString(comunidade));
            //target.setCommunity(new OctetString("public"));
            target.setVersion(SnmpConstants.version2c);

            PDU request = new PDU();
            request.setType(PDU.GET);
            OID oid = new OID(oidASerBuscado);
            //OID oid= new OID(".1.3.6.1.2.1.1.1.0");
            //1.3.6.1.2.1.1.5
            request.add(new VariableBinding(oid));

            PDU responsePDU = null;
            ResponseEvent responseEvent;
            responseEvent = snmp4j.send(request, target);

            if (responseEvent != null) {
                responsePDU = responseEvent.getResponse();
                if (responsePDU != null) {

                    Vector<VariableBinding> tmpv = (Vector<VariableBinding>) responsePDU.getVariableBindings();
                    if (tmpv != null) {
                        for (int k = 0; k < tmpv.size(); k++) {
                            VariableBinding vb = (VariableBinding) tmpv.get(k);
                            String output = null;
                            if (vb.isException()) {

                                String errorstring = vb.getVariable().getSyntaxString();
                                System.out.println("Error:" + errorstring);

                                menssagem = "Erro não pode ser encontrado o oid solicitado";
                            } else {
                                String sOid = vb.getOid().toString();
                                Variable var = vb.getVariable();
                                OctetString oct = new OctetString((OctetString) var);
                                String sVar = oct.toString();

                                //System.out.println("success:"+sVar);
                                menssagem = sVar;
                            }


                        }

                    }

                } else {
                    menssagem = "Erro não pode ser encontrado o oid solicitado";
                }
            } else {
                menssagem = "Erro não pode ser encontrado o oid solicitado";
            }

        } catch (IOException e) {

            //      e.printStackTrace();
            menssagem = "Erro não pode ser encontrado o oid solicitado";
        }
        return menssagem;


    }

    public String metodoGetNext(String ip, String oidASerBuscado, String comunidade) {
        String menssagem = "";
        try {
            Snmp snmp4j = new Snmp(new DefaultUdpTransportMapping());
            snmp4j.listen();
            //Address add = new UdpAddress("192.168.25.29"+"/"+"161");
            Address ipAlvo = new UdpAddress(ip + "/" + "161");
            CommunityTarget target = new CommunityTarget();
            target.setAddress(ipAlvo);
            target.setTimeout(500);

            target.setRetries(3);
            target.setCommunity(new OctetString(comunidade));
            //target.setCommunity(new OctetString("public"));
            target.setVersion(SnmpConstants.version2c);

            PDU request = new PDU();
            request.setType(PDU.GETNEXT);
            OID oid = new OID(oidASerBuscado);
            //OID oid= new OID(".1.3.6.1.2.1.1.1.0");
            //1.3.6.1.2.1.1.5N
            request.add(new VariableBinding(oid));

            PDU responsePDU = null;
            ResponseEvent responseEvent;
            responseEvent = snmp4j.send(request, target);

            if (responseEvent != null) {
                responsePDU = responseEvent.getResponse();

                if (responsePDU != null) {

                    Vector<VariableBinding> tmpv = (Vector<VariableBinding>) responsePDU.getVariableBindings();

                    if (tmpv != null) {
                        for (int k = 0; k < tmpv.size(); k++) {
                            VariableBinding vb = (VariableBinding) tmpv.get(k);
                            String output = null;
                            if (vb.isException()) {

                                String errorstring = vb.getVariable().getSyntaxString();
                                System.out.println("Error:" + errorstring);

                                menssagem = "Erro não pode ser encontrado o oid solicitado";
                            } else {
                                String sOid = vb.getOid().toString();
                                Variable var = vb.getVariable();
                                //  OctetString oct = new OctetString((OctetString)var);
                                //  String sVar =  oct.toString();
                                String sVar = var.toString();
                                //System.out.println("success:"+var);
                                menssagem = sVar;
                            }


                        }

                    }

                } else {
                    menssagem = "Erro não pode ser encontrado o oid solicitado";
                }
            } else {
                menssagem = "Erro não pode ser encontrado o oid solicitado";
            }

        } catch (IOException e) {

            //      e.printStackTrace();
            menssagem = "Erro não pode ser encontrado o oid solicitado";
        }
        return menssagem;


    }

    public String metodoSet(String ip, String oidASerBuscado, String comunidade, String novoParametro) {
        String menssagem = "";
        try {
            Snmp snmp4j = new Snmp(new DefaultUdpTransportMapping());
            snmp4j.listen();
            //Address add = new UdpAddress("192.168.25.29"+"/"+"161");
            Address ipAlvo = new UdpAddress(ip + "/" + "161");
            CommunityTarget target = new CommunityTarget();
            target.setAddress(ipAlvo);
            target.setTimeout(500);

            target.setRetries(3);
            target.setCommunity(new OctetString(comunidade));
            //target.setCommunity(new OctetString("public"));
            target.setVersion(SnmpConstants.version2c);

            PDU request = new PDU();
            request.setType(PDU.SET);
            OID oid = new OID(oidASerBuscado);
            Variable newOid = new OctetString(novoParametro);
            //OID oid= new OID(".1.3.6.1.2.1.1.1.0");
            //1.3.6.1.2.1.1.5
            request.add(new VariableBinding(oid, newOid));

            PDU responsePDU = null;
            ResponseEvent responseEvent;
            responseEvent = snmp4j.send(request, target);

            if (responseEvent != null) {
                responsePDU = responseEvent.getResponse();

                if (responsePDU != null) {

                    int errorStatus = responsePDU.getErrorStatus();
                    int errorIndex = responsePDU.getErrorIndex();
                    String errorStatusText = responsePDU.getErrorStatusText();

                    if (errorStatus == PDU.noError) {
                       menssagem= "Funcionou = " + responsePDU.getVariableBindings();
                    } else {
                        menssagem = "Erro: falhou requisição";
                        menssagem = menssagem + "\nErro Status = " + errorStatus;
                        menssagem=menssagem+"\nErro Index = " + errorIndex;
                        menssagem=menssagem+"\nErro Status Text = " + errorStatusText;
                    }


                }




            } else {
                menssagem = "Erro não pode ser encontrado o oid solicitado";
            }

        } catch (IOException e) {

            //      e.printStackTrace();
            menssagem = "Erro não pode ser encontrado o oid solicitado";
        }
        return menssagem;


    }
    
    public String metodoGetBulk(String ip,String comunidade,ArrayList listaDosQueNaoRepetem,ArrayList listaDosQueRepetem,int maxRepetitions) {
            String menssagem = "";
        try {
            Snmp snmp4j = new Snmp(new DefaultUdpTransportMapping());
            snmp4j.listen();
            //Address add = new UdpAddress("192.168.25.29"+"/"+"161");
            Address ipAlvo = new UdpAddress(ip + "/" + "161");
            CommunityTarget target = new CommunityTarget();
            target.setAddress(ipAlvo);
            target.setTimeout(500);

            target.setRetries(3);
            target.setCommunity(new OctetString(comunidade));
            //target.setCommunity(new OctetString("public"));
            target.setVersion(SnmpConstants.version2c);

            PDU request = new PDU();
            request.setType(PDU.GETBULK);
            request.setNonRepeaters(listaDosQueNaoRepetem.size());
            request.setMaxRepetitions(maxRepetitions);
//            OID oid = new OID(oidInicial);            
            //OID oid= new OID(".1.3.6.1.2.1.1.1.0");
            //1.3.6.1.2.1.1.5N
            
            ArrayList aux = new ArrayList();
            
            aux.addAll(listaDosQueNaoRepetem); 
            aux.addAll(listaDosQueRepetem); 
            VariableBinding[] v = new VariableBinding[aux.size()+1];
            for (int i =0 ;i< aux.size();i++){
            OID oid2 = new OID((String) aux.get(i));
            v[i]=new VariableBinding(oid2);
            }
            
    
//            request.add(new VariableBinding(oid));
            
            PDU responsePDU = null;
            ResponseEvent responseEvent;
            responseEvent = snmp4j.send(request, target);

            if (responseEvent != null) {
                responsePDU = responseEvent.getResponse();

                if (responsePDU != null) {

                    Vector<VariableBinding> tmpv = (Vector<VariableBinding>) responsePDU.getVariableBindings();

                    if (tmpv != null) {
                        for (int k = 0; k < tmpv.size(); k++) {
                            VariableBinding vb = (VariableBinding) tmpv.get(k);
                            String output = null;
                            if (vb.isException()) {

                                String errorstring = vb.getVariable().getSyntaxString();
                                System.out.println("Error:" + errorstring);

                                menssagem = "Erro não pode ser encontrado o oid solicitado";
                            } else {
                                String sOid = vb.getOid().toString();
                                Variable var = vb.getVariable();
                                //  OctetString oct = new OctetString((OctetString)var);
                                //  String sVar =  oct.toString();
                                String sVar = var.toString();
                                //System.out.println("success:"+var);
                                menssagem = sVar;
                            }


                        }

                    }

                } else {
                    menssagem = "Erro não pode ser encontrado o oid solicitado";
                }
            } else {
                menssagem = "Erro não pode ser encontrado o oid solicitado";
            }

        } catch (IOException e) {

            //      e.printStackTrace();
            menssagem = "Erro não pode ser encontrado o oid solicitado";
        }
        return menssagem;


    }
   
}
