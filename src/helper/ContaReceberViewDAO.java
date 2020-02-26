package helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.CentroCusto;
import model.Cliente;
import model.ContaReceberDataView;
import model.PlanoConta;

public class ContaReceberViewDAO {
	private Session s;
	private long tempoInicial;

	public ContaReceberViewDAO(Session sessao) {
		this.s = sessao;
	}
	
	public List<ContaReceberDataView> listContaReceberJasper(Date dataVendaInicio, Date dataVendaFim, Date dataVencimentoInicio,
			Date dataVencimentoFim, Date dataRecebimentoInicio, Date dataRecebimentoFim, Cliente  cliente,
			CentroCusto centroCusto, PlanoConta planoConta, int tipoOrdenacao, int status, int tipoQuebra) {
		tempoInicial = System.currentTimeMillis();

		List<ContaReceberDataView> list = new ArrayList<ContaReceberDataView>();
		s.beginTransaction();
		String sql = "from ContaReceberDataView where";
		StringBuilder hql = new StringBuilder();
		hql.append(sql);
		 if (dataVendaInicio != null) {
			  hql.append(" dataVenda between :dataVendaInicio and :dataVendaFinal and "
			  ); }
			  
			  if (dataVencimentoInicio != null) { hql.
			  append(" dataVencimento between :dataVencimentoInicio and :dataVencimentoFinal and "
			  ); }
			  
			  if (dataRecebimentoInicio != null) { hql.
			  append(" dataRecebimento between :dataRecebimentoInicio and :dataRecebimentoFinal and "
			  ); }
			  
			  if (cliente != null) { hql.append(" idCliente =  :idCliente and ");
			  }
			  
			  if (centroCusto != null) {
			  hql.append(" idCentroCusto =  :idCentroCusto and "); }
			  
			  if (planoConta != null) { hql.append(" idPlanoConta =  :idPlanoConta and ");
			  }
			  
			  if (status == 0) { hql.append(" (status =  'A' OR status = 'B')"); } else { if
			  (status == 1) { hql.append(" status =  'A' "); } else {
			  hql.append(" status = 'B' "); } }

		if (tipoQuebra == 0) {
			hql.append(" ORDER BY clienteNome ");
		} else {
			if (tipoQuebra == 1) {
				hql.append(" ORDER BY centroCustoNome ");
			} else {
				if (tipoQuebra == 2) {
					hql.append(" ORDER BY planoContaNome ");
				} else {
					if (tipoQuebra == 3) {
						hql.append(" ORDER BY dataVenda ");
					} else {
						if (tipoQuebra == 4) {
							hql.append(" ORDER BY dataVencimento ");
						} else {
							hql.append(" ORDER BY dataRecebimento ");
						}
					}
				}
			}
		}
		
		if(tipoQuebra != tipoOrdenacao) {
			if (tipoOrdenacao == 0) {
				hql.append(" ,clienteNome ");
			} else {
				if (tipoOrdenacao == 1) {
					hql.append(" ,centroCustoNome ");
				} else {
					if (tipoOrdenacao == 2) {
						hql.append(" ,planoContaNome ");
					} else {
						if (tipoOrdenacao == 3) {
							hql.append(" ,dataVenda ");
						} else {
							if (tipoOrdenacao == 4) {
								hql.append(" ,dataVencimento ");
							} else {
								hql.append(" ,dataRecebimento ");
							}
						}
					}
				}
			}
		}

		Query<ContaReceberDataView> query = s.createQuery(hql.toString(), ContaReceberDataView.class);

		if (dataVendaInicio != null) {
			query.setParameter("dataVendaInicio", dataVendaInicio);
			query.setParameter("dataVendaFinal", dataVendaFim);
		}

		if (dataVencimentoInicio != null) {
			query.setParameter("dataVencimentoInicio", dataVencimentoInicio);
			query.setParameter("dataVencimentoFinal", dataVencimentoFim);
		}

		if (dataRecebimentoInicio != null) {
			query.setParameter("dataRecebimentoInicio", dataRecebimentoInicio);
			query.setParameter("dataRecebimentoFinal", dataRecebimentoFim);
		}

		if (cliente != null) {
			query.setParameter("idCliente", cliente.getIdCliente());
		}

		if (centroCusto != null) {
			query.setParameter("idCentroCusto", centroCusto.getIdCentroCusto());
		}

		if (planoConta != null) {
			query.setParameter("idPlanoConta", planoConta.getIdPlanoConta());
		}

		list = query.list();
		s.getTransaction().commit();
		
		GeradorCsv.tempoFinal(tempoInicial, "Pesquisar ContaReceberView");

		return list;
	}

}
