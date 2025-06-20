package com.dcr.api.schedule;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.dcr.api.repository.as400.MatriprdRepository;
import com.dcr.api.schedule.dto.Reg2TXT;
import com.dcr.api.schedule.dto.Reg3TXT;
import com.dcr.api.schedule.dto.Reg4TXT;
import com.dcr.api.schedule.dto.TXTShowa;
import com.dcr.api.utils.FileProperties;
import com.dcr.api.utils.FileUtil;
import com.dcr.api.utils.Log;



@Service
public class ScheduleService {

	@Autowired
	MatriprdRepository repositoryMartiz;
	@Autowired
	ScheduleRepository repositorySchedule;
	@Autowired
    Environment env;

	
	public List<Object[]> getMatriprdWithNotInDcrprocc() {
		return repositoryMartiz.getMatriprdWithNotInDcrprocc();
	}
	

	public List<Object[]> getPendenciasCadastro() {
		return repositoryMartiz.getPendenciasCadastro();
	}

	
	//Create update PPB base PRODMOD, PRODFAT, VW_PRODUTOS
	public int getProdutosPendentes() {
		return repositorySchedule.produtosPendentes();
	}


	public int atualizaProdutoAcabado() {
		return repositorySchedule.atualizaProdutoAcabado();
	}


	public int explodeMatrizAvulsa(String TpPrd, String usersys, String idMatriz){

		//complete blank til 10 in idmatriz 
		idMatriz = (idMatriz+"          ").substring(0, 10);		
		usersys = StringUtils.rightPad(usersys, 10);
		return repositorySchedule.explodeMatrizAvulsa(TpPrd, usersys, idMatriz);
		//String cmd = "call HDCR004C('"+TpPrd+"' 'DCRMODELO ' '"+idMatriz+"')";
		//return repositorySchedule.callCL(cmd);
	}

	
	public int reprocessaPendencias(String TpPrd, String idMatriz, String usersys){
		
		idMatriz = StringUtils.rightPad(idMatriz, 10);
		usersys  = StringUtils.rightPad(usersys, 10);
		return repositorySchedule.reprocessaPendencias(TpPrd, idMatriz, usersys, "PEN");
				
	}	


	public int reprocessaMatrizAvulsa(String tpprd){
				
		return repositorySchedule.reprocessaAvulsa(tpprd);

	}


	public int processaINT_TXTShowa() throws IOException{

		String INT_NAME= "DCRE_API-TXT SHOWA";
		String logName= "INT_TXT_SHOWA";
        Log log = new Log(logName, "logs/", false, INT_NAME);
		String dirIN= env.getProperty("interface.txt_showa.dirIN");
		String dirTMP= env.getProperty("interface.txt_showa.dirTMP");
		String dirPROC= env.getProperty("interface.txt_showa.dirPROC");

		try{

			List<String> files = FileUtil.getFiles(dirIN, "", ".txt");
			FileUtil.movFiles(files, dirIN, dirTMP, log);
            //String file_name = dirIN+"\\"+files.get(0);  
            //FileProperties fp = FileUtil.setFileProperties(file_name);

			for(String file : files){

				TXTShowa txt = doTXTEntity(dirTMP+"\\"+file);

				
                FileUtil.movFile(file, dirTMP, dirPROC, log);

			}

		} catch (Exception e) {
			//throw new Exception();
			log.saveLog("Erro ao carregar KD (load_KDList)! ["+e+"]");
		}

		return 2;

	}

	public TXTShowa doTXTEntity(String file){

		TXTShowa txt = new TXTShowa();
		//Reg2TXT reg2 = new Reg2TXT();
		Reg3TXT reg3 = new Reg3TXT();
		Reg4TXT reg4 = new Reg4TXT();
		List<Reg2TXT> list_reg2 = new ArrayList<Reg2TXT>();
		List<Reg3TXT> list_reg3 = new ArrayList<Reg3TXT>();
		List<Reg4TXT> list_reg4 = new ArrayList<Reg4TXT>();	
		BufferedReader reader = null;                 
        String tprecord, line;
		
		
		try {

			FileProperties fp = FileUtil.setFileProperties(file);
			System.out.println(fp.getName());
			int positionFrom= fp.getName().indexOf("DCRE")+5; //Start after "DCRE "
			int positionTo= positionFrom+12; //DCRE format: YYYY-NNNNN-D year + seq + digit			
			String dcre= fp.getName().substring(positionFrom, positionTo).replace("-", "");			
			txt.setDcre(dcre);
			txt.setDtdcre(fp.getDateModified());


			reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();                      
            while (line != null) 
            {                                                
                                
                tprecord = line.substring(0, 1);
											
                if(StringUtils.equals(tprecord, "0")){
                    txt.setCnpj(line.substring(1, 15));
					txt.setPpb(line.substring(26, 105));
					txt.setDenom(line.substring(106, 186));
					txt.setNcm(line.substring(186, 194));
					txt.setUndcom(line.substring(194, 274));
					txt.setPeso(line.substring(274, 288));
					txt.setTpdcre(line.substring(318, 319));
					txt.setDcrant(line.substring(319, 329));
					txt.setOrigdcr(line.substring(350, 351));
					txt.setTpcoef(line.substring(351, 352));
				};

				if(StringUtils.equals(tprecord, "1")){					
					txt.setModelo(line.substring(1, 5));
					txt.setDescricao(line.substring(5, 85));
					txt.setPreco(line.substring(85, 100));
					txt.setCodint(line.substring(100, 115));
					String descricao = line.substring(5, 85);
					int posCodClient= descricao.indexOf("/")+2; //Start after "/ "
					txt.setCdclient(descricao.substring(posCodClient, posCodClient+25));
				}
				
				if(StringUtils.equals(tprecord, "2")){
					/*reg2.setNumcomp(Integer.valueOf(line.substring(1, 5)));
					reg2.setNumnf(Long.valueOf(line.substring(5, 15)));
					reg2.setSernf(line.substring(15, 20));
					reg2.setCnpjfor(line.substring(20, 34));
					reg2.setIe(line.substring(34, 49));
					reg2.setEminf(line.substring(49, 57));
					reg2.setEspec(line.substring(57, 137));
					reg2.setUndcom(line.substring(137, 217));
					reg2.setNcm(line.substring(217, 225));
					reg2.setQtde(Double.valueOf(line.substring(225, 240))/10000000); //7decimais
					reg2.setVlrunit(Double.valueOf(line.substring(240, 255))/1000000); //6decimais*/
					list_reg2.add(new Reg2TXT(
						//Integer numcomp, Long numnf, String sernf, String cnpjfor, String ie, String eminf, String espec, String undcom, String ncm, Double qtde, Double vlrunit
						Integer.valueOf(line.substring(1, 5)),
						Long.valueOf(line.substring(5, 15)),
						line.substring(15, 20),
						line.substring(20, 34),
						line.substring(34, 49),
						line.substring(49, 57),
						line.substring(57, 137),
						line.substring(137, 217),
						line.substring(217, 225),
						Double.valueOf(line.substring(225, 240))/10000000), //7decimais
						Double.valueOf(line.substring(240, 255))/1000000) //6decimais						
						)
					);
				}
				
				if(StringUtils.equals(tprecord, "3")){
					reg3.setNumsubcomp(Integer.valueOf(line.substring(1, 5)));
					reg3.setNumcomp(Integer.valueOf(line.substring(5, 9)));
					reg3.setIibasecalc(line.substring(9, 10).charAt(0));
					reg3.setImpdireta(line.substring(10, 11).charAt(0));
					reg3.setSuspens(line.substring(11, 12).charAt(0));
					reg3.setDi(line.substring(12, 22));
					reg3.setAdicao(line.substring(23, 25));
					reg3.setItemadicao(line.substring(25, 27));
					reg3.setNumnf(Long.valueOf(line.substring(27, 37)));
					reg3.setSernf(line.substring(37, 42));
					reg3.setCnpjfor(line.substring(42, 56));
					reg3.setIe(line.substring(56, 71));
					reg3.setEminf(line.substring(71, 79));
					reg3.setEspec(line.substring(79, 159));
					reg3.setUndcom(line.substring(159, 239));
					reg3.setNcm(line.substring(239, 247));
					reg3.setQtde(Double.valueOf(line.substring(247, 262))/10000000); //7decimais
					reg3.setInreducii(line.substring(262, 263).charAt(0));
					reg3.setVlrunit(Double.valueOf(line.substring(263, 278))/1000000); //6decimais
					list_reg3.add(reg3);
				}
				
				if(StringUtils.equals(tprecord, "4")){
					reg4.setNumcomp(Integer.valueOf(line.substring(1, 5)));										
					reg4.setImpdireta(line.substring(5, 6).charAt(0));
					reg4.setSuspens(line.substring(6, 7).charAt(0));
					reg4.setDi(line.substring(7, 17));
					reg4.setAdicao(line.substring(17, 20));
					reg4.setItemadicao(line.substring(20, 22));
					reg4.setNumnf(Long.valueOf(line.substring(22, 32)));
					reg4.setSernf(line.substring(33, 37));
					reg4.setCnpjfor(line.substring(37, 51));
					reg4.setIe(line.substring(51, 66));
					reg4.setEminf(line.substring(66, 74));
					reg4.setEspec(line.substring(74, 154));
					reg4.setUndcom(line.substring(154, 234));
					reg4.setNcm(line.substring(234, 242));
					reg4.setQtde(Double.valueOf(line.substring(242, 257))/10000000); //7decimais
					reg4.setInreducii(line.substring(257, 258).charAt(0));
					reg4.setVlrunit(Double.valueOf(line.substring(258, 273))/1000000); //6decimais					
					list_reg4.add(reg4);
				}				
					/*
					private Integer numcomp;    //NUM_COMPONENTE_IMPORTADO	02	05	04
					private char impdireta;     // IN_IMP_DIRETA	        06	06	01
					private char suspens;       // IN_COM_SUSPENSAO	        07	07	01
					private String di;          // NUM_DI	                08	17	10
					private String adicao;      // NUM_ADICAO	            18	20	03
					private String itemadicao;  // NUM_ITEM	                21	22	02
					private Long numnf;         // NUM_NOTA_FISCAL	        23	32	10
					private String sernf;       // NUM_SERIE_NF	            33	37	05
					private String cnpjfor;     // CNPJ_FORNECEDOR	        38	51	14
					private String ie;          // INSCRIÇÃO_ESTADUAL	    52	66	15
					private String eminf;       // DATA_EMISSAO_NF	        67	74	08
					private String espec;       // ESPECIFICACAO	        75	154	80
					private String undcom;      // UNIDADE_COMERCIAL	    155	234	80
					private String ncm;         // NCM	                    235	242	08
					private Double qtde;        // QUANTIDADE	            243	257	15 (7deci)
					private char inreducii;     // IN_REDUÇÃO_II	        258	258	01
					private Double vlrunit;     // CUSTO_UNITARIO	        259	273	15 (6deci)
					 */			
				line = reader.readLine();
			}			
			txt.setReg2(list_reg2);
			txt.setReg3(list_reg3);
			txt.setReg4(list_reg4);
						
		} catch (Exception e) {
			
		}finally{
			
		}

		return txt;

	}


}
