package com.dcr.api.schedule;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.dcr.api.repository.as400.MatriprdRepository;
import com.dcr.api.schedule.dto.Reg2TXT;
import com.dcr.api.schedule.dto.Reg3TXT;
import com.dcr.api.schedule.dto.Reg4TXT;
import com.dcr.api.schedule.dto.TXTShowa;
import com.dcr.api.service.as400.ColigadaService;
import com.dcr.api.utils.FileProperties;
import com.dcr.api.utils.FileUtil;
import com.dcr.api.utils.Log;
import com.dcr.api.model.dto.Dcrcoli0DTO;
import com.dcr.api.model.dto.Dcrcoli1DTO;
import com.dcr.api.model.dto.Dcrcoli2DTO;



@Service
public class ScheduleService {

	@Autowired
	MatriprdRepository repositoryMartiz;
	@Autowired
	ScheduleRepository repositorySchedule;
	@Autowired
    Environment env;

	@Autowired
	ColigadaService coligadaService;

	@Autowired
	private ModelMapper mapper;

	
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

	
	public int reprocessaPendencias(String TpPrd, String idMatriz, String usersys, String step){
		
		idMatriz = StringUtils.rightPad(idMatriz, 10);
		usersys  = StringUtils.rightPad(usersys, 10);
		//return repositorySchedule.reprocessaPendencias(TpPrd, idMatriz, usersys, "PEN");
		
		if(step.equals("PEN")){ //call if just PEN / else submit			
			return repositorySchedule.reprocessaPendencias(TpPrd, idMatriz, usersys, step);
		}
		
		return repositorySchedule.reprocessaPendenciasSubmit(TpPrd, idMatriz, usersys, step);
				
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
				String partnumber = formatPartnumber(txt.getCdclient());
				txt.setCdclient(partnumber);

				cadastraLoteTXT(txt, log);

                FileUtil.movFile(file, dirTMP, dirPROC, log);

			}

		} catch (Exception e) {
			//throw new Exception();
			log.saveLog("Falha ao processar arquivos TXT Showa! ["+e+"]");
		}

		return 2;

	}


	public TXTShowa doTXTEntity(String file){

		TXTShowa txt = new TXTShowa();		
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
					txt.setPeso(Double.valueOf(line.substring(274, 288))/100000); //5deci
					txt.setTpdcre(line.substring(318, 319));
					txt.setDcrant(line.substring(319, 329));
					txt.setOrigdcr(line.substring(350, 351));
					txt.setTpcoef(line.substring(351, 352));
				};

				if(StringUtils.equals(tprecord, "1")){					
					txt.setModelo(line.substring(1, 5));
					txt.setDescricao(line.substring(5, 85));
					txt.setPreco(Double.valueOf(line.substring(85, 100))/100); //2deci
					txt.setCodint(line.substring(100, 115));
					String descricao = line.substring(5, 85);
					int posCodClient= descricao.indexOf("/")+2; //Start after "/ "
					txt.setCdclient(descricao.substring(posCodClient, posCodClient+25));
				}
				
				if(StringUtils.equals(tprecord, "2")){					
					list_reg2.add(new Reg2TXT(						
						Integer.valueOf(line.substring(1, 5)),
						Long.valueOf(line.substring(5, 15)),
						line.substring(15, 20),
						line.substring(20, 34),
						line.substring(34, 49),
						line.substring(49, 57),
						line.substring(57, 137),
						line.substring(137, 217),
						line.substring(217, 225),
						Double.valueOf(line.substring(225, 240))/10000000, //7decimais
						Double.valueOf(line.substring(240, 255))/1000000 //6decimais						
						)
					);
				}
				
				if(StringUtils.equals(tprecord, "3")){
					
					list_reg3.add(new Reg3TXT(
						Integer.valueOf(line.substring(1, 5)),
						Integer.valueOf(line.substring(5, 9)),
						line.substring(9, 10).charAt(0),
						line.substring(10, 11).charAt(0),
						line.substring(11, 12).charAt(0),
						line.substring(12, 22),
						line.substring(23, 25),
						line.substring(25, 27),
						Long.valueOf(line.substring(27, 37)),
						line.substring(37, 42),
						line.substring(42, 56),
						line.substring(56, 71),
						line.substring(71, 79),
						line.substring(79, 159),
						line.substring(159, 239),
						line.substring(239, 247),
						Double.valueOf(line.substring(247, 262))/10000000, //7decimais
						line.substring(262, 263).charAt(0),
						Double.valueOf(line.substring(263, 278))/1000000   //6decimais
					));

				}
				
				if(StringUtils.equals(tprecord, "4")){					
					list_reg4.add(new Reg4TXT(
						Integer.valueOf(line.substring(1, 5)),										
						line.substring(5, 6).charAt(0),
						line.substring(6, 7).charAt(0),
						line.substring(7, 17),
						line.substring(17, 20),
						line.substring(20, 22),
						Long.valueOf(line.substring(22, 32)),
						line.substring(33, 37),
						line.substring(37, 51),
						line.substring(51, 66),
						line.substring(66, 74),
						line.substring(74, 154),
						line.substring(154, 234),
						line.substring(234, 242),
						Double.valueOf(line.substring(242, 257))/10000000, //7decimais
						line.substring(257, 258).charAt(0),
						Double.valueOf(line.substring(258, 273))/1000000 //6decimais	
					));
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


	private String formatPartnumber(String codigo){

		String codigoFormatado = codigo;

		return codigoFormatado;

	}


	private int cadastraLoteTXT(TXTShowa txt, Log log) throws Exception{

		//Registro Zero (0)
		Dcrcoli0DTO dto0 = new Dcrcoli0DTO(			
			txt.getDcre(), txt.getDenom(), txt.getCdclient(), txt.getDtdcre(), "0", 
			txt.getCnpj(), "*", txt.getPpb(), txt.getNcm(), txt.getUndcom(), txt.getPeso(), 
			0.0, 0.0, txt.getTpdcre(), txt.getDcrant(), "", "", 
			txt.getOrigdcr(), txt.getTpcoef()
		);
		coligadaService.criaReg0(dto0);


		//Registro Um (1) **No DCR-e é lista, mas p/ Honda é apenas um nível
		Dcrcoli1DTO dt01 = new Dcrcoli1DTO(
			txt.getDcre(), txt.getCdclient(),  
			Integer.valueOf(txt.getModelo()), "1", txt.getDescricao(), txt.getPreco()
		);
		coligadaService.criaReg1(dt01);


		//Registro Dois (2)
		//List<Dcrcoli2DTO> dto2 = Arrays.asList(mapper.map(txt.getReg2(), Dcrcoli2DTO[].class)); **modelmapper not work with Record class
		//String dcre, Integer numcomp, String idreg,String numnf,String sernf,Long cnpjfor,String ie,String eminf,String espec,String undcom,String ncm,Double qtde,Double vlrunit){
		//Reg2TXT(Integer numcomp, Long numnf, String sernf, String cnpjfor, String ie, String eminf, String espec, String undcom, String ncm, Double qtde, Double vlrunit) {

		return 1;

	}


}
