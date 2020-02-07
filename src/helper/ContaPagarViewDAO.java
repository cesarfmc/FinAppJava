package helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.CentroCusto;
import model.ContaPagarDataView;
import model.Fornecedor;
import model.PlanoConta;

public class ContaPagarViewDAO {
	
	private Session s;

	public ContaPagarViewDAO(Session sessao) {
		this.s = sessao;
	}
	
	public List<ContaPagarDataView> listContaPagarJasper(Date dataCompraInicio, Date dataCompraFim, Date dataVencimentoInicio,
			Date dataVencimentoFim, Date dataPagamentoInicio, Date dataPagamentoFim, Fornecedor fornecedor,
			CentroCusto centroCusto, PlanoConta planoConta, int tipoOrdenacao, int status, int tipoQuebra) {
		List<ContaPagarDataView> list = new ArrayList<ContaPagarDataView>();
		s.beginTransaction();
		
		String sql = "from ContaPagarDataView where";
		StringBuilder hql = new StringBuilder();
		hql.append(sql);

		
		  if (dataCompraInicio != null) {
		  hql.append(" dataCompra between :dataCompraInicio and :dataCompraFinal and "
		  ); }
		  
		  if (dataVencimentoInicio != null) { hql.
		  append(" dataVencimento between :dataVencimentoInicio and :dataVencimentoFinal and "
		  ); }
		  
		  if (dataPagamentoInicio != null) { hql.
		  append(" dataPagamento between :dataPagamentoInicio and :dataPagamentoFinal and "
		  ); }
		  
		  if (fornecedor != null) { hql.append(" idFornecedor =  :idFornecedor and ");
		  }
		  
		  if (centroCusto != null) {
		  hql.append(" idCentroCusto =  :idCentroCusto and "); }
		  
		  if (planoConta != null) { hql.append(" idPlanoConta =  :idPlanoConta and ");
		  }
		  
		  if (status == 0) { hql.append(" (status =  'A' OR status = 'B')"); } else { if
		  (status == 1) { hql.append(" status =  'A' "); } else {
		  hql.append(" status = 'B' "); } }

		  if (tipoQuebra == 0) { hql.append(" ORDER BY fornecedorNome "); } else {
			  if (tipoQuebra == 1) { hql.append(" ORDER BY centroCustoNome "); } else {
			  if (tipoQuebra == 2) { hql.append(" ORDER BY planoContaNome "); } else {
			  if (tipoQuebra == 3) { hql.append(" ORDER BY dataCompra "); } else { if
			  (tipoQuebra == 4) { hql.append(" ORDER BY dataVencimento "); } else {
			  hql.append(" ORDER BY dataPagamento "); } } } } }
		  
		 if(tipoQuebra != tipoOrdenacao) {
			 if (tipoOrdenacao == 0) { hql.append(",fornecedorNome "); } else {
				  if (tipoOrdenacao == 1) { hql.append(",centroCustoNome "); } else {
				  if (tipoOrdenacao == 2) { hql.append(",planoContaNome "); } else {
				  if (tipoOrdenacao == 3) { hql.append(",dataCompra "); } else { if
				  (tipoOrdenacao == 4) { hql.append(",dataVencimento "); } else {
				  hql.append(",dataPagamento "); } } } } }
		 }
		  
		  Query<ContaPagarDataView> query = s.createQuery(hql.toString(),ContaPagarDataView.class);
		  
		  if (dataCompraInicio != null) { query.setParameter("dataCompraInicio",
		  dataCompraInicio); query.setParameter("dataCompraFinal", dataCompraFim); }
		  
		  if (dataVencimentoInicio != null) {
		  query.setParameter("dataVencimentoInicio", dataVencimentoInicio);
		  query.setParameter("dataVencimentoFinal", dataVencimentoFim); }
		  
		  if (dataPagamentoInicio != null) { query.setParameter("dataPagamentoInicio",
		  dataPagamentoInicio); query.setParameter("dataPagamentoFinal",
		  dataPagamentoFim); }
		  
		  if (fornecedor != null) { query.setParameter("idFornecedor",
		  fornecedor.getIdFornecedor()); }
		  
		  if (centroCusto != null) { query.setParameter("idCentroCusto",
		  centroCusto.getIdCentroCusto()); }
		  
		  if (planoConta != null) { query.setParameter("idPlanoConta",
		  planoConta.getIdPlanoConta()); }
		 
		 list = query.list();
		 
		s.getTransaction().commit();

		return list;
	}
}
