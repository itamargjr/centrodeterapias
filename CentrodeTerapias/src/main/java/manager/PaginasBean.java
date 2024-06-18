package manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

@ManagedBean
@RequestScoped
public class PaginasBean {
	private final String index = "index.xhtml";
	private final String principal = "principal.xhtml";
	private final String facesRedirect = "?facesRedirect=true";
	
	//Antiga FichaTecnicaImpressao
	//private final String fichaTecnicaImpressao = "FichaTecnicaImpressao.xhtml";
	
	private final String fichaTecnicaImpressao = "fichaTecnicaImpressao.xhtml";
	
	private final String _template = "templates/_template_V2.0.xhtml";
	
	// Strings dos arquivos, eles estão sendo usados no componente _Head resources/components
		private final String reset = "reset_V1.0.css";
		private final String style = "style_V3.0.css";
		private final String styleRelatorio = "styleRelatorio_V2.0.css";
		private final String tamanho320px = "tamanho320px_V1.0.css";
		private final String variaveis = "variaveis.css";
		private final String funcoes = "funcoes_V1.0.js";
		private final String mascaras = "mascaras_V1.0.js";
		private final String _relatorioBasico = "templates/_RelatorioBasico_V2.0.xhtml";
	//
	
	public String getFichaTecnicaImpressao() {
		return fichaTecnicaImpressao;
	}
	public String getIndex() {
		return index;
	}
	public String getFacesRedirect() {
		return facesRedirect;
	}

	public String Principal() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			if (session.getAttribute("idusulogado") == null) {
				return null;
			} else if (((Integer) session.getAttribute("idusulogado"))!= 0) {
				return principal + facesRedirect;				
			} else {
				return null;
			}  													
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Executa Codigo Js(javaScript), JQuery via Bean.
	public void ExecutaJs(String js){
		PrimeFaces.current().executeScript(js); // Alterado para o primefaces 8 (itamar)
	}
	
	/* Diferença entre Redirect e Forward e a quantidade de requisições utilizadas,
	 * os dois servem para mudar a "view"(página) do usuário,
	 * isso fará diferença dependendo do Bean utlizado (para mais informações consultar documentação: tema grande).
	 */
	public void Redirecionar(String pagina) throws Exception{
		FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
	}
	
	public void Forward(String pagina) throws Exception{
	    FacesContext.getCurrentInstance().getExternalContext().dispatch(pagina);
	}
	
	/* *Flash Scope */
		/* *
		 * Flash Scope é um recurso que permite trasmitir variáveis e objetos entre views 
		 * Obs:. O flash Scope toda vez após um request tudo que está no flashScope é apagado da memória
		 * se precisar que ele "sobreviva" mais um request use a propriedade "Keep" 
		 * */
		public void SetPropriedadeFlashScope(String texto, Object obj){
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put(texto, obj);
		}
		public Object GetPropriedadeFlashScope(String texto){
			return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(texto);
		}
	/* *Fim Flash Scope */
	
	/* *Session Scope */
		public void SetPropriedadeSessionScope(String texto, Object obj){
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(texto, obj);
		}
		public void RemovePropriedadeSessionScope(String texto){
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(texto);
		}
		public Object GetPropriedadeSessionScope(String texto){
			return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(texto);
		}
	/* *Fim Session Scope */
		public String getPrincipal() {
			return principal;
		}
		public String get_template() {
			return _template;
		}
		public String getReset() {
			return reset;
		}
		public String getStyle() {
			return style;
		}
		public String getStyleRelatorio() {
			return styleRelatorio;
		}
		public String getTamanho320px() {
			return tamanho320px;
		}
		public String getVariaveis() {
			return variaveis;
		}
		public String getFuncoes() {
			return funcoes;
		}
		public String getMascaras() {
			return mascaras;
		}
		public String get_relatorioBasico() {
			return _relatorioBasico;
		}
}
